package net.highskiesmc.hsmisc.util;

import net.highskiesmc.hscore.configuration.Config;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DamageModifiers {
    private DamageModifiers() {

    }

    private enum GearType {
        NONE,
        CHAIN,
        IRON,
        DIAMOND
    }

    public static double getGearModifier(Config config, Entity attacker, Entity victim) {
        double modifier = 0;

        if (attacker instanceof LivingEntity a) {
            if (victim instanceof LivingEntity v) {
                EntityEquipment aEquip = a.getEquipment();
                EntityEquipment vEquip = v.getEquipment();

                if (aEquip != null && vEquip != null) {
                    ItemStack aHeld = aEquip.getItemInMainHand();
                    GearType weapon = isArmorOrSword(aHeld) ? getGearType(aHeld) : GearType.NONE;

                    List<GearType> armor =
                            Arrays.stream(vEquip.getArmorContents()).filter(DamageModifiers::isArmorOrSword).map(DamageModifiers::getGearType).toList();

                    for (GearType x : armor) {
                        modifier += getModifier(config, weapon, x);
                    }
                }
            }
        }

        return modifier;
    }

    private static GearType getGearType(ItemStack item) {
        String type = item.getType().toString().toLowerCase();

        if (type.matches("^((stone)|(chainmail)).+$")) {
            return GearType.CHAIN;
        }

        if (type.startsWith("iron")) {
            return GearType.IRON;
        }

        if (type.startsWith("diamond")) {
            return GearType.DIAMOND;
        }

        return GearType.NONE;
    }

    private static boolean isArmorOrSword(ItemStack item) {
        if (item == null) {
            return false;
        }

        String type = item.getType().toString().toLowerCase();
        return type.matches("^.+((helmet)|(chestplate)|(leggings)|(boots)|(sword)|((?<!pick)axe))$");
    }

    private static double getModifier(Config config, GearType weapon, GearType armor) {
        return config.get("damage-modifiers." + weapon.name().toLowerCase() + "." + armor.name().toLowerCase(),
                Double.class, 0D);
    }

    public static boolean isUsedInWorld(Config config, @NonNull World world) {
        List<String> worlds = config.get("damage-modifiers.worlds", ArrayList.class, new ArrayList<>());

        return worlds.contains(world.getName().toLowerCase());
    }
}

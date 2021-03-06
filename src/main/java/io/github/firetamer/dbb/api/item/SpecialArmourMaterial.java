package io.github.firetamer.dbb.api.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public abstract class SpecialArmourMaterial implements IArmorMaterial {

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slotType) {
        return 0;
    }

    @Override
    public int getEnchantmentValue() { return 0; }

    @Override
    public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; }

    @Override
    public Ingredient getRepairIngredient() { return Ingredient.EMPTY; }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slotType) {
        return 0;
    }

    @Override
    public float getToughness() { return 0; }

    @Override
    public float getKnockbackResistance() { return 0; }

}

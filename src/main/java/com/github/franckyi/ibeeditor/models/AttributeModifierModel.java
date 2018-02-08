package com.github.franckyi.ibeeditor.models;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;

import javax.annotation.Nullable;

public class AttributeModifierModel {

    private String name;
    private double amount;
    private int operation;
    private EntityEquipmentSlot slot;

    public AttributeModifierModel(AttributeModifier modifier, EntityEquipmentSlot slot) {
        this(modifier.getName(), modifier.getAmount(), modifier.getOperation(), slot);
    }

    public AttributeModifierModel(String name, double amount, int operation, EntityEquipmentSlot slot) {
        this.name = name;
        this.amount = amount;
        this.operation = operation;
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public EntityEquipmentSlot getSlot() {
        return slot;
    }

    public void setSlot(EntityEquipmentSlot slot) {
        this.slot = slot;
    }

    @Nullable
    public AttributeModifier toAttributeModifier() {
        return name.isEmpty() ? null : new AttributeModifier(name, amount, operation);
    }
}
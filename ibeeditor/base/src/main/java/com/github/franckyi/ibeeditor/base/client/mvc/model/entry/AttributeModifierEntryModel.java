package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

import java.util.Locale;
import java.util.UUID;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class AttributeModifierEntryModel extends EntryModel {
    private final StringProperty attributeNameProperty;
    private final ObjectProperty<Slot> slotProperty;
    private final ObjectProperty<Operation> operationProperty;
    private final ObjectProperty<Double> amountProperty;
    private final UUID uuid;
    private final AttributeModifierAction action;
    private final String defaultAttributeName;
    private final Slot defaultSlot;
    private final Operation defaultOperation;
    private final double defaultAmount;

    public AttributeModifierEntryModel(CategoryModel category, AttributeModifierAction action) {
        this(category, "", Slot.MAINHAND, Operation.ADD, 0, UUID.randomUUID(), action);
    }

    public AttributeModifierEntryModel(CategoryModel category, String attributeName, String slot, int operation, double amount, UUID uuid, AttributeModifierAction action) {
        this(category, attributeName, Slot.from(slot), Operation.from(operation), amount, uuid, action);
    }

    public AttributeModifierEntryModel(CategoryModel category, String attributeName, Slot slot, Operation operation, double amount, UUID uuid, AttributeModifierAction action) {
        super(category);
        attributeNameProperty = DataBindings.getPropertyFactory().createStringProperty(attributeName);
        slotProperty = DataBindings.getPropertyFactory().createObjectProperty(slot);
        operationProperty = DataBindings.getPropertyFactory().createObjectProperty(operation);
        amountProperty = DataBindings.getPropertyFactory().createObjectProperty(amount);
        this.uuid = uuid;
        this.action = action;
        defaultAttributeName = attributeName;
        defaultSlot = slot;
        defaultOperation = operation;
        defaultAmount = amount;
    }

    public String getAttributeName() {
        return attributeNameProperty().getValue();
    }

    public StringProperty attributeNameProperty() {
        return attributeNameProperty;
    }

    public void setAttributeName(String value) {
        attributeNameProperty().setValue(value);
    }

    public Slot getSlot() {
        return slotProperty().getValue();
    }

    public ObjectProperty<Slot> slotProperty() {
        return slotProperty;
    }

    public void setSlot(Slot value) {
        slotProperty().setValue(value);
    }

    public Operation getOperation() {
        return operationProperty().getValue();
    }

    public ObjectProperty<Operation> operationProperty() {
        return operationProperty;
    }

    public void setOperation(Operation value) {
        operationProperty().setValue(value);
    }

    public double getAmount() {
        return amountProperty().getValue();
    }

    public ObjectProperty<Double> amountProperty() {
        return amountProperty;
    }

    public void setAmount(Double value) {
        amountProperty().setValue(value);
    }

    @Override
    public void apply() {
        action.apply(attributeNameProperty.getValue(), slotProperty.getValue().getValue(), operationProperty.getValue().getValue(), amountProperty.getValue(), uuid);
    }

    @Override
    public void reset() {
        amountProperty().unbind();
        setAttributeName(defaultAttributeName);
        setSlot(defaultSlot);
        setOperation(defaultOperation);
        setAmount(defaultAmount);
    }

    @Override
    public Type getType() {
        return Type.ATTRIBUTE_MODIFIER;
    }

    @FunctionalInterface
    public interface AttributeModifierAction {
        void apply(String attributeName, String slot, int operation, double amount, UUID uuid);
    }

    public enum Slot {
        MAINHAND, OFFHAND, FEET, LEGS, CHEST, HEAD;

        private final Text text;

        Slot() {
            text = translated("ibeeditor.gui." + getValue());
        }

        public static Slot from(String value) {
            for (Slot slot : values()) {
                if (slot.getValue().equals(value)) {
                    return slot;
                }
            }
            return null;
        }

        public String getValue() {
            return name().toLowerCase(Locale.ROOT);
        }

        public Text getText() {
            return text;
        }
    }

    public enum Operation {
        ADD, MULTIPLY_BASE, MULTIPLY;

        private final Text text, tooltip;

        Operation() {
            text = text("OP: " + getValue());
            tooltip = translated("ibeeditor.gui.operation").with(translated("ibeeditor.gui.operation_" + getValue()));
        }

        public static Operation from(int value) {
            for (Operation operation : values()) {
                if (operation.getValue() == value) {
                    return operation;
                }
            }
            return null;
        }

        public int getValue() {
            return ordinal();
        }

        public Text getText() {
            return text;
        }

        public Text getTooltip() {
            return tooltip;
        }
    }
}

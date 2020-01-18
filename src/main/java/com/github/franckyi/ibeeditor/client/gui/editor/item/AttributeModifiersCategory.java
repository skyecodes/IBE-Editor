package com.github.franckyi.ibeeditor.client.gui.editor.item;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.DoubleField;
import com.github.franckyi.guapi.node.EnumButton;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.IEditableCategoryProperty;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class AttributeModifiersCategory extends EditableCategory<AttributeModifiersCategory.AttributeModifierModel> {

    private final List<AttributeModifierModel> initialModifiers;
    private final ItemStack itemStack;
    private final List<AttributeModifierModel> modifiers;

    public AttributeModifiersCategory(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.modifiers = this.getModifiers(itemStack::getAttributeModifiers);
        this.initialModifiers = this.getModifiers(slot -> itemStack.getItem().getAttributeModifiers(slot, itemStack));
        this.getChildren().add(new AddButton("Add attribute modifier"));
        modifiers.forEach(this::addProperty);
    }

    private List<AttributeModifierModel> getModifiers(Function<EquipmentSlotType, Multimap<String, AttributeModifier>> getAttributeModifiers) {
        List<AttributeModifierModel> res = new ArrayList<>();
        Stream.of(EquipmentSlotType.values())
                .forEach(slot -> getAttributeModifiers
                        .apply(slot)
                        .asMap()
                        .forEach((name, modifiers) -> modifiers
                                .stream()
                                .map(modifier -> new AttributeModifierModel(name, modifier, slot))
                                .forEach(res::add)
                        )
                );
        return res;
    }

    @Override
    public void apply() {
        itemStack.getOrCreateTag().remove("AttributeModifiers");
        modifiers.clear();
        super.apply();
        if (!modifiers.equals(initialModifiers)) {
            modifiers.forEach(modifier -> itemStack.addAttributeModifier(modifier.getAttributeName(), modifier.getModifier(), modifier.getSlot()));
        }
    }

    @Override
    protected AbstractProperty<AttributeModifierModel> createNewProperty(AttributeModifierModel initialValue, int index) {
        return new PropertyAttributeModifier(index, initialValue, modifiers::add);
    }

    @Override
    protected AttributeModifierModel getDefaultPropertyValue() {
        return new AttributeModifierModel("", new AttributeModifier("", 0, AttributeModifier.Operation.ADDITION), EquipmentSlotType.MAINHAND);
    }

    protected static class AttributeModifierModel {

        private final String attributeName;
        private final AttributeModifier modifier;
        private final EquipmentSlotType slot;

        public AttributeModifierModel(String attributeName, AttributeModifier modifier, EquipmentSlotType slot) {
            this.attributeName = attributeName;
            this.modifier = modifier;
            this.slot = slot;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public AttributeModifier getModifier() {
            return modifier;
        }

        public EquipmentSlotType getSlot() {
            return slot;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj instanceof AttributeModifierModel) {
                AttributeModifierModel model = (AttributeModifierModel) obj;
                return slot.equals(model.slot) && modifier.getName().equals(model.modifier.getName())
                        && modifier.getOperation() == model.modifier.getOperation()
                        && modifier.getAmount() == model.modifier.getAmount() && attributeName.equals(model.attributeName);
            }
            return false;
        }

    }

    public class PropertyAttributeModifier extends AbstractProperty<AttributeModifierModel> implements IEditableCategoryProperty {

        private PropertyControls controls;

        private TextField nameField;
        private EnumButton<EquipmentSlotType> slotButton;
        private EnumButton<AttributeModifier.Operation> operationField;
        private DoubleField amountField;

        public PropertyAttributeModifier(int index, AttributeModifierModel initialValue, Consumer<AttributeModifierModel> action) {
            super(initialValue, action);
            controls = new PropertyControls(AttributeModifiersCategory.this, index);
            IEditableCategoryProperty.super.build();
        }

        @Override
        public AttributeModifierModel getValue() {
            return new AttributeModifierModel(nameField.getValue(), new AttributeModifier(
                    initialValue.getModifier().getID(), initialValue.getModifier().getName(),
                    amountField.getValue(), operationField.getValue()), slotButton.getValue());
        }

        @Override
        protected void setValue(AttributeModifierModel value) {
            nameField.setValue(value.getAttributeName());
            slotButton.setValue(value.getSlot());
            operationField.setValue(value.getModifier().getOperation());
            amountField.setValue(value.getModifier().getAmount());
        }

        @Override
        public void build() {
            this.getNode().setAlignment(Pos.LEFT);
            this.addAll(
                    nameField = new TextField(initialValue.getAttributeName()),
                    slotButton = new EnumButton<>(EquipmentSlotType.values()),
                    operationField = new EnumButton<>(AttributeModifier.Operation.values()),
                    amountField = new DoubleField(initialValue.getModifier().getAmount())
            );
            operationField.setValue(initialValue.getModifier().getOperation());
            nameField.setMargin(Insets.left(5));
            slotButton.setValue(initialValue.getSlot());
            slotButton.setRenderer(aSlot -> StringUtils.capitalize(aSlot.getName().toLowerCase()));
            slotButton.setMargin(Insets.horizontal(2));
            operationField.setPrefWidth(15);
            amountField.setMargin(Insets.horizontal(2));
            nameField.getTooltipText().add("Attribute name");
            //operationField.getTooltipText().add("Operation");
            amountField.getTooltipText().add("Amount");
        }

        @Override
        public void updateSize(int listWidth) {
            amountField.setPrefWidth(listWidth - 268);
        }

        @Override
        public PropertyControls getControls() {
            return controls;
        }
    }
}

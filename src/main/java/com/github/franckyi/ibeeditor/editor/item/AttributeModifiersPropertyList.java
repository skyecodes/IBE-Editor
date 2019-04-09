package com.github.franckyi.ibeeditor.editor.item;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.DoubleField;
import com.github.franckyi.guapi.node.EnumButton;
import com.github.franckyi.guapi.node.IntegerField;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.ibeeditor.editor.AbstractProperty;
import com.github.franckyi.ibeeditor.editor.EditablePropertyList;
import com.github.franckyi.ibeeditor.editor.EditablePropertyListChild;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class AttributeModifiersPropertyList extends EditablePropertyList<AttributeModifiersPropertyList.AttributeModifierModel> {

    private final List<AttributeModifierModel> initialModifiers;
    private ItemStack itemStack;
    private List<AttributeModifierModel> modifiers;

    public AttributeModifiersPropertyList(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.modifiers = this.getModifiers(itemStack::getAttributeModifiers);
        this.initialModifiers = this.getModifiers(slot -> itemStack.getItem().getAttributeModifiers(slot, itemStack));
        this.getChildren().add(new AddButton("Add attribute modifier"));
        modifiers.forEach(this::addProperty);
    }

    private List<AttributeModifierModel> getModifiers(Function<EntityEquipmentSlot, Multimap<String, AttributeModifier>> getAttributeModifiers) {
        List<AttributeModifierModel> res = new ArrayList<>();
        Stream.of(EntityEquipmentSlot.values())
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
        System.out.println(itemStack.getOrCreateTag());
        itemStack.getOrCreateTag().remove("AttributeModifiers");
        modifiers.clear();
        super.apply();
        if (!modifiers.equals(initialModifiers)) {
            modifiers.forEach(modifier -> itemStack.addAttributeModifier(modifier.getAttributeName(), modifier.getModifier(), modifier.getSlot()));
        }
        System.out.println(itemStack.getOrCreateTag());
    }

    @Override
    protected AbstractProperty<AttributeModifierModel> createNewProperty(AttributeModifierModel initialValue, int index) {
        return new AttributeModifierProperty(index, initialValue, modifiers::add);
    }

    @Override
    protected AttributeModifierModel getDefaultPropertyValue() {
        return new AttributeModifierModel("", new AttributeModifier("", 0, 0), EntityEquipmentSlot.MAINHAND);
    }

    public class AttributeModifierProperty extends AbstractProperty<AttributeModifierModel> implements EditablePropertyListChild {

        private ListControls controls;

        private TextField nameField;
        private EnumButton<EntityEquipmentSlot> slotButton;
        private IntegerField operationField;
        private DoubleField amountField;

        public AttributeModifierProperty(int index, AttributeModifierModel initialValue, Consumer<AttributeModifierModel> action) {
            super(initialValue, action);
            controls = new ListControls(AttributeModifiersPropertyList.this, index);
            EditablePropertyListChild.super.build();
        }

        @Override
        public AttributeModifierModel getValue() {
            return new AttributeModifierModel(nameField.getText(), new AttributeModifier(
                    initialValue.getModifier().getID(), initialValue.getModifier().getName(),
                    amountField.getValue(), operationField.getValue()), slotButton.getValue());
        }

        @Override
        protected void setValue(AttributeModifierModel value) {
            nameField.setText(value.getAttributeName());
            slotButton.setValue(value.getSlot());
            operationField.setValue(value.getModifier().getOperation());
            amountField.setValue(value.getModifier().getAmount());
        }

        @Override
        public void build() {
            this.getNode().setAlignment(Pos.LEFT);
            this.getNode().getChildren().add(nameField = new TextField(initialValue.getAttributeName()));
            nameField.setMargin(Insets.left(5));
            this.getNode().getChildren().add(slotButton = new EnumButton<>(EntityEquipmentSlot.values()));
            slotButton.setValue(initialValue.getSlot());
            slotButton.setRenderer(aSlot -> StringUtils.capitalize(aSlot.getName()));
            slotButton.setMargin(Insets.horizontal(4));
            this.getNode().getChildren().add(operationField = new IntegerField(initialValue.getModifier().getOperation(), 0, 3));
            operationField.setPrefWidth(15);
            this.getNode().getChildren().add(amountField = new DoubleField(initialValue.getModifier().getAmount()));
            amountField.setMargin(Insets.horizontal(4));
        }

        @Override
        protected void updateSize(int listWidth) {
            amountField.setPrefWidth(listWidth - 270);
        }

        @Override
        public ListControls getControls() {
            return controls;
        }
    }

    protected class AttributeModifierModel {

        private final String attributeName;
        private final AttributeModifier modifier;
        private final EntityEquipmentSlot slot;

        public AttributeModifierModel(String attributeName, AttributeModifier modifier, EntityEquipmentSlot slot) {
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

        public EntityEquipmentSlot getSlot() {
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
}

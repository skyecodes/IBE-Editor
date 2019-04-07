package com.github.franckyi.ibeeditor.editor.item.property;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.IntField;
import com.github.franckyi.ibeeditor.editor.property.EmptyProperty;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.function.Consumer;

public class EnchantmentProperty extends EmptyProperty<Integer> {

    private final Enchantment enchantment;
    private final IntField intField;

    public EnchantmentProperty(ItemStack itemStack, Enchantment enchantment, Integer initialValue, Consumer<Integer> action) {
        super(enchantment.getDisplayName(0).getUnformattedComponentText(), initialValue, action);
        this.enchantment = enchantment;
        this.getNode().setAlignment(Pos.RIGHT);
        this.getNameLabel().setPrefWidth(Node.COMPUTED_SIZE);
        this.getNameLabel().setColor(enchantment.isCurse() ? TextFormatting.RED.getColor() : (enchantment.canApply(itemStack) ? TextFormatting.GREEN.getColor() : 0xffffff));
        this.getNode().getChildren().add(intField = new IntField(initialValue));
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    @Override
    public Integer getValue() {
        return intField.getValue();
    }
}

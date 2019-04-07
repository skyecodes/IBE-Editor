package com.github.franckyi.ibeeditor.editor.item.property;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.ibeeditor.editor.property.EmptyProperty;
import net.minecraft.enchantment.Enchantment;

import java.util.function.Consumer;

public class EnchantmentProperty extends EmptyProperty<Integer> {

    private final Enchantment enchantment;
    private final TextField textField;

    public EnchantmentProperty(Enchantment enchantment, Integer initialValue, Consumer<Integer> action) {
        super(enchantment.getDisplayName(0).getUnformattedComponentText(), initialValue, action);
        this.enchantment = enchantment;
        this.getNode().setAlignment(Pos.RIGHT);
        this.getNameLabel().setPrefWidth(Node.COMPUTED_SIZE);
        this.getNode().getChildren().add(textField = new TextField(initialValue.toString()));
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    @Override
    public Integer getValue() {
        try {
            return Integer.valueOf(textField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}

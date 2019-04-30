package com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom;

import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.IntegerField;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.LabeledProperty;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ColorProperty extends LabeledProperty<Color> {

    protected IntegerField red;
    protected IntegerField green;
    protected IntegerField blue;
    protected HBox testGroup;

    public ColorProperty(String name, Color initialValue, Consumer<Color> action) {
        super(name, initialValue, action, 70);
    }

    @Override
    protected Color getValue() {
        return new Color(red.getValue(), green.getValue(), blue.getValue());
    }

    @Override
    protected void setValue(Color value) {
        red.setValue(value.getRed());
        green.setValue(value.getGreen());
        blue.setValue(value.getBlue());
    }

    @Override
    protected void build() {
        super.build();
        this.addAll(
                red = new IntegerField(0, 0, 255),
                green = new IntegerField(0, 0, 255),
                blue = new IntegerField(0, 0, 255),
                testGroup = new HBox()
        );
        red.setPrefWidth(26);
        green.setPrefWidth(26);
        blue.setPrefWidth(26);
        testGroup.setAlignment(Pos.CENTER);
        red.getTooltipText().add(TextFormatting.RED + "Red");
        green.getTooltipText().add(TextFormatting.GREEN + "Green");
        blue.getTooltipText().add(TextFormatting.BLUE + "Blue");
        BiConsumer<Integer, Integer> e = (a, b) -> this.setValue(this.getValue());
        red.getOnValueChangedListeners().add(e);
        green.getOnValueChangedListeners().add(e);
        blue.getOnValueChangedListeners().add(e);
    }

    @Override
    public void updateSize(int listWidth) {
        testGroup.setPrefWidth(listWidth - 219);
    }
}

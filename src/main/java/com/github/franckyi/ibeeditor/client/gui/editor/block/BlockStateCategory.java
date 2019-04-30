package com.github.franckyi.ibeeditor.client.gui.editor.block;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyBoolean;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyEnum;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyInteger;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.IntegerProperty;
import org.apache.commons.lang3.StringUtils;

public class BlockStateCategory extends AbstractCategory {

    private final BlockEditor editor;

    @SuppressWarnings("unchecked")
    public BlockStateCategory(BlockEditor editor) {
        this.editor = editor;
        for (IProperty p : editor.getBlockState().getProperties()) {
            String name = StringUtils.capitalize(p.getName().toLowerCase());
            if (p instanceof BooleanProperty) {
                BooleanProperty p1 = (BooleanProperty) p;
                this.getChildren().add(new PropertyBoolean(name, editor.getBlockState().get(p1), b -> this.withProperty(p1, b)));
            } else if (p instanceof IntegerProperty) {
                IntegerProperty p1 = (IntegerProperty) p;
                this.getChildren().add(new PropertyInteger(name, editor.getBlockState().get(p1), i -> this.withProperty(p1, i),
                        p1.getAllowedValues().stream().min(Integer::compareTo).orElse(Integer.MIN_VALUE),
                        p1.getAllowedValues().stream().max(Integer::compareTo).orElse(Integer.MAX_VALUE)));
            } else {
                this.getChildren().add(new PropertyEnum<>(name, editor.getBlockState().get(p), p.getAllowedValues(), t -> this.withProperty(p, t)));
            }
        }
    }

    private <T extends Comparable<T>> void withProperty(IProperty<T> p, T t) {
        editor.setBlockState(editor.getBlockState().with(p, t));
    }
}

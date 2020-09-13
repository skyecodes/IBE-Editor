package com.github.franckyi.ibeeditor.client.gui.editor.block;

import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyBoolean;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyEnum;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.PropertyInteger;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import org.apache.commons.lang3.StringUtils;

public class BlockStateCategory extends AbstractCategory {
    private final IBlockStateContainer container;
    private final Runnable applyAction;

    public BlockStateCategory(IBlockStateContainer container) {
        this(container, null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public BlockStateCategory(IBlockStateContainer container, Runnable applyAction) {
        this.container = container;
        this.applyAction = applyAction;
        for (Property p : container.getBlockState().getProperties()) {
            String name = StringUtils.capitalize(p.getName().toLowerCase());
            if (p instanceof BooleanProperty) {
                BooleanProperty p1 = (BooleanProperty) p;
                this.getChildren().add(new PropertyBoolean(name, container.getBlockState().get(p1), b -> this.withProperty(p1, b)));
            } else if (p instanceof IntegerProperty) {
                IntegerProperty p1 = (IntegerProperty) p;
                this.getChildren().add(new PropertyInteger(name, container.getBlockState().get(p1), i -> this.withProperty(p1, i),
                        p1.getAllowedValues().stream().min(Integer::compareTo).orElse(Integer.MIN_VALUE),
                        p1.getAllowedValues().stream().max(Integer::compareTo).orElse(Integer.MAX_VALUE)));
            } else {
                this.getChildren().add(new PropertyEnum<>(name, container.getBlockState().get(p), p.getAllowedValues(), t -> this.withProperty(p, t)));
            }
        }
    }

    @Override
    public void apply() {
        super.apply();
        if (applyAction != null) {
            applyAction.run();
        }
    }

    private <T extends Comparable<T>> void withProperty(Property<T> p, T t) {
        container.setBlockState(container.getBlockState().with(p, t));
    }

    public interface IBlockStateContainer {
        BlockState getBlockState();

        void setBlockState(BlockState blockState);
    }
}

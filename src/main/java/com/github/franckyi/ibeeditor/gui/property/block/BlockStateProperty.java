package com.github.franckyi.ibeeditor.gui.property.block;

import com.github.franckyi.ibeeditor.gui.property.base.EnumProperty;
import net.minecraft.block.properties.IProperty;

public class BlockStateProperty<E extends Comparable<E>> extends EnumProperty<E> {

    public BlockStateProperty(IProperty<E> property, E comparable, IBlockStatePropertyEnumConverter<E> converter) {
        super(property.getName(), property.getAllowedValues(), () -> comparable, e -> converter.with(property, e));
    }

    @FunctionalInterface
    public interface IBlockStatePropertyEnumConverter<E extends Comparable<E>> {
        void with(IProperty<E> property, E e);
    }

}

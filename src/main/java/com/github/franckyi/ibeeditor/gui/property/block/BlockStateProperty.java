package com.github.franckyi.ibeeditor.gui.property.block;

import com.github.franckyi.ibeeditor.gui.property.EnumProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

public class BlockStateProperty<E extends Enum<E> & IStringSerializable> extends EnumProperty<E> {

    public BlockStateProperty(PropertyEnum<E> property, IBlockState blockState, E comparable, IBlockStatePropertyEnumConverter<E> converter) {
        super(property.getName(), property.getAllowedValues(), () -> comparable, e -> converter.with(blockState, property, e));
    }

    @FunctionalInterface
    public interface IBlockStatePropertyEnumConverter<E extends Enum<E> & IStringSerializable> {
        void with(IBlockState blockState, PropertyEnum<E> property, E e);
    }

}

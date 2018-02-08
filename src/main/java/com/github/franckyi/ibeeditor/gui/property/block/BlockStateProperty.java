package com.github.franckyi.ibeeditor.gui.property.block;

import com.github.franckyi.ibeeditor.gui.property.EnumProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

import java.util.function.Consumer;

public class BlockStateProperty<E extends Enum<E> & IStringSerializable> extends EnumProperty<E> {

    public BlockStateProperty(PropertyEnum<E> property, IBlockState blockState, E comparable, Consumer<E> apply) {
        super(property.getName(), property.getAllowedValues(), () -> comparable, apply);
    }
}

package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.IBEEditor;
import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import com.github.franckyi.ibeeditor.gui.property.BooleanProperty;
import com.github.franckyi.ibeeditor.gui.property.IntegerProperty;
import com.github.franckyi.ibeeditor.gui.property.PropertyCategory;
import com.github.franckyi.ibeeditor.gui.property.block.BlockStateProperty;
import com.github.franckyi.ibeeditor.network.UpdateBlockMessage;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

import static com.github.franckyi.ibeeditor.IBEEditor.logger;

public class GuiBlockEditor extends GuiEditor {

    // Block
    private BlockPos blockPos;
    private IBlockState blockState;

    public GuiBlockEditor(BlockPos blockPos) {
        this(null, blockPos);
    }

    public GuiBlockEditor(GuiScreen parentScreen, BlockPos blockPos) {
        super(parentScreen);
        this.blockPos = blockPos;
        this.blockState = Minecraft.getMinecraft().world.getBlockState(blockPos).getActualState(Minecraft.getMinecraft().world, blockPos);
        if (!blockState.getProperties().isEmpty()) {
            List<BaseProperty<?>> blockStates = new ArrayList<>(blockState.getProperties().size());
            blockState.getProperties().forEach((property, comparable) -> {
                if (property instanceof PropertyBool) {
                    PropertyBool bProperty = (PropertyBool) property;
                    blockStates.add(new BooleanProperty(bProperty.getName(), () -> (Boolean) comparable, b -> with(blockState, bProperty, b)));
                } else if (property instanceof PropertyInteger) {
                    PropertyInteger iProperty = (PropertyInteger) property;
                    blockStates.add(new IntegerProperty(iProperty.getName(), () -> (Integer) comparable, i -> with(blockState, iProperty, i)));
                } else if (property instanceof PropertyEnum) {
                    PropertyEnum eProperty = (PropertyEnum) property;
                    blockStates.add(new BlockStateProperty(eProperty, blockState, (Enum) comparable, this::with));
                }
            });
            categories.add(new PropertyCategory<>("Block States").addAll(blockStates));
        }
    }

    @Override
    protected void apply() {
        logger.info("Preparing to apply...");
        super.apply();
        IBEEditor.netwrapper.sendToServer(new UpdateBlockMessage(blockState, blockPos));
        logger.info("Done !");
    }

    public <T extends Comparable<T>> void with(IBlockState blockState, IProperty<T> property, T t) {
        if (property.getAllowedValues().contains(t)) {
            this.blockState = blockState.withProperty(property, t);
        }
    }

}

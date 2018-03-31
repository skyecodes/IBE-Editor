package com.github.franckyi.ibeeditor.gui;

import com.github.franckyi.ibeeditor.IBEEditor;
import com.github.franckyi.ibeeditor.gui.property.BaseProperty;
import com.github.franckyi.ibeeditor.gui.property.PropertyCategory;
import com.github.franckyi.ibeeditor.gui.property.block.BlockStateProperty;
import com.github.franckyi.ibeeditor.network.UpdateBlockMessage;
import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.math.BlockPos;

import java.util.*;

import static com.github.franckyi.ibeeditor.IBEEditor.logger;

public class GuiBlockEditor extends GuiEditor {

    private BlockPos blockPos;
    private IBlockState blockState;

    public GuiBlockEditor(BlockPos blockPos) {
        this(null, blockPos);
    }

    public GuiBlockEditor(GuiScreen parentScreen, BlockPos blockPos) {
        super(parentScreen);
        this.blockPos = blockPos;
        this.blockState = Minecraft.getMinecraft().world.getBlockState(blockPos).getActualState(Minecraft.getMinecraft().world, blockPos);
        Map<IProperty<?>, Comparable<?>> blockStateProperties = new HashMap<>();
        blockState.getPropertyKeys().stream().filter(iProperty -> verifyBlockStateProperty(iProperty.getName())).forEach(iProperty -> blockStateProperties.put(iProperty, blockState.getProperties().get(iProperty)));
        if (!blockStateProperties.isEmpty()) {
            List<BaseProperty<?>> blockStates = new ArrayList<>(blockStateProperties.size());
            blockStateProperties.forEach((property, comparable) -> blockStates.add(new BlockStateProperty(property, comparable, this::with)));
            categories.add(new PropertyCategory<>("Block States").addAll(blockStates));
        }
    }

    private boolean verifyBlockStateProperty(String name) {
        //
        // Disabling properties that depends on surrounding blocks and are not saved on disk. Will be removed in 1.13, because all properties will be saved on disk.
        //
        if (blockState.getBlock() instanceof BlockFence || blockState.getBlock() instanceof BlockPane) {
            return !Arrays.asList("south", "north", "east", "west").contains(name);
        }
        if (blockState.getBlock() instanceof BlockStairs) {
            return !"shape".equals(name);
        }
        if (blockState.getBlock() instanceof BlockRedstoneRepeater) {
            return !"locked".equals(name);
        }
        if (blockState.getBlock() instanceof BlockRedstoneWire) {
            return "power".equals(name);
        }
        if (blockState.getBlock() instanceof BlockGrass || blockState.getBlock() instanceof BlockDirt) {
            return !"snowy".equals(name);
        }
        if (blockState.getBlock() instanceof BlockWall) {
            return !Arrays.asList("south", "north", "east", "west", "up").contains(name);
        }
        return true;
    }

    @Override
    protected void apply() {
        logger.info("Preparing to apply...");
        super.apply();
        IBEEditor.netwrapper.sendToServer(new UpdateBlockMessage(blockState, blockPos));
        logger.info("Done !");
    }

    public <T extends Comparable<T>> void with(IProperty<T> property, T t) {
        if (property.getAllowedValues().contains(t)) {
            setBlockState(blockState.withProperty(property, t));
        }
    }

    private void setBlockState(IBlockState blockState) {
        this.blockState = blockState;
    }

}

package com.github.franckyi.ibeeditor.client.block;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.client.AbstractEditor;
import com.github.franckyi.ibeeditor.client.Category;
import com.github.franckyi.ibeeditor.client.block.tileentity.BannerCategory;
import com.github.franckyi.ibeeditor.client.block.tileentity.CommandBlockCategory;
import com.github.franckyi.ibeeditor.client.block.tileentity.LockableCategory;
import com.github.franckyi.ibeeditor.client.block.tileentity.LootTableCategory;
import com.github.franckyi.ibeeditor.client.block.tileentity.capability.EnergyCategory;
import com.github.franckyi.ibeeditor.client.block.tileentity.capability.FluidHandlerCategory;
import com.github.franckyi.ibeeditor.client.block.tileentity.capability.ItemHandlerCategory;
import com.github.franckyi.ibeeditor.network.block.BlockEditorMessage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class BlockEditor extends AbstractEditor {

    private final BlockPos blockPos;
    private TileEntity tileEntity;
    private IBlockState blockState;

    public BlockEditor(BlockPos blockPos, TileEntity tileEntity) {
        super("Block Editor");
        this.blockPos = blockPos;
        this.tileEntity = mc.world.getTileEntity(blockPos);
        this.tileEntity = tileEntity;
        this.blockState = mc.world.getBlockState(blockPos);
        if (!blockState.getProperties().isEmpty()) {
            this.addCategory("Block state", new BlockStateCategory(this));
        }
        if (tileEntity != null) {
            BlockEditorConfiguration.get().forEach(configuration -> {
                if (configuration.condition.test(this)) {
                    this.addCategory(configuration.name, configuration.categoryBuilder.apply(this, configuration.caster.apply(this)));
                }
            });
        }
        this.addCategory("Tools", new ToolsBlockCategory(this));
        this.show();
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public IBlockState getBlockState() {
        return blockState;
    }

    public void setBlockState(IBlockState blockState) {
        this.blockState = blockState;
    }

    @Override
    protected void apply() {
        super.apply();
        IBEEditorMod.CHANNEL.sendToServer(new BlockEditorMessage(blockPos, blockState, tileEntity));
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }

    private static class BlockEditorConfiguration<T> {

        private static List<BlockEditorConfiguration<Object>> config;

        private final Predicate<BlockEditor> condition;
        private final Function<BlockEditor, T> caster;
        private final String name;
        private final BiFunction<BlockEditor, T, Category> categoryBuilder;

        private BlockEditorConfiguration(Predicate<BlockEditor> condition, Function<BlockEditor, T> caster, String name, BiFunction<BlockEditor, T, Category> categoryBuilder) {
            this.condition = condition;
            this.caster = caster;
            this.name = name;
            this.categoryBuilder = categoryBuilder;
        }

        @SuppressWarnings("unchecked")
        private static List<BlockEditorConfiguration<Object>> get() {
            return config == null ? config = Arrays.asList(
                    createCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, "Inventory", ItemHandlerCategory::new),
                    createCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, "TODO", FluidHandlerCategory::new),
                    createCapability(CapabilityEnergy.ENERGY, "TODO", EnergyCategory::new),
                    createTE(TileEntityLockable.class, "Lock", LockableCategory::new),
                    createTE(TileEntityLockableLoot.class, "Name & Loot table", LootTableCategory::new),
                    createTE(TileEntityBanner.class, "Banner", BannerCategory::new),
                    createTE(TileEntityCommandBlock.class, "Command block", CommandBlockCategory::new)
            ) : config;
        }

        private static <T> BlockEditorConfiguration createTE(Class<T> teClass, String name, BiFunction<BlockEditor, T, Category> categoryBuilder) {
            return new BlockEditorConfiguration<>(editor -> teClass.isInstance(editor.tileEntity), editor -> teClass.cast(editor.tileEntity), name, categoryBuilder);
        }

        private static <T> BlockEditorConfiguration createCapability(Capability<T> capability, String name, BiFunction<BlockEditor, T, Category> categoryBuilder) {
            return new BlockEditorConfiguration<>(editor -> editor.tileEntity.getCapability(capability).isPresent(), editor -> editor.tileEntity.getCapability(capability).orElse(null), name, categoryBuilder);
        }

    }

}

package com.github.franckyi.ibeeditor.client.editor.block;

import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.client.editor.block.tileentity.*;
import com.github.franckyi.ibeeditor.client.editor.common.AbstractCategory;
import com.github.franckyi.ibeeditor.client.editor.common.CapabilityProviderEditor;
import com.github.franckyi.ibeeditor.client.util.IBENotification;
import com.github.franckyi.ibeeditor.network.block.BlockEditorMessage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class BlockEditor extends CapabilityProviderEditor {

    private final BlockPos blockPos;
    private TileEntity tileEntity;
    private IBlockState blockState;

    public BlockEditor(BlockPos blockPos, TileEntity tileEntity) {
        super("Block Editor :");
        this.blockPos = blockPos;
        this.tileEntity = tileEntity;
        this.blockState = mc.world.getBlockState(blockPos);
        header.getChildren().add(new TexturedButton(new ItemStack(blockState.getBlock())));
        if (!blockState.getProperties().isEmpty()) {
            this.addCategory("Block state", new BlockStateCategory(this));
        }
        if (tileEntity != null) {
            this.applyConfigurations(this.getCapabilityConfigurations(), tileEntity);
            this.applyConfigurations(BlockEditorConfiguration.get(), tileEntity);
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
        IBlockState baseState = blockState;
        NBTTagCompound teTag = null;
        if (tileEntity != null) {
            teTag = tileEntity.write(new NBTTagCompound());
        }
        super.apply();
        if (baseState.equals(blockState) && (teTag == null || teTag.equals(tileEntity.write(new NBTTagCompound())))) {
            IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.YELLOW + "Nothing to save.");
        } else {
            IBEEditorMod.CHANNEL.sendToServer(new BlockEditorMessage(blockPos, blockState, tileEntity));
            IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.GREEN + "Block saved.");
        }
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }

    protected static class BlockEditorConfiguration<T> extends AbstractEditorConfiguration<TileEntity, T> {

        private static final List<? extends BlockEditorConfiguration<Object>> config;

        static {
            config = build();
        }

        private BlockEditorConfiguration(Predicate<TileEntity> condition, Function<TileEntity, T> caster, String name, Function<T, AbstractCategory> categoryBuilder) {
            super(condition, caster, name, categoryBuilder);
        }

        @SuppressWarnings("unchecked")
        private static List<BlockEditorConfiguration<Object>> build() {
            return Arrays.asList(
                    create(TileEntityLockable.class, "Lock", LockableCategory::new),
                    create(TileEntityLockableLoot.class, "Name & Loot table", LootTableCategory::new),
                    create(TileEntityCommandBlock.class, "Command block", CommandBlockCategory::new),
                    create(TileEntityMobSpawner.class, "Mob Spawner", MobSpawnerCategory::new),
                    create(TileEntityMobSpawner.class, "Spawn Potentials", SpawnPotentialsCategory::new)
            );
        }

        private static List<? extends BlockEditorConfiguration<Object>> get() {
            return config;
        }

        private static <T> BlockEditorConfiguration create(Class<T> teClass, String name, Function<T, AbstractCategory> categoryBuilder) {
            return new BlockEditorConfiguration<>(teClass::isInstance, teClass::cast, name, categoryBuilder);
        }

    }

}

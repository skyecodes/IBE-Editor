package com.github.franckyi.ibeeditor.client.gui.editor.block;

import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.ClientUtils;
import com.github.franckyi.ibeeditor.client.EditorHelper;
import com.github.franckyi.ibeeditor.client.IBENotification;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.CapabilityProviderEditor;
import com.github.franckyi.ibeeditor.client.gui.editor.block.tileentity.CommandBlockCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.block.tileentity.LockableCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.block.tileentity.MobSpawnerCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.block.tileentity.SpawnPotentialsCategory;
import com.github.franckyi.ibeeditor.common.network.IBENetworkHandler;
import com.github.franckyi.ibeeditor.common.network.editor.block.BlockEditorMessage;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.client.CChatMessagePacket;
import net.minecraft.tileentity.CommandBlockTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class BlockEditor extends CapabilityProviderEditor {

    private final BlockPos blockPos;
    private TileEntity tileEntity;
    private BlockState blockState;

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
            this.applyConfigurations(BlockEditorConfiguration.config, tileEntity);
        }
        this.addCategory("Tools", new ToolsBlockCategory(this));
        this.show();
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public void setBlockState(BlockState blockState) {
        this.blockState = blockState;
    }

    @Override
    protected void apply() {
        BlockState baseState = blockState;
        CompoundNBT teTag = null;
        if (tileEntity != null) {
            teTag = tileEntity.write(new CompoundNBT());
        }
        super.apply();
        if (baseState.equals(blockState) && (teTag == null || teTag.equals(tileEntity.write(new CompoundNBT())))) {
            IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.YELLOW + "Nothing to save.");
        } else {
            if (EditorHelper.isServerEnabled()) {
                IBENetworkHandler.getModChannel().sendToServer(new BlockEditorMessage(blockPos, blockState, tileEntity));
            } else {
                ClientUtils.sendCommand(ClientUtils.getSetblockCommand(blockPos, blockState, tileEntity));
            }
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
                    create(LockableTileEntity.class, "Name", LockableCategory::new),
                    create(CommandBlockTileEntity.class, "Command block", CommandBlockCategory::new),
                    create(MobSpawnerTileEntity.class, "Mob Spawner", MobSpawnerCategory::new),
                    create(MobSpawnerTileEntity.class, "Spawn Potentials", SpawnPotentialsCategory::new)
            );
        }

        private static <T> BlockEditorConfiguration create(Class<T> teClass, String name, Function<T, AbstractCategory> categoryBuilder) {
            return new BlockEditorConfiguration<>(teClass::isInstance, teClass::cast, name, categoryBuilder);
        }

    }

}

package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.common.EditorContext;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EditorContextSerializer implements PacketSerializer<EditorContext> {
    public static final EditorContextSerializer INSTANCE = new EditorContextSerializer();

    protected EditorContextSerializer() {
    }

    @Override
    public void write(EditorContext ctx, FriendlyByteBuf buf) {
        buf.writeEnum(ctx.getStatus());
        buf.writeEnum(ctx.getTrigger());
        buf.writeEnum(ctx.getLocation());
        buf.writeEnum(ctx.getCommandTarget());
        buf.writeEnum(ctx.getEditorType());
        if (ctx.getStatus().isAfter(EditorContext.Status.COMMAND)) {
            buf.writeEnum(ctx.getTarget());
            switch (ctx.getTarget()) {
                case ITEM -> {
                    if (ctx.getTrigger() == EditorContext.Trigger.KEYBIND_INVENTORY) {
                        buf.writeEnum(ctx.getItemInventory());
                        buf.writeVarInt(ctx.getSlotIndex());
                        switch (ctx.getItemInventory()) {
                            case PLAYER_INVENTORY -> buf.writeBoolean(ctx.isCreativeInventoryScreen());
                            case BLOCK_INVENTORY -> buf.writeBlockPos(ctx.getBlockPos());
                        }
                    }
                    if (ctx.getStatus().isAfter(EditorContext.Status.REQUEST)) {
                        buf.writeItem(ctx.getItemStack());
                    }
                }
                case BLOCK -> {
                    buf.writeBlockPos(ctx.getBlockPos());
                    if (ctx.getStatus().isAfter(EditorContext.Status.REQUEST)) {
                        buf.writeWithCodec(BlockState.CODEC, ctx.getBlockState());
                        buf.writeNbt(ctx.getTag());
                    }
                }
                case ENTITY -> {
                    buf.writeVarInt(ctx.getEntityId());
                    if (ctx.getStatus().isAfter(EditorContext.Status.REQUEST)) {
                        buf.writeNbt(ctx.getTag());
                    }
                }
            }
        }
        if (ctx.getStatus().isAfter(EditorContext.Status.REQUEST)) {
            buf.writeBoolean(ctx.isSaveable());
        }
    }

    @Override
    public EditorContext read(FriendlyByteBuf buf) {
        var ctx = new EditorContext();
        ctx.setStatus(buf.readEnum(EditorContext.Status.class));
        ctx.setTrigger(buf.readEnum(EditorContext.Trigger.class));
        ctx.setLocation(buf.readEnum(EditorContext.Location.class));
        ctx.setCommandTarget(buf.readEnum(EditorContext.CommandTarget.class));
        ctx.setEditorType(buf.readEnum(EditorContext.EditorType.class));
        if (ctx.getStatus().isAfter(EditorContext.Status.COMMAND)) {
            ctx.setTarget(buf.readEnum(EditorContext.Target.class));
            switch (ctx.getTarget()) {
                case ITEM -> {
                    ctx.setItemInventory(buf.readEnum(EditorContext.ItemInventory.class));
                    if (ctx.getItemInventory() != EditorContext.ItemInventory.MAIN_HAND) {
                        ctx.setSlotIndex(buf.readVarInt());
                        switch (ctx.getItemInventory()) {
                            case PLAYER_INVENTORY -> ctx.setCreativeInventoryScreen(buf.readBoolean());
                            case BLOCK_INVENTORY -> ctx.setBlockPos(buf.readBlockPos());
                        }
                    }
                    if (ctx.getStatus().isAfter(EditorContext.Status.REQUEST)) {
                        ctx.setItemStack(buf.readItem());
                    }
                }
                case BLOCK -> {
                    ctx.setBlockPos(buf.readBlockPos());
                    if (ctx.getStatus().isAfter(EditorContext.Status.REQUEST)) {
                        ctx.setBlockState(buf.readWithCodec(BlockState.CODEC));
                        var tag = buf.readNbt();
                        if (tag != null) {
                            ctx.setTag(tag);
                            ctx.setBlockEntity(BlockEntity.loadStatic(ctx.getBlockPos(), ctx.getBlockState(), tag));
                        }
                    }
                }
                case ENTITY -> {
                    ctx.setEntityId(buf.readVarInt());
                    if (ctx.getStatus().isAfter(EditorContext.Status.REQUEST)) {
                        ctx.setTag(buf.readNbt());
                        ctx.setEntity(EntityType.create(ctx.getTag(), null).orElseThrow());
                    }
                }
            }
        }
        if (ctx.getStatus().isAfter(EditorContext.Status.REQUEST)) {
            ctx.setSaveable(buf.readBoolean());
        }
        return ctx;
    }
}

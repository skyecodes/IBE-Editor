package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.common.EditorContext;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EditorContextSerializer implements PacketSerializer<EditorContext> {
    public static final EditorContextSerializer INSTANCE = new EditorContextSerializer();

    protected EditorContextSerializer() {
    }

    @Override
    public void write(EditorContext ctx, FriendlyByteBuf buf) {
        buf.writeEnum(ctx.getStatus());
        if (ctx.getStatus() == EditorContext.Status.UPDATE) {
            writeTarget(ctx, buf);
            return;
        }
        buf.writeEnum(ctx.getTrigger());
        buf.writeEnum(ctx.getLocation());
        buf.writeEnum(ctx.getCommandTarget());
        buf.writeEnum(ctx.getEditorType());
        if (ctx.getStatus().isAfter(EditorContext.Status.COMMAND)) {
            writeTarget(ctx, buf);
        }
        if (ctx.getStatus().isAfter(EditorContext.Status.REQUEST)) {
            buf.writeBoolean(ctx.isSaveable());
        }
    }

    private void writeTarget(EditorContext ctx, FriendlyByteBuf buf) {
        buf.writeEnum(ctx.getTarget());
        switch (ctx.getTarget()) {
            case ITEM -> {
                buf.writeEnum(ctx.getItemInventory());
                if (ctx.getTrigger() == EditorContext.Trigger.KEYBIND_INVENTORY) {
                    buf.writeVarInt(ctx.getSlotIndex());
                    switch (ctx.getItemInventory()) {
                        case PLAYER_INVENTORY -> buf.writeBoolean(ctx.isCreativeInventoryScreen());
                        case BLOCK_INVENTORY -> buf.writeBlockPos(ctx.getBlockPos());
                    }
                }
                if (ctx.getStatus().isAfter(EditorContext.Status.REQUEST)) {
                    buf.writeItem(ItemStack.of(ctx.getTag()));
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

    @Override
    public EditorContext read(FriendlyByteBuf buf) {
        var ctx = new EditorContext();
        ctx.setStatus(buf.readEnum(EditorContext.Status.class));
        if (ctx.getStatus() == EditorContext.Status.UPDATE) {
            readTarget(ctx, buf);
            return ctx;
        }
        ctx.setTrigger(buf.readEnum(EditorContext.Trigger.class));
        ctx.setLocation(buf.readEnum(EditorContext.Location.class));
        ctx.setCommandTarget(buf.readEnum(EditorContext.CommandTarget.class));
        ctx.setEditorType(buf.readEnum(EditorContext.EditorType.class));
        if (ctx.getStatus().isAfter(EditorContext.Status.COMMAND)) {
            readTarget(ctx, buf);
        }
        if (ctx.getStatus().isAfter(EditorContext.Status.REQUEST)) {
            ctx.setSaveable(buf.readBoolean());
        }
        return ctx;
    }

    private void readTarget(EditorContext ctx, FriendlyByteBuf buf) {
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
                }
            }
        }
    }
}

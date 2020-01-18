package com.github.franckyi.ibeeditor.client;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public final class ClientUtils {

    private static final Minecraft mc = Minecraft.getInstance();

    public static Entity createEntity(CompoundNBT entityTag) {
        EntityType<?> entityType = EntityType.byKey(entityTag.getString("id")).orElse(EntityType.PIG);
        return createEntity(entityType, entityTag);
    }

    public static <T extends Entity> T createEntity(EntityType<T> entityType, CompoundNBT entityTag) {
        T entity = entityType.create(Minecraft.getInstance().world);
        entity.read(entityTag);
        return entity;
    }

    public static CompoundNBT getCleanEntityTag(Entity entity) {
        CompoundNBT tag = new CompoundNBT();
        entity.writeUnlessRemoved(tag);
        tag.remove("UUIDMost");
        tag.remove("UUIDLeast");
        tag.remove("Pos");
        return tag;
    }

    private static void copyCommand(String command) {
        mc.keyboardListener.setClipboardString(command);
        IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.GREEN + "Command copied to clipboard.");
    }

    private static String getGiveCommand(ResourceLocation name, CompoundNBT tag, int count) {
        return String.format("/give @p %s%s %d", name, tag, count);
    }

    private static String getGiveCommand(ItemStack itemStack) {
        return getGiveCommand(itemStack.getItem().getRegistryName(), itemStack.getOrCreateTag(), itemStack.getCount());
    }

    private static String getGiveCommand(BlockState blockState, TileEntity tileEntity) {
        CompoundNBT tag = new CompoundNBT();
        return getGiveCommand(blockState.getBlock().getRegistryName(), tileEntity == null ? tag : tileEntity.write(tag), 1);
    }

    private static String getSetblockCommand(BlockState blockState, TileEntity tileEntity) {
        StringBuilder builder = new StringBuilder("[");
        blockState.getProperties().forEach(property -> builder
                .append(property.getName()).append("=")
                .append(blockState.get(property))
                .append(","));
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append("]");
        CompoundNBT tag = new CompoundNBT();
        return String.format("/setblock ~ ~ ~ %s%s%s",
                blockState.getBlock().getRegistryName(), builder, tileEntity == null ? tag : tileEntity.write(tag));
    }

    private static String getSummonCommand(Entity entity) {
        return String.format("/summon %s ~ ~ ~ %s",
                entity.getType().getRegistryName(), getCleanEntityTag(entity));
    }

    public static void copyGiveCommand(ItemStack itemStack) {
        copyCommand(getGiveCommand(itemStack));
    }

    public static void copyGiveCommandWithoutFormatting(ItemStack itemStack) {
        copyCommand(TextFormatting.getTextWithoutFormattingCodes(getGiveCommand(itemStack)));
    }

    public static void copyGiveCommand(BlockState blockState, TileEntity tileEntity) {
        copyCommand(getGiveCommand(blockState, tileEntity));
    }

    public static void copyGiveCommandWithoutFormatting(BlockState blockState, TileEntity tileEntity) {
        copyCommand(TextFormatting.getTextWithoutFormattingCodes(getGiveCommand(blockState, tileEntity)));
    }

    public static void copySetblockCommand(BlockState blockState, TileEntity tileEntity) {
        copyCommand(getSetblockCommand(blockState, tileEntity));
    }

    public static void copySetblockCommandWithoutFormatting(BlockState blockState, TileEntity tileEntity) {
        copyCommand(TextFormatting.getTextWithoutFormattingCodes(getSetblockCommand(blockState, tileEntity)));
    }

    public static void copySummonCommand(Entity entity) {
        copyCommand(getSummonCommand(entity));
    }

    public static void copySummonCommandWithoutFormatting(Entity entity) {
        copyCommand(TextFormatting.getTextWithoutFormattingCodes(getSummonCommand(entity)));
    }
}

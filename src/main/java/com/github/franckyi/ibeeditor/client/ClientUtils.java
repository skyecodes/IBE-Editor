package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public static String getGiveCommand(ResourceLocation name, CompoundNBT tag, int count) {
        return String.format("/give @p %s%s %d", name, tag, count);
    }

    public static String getGiveCommand(ItemStack itemStack) {
        return getGiveCommand(itemStack.getItem().getRegistryName(), itemStack.getOrCreateTag(), itemStack.getCount());
    }

    public static String getGiveCommand(BlockState blockState, TileEntity tileEntity) {
        CompoundNBT tag = new CompoundNBT();
        return getGiveCommand(blockState.getBlock().getRegistryName(), tileEntity == null ? tag : tileEntity.write(tag), 1);
    }

    public static String getSetblockCommand(BlockState blockState, TileEntity tileEntity) {
        return getSetblockCommand(null, blockState, tileEntity);
    }

    public static String getSetblockCommand(BlockPos blockPos, BlockState blockState, TileEntity tileEntity) {
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
        return String.format("/setblock %s %s%s%s", blockPos == null ? "~ ~ ~" : String.format("%d %d %d", blockPos.getX(), blockPos.getY(), blockPos.getZ()),
                blockState.getBlock().getRegistryName(), builder, tileEntity == null ? tag : tileEntity.write(tag));
    }

    public static String getSummonCommand(Entity entity) {
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

    public static String getReplaceItemCommandForPlayerMainHand(ItemStack itemStack) {
        return String.format("/replaceitem entity @p weapon.mainhand %s%s %d",
                itemStack.getItem().getRegistryName(), itemStack.getOrCreateTag(), itemStack.getCount());
    }

    public static String getReplaceItemCommandForPlayer(ItemStack itemStack, Slot slot) {
        return String.format("/replaceitem entity @p container.%d %s%s %d",
                slot.slotNumber, itemStack.getItem().getRegistryName(), itemStack.getOrCreateTag(), itemStack.getCount());
    }

    public static String getReplaceItemCommandForBlock(ItemStack itemStack, Slot slot, BlockPos pos) {
        return String.format("/replaceitem block %d %d %d container.%d %s%s %d", pos.getX(), pos.getY(), pos.getZ(),
                slot.slotNumber, itemStack.getItem().getRegistryName(), itemStack.getOrCreateTag(), itemStack.getCount());
    }

    public static String getReplaceItemCommandForEntity(ItemStack itemStack, Slot slot, Entity entity) {
        return String.format("/replaceitem entity %s container.%d %s%s %d", entity.getUniqueID(),
                slot.slotNumber, itemStack.getItem().getRegistryName(), itemStack.getOrCreateTag(), itemStack.getCount());
    }

    public static String getEntityData(Entity entity, INBT tag) {
        return String.format("/data merge entity %s %s", entity.getUniqueID(), tag);
    }

    public static void sendCommand(String command) {
        IBEEditorMod.LOGGER.debug(MarkerManager.getMarker("COMMAND"), command);
        mc.player.sendChatMessage(command);
    }

    public static CompoundNBT compareNBT(CompoundNBT oldTag, CompoundNBT newTag) {
        CompoundNBT res = new CompoundNBT();
        for (String s : newTag.keySet()) {
            INBT newNbt = newTag.get(s);
            INBT oldNbt = oldTag.get(s);
            if (newNbt == null) continue;
            if (newNbt instanceof CompoundNBT) {
                res.put(s, compareNBT(oldTag.getCompound(s), (CompoundNBT) newNbt));
            } else if (!newNbt.equals(oldNbt)) {
                res.put(s, newNbt);
            }
        }
        return res;
    }

    public static List<CompoundNBT> split(CompoundNBT nbt) {
        List<CompoundNBT> res = new ArrayList<>();
        for (String s : nbt.keySet()) {
            CompoundNBT c = new CompoundNBT();
            c.put(s, nbt.get(s));
            res.add(c);
        }
        return res;
    }
}

package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import org.apache.logging.log4j.MarkerManager;

public final class ClientUtils {
    private static final Minecraft mc = Minecraft.getInstance();
    private static final ITextComponent COMMAND_SENT_MESSAGE = new StringTextComponent("[IBE Editor] Command sent.")
            .mergeStyle(TextFormatting.GREEN);
    private static final ITextComponent COMMAND_COPIED_MESSAGE = new StringTextComponent("[IBE Editor] Command copied in your clipboard. Paste it in a ")
            .mergeStyle(TextFormatting.YELLOW)
            .append(new TranslationTextComponent("block.minecraft.command_block")
                    .modifyStyle(style -> style
                            .setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/give @p minecraft:command_block 1"))
                            .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new HoverEvent.ItemHover(new ItemStack(Items.COMMAND_BLOCK))))
                            .setColor(Color.func_240744_a_(TextFormatting.LIGHT_PURPLE))
                            .setUnderlined(true))
            )
            .append(ITextComponent.func_244388_a(" to apply the changes."));

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
                .append(blockState.get(property).toString().toLowerCase())
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

    public static boolean handleCommand(String command) {
        IBEEditorMod.LOGGER.debug(MarkerManager.getMarker("COMMAND"), command);
        if (command.length() < 256) {
            sendCommand(command);
            mc.player.sendMessage(COMMAND_SENT_MESSAGE, null);
            return true;
        } else {
            copyCommand(command);
            mc.player.sendMessage(COMMAND_COPIED_MESSAGE, null);
            return false;
        }
    }

    public static void sendCommand(String command) {
        mc.player.sendChatMessage(command);
    }
}

package com.github.franckyi.ibeeditor.common;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EditorContext {
    private Status status;
    private Trigger trigger;
    private Location location;
    private CommandTarget commandTarget;
    private EditorType editorType;

    private Target target;

    private ItemInventory itemInventory;
    private int slotIndex;
    private boolean creativeInventoryScreen;
    private ItemStack itemStack;

    private BlockPos blockPos;
    private BlockState blockState;
    private BlockEntity blockEntity;

    private int entityId;

    private CompoundTag tag;
    private boolean saveable;

    public EditorContext() {
    }

    public static EditorContext fromCommand() {
        var ctx = new EditorContext();
        ctx.setStatus(EditorContext.Status.COMMAND);
        ctx.setTrigger(EditorContext.Trigger.COMMAND);
        ctx.setLocation(EditorContext.Location.WORLD);
        return ctx;
    }

    public static EditorContext fromKeybind() {
        var ctx = new EditorContext();
        ctx.setLocation(EditorContext.Location.WORLD);
        ctx.setCommandTarget(EditorContext.CommandTarget.NULL);
        return ctx;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CommandTarget getCommandTarget() {
        return commandTarget;
    }

    public void setCommandTarget(CommandTarget commandTarget) {
        this.commandTarget = commandTarget;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public EditorType getEditorType() {
        return editorType;
    }

    public void setEditorType(EditorType editorType) {
        this.editorType = editorType;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public ItemInventory getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(ItemInventory itemInventory) {
        this.itemInventory = itemInventory;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public void setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    public boolean isCreativeInventoryScreen() {
        return creativeInventoryScreen;
    }

    public void setCreativeInventoryScreen(boolean creativeInventoryScreen) {
        this.creativeInventoryScreen = creativeInventoryScreen;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        if (itemStack != null) {
            this.tag = itemStack.save(new CompoundTag());
        }
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public void setBlockState(BlockState blockState) {
        this.blockState = blockState;
    }

    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    public void setBlockEntity(BlockEntity blockEntity) {
        this.blockEntity = blockEntity;
        if (blockEntity != null && this.tag == null) {
            this.tag = blockEntity.saveWithId();
        }
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public void setEntity(Entity entity) {
        tag = new CompoundTag();
        entity.saveAsPassenger(tag);
    }

    public CompoundTag getTag() {
        return tag;
    }

    public void setTag(CompoundTag tag) {
        this.tag = tag;
    }

    public boolean isSaveable() {
        return saveable;
    }

    public void setSaveable(boolean saveable) {
        this.saveable = saveable;
    }

    public enum Status {
        COMMAND, REQUEST, RESPONSE, UPDATE;

        public boolean isAfter(Status other) {
            return ordinal() > other.ordinal();
        }
    }

    public enum Trigger {
        KEYBIND_WORLD, KEYBIND_INVENTORY, COMMAND, VAULT
    }

    public enum CommandTarget {
        WORLD, ITEM, BLOCK, ENTITY, SELF, NULL
    }

    public enum EditorType {
        STANDARD, NBT, SNBT;

        @Deprecated
        public boolean isNBT() {
            return this != STANDARD;
        }
    }

    public enum Target {
        ITEM, BLOCK, ENTITY

    }

    public enum Location {
        WORLD, VAULT

    }

    public enum ItemInventory {
        MAIN_HAND, PLAYER_INVENTORY, BLOCK_INVENTORY
    }
}

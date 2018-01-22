package com.github.franckyi.ibeeditor.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class IBEUtil {

    private static Supplier<ItemStack> itemSupplier;
    private static Supplier<BlockPos> blockSupplier;
    private static Supplier<Entity> entitySupplier;

    public static ItemStack getItem() {
        return itemSupplier.get();
    }

    public static void setItemSupplier(Supplier<ItemStack> itemSupplier) {
        IBEUtil.itemSupplier = itemSupplier;
    }

    public static BlockPos getBlockPos() {
        return blockSupplier.get();
    }

    public static IBlockState getBlockState() {
        return Minecraft.getMinecraft().world.getBlockState(blockSupplier.get());
    }

    public static void setBlockPosSupplier(Supplier<BlockPos> blockSupplier) {
        IBEUtil.blockSupplier = blockSupplier;
    }

    public static Entity getEntity() {
        return entitySupplier.get();
    }

    public static void setEntitySupplier(Supplier<Entity> entitySupplier) {
        IBEUtil.entitySupplier = entitySupplier;
    }

    public static Supplier<String> formattedStringSupplier(Supplier<String> supplier) {
        return supplier.get().startsWith("§r") ? () -> supplier.get().substring(2) : supplier;
    }

    public static Consumer<String> formattedStringConsumer(Consumer<String> consumer) {
        return (s) -> consumer.accept(s.startsWith("§r") ? s : String.format("§r%s", s));
    }

}

package com.github.franckyi.ibeeditor.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

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
}

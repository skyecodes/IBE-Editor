package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.world.IBlockPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockPos.class)
public abstract class FabricBlockPosMixin extends Vec3i implements IBlockPos {
    protected FabricBlockPosMixin(int x, int y, int z) {
        super(x, y, z);
    }

    protected FabricBlockPosMixin(double x, double y, double z) {
        super(x, y, z);
    }
}

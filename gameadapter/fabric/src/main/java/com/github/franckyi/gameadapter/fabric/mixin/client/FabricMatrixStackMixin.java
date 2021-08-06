package com.github.franckyi.gameadapter.fabric.mixin.client;

import com.github.franckyi.gameadapter.api.client.IMatrices;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MatrixStack.class)
public abstract class FabricMatrixStackMixin implements IMatrices {
}

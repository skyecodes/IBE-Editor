package com.github.franckyi.gameadapter.forge.mixin.client;

import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.mojang.blaze3d.matrix.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MatrixStack.class)
public abstract class ForgeMatrixStackMixin implements IMatrices {
}

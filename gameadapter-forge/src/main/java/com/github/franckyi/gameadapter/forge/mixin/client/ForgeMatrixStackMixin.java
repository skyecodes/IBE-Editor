package com.github.franckyi.gameadapter.forge.mixin.client;

import com.github.franckyi.gameadapter.api.client.IMatrices;
import com.mojang.blaze3d.vertex.PoseStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PoseStack.class)
public abstract class ForgeMatrixStackMixin implements IMatrices {
}

package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ResourceLocation.class)
public abstract class ForgeResourceLocationMixin implements Comparable<ResourceLocation>, IIdentifier {
}

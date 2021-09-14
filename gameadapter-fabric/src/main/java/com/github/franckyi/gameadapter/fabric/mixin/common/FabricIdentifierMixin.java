package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Identifier.class)
public abstract class FabricIdentifierMixin implements Comparable<Identifier>, IIdentifier {
}

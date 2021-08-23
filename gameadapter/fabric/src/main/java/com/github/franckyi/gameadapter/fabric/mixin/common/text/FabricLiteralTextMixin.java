package com.github.franckyi.gameadapter.fabric.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IPlainText;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LiteralText.class)
public abstract class FabricLiteralTextMixin extends FabricBaseTextMixin implements IPlainText {
    @Shadow @Final private String string;

    @Override
    public String getText() {
        return string;
    }
}

package com.github.franckyi.gameadapter.fabric.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IPlainText;
import com.google.gson.JsonArray;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LiteralText.class)
public abstract class FabricLiteralTextMixin extends FabricBaseTextMixin implements IPlainText {
    @Shadow
    @Final
    private String string;

    @Override
    public String getText() {
        return string;
    }

    @Override
    public String toJson() {
        if (getText().isEmpty() && getColor() == null && getBold() == null && getItalic() == null &&
                getUnderlined() == null && getStrikethrough() == null && getObfuscated() == null &&
                getClickEvent() == null && getHoverEvent() == null) {
            JsonArray array = new JsonArray();
            getSiblings().forEach(text -> array.add(Text.Serializer.toJsonTree(text)));
            return array.toString();
        }
        return super.toJson();
    }
}

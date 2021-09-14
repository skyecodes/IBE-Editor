package com.github.franckyi.gameadapter.fabric.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IPlainText;
import com.google.gson.JsonArray;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LiteralText.class)
@Implements(@Interface(iface = IPlainText.class, prefix = "proxy$"))
public abstract class FabricLiteralTextMixin extends FabricBaseTextMixin {
    @Shadow
    public abstract String getRawString();

    public String proxy$getText() {
        return getRawString();
    }

    @Override
    public String proxy$toJson() {
        if (getRawString().isEmpty() && proxy$getColor() == null && proxy$getBold() == null &&
                proxy$getItalic() == null && proxy$getUnderlined() == null && proxy$getStrikethrough() == null &&
                proxy$getObfuscated() == null && proxy$getClickEvent() == null && proxy$getHoverEvent() == null) {
            JsonArray array = new JsonArray();
            getSiblings().forEach(text -> array.add(Text.Serializer.toJsonTree(text)));
            return array.toString();
        }
        return super.proxy$toJson();
    }
}

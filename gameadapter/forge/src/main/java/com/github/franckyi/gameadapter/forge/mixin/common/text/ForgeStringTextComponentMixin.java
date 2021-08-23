package com.github.franckyi.gameadapter.forge.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IPlainText;
import com.google.gson.JsonArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(StringTextComponent.class)
public abstract class ForgeStringTextComponentMixin extends ForgeTextComponentMixin implements IPlainText {
    @Override
    public String toJson() {
        if (getText().isEmpty() && getColor() == null && getBold() == null && getItalic() == null &&
                getUnderlined() == null && getStrikethrough() == null && getObfuscated() == null &&
                getClickEvent() == null && getHoverEvent() == null) {
            JsonArray array = new JsonArray();
            getSiblings().forEach(text -> array.add(ITextComponent.Serializer.toJsonTree(text)));
            return array.toString();
        }
        return super.toJson();
    }
}

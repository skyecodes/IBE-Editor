package com.github.franckyi.gameadapter.forge.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IPlainText;
import com.google.gson.JsonArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.spongepowered.asm.mixin.*;

@Mixin(StringTextComponent.class)
@Implements(@Interface(iface = IPlainText.class, prefix = "proxy$"))
public abstract class ForgeStringTextComponentMixin extends ForgeTextComponentMixin {
    @Shadow
    public abstract String getText();

    @Intrinsic
    public String proxy$getText() {
        return getText();
    }

    @Override
    public String proxy$toJson() {
        if (getText().isEmpty() && proxy$getColor() == null && proxy$getBold() == null && proxy$getItalic() == null &&
                proxy$getUnderlined() == null && proxy$getStrikethrough() == null && proxy$getObfuscated() == null &&
                proxy$getClickEvent() == null && proxy$getHoverEvent() == null) {
            JsonArray array = new JsonArray();
            getSiblings().forEach(text -> array.add(ITextComponent.Serializer.toJsonTree(text)));
            return array.toString();
        }
        return super.proxy$toJson();
    }
}

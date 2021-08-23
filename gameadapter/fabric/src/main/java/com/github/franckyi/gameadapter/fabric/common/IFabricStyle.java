package com.github.franckyi.gameadapter.fabric.common;
import com.github.franckyi.gameadapter.api.internal.IStyle;
import net.minecraft.text.Style;

public interface IFabricStyle extends IStyle {
    Style withStrikethrough(Boolean strikethrough);

    Style withObfuscated(Boolean obfuscated);
}

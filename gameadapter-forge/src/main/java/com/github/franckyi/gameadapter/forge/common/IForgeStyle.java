package com.github.franckyi.gameadapter.forge.common;

import com.github.franckyi.gameadapter.api.internal.IStyle;
import net.minecraft.network.chat.Style;

public interface IForgeStyle extends IStyle {
    Style withStrikethrough(Boolean strikethrough);

    Style withObfuscated(Boolean obfuscated);
}

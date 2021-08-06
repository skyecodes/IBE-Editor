package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.api.common.IIdentifier;

public class ModTextures {
    public static final IIdentifier SETTINGS = gui("settings");
    public static final IIdentifier ZOOM_RESET = gui("zoom_reset");
    public static final IIdentifier ZOOM_IN = gui("zoom_in");
    public static final IIdentifier ZOOM_OUT = gui("zoom_out");
    public static final IIdentifier BYTE_TAG_ADD = gui("byte_tag_add");
    public static final IIdentifier SHORT_TAG_ADD = gui("short_tag_add");
    public static final IIdentifier INT_TAG_ADD = gui("int_tag_add");
    public static final IIdentifier LONG_TAG_ADD = gui("long_tag_add");
    public static final IIdentifier FLOAT_TAG_ADD = gui("float_tag_add");
    public static final IIdentifier DOUBLE_TAG_ADD = gui("double_tag_add");
    public static final IIdentifier BYTE_ARRAY_TAG_ADD = gui("byte_array_tag_add");
    public static final IIdentifier STRING_TAG_ADD = gui("string_tag_add");
    public static final IIdentifier LIST_TAG_ADD = gui("list_tag_add");
    public static final IIdentifier COMPOUND_TAG_ADD = gui("compound_tag_add");
    public static final IIdentifier INT_ARRAY_TAG_ADD = gui("int_array_tag_add");
    public static final IIdentifier LONG_ARRAY_TAG_ADD = gui("long_array_tag_add");
    public static final IIdentifier MOVE_UP = gui("move_up");
    public static final IIdentifier MOVE_DOWN = gui("move_down");
    public static final IIdentifier ADD = gui("add");
    public static final IIdentifier REMOVE = gui("remove");
    public static final IIdentifier CUT = gui("cut");
    public static final IIdentifier COPY = gui("copy");
    public static final IIdentifier PASTE = gui("paste");
    public static final IIdentifier COLLAPSE = gui("collapse_all");
    public static final IIdentifier EXPAND = gui("expand_all");
    public static final IIdentifier SCROLL_FOCUSED = gui("scroll_focused");
    public static final IIdentifier RESET = gui("reset");
    public static final IIdentifier SEARCH = gui("search");
    public static final IIdentifier COLOR_CUSTOM = gui("color_custom");
    public static final IIdentifier LEVEL_ADD = gui("level_add");
    public static final IIdentifier LEVEL_REMOVE = gui("level_remove");
    public static final IIdentifier RESET_COLOR = gui("reset_color");
    public static final IIdentifier COLOR_BLACK = gui("color_black");
    public static final IIdentifier COLOR_DARK_BLUE = gui("color_dark_blue");
    public static final IIdentifier COLOR_DARK_GREEN = gui("color_dark_green");
    public static final IIdentifier COLOR_DARK_AQUA = gui("color_dark_aqua");
    public static final IIdentifier COLOR_DARK_RED = gui("color_dark_red");
    public static final IIdentifier COLOR_DARK_PURPLE = gui("color_dark_purple");
    public static final IIdentifier COLOR_GOLD = gui("color_gold");
    public static final IIdentifier COLOR_GRAY = gui("color_gray");
    public static final IIdentifier COLOR_DARK_GRAY = gui("color_dark_gray");
    public static final IIdentifier COLOR_BLUE = gui("color_blue");
    public static final IIdentifier COLOR_GREEN = gui("color_green");
    public static final IIdentifier COLOR_AQUA = gui("color_aqua");
    public static final IIdentifier COLOR_RED = gui("color_red");
    public static final IIdentifier COLOR_LIGHT_PURPLE = gui("color_light_purple");
    public static final IIdentifier COLOR_YELLOW = gui("color_yellow");
    public static final IIdentifier COLOR_WHITE = gui("color_white");
    public static final IIdentifier TEXT_BOLD = gui("text_bold");
    public static final IIdentifier TEXT_ITALIC = gui("text_italic");
    public static final IIdentifier TEXT_UNDERLINED = gui("text_underline");
    public static final IIdentifier TEXT_STRIKETHROUGH = gui("text_strikethrough");
    public static final IIdentifier TEXT_OBFUSCATED = gui("text_obfuscated");

    public static IIdentifier gui(String textureName) {
        return IIdentifier.of("ibeeditor", String.format("textures/gui/%s.png", textureName));
    }
}

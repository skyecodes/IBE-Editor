package com.github.franckyi.ibeeditor.client;


import net.minecraft.resources.ResourceLocation;

public class ModTextures {
    public static final ResourceLocation ADD = gui("add");
    public static final ResourceLocation BYTE_ARRAY_TAG = gui("byte_array_tag");
    public static final ResourceLocation BYTE_ARRAY_TAG_ADD = gui("byte_array_tag_add");
    public static final ResourceLocation BYTE_TAG = gui("byte_tag");
    public static final ResourceLocation BYTE_TAG_ADD = gui("byte_tag_add");
    public static final ResourceLocation COLLAPSE = gui("collapse_all");
    public static final ResourceLocation COLOR_AQUA = gui("color_aqua");
    public static final ResourceLocation COLOR_BLACK = gui("color_black");
    public static final ResourceLocation COLOR_BLUE = gui("color_blue");
    public static final ResourceLocation COLOR_CUSTOM = gui("color_custom");
    public static final ResourceLocation COLOR_DARK_AQUA = gui("color_dark_aqua");
    public static final ResourceLocation COLOR_DARK_BLUE = gui("color_dark_blue");
    public static final ResourceLocation COLOR_DARK_GRAY = gui("color_dark_gray");
    public static final ResourceLocation COLOR_DARK_GREEN = gui("color_dark_green");
    public static final ResourceLocation COLOR_DARK_PURPLE = gui("color_dark_purple");
    public static final ResourceLocation COLOR_DARK_RED = gui("color_dark_red");
    public static final ResourceLocation COLOR_GOLD = gui("color_gold");
    public static final ResourceLocation COLOR_GRAY = gui("color_gray");
    public static final ResourceLocation COLOR_GREEN = gui("color_green");
    public static final ResourceLocation COLOR_LIGHT_PURPLE = gui("color_light_purple");
    public static final ResourceLocation COLOR_RED = gui("color_red");
    public static final ResourceLocation COLOR_WHITE = gui("color_white");
    public static final ResourceLocation COLOR_YELLOW = gui("color_yellow");
    public static final ResourceLocation COMPOUND_TAG = gui("compound_tag");
    public static final ResourceLocation COMPOUND_TAG_ADD = gui("compound_tag_add");
    public static final ResourceLocation COPY = gui("copy");
    public static final ResourceLocation CUT = gui("cut");
    public static final ResourceLocation DOUBLE_TAG = gui("double_tag");
    public static final ResourceLocation DOUBLE_TAG_ADD = gui("double_tag_add");
    public static final ResourceLocation EXPAND = gui("expand_all");
    public static final ResourceLocation FLOAT_TAG = gui("float_tag");
    public static final ResourceLocation FLOAT_TAG_ADD = gui("float_tag_add");
    public static final ResourceLocation INT_ARRAY_TAG = gui("int_array_tag");
    public static final ResourceLocation INT_ARRAY_TAG_ADD = gui("int_array_tag_add");
    public static final ResourceLocation INT_TAG = gui("int_tag");
    public static final ResourceLocation INT_TAG_ADD = gui("int_tag_add");
    public static final ResourceLocation LEVEL_ADD = gui("level_add");
    public static final ResourceLocation LEVEL_REMOVE = gui("level_remove");
    public static final ResourceLocation LIST_TAG = gui("list_tag");
    public static final ResourceLocation LIST_TAG_ADD = gui("list_tag_add");
    public static final ResourceLocation LONG_ARRAY_TAG = gui("long_array_tag");
    public static final ResourceLocation LONG_ARRAY_TAG_ADD = gui("long_array_tag_add");
    public static final ResourceLocation LONG_TAG = gui("long_tag");
    public static final ResourceLocation LONG_TAG_ADD = gui("long_tag_add");
    public static final ResourceLocation MOVE_DOWN = gui("move_down");
    public static final ResourceLocation MOVE_UP = gui("move_up");
    public static final ResourceLocation PASTE = gui("paste");
    public static final ResourceLocation REMOVE = gui("remove");
    public static final ResourceLocation RESET = gui("reset");
    public static final ResourceLocation RESET_COLOR = gui("reset_color");
    public static final ResourceLocation SCROLL_FOCUSED = gui("scroll_focused");
    public static final ResourceLocation SEARCH = gui("search");
    public static final ResourceLocation SETTINGS = gui("settings");
    public static final ResourceLocation SHORT_TAG = gui("short_tag");
    public static final ResourceLocation SHORT_TAG_ADD = gui("short_tag_add");
    public static final ResourceLocation STRING_TAG = gui("string_tag");
    public static final ResourceLocation STRING_TAG_ADD = gui("string_tag_add");
    public static final ResourceLocation TEXT_BOLD = gui("text_bold");
    public static final ResourceLocation TEXT_ITALIC = gui("text_italic");
    public static final ResourceLocation TEXT_OBFUSCATED = gui("text_obfuscated");
    public static final ResourceLocation TEXT_STRIKETHROUGH = gui("text_strikethrough");
    public static final ResourceLocation TEXT_UNDERLINED = gui("text_underline");
    public static final ResourceLocation ZOOM_IN = gui("zoom_in");
    public static final ResourceLocation ZOOM_OUT = gui("zoom_out");
    public static final ResourceLocation ZOOM_RESET = gui("zoom_reset");

    public static ResourceLocation gui(String textureName) {
        return new ResourceLocation("ibeeditor", String.format("textures/gui/%s.png", textureName));
    }
}

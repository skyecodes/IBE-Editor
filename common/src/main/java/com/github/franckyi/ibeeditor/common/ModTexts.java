package com.github.franckyi.ibeeditor.common;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public final class ModTexts {
    public static final MutableComponent ADD = modTranslated("add").withStyle(ChatFormatting.GREEN);
    public static final MutableComponent AMBIENT = modTranslated("ambient");
    public static final MutableComponent AMOUNT = modTranslated("amount");
    public static final MutableComponent AMPLIFIER = modTranslated("amplifier");
    public static final MutableComponent AQUA = modTranslated("aqua").withStyle(ChatFormatting.AQUA);
    public static final MutableComponent ARMOR_COLOR = modTranslated("armor_color");
    public static final MutableComponent ATTRIBUTE = modTranslated("attribute");
    public static final MutableComponent ATTRIBUTE_MODIFIERS = modTranslated("attribute_modifiers");
    public static final MutableComponent ATTRIBUTE_NAME = modTranslated("attribute_name");
    public static final MutableComponent BLACK = modTranslated("black").withStyle(ChatFormatting.AQUA);
    public static final MutableComponent BLOCK = modTranslated("block");
    public static final MutableComponent BLUE = modTranslated("blue").withStyle(ChatFormatting.BLUE);
    public static final MutableComponent BLUE_COLOR = modTranslated("blue");
    public static final MutableComponent BOLD = modTranslated("bold");
    public static final MutableComponent CANCEL = translated("gui.cancel").withStyle(ChatFormatting.RED);
    public static final MutableComponent CAN_DESTROY = modTranslated("can_destroy");
    public static final MutableComponent CAN_PLACE_ON = modTranslated("can_place_on");
    public static final MutableComponent CLIENT = modTranslated("client");
    public static final MutableComponent CLOSE = modTranslated("close_without_saving").withStyle(ChatFormatting.RED);
    public static final MutableComponent COLLAPSE = modTranslated("collapse");
    public static final MutableComponent COPY = modTranslated("copy");
    public static final MutableComponent COUNT = modTranslated("count");
    public static final MutableComponent CUSTOM_COLOR = modTranslated("custom_color");
    public static final MutableComponent CUSTOM_NAME = modTranslated("custom_name");
    public static final MutableComponent CUT = modTranslated("cut");
    public static final MutableComponent DAMAGE = modTranslated("damage");
    public static final MutableComponent DARK_AQUA = modTranslated("dark_aqua").withStyle(ChatFormatting.AQUA);
    public static final MutableComponent DARK_BLUE = modTranslated("dark_blue").withStyle(ChatFormatting.BLUE);
    public static final MutableComponent DARK_GRAY = modTranslated("dark_gray").withStyle(ChatFormatting.GRAY);
    public static final MutableComponent DARK_GREEN = modTranslated("dark_green").withStyle(ChatFormatting.GREEN);
    public static final MutableComponent DARK_PURPLE = modTranslated("dark_purple").withStyle(ChatFormatting.LIGHT_PURPLE);
    public static final MutableComponent DARK_RED = modTranslated("dark_red").withStyle(ChatFormatting.RED);
    public static final MutableComponent DEBUG_MODE = modTranslated("debug_mode");
    public static final MutableComponent DEFAULT_POTION = modTranslated("default_potion");
    public static final MutableComponent DISPLAY = modTranslated("display");
    public static final MutableComponent DONE = translated("gui.done").withStyle(ChatFormatting.GREEN);
    public static final MutableComponent DURATION = modTranslated("duration");
    public static final MutableComponent EFFECT = modTranslated("effect");
    public static final MutableComponent EFFECTS = modTranslated("effect");
    public static final MutableComponent ENCHANTMENTS = modTranslated("enchantments");
    public static final MutableComponent ENTITY = modTranslated("entity");
    public static final MutableComponent EXPAND = modTranslated("expand");
    public static final MutableComponent FIX_ERRORS = modTranslated("fix_errors").withStyle(ChatFormatting.RED);
    public static final MutableComponent GENERAL = modTranslated("general");
    public static final MutableComponent GOLD = modTranslated("gold").withStyle(ChatFormatting.YELLOW);
    public static final MutableComponent GRAY = modTranslated("gray").withStyle(ChatFormatting.GRAY);
    public static final MutableComponent GREEN = modTranslated("green").withStyle(ChatFormatting.GREEN);
    public static final MutableComponent GREEN_COLOR = modTranslated("green");
    public static final MutableComponent HIDE_FLAGS = modTranslated("hide_flags");
    public static final MutableComponent[] HIDE_OTHER_TOOLTIP = modTextArray("hide_other_tooltip", 8);
    public static final MutableComponent ITALIC = modTranslated("italic");
    public static final MutableComponent ITEM = modTranslated("item");
    public static final MutableComponent ITEM_ID = modTranslated("item_id");
    public static final MutableComponent LEVEL_ADD = modTranslated("level_add", text("+1")).withStyle(ChatFormatting.GREEN);
    public static final MutableComponent LEVEL_REMOVE = modTranslated("level_add", text("-1")).withStyle(ChatFormatting.GREEN);
    public static final MutableComponent LIGHT_PURPLE = modTranslated("light_purple").withStyle(ChatFormatting.LIGHT_PURPLE);
    public static final MutableComponent LORE_ADD = modTranslated("lore_add");
    public static final MutableComponent MODIFIER = modTranslated("modifier");
    public static final MutableComponent MOVE_DOWN = modTranslated("move_down");
    public static final MutableComponent MOVE_UP = modTranslated("move_up");
    public static final MutableComponent OBFUSCATED = modTranslated("obfuscated");
    public static final MutableComponent PASTE = modTranslated("paste");
    public static final MutableComponent POTION = modTranslated("potion");
    public static final MutableComponent POTION_COLOR = modTranslated("potion_color");
    public static final MutableComponent POTION_EFFECTS = modTranslated("potion_effects");
    public static final MutableComponent RED = modTranslated("red").withStyle(ChatFormatting.RED);
    public static final MutableComponent RED_COLOR = modTranslated("red");
    public static final MutableComponent RELOAD_CONFIG = modTranslated("reload_config").withStyle(ChatFormatting.YELLOW);
    public static final MutableComponent REMOVE = modTranslated("remove").withStyle(ChatFormatting.RED);
    public static final MutableComponent REMOVE_CUSTOM_COLOR = modTranslated("remove_custom_color").withStyle(ChatFormatting.RED);
    public static final MutableComponent RESET = modTranslated("reset").withStyle(ChatFormatting.YELLOW);
    public static final MutableComponent RESET_COLOR = modTranslated("reset_color");
    public static final MutableComponent SAVE = modTranslated("save").withStyle(ChatFormatting.GREEN);
    public static final MutableComponent SCROLL_FOCUSED = modTranslated("scroll_focused");
    public static final MutableComponent SEARCH = modTranslated("search");
    public static final MutableComponent SETTINGS = modTranslated("settings");
    public static final MutableComponent SHOW_ICON = modTranslated("show_icon");
    public static final MutableComponent SHOW_PARTICLES = modTranslated("show_particles");
    public static final MutableComponent SLOT = modTranslated("slot");
    public static final MutableComponent STRIKETHROUGH = modTranslated("strikethough");
    public static final MutableComponent THEME = modTranslated("theme");
    public static final MutableComponent UNBREAKABLE = modTranslated("unbreakable");
    public static final MutableComponent UNDERLINED = modTranslated("underline");
    public static final MutableComponent WHITE = modTranslated("white").withStyle(ChatFormatting.WHITE);
    public static final MutableComponent YELLOW = modTranslated("yellow").withStyle(ChatFormatting.YELLOW);
    public static final MutableComponent ZOOM_IN = modTranslated("zoom_in");
    public static final MutableComponent ZOOM_OUT = modTranslated("zoom_out");
    public static final MutableComponent ZOOM_RESET = modTranslated("zoom_reset");
    public static final MutableComponent SELECTION_SCREEN_MAX_ITEMS = modTranslated("selection_screen_max_items");
    public static final MutableComponent BLOCK_STATE = modTranslated("block_state");
    public static final MutableComponent SPAWN_EGG = modTranslated("spawn_egg");
    public static final MutableComponent INVENTORY = translated("container.inventory");
    public static final MutableComponent OPEN_VAULT = translated("ibeeditor.key.vault");
    public static final MutableComponent ERROR_GENERIC = prefixed(translated("ibeeditor.error_generic")).withStyle(ChatFormatting.RED);

    public static MutableComponent serverMod(MutableComponent with) {
        return translated("ibeeditor.error_server_mod", with).withStyle(ChatFormatting.RED);
    }

    public static MutableComponent creativeMode(MutableComponent with) {
        return translated("ibeeditor.error_creative_mode", with).withStyle(ChatFormatting.RED);
    }

    public static MutableComponent choose(MutableComponent with) {
        return modTranslated("choose", with);
    }

    public static MutableComponent addTag(String color, String with) {
        return modTranslated("add_tag", text(with)).withStyle(style -> style.withColor(TextColor.parseColor(color)));
    }

    public static MutableComponent editorTitle(MutableComponent type) {
        return title(modTranslated("editor_title", type));
    }

    public static MutableComponent editorTitle(String type) {
        return editorTitle(text(type));
    }

    public static MutableComponent title(MutableComponent text) {
        return text.withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD);
    }

    public static MutableComponent addListEntry(MutableComponent with) {
        return modTranslated("add", with);
    }

    public static MutableComponent lore(int i) {
        return modTranslated("lore", text(Integer.toString(i)));
    }

    public static MutableComponent attributeModifierOperationText(int value) {
        return text("OP: " + value);
    }

    public static MutableComponent attributeModifierOperationTooltip(int value) {
        return modTranslated("operation", modTranslated("operation." + value));
    }

    public static MutableComponent hide(MutableComponent with) {
        return modTranslated("hide", with);
    }

    private static MutableComponent[] modTextArray(String key, int size) {
        MutableComponent[] array = new MutableComponent[size];
        for (int i = 0; i < size; i++) {
            array[i] = modTranslated(key + "." + i);
        }
        return array;
    }

    public static MutableComponent edit(MutableComponent arg) {
        return modTranslated("edit", arg);
    }

    public static MutableComponent modTranslated(String s) {
        return translated("ibeeditor." + s);
    }

    public static MutableComponent modTranslated(String s, Object... args) {
        return translated("ibeeditor." + s, args);
    }

    public static MutableComponent successUpdate(MutableComponent arg) {
        return prefixed(modTranslated("success_update", arg)).withStyle(ChatFormatting.GREEN);
    }

    public static MutableComponent errorServerMod(MutableComponent arg) {
        return prefixed(ModTexts.serverMod(arg)).withStyle(ChatFormatting.RED);
    }

    public static MutableComponent errorCreativeMode(MutableComponent arg) {
        return prefixed(ModTexts.creativeMode(arg)).withStyle(ChatFormatting.RED);
    }

    public static MutableComponent errorNoTargetFound(MutableComponent arg) {
        return prefixed(modTranslated("no_target_found", arg)).withStyle(ChatFormatting.RED);
    }

    public static MutableComponent warnNotImplemented(MutableComponent arg) {
        return prefixed(modTranslated("not_implemented", arg)).withStyle(ChatFormatting.YELLOW);
    }

    private static MutableComponent prefixed(MutableComponent arg) {
        return translated("chat.type.announcement", translated("ibeeditor"), arg);
    }

    public static class Literal {
        public static final MutableComponent BYTE = text("Byte").withStyle(ChatFormatting.BLUE);
        public static final MutableComponent BYTE_ARRAY = text("Byte Array").withStyle(ChatFormatting.BLUE);
        public static final MutableComponent COMPOUND = text("Compound").withStyle(ChatFormatting.LIGHT_PURPLE);
        public static final MutableComponent DOUBLE = text("Double").withStyle(ChatFormatting.YELLOW);
        public static final MutableComponent FLOAT = text("Float").withStyle(ChatFormatting.LIGHT_PURPLE);
        public static final MutableComponent GREEN = text("List").withStyle(ChatFormatting.GREEN);
        public static final MutableComponent HEX = text("Hex");
        public static final MutableComponent INT = text("Int").withStyle(ChatFormatting.AQUA);
        public static final MutableComponent INT_ARRAY = text("Int Array").withStyle(ChatFormatting.AQUA);
        public static final MutableComponent LONG = text("Long").withStyle(ChatFormatting.RED);
        public static final MutableComponent LONG_ARRAY = text("Long Array").withStyle(ChatFormatting.RED);
        public static final MutableComponent SHORT = text("Short").withStyle(ChatFormatting.GREEN);
        public static final MutableComponent STRING = text("String").withStyle(ChatFormatting.GRAY);
    }
}

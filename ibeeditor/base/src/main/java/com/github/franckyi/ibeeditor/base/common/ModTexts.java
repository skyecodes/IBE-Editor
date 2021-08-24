package com.github.franckyi.ibeeditor.base.common;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.text.ITranslatedText;

import static com.github.franckyi.guapi.GuapiHelper.*;

public final class ModTexts {
    public static final IText SETTINGS = translated("ibeeditor.gui.settings");
    public static final IText MOVE_UP = translated("ibeeditor.gui.move_up");
    public static final IText MOVE_DOWN = translated("ibeeditor.gui.move_down");
    public static final IText ADD = translated("ibeeditor.gui.add").green();
    public static final IText REMOVE = translated("ibeeditor.gui.remove").red();
    public static final IText CUT = translated("ibeeditor.gui.cut");
    public static final IText COPY = translated("ibeeditor.gui.copy");
    public static final IText PASTE = translated("ibeeditor.gui.paste");
    public static final IText COLLAPSE = translated("ibeeditor.gui.collapse");
    public static final IText EXPAND = translated("ibeeditor.gui.expand");
    public static final IText SCROLL_FOCUSED = translated("ibeeditor.gui.scroll_focused");
    public static final IText CHOOSE_CUSTOM_COLOR = translated("ibeeditor.gui.choose_custom_color").aqua().bold();
    public static final IText RED_COLOR = translated("ibeeditor.gui.red");
    public static final IText GREEN_COLOR = translated("ibeeditor.gui.green");
    public static final IText BLUE_COLOR = translated("ibeeditor.gui.blue");
    public static final IText CANCEL = translated("gui.cancel").red();
    public static final IText DONE = translated("gui.done").green();
    public static final IText SAVE = translated("ibeeditor.gui.save").green();
    public static final IText RESET = translated("ibeeditor.gui.reset").yellow();
    public static final IText POTION_COLOR = translated("ibeeditor.gui.potion_color");
    public static final IText REMOVE_CUSTOM_COLOR = translated("ibeeditor.gui.remove_custom_color").red();
    public static final IText LEVEL_ADD = translated("ibeeditor.gui.level_add", text("+1")).green();
    public static final IText LEVEL_REMOVE = translated("ibeeditor.gui.level_add", text("-1")).green();
    public static final IText ATTRIBUTE_NAME = translated("ibeeditor.gui.attribute_name");
    public static final IText ATTRIBUTE = translated("ibeeditor.gui.attribute");
    public static final IText SLOT = translated("ibeeditor.gui.slot");
    public static final IText AMOUNT = translated("ibeeditor.gui.amount");
    public static final IText RESET_COLOR = translated("ibeeditor.gui.reset_color");
    public static final IText CUSTOM_COLOR = translated("ibeeditor.gui.custom_color");
    public static final IText BLACK = translated("ibeeditor.gui.black").gray();
    public static final IText DARK_BLUE = translated("ibeeditor.gui.dark_blue").blue();
    public static final IText DARK_GREEN = translated("ibeeditor.gui.dark_green").green();
    public static final IText DARK_AQUA = translated("ibeeditor.gui.dark_aqua").aqua();
    public static final IText DARK_RED = translated("ibeeditor.gui.dark_red").red();
    public static final IText DARK_PURPLE = translated("ibeeditor.gui.dark_purple").lightPurple();
    public static final IText GOLD = translated("ibeeditor.gui.gold").yellow();
    public static final IText GRAY = translated("ibeeditor.gui.gray").gray();
    public static final IText DARK_GRAY = translated("ibeeditor.gui.dark_gray").gray();
    public static final IText BLUE = translated("ibeeditor.gui.blue").blue();
    public static final IText GREEN = translated("ibeeditor.gui.green").green();
    public static final IText AQUA = translated("ibeeditor.gui.aqua").aqua();
    public static final IText RED = translated("ibeeditor.gui.red").red();
    public static final IText LIGHT_PURPLE = translated("ibeeditor.gui.light_purple").lightPurple();
    public static final IText YELLOW = translated("ibeeditor.gui.yellow").yellow();
    public static final IText WHITE = translated("ibeeditor.gui.white").white();
    public static final IText BOLD = translated("ibeeditor.gui.bold");
    public static final IText ITALIC = translated("ibeeditor.gui.italic");
    public static final IText UNDERLINED = translated("ibeeditor.gui.underline");
    public static final IText STRIKETHROUGH = translated("ibeeditor.gui.strikethough");
    public static final IText OBFUSCATED = translated("ibeeditor.gui.obfuscated");

    public static final IText NO_ITEM_FOUND_TEXT = prefixed(translated("ibeeditor.message.no_target_found", translated("ibeeditor.text.item"))).red();
    public static final IText NO_BLOCK_FOUND_TEXT = prefixed(translated("ibeeditor.message.no_target_found", translated("ibeeditor.text.block"))).red();
    public static final IText NO_ENTITY_FOUND_TEXT = prefixed(translated("ibeeditor.message.no_target_found", translated("ibeeditor.text.entity"))).red();
    public static final IText ERROR_CREATIVE_ITEM = prefixed(translated("ibeeditor.message.error_creative_mode", translated("ibeeditor.text.item"))).red();
    public static final IText ERROR_SERVERMOD_ITEM = prefixed(translated("ibeeditor.message.error_server_mod", translated("ibeeditor.text.item"))).red();
    public static final IText ERROR_SERVERMOD_BLOCK = prefixed(translated("ibeeditor.message.error_server_mod", translated("ibeeditor.text.block"))).red();
    public static final IText ERROR_SERVERMOD_ENTITY = prefixed(translated("ibeeditor.message.error_server_mod", translated("ibeeditor.text.entity"))).red();
    public static final IText ERROR_NOT_IMPLEMENTED_BLOCK = prefixed(translated("ibeeditor.message.not_implemented", translated("ibeeditor.text.block"))).yellow();
    public static final IText ERROR_NOT_IMPLEMENTED_ENTITY = prefixed(translated("ibeeditor.message.not_implemented", translated("ibeeditor.text.entity"))).yellow();
    public static final IText ITEM_UPDATED = prefixed(translated("ibeeditor.message.success_update", translated("ibeeditor.text.item"))).green();
    public static final IText BLOCK_UPDATED = prefixed(translated("ibeeditor.message.success_update", translated("ibeeditor.text.block"))).green();
    public static final IText ENTITY_UPDATED = prefixed(translated("ibeeditor.message.success_update", translated("ibeeditor.text.entity"))).green();

    public static ITranslatedText prefixed(IText text) {
        return translated("ibeeditor.message.prefix").extra(text);
    }

    public static IText choose(IText with) {
        return translated("ibeeditor.gui.choose", with);
    }

    public static ITranslatedText addTag(String color, String with) {
        return translated("ibeeditor.gui.add_tag", text(with)).color(color);
    }

    public static ITranslatedText editorTitle() {
        return translated("ibeeditor.gui.editor_title").aqua().bold();
    }

    public static IText editorTitle(IText type) {
        return translated("ibeeditor.gui.editor_title", type).aqua().bold();
    }

    public static IText editorTitle(String type) {
        return editorTitle(text(type));
    }

    public static class Literal {
        public static final IText HEX = text("Hex");
    }
}

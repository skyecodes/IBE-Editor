package com.github.franckyi.ibeeditor.base.common;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.text.builder.TranslatedTextBuilder;

import static com.github.franckyi.guapi.GuapiHelper.*;

public final class ModTexts {
    public static final TranslatedTextBuilder EDITOR_TITLE = translated("ibeeditor.gui.editor_title").aqua().bold();
    public static final Text SETTINGS = translated("ibeeditor.gui.settings");
    public static final Text MOVE_UP = translated("ibeeditor.gui.move_up");
    public static final Text MOVE_DOWN = translated("ibeeditor.gui.move_down");
    public static final Text ADD = translated("ibeeditor.gui.add").green();
    public static final Text REMOVE = translated("ibeeditor.gui.remove").red();
    public static final Text CUT = translated("ibeeditor.gui.cut");
    public static final Text COPY = translated("ibeeditor.gui.copy");
    public static final Text PASTE = translated("ibeeditor.gui.paste");
    public static final Text COLLAPSE = translated("ibeeditor.gui.collapse");
    public static final Text EXPAND = translated("ibeeditor.gui.expand");
    public static final Text SCROLL_FOCUSED = translated("ibeeditor.gui.scroll_focused");
    public static final Text CHOOSE_CUSTOM_COLOR = translated("ibeeditor.gui.choose_custom_color").aqua().bold();
    public static final Text RED_COLOR = translated("ibeeditor.gui.red");
    public static final Text GREEN_COLOR = translated("ibeeditor.gui.green");
    public static final Text BLUE_COLOR = translated("ibeeditor.gui.blue");
    public static final Text CANCEL = translated("gui.cancel").red();
    public static final Text DONE = translated("gui.done").green();
    public static final Text RESET = translated("ibeeditor.gui.reset").yellow();
    public static final Text POTION_COLOR = translated("ibeeditor.gui.potion_color");
    public static final Text REMOVE_CUSTOM_COLOR = translated("ibeeditor.gui.remove_custom_color").red();
    public static final Text LEVEL_ADD = translated("ibeeditor.gui.level_add").with(text("+1")).green();
    public static final Text LEVEL_REMOVE = translated("ibeeditor.gui.level_add").with(text("-1")).green();
    public static final Text ATTRIBUTE_NAME = translated("ibeeditor.gui.attribute_name");
    public static final Text ATTRIBUTE = translated("ibeeditor.gui.attribute");
    public static final Text SLOT = translated("ibeeditor.gui.slot");
    public static final Text AMOUNT = translated("ibeeditor.gui.amount");
    public static final Text RESET_COLOR = translated("ibeeditor.gui.reset_color");
    public static final Text CUSTOM_COLOR = translated("ibeeditor.gui.custom_color");
    public static final Text BLACK = translated("ibeeditor.gui.black").gray();
    public static final Text DARK_BLUE = translated("ibeeditor.gui.dark_blue").blue();
    public static final Text DARK_GREEN = translated("ibeeditor.gui.dark_green").green();
    public static final Text DARK_AQUA = translated("ibeeditor.gui.dark_aqua").aqua();
    public static final Text DARK_RED = translated("ibeeditor.gui.dark_red").red();
    public static final Text DARK_PURPLE = translated("ibeeditor.gui.dark_purple").lightPurple();
    public static final Text GOLD = translated("ibeeditor.gui.gold").yellow();
    public static final Text GRAY = translated("ibeeditor.gui.gray").gray();
    public static final Text DARK_GRAY = translated("ibeeditor.gui.dark_gray").gray();
    public static final Text BLUE = translated("ibeeditor.gui.blue").blue();
    public static final Text GREEN = translated("ibeeditor.gui.green").green();
    public static final Text AQUA = translated("ibeeditor.gui.aqua").aqua();
    public static final Text RED = translated("ibeeditor.gui.red").red();
    public static final Text LIGHT_PURPLE = translated("ibeeditor.gui.light_purple").lightPurple();
    public static final Text YELLOW = translated("ibeeditor.gui.yellow").yellow();
    public static final Text WHITE = translated("ibeeditor.gui.white").white();
    public static final Text BOLD = translated("ibeeditor.gui.bold");
    public static final Text ITALIC = translated("ibeeditor.gui.italic");
    public static final Text UNDERLINED = translated("ibeeditor.gui.underline");
    public static final Text STRIKETHROUGH = translated("ibeeditor.gui.strikethough");
    public static final Text OBFUSCATED = translated("ibeeditor.gui.obfuscated");

    public static TranslatedTextBuilder prefixed(Text text) {
        return translated("ibeeditor.message.prefix").extra(text);
    }

    public static TranslatedTextBuilder choose() {
        return translated("ibeeditor.gui.choose");
    }

    public static Text choose(Text with) {
        return choose().with(with);
    }

    public static TranslatedTextBuilder addTag(String color, String with) {
        return translated("ibeeditor.gui.add_tag").color(color).with(text(with));
    }

    public static TranslatedTextBuilder editorTitle() {
        return translated("ibeeditor.gui.editor_title").aqua().bold();
    }

    public static Text editorTitle(String type) {
        return editorTitle().with(text(type));
    }

    public static class Literal {
        public static final Text HEX = text("Hex");
    }
}

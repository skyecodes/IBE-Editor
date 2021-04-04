package com.github.franckyi.ibeeditor.api.client.mvc.editor.view;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;

import java.util.function.Consumer;

public interface EditorView extends View {
    Label getHeaderLabel();

    ListView<CategoryModel> getCategoryList();

    ListView<EntryModel> getEntryList();

    Button getDoneButton();

    Button getCancelButton();

    default boolean isShowTextButtons() {
        return showTextButtonsProperty().getValue();
    }

    BooleanProperty showTextButtonsProperty();

    default void setShowTextButtons(boolean value) {
        showTextButtonsProperty().setValue(value);
    }

    void setOnTextButtonClick(Consumer<TextButtonType> action);

    enum TextButtonType {
        EDIT, RESET, BOLD, ITALIC, UNDERLINE, STRIKETHROUGH, OBFUSCATED, BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA,
        DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE, CUSTOM
    }
}

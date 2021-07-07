package com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.view;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.ListEditorView;
import com.github.franckyi.ibeeditor.impl.client.util.texteditor.TextEditorActionHandler;
import com.github.franckyi.minecraft.api.common.text.builder.TranslatedTextBuilder;

public interface StandardEditorView extends ListEditorView {
    TranslatedTextBuilder getHeaderText();

    default boolean isShowTextButtons() {
        return showTextButtonsProperty().getValue();
    }

    BooleanProperty showTextButtonsProperty();

    default void setShowTextButtons(boolean value) {
        showTextButtonsProperty().setValue(value);
    }

    void setTextEditorActionHandler(TextEditorActionHandler handler);
}

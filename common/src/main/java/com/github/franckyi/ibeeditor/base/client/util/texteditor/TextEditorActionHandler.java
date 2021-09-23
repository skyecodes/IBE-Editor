package com.github.franckyi.ibeeditor.base.client.util.texteditor;

public interface TextEditorActionHandler {
    void removeColorFormatting();

    void addColorFormatting(String color);

    void addStyleFormatting(StyleType type);
}

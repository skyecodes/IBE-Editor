package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gamehooks.util.common.text.Text;
import com.github.franckyi.ibeeditor.api.client.mvc.model.EditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.model.EntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.model.IntegerEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.model.StringEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.model.CategoryModelImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.model.EditorModelImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.model.IntegerEntryModelImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.model.StringEntryModelImpl;

import java.util.function.Consumer;

public final class EditorFactory {
    public static EditorModel editor(Consumer<EditorBuilder> builder) {
        EditorBuilder editor = new EditorBuilder();
        builder.accept(editor);
        return editor.build();
    }

    public static StringEntryModel stringEntry(Text name, String value, Consumer<String> apply) {
        return new StringEntryModelImpl(name, value, apply);
    }

    public static StringEntryModel stringEntry(String name, String value, Consumer<String> apply) {
        return new StringEntryModelImpl(Text.translated(name), value, apply);
    }

    public static IntegerEntryModel integerEntry(Text name, int value, Consumer<Integer> apply) {
        return new IntegerEntryModelImpl(name, value, apply);
    }

    public static IntegerEntryModel integerEntry(String name, int value, Consumer<Integer> apply) {
        return new IntegerEntryModelImpl(Text.translated(name), value, apply);
    }

    public static class EditorBuilder {
        private final EditorModel editor = new EditorModelImpl();

        public void category(String name, EntryModel... entries) {
            CategoryModelImpl category = new CategoryModelImpl(name, editor);
            category.getEntries().addAll(entries);
            editor.getCategories().add(category);
        }

        private EditorModel build() {
            return editor;
        }
    }
}

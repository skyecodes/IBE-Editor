package com.github.franckyi.ibeeditor.impl.client;

public final class EditorFactory {
    /*public static EditorModel editor(Consumer<EditorBuilder> builder) {
        EditorBuilder editor = new EditorBuilder();
        builder.accept(editor);
        return editor.build();
    }

    public static StringEntryModel stringEntry(Text name, String value, Consumer<String> apply) {
        return new StringEntryModelImpl(name, value, apply);
    }

    public static StringEntryModel stringEntry(String name, String value, Consumer<String> apply) {
        return new StringEntryModelImpl(translatedText(name), value, apply);
    }

    public static IntegerEntryModel integerEntry(Text name, int value, Consumer<Integer> apply) {
        return new IntegerEntryModelImpl(name, value, apply);
    }

    public static IntegerEntryModel integerEntry(String name, int value, Consumer<Integer> apply) {
        return new IntegerEntryModelImpl(translatedText(name), value, apply);
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
    }*/
}

package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.ibeeditor.api.client.mvc.view.EditorView;

import static com.github.franckyi.guapi.GUAPIFactory.*;
import static com.github.franckyi.ibeeditor.impl.client.EditorFactory.editor;
import static com.github.franckyi.ibeeditor.impl.client.EditorFactory.stringEntry;

public final class ItemEditor {
    public static void show(Item item) {
        GUAPI.getScreenHandler().show(scene(mvc(EditorView.class, editor(editor -> {
            editor.category(
                    "ibeeditor.gui.category.main",
                    stringEntry("ibeeditor.gui.entry.test", "Hmm", s -> {
                    })
            );
            editor.category(
                    "Another one",
                    stringEntry("Idk", "AAAAAAA", s -> {
                    })
            );
        })), true, true));
    }
}

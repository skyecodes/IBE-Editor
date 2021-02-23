package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.builder.SceneBuilder;
import com.github.franckyi.ibeeditor.api.client.mvc.view.EditorView;
import com.github.franckyi.ibeeditor.api.client.mvc.view.NBTEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.model.NBTEditorModelImpl;
import com.github.franckyi.ibeeditor.impl.common.IBEEditorConfiguration;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIFactory.*;
import static com.github.franckyi.ibeeditor.impl.client.EditorFactory.editor;
import static com.github.franckyi.ibeeditor.impl.client.EditorFactory.stringEntry;

public final class Editor {
    private static final IntegerProperty initialScale = PropertyFactory.ofInteger();

    public static void showItemEditor(Item item) {
        GUAPI.getScreenHandler().showScene(editorScene(mvc(EditorView.class, editor(editor -> {
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
        }))));
    }

    public static void showNBTEditor(ObjectTag tag, Consumer<ObjectTag> action) {
        GUAPI.getScreenHandler().showScene(editorScene(mvc(NBTEditorView.class, new NBTEditorModelImpl(tag, action))));
    }

    private static SceneBuilder editorScene(Node root) {
        return scene(root, true, true)
                .show(() -> {
                    initialScale.setValue(GameHooks.client().screen().getScale());
                    if (IBEEditorConfiguration.INSTANCE.getEditorScale() != -1) {
                        GameHooks.client().screen().setScale(IBEEditorConfiguration.INSTANCE.getEditorScale());
                    }
                })
                .hide(() -> {
                    IBEEditorConfiguration.INSTANCE.setEditorScale(GameHooks.client().screen().getScale());
                    IBEEditorConfiguration.save();
                    GameHooks.client().screen().setScale(initialScale.getValue());
                });
    }
}

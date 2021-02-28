package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.builder.SceneBuilder;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EditorView;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.view.NBTEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.model.NBTEditorModelImpl;
import com.github.franckyi.ibeeditor.impl.common.IBEEditorConfiguration;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIFactory.mvc;
import static com.github.franckyi.guapi.GUAPIFactory.scene;
import static com.github.franckyi.ibeeditor.impl.client.EditorFactory.editor;
import static com.github.franckyi.ibeeditor.impl.client.EditorFactory.stringEntry;

public final class EditorHandler {
    public static void showItemEditor(Item item) {
        GameHooks.client().getScreenHandler().showScene(editorScene(mvc(EditorView.class, editor(editor -> {
            editor.category(
                    "ibeeditor.gui.category.main",
                    stringEntry("ibeeditor.gui.entry.item_id", "Hmm", s -> {
                    })
            );
            editor.category(
                    "Another one",
                    stringEntry("Idk", "AAAAAAA", s -> {
                    })
            );
        }))));
    }

    public static void showNBTEditor(CompoundTag tag, Consumer<CompoundTag> action) {
        GameHooks.client().getScreenHandler().showScene(editorScene(mvc(NBTEditorView.class, new NBTEditorModelImpl(tag, action))));
    }

    private static SceneBuilder editorScene(Node root) {
        return scene(root, true, true).show(scene -> {
            GameHooks.client().getScreenScaling().setBaseScale(IBEEditorConfiguration.INSTANCE.getEditorScale());
            scene.widthProperty().addListener(GameHooks.client().getScreenScaling()::refresh);
            scene.heightProperty().addListener(GameHooks.client().getScreenScaling()::refresh);
        }).hide(scene -> {
            IBEEditorConfiguration.INSTANCE.setEditorScale(GameHooks.client().getScreenScaling().getScaleAndReset());
            IBEEditorConfiguration.save();
        });
    }
}

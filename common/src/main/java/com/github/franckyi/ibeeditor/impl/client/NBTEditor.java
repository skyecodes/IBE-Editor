package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.ibeeditor.api.client.mvc.view.NBTEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.model.NBTEditorModelImpl;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public final class NBTEditor {
    public static void show(ObjectTag tag, Consumer<ObjectTag> action) {
        GUAPI.getScreenHandler().showScene(scene(mvc(NBTEditorView.class, new NBTEditorModelImpl(tag, action)), true, true));
    }
}

package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.ibeeditor.client.ClientNetworkEmitter;
import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.common.EditorContext;

public interface EditorModel extends Model {
    EditorContext getContext();

    boolean saveToVault();

    default void changeEditor(EditorContext.EditorType type) {
        getContext().setEditorType(type);
        Guapi.getScreenHandler().hideScene();
        ModScreenHandler.openEditor(getContext());
    }

    default void apply() {
        ClientNetworkEmitter.sendEditorUpdate(getContext());
    }
}

package com.github.franckyi.ibeeditor.impl.client.mvc.config.view;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.api.client.mvc.config.view.ConfigEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.AbstractListEditorView;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class ConfigEditorViewImpl extends AbstractListEditorView implements ConfigEditorView {
    public ConfigEditorViewImpl() {
    }

    @Override
    protected Node createHeader() {
        return label(translated("ibeeditor.gui.settings").aqua().bold(), true).textAlign(CENTER).prefHeight(20);
    }
}

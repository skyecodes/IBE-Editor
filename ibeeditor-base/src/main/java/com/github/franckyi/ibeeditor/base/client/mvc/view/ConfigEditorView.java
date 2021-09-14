package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import static com.github.franckyi.guapi.api.GuapiHelper.CENTER;
import static com.github.franckyi.guapi.api.GuapiHelper.label;

public class ConfigEditorView extends ListEditorView {
    @Override
    public void build() {
        super.build();
        getCancelButton().setLabel(ModTexts.CLOSE);
        getCancelButton().setPrefWidth(150);
        getDoneButton().setLabel(ModTexts.SAVE);
        getDoneButton().setPrefWidth(150);
    }

    @Override
    protected Node createHeader() {
        return label(ModTexts.title(ModTexts.SETTINGS.copy())).textAlign(CENTER).prefHeight(20);
    }
}

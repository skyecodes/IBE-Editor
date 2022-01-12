package com.github.franckyi.ibeeditor.client.screen.view;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.ibeeditor.common.ModTexts;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class ConfigScreenView extends CategoryEntryScreenView {
    public ConfigScreenView() {
        super(false);
    }

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

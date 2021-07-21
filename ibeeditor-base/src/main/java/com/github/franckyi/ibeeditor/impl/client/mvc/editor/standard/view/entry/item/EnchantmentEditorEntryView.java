package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.view.entry.item;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.IntegerEditorEntryView;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class EnchantmentEditorEntryView extends IntegerEditorEntryView {
    private Button plusButton, minusButton;

    @Override
    public void build() {
        super.build();
        getRoot().setWeight(getContent(), 2);
        getRight().getChildren().add(1, vBox(2,
                plusButton = button(text("+").green()).tooltip(translated("ibeeditor.gui.editor.item.entry.add_level").with(text("+1")).green()).prefSize(16, 7),
                minusButton = button(text("-").red()).tooltip(translated("ibeeditor.gui.editor.item.entry.add_level").with(text("-1")).red()).prefSize(16, 7)
        ));
    }

    public Button getPlusButton() {
        return plusButton;
    }

    public Button getMinusButton() {
        return minusButton;
    }
}

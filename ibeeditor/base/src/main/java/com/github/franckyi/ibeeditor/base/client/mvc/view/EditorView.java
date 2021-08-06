package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.*;
import com.github.franckyi.guapi.api.node.builder.TexturedButtonBuilder;
import com.github.franckyi.ibeeditor.base.client.ModTextures;
import com.github.franckyi.ibeeditor.base.client.util.ScreenScalingManager;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class EditorView implements View {
    private VBox root;
    private TexturedButton zoomResetButton;
    private TexturedButton zoomOutButton;
    private TexturedButton zoomInButton;
    private Button cancelButton;
    private Button doneButton;
    private Label zoomLabel;

    @Override
    public void build() {
        root = vBox(root -> {
            root.spacing(5).align(CENTER).padding(5).fillWidth();
            root.add(createHeader());
            root.add(createMain(), 1);
            root.add(createFooter());
        });
        ScreenScalingManager.get().scaleProperty().addListener(this::onZoomUpdated);
        zoomResetButton.disableProperty().bind(ScreenScalingManager.get().canScaleBeResetProperty().not());
        onZoomUpdated();
    }

    protected abstract Node createHeader();

    protected Node createMain() {
        return vBox(main -> {
            main.add(createButtonBar());
            main.add(createEditor(), 1);
            main.spacing(2).fillWidth();
        });
    }

    protected Node createButtonBar() {
        return hBox(buttons -> {
            buttons.add(zoomResetButton = createButton(ModTextures.ZOOM_RESET, "ibeeditor.gui.zoom_reset").action(ScreenScalingManager.get()::restoreScale));
            buttons.add(zoomOutButton = createButton(ModTextures.ZOOM_OUT, "ibeeditor.gui.zoom_out").action(ScreenScalingManager.get()::scaleDown));
            buttons.add(zoomLabel = label().prefWidth(25).textAlign(CENTER).padding(0, 3));
            buttons.add(zoomInButton = createButton(ModTextures.ZOOM_IN, "ibeeditor.gui.zoom_in").action(ScreenScalingManager.get()::scaleUp));
            buttons.fillHeight().spacing(2).prefHeight(16).align(CENTER_RIGHT);
        });
    }

    protected abstract Node createEditor();

    protected Node createFooter() {
        return hBox(footer -> {
            footer.spacing(20).align(CENTER);
            footer.add(cancelButton = button(ModTexts.CANCEL).prefWidth(90));
            footer.add(doneButton = button(ModTexts.DONE).prefWidth(90));
        });
    }

    protected TexturedButtonBuilder createButton(IIdentifier id, String tooltipText) {
        return createButton(id, translated(tooltipText));
    }

    protected TexturedButtonBuilder createButton(IIdentifier id, Text tooltipText) {
        return texturedButton(id, 16, 16, false).tooltip(tooltipText);
    }

    protected void onZoomUpdated() {
        zoomOutButton.setDisable(!ScreenScalingManager.get().canScaleDown());
        zoomLabel.setLabel(text(ScreenScalingManager.get().getScale() == 0 ? "Auto" : Integer.toString(ScreenScalingManager.get().getScale())));
        zoomInButton.setDisable(!ScreenScalingManager.get().canScaleUp());
    }

    @Override
    public VBox getRoot() {
        return root;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public Button getDoneButton() {
        return doneButton;
    }
}

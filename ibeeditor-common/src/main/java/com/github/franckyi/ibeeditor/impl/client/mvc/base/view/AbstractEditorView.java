package com.github.franckyi.ibeeditor.impl.client.mvc.base.view;

import com.github.franckyi.guapi.api.node.*;
import com.github.franckyi.guapi.api.node.builder.TexturedButtonBuilder;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.EditorView;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.text.Text;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public abstract class AbstractEditorView implements EditorView {
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
        Minecraft.getClient().getScreenScaling().scaleProperty().addListener(this::onZoomUpdated);
        zoomResetButton.disableProperty().bind(Minecraft.getClient().getScreenScaling().canScaleBeResetProperty().not());
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
            buttons.add(zoomResetButton = createButton("ibeeditor:textures/gui/zoom_reset.png", "ibeeditor.gui.zoom_reset").action(Minecraft.getClient().getScreenScaling()::restoreScale));
            buttons.add(zoomOutButton = createButton("ibeeditor:textures/gui/zoom_out.png", "ibeeditor.gui.zoom_out").action(Minecraft.getClient().getScreenScaling()::scaleDown));
            buttons.add(zoomLabel = label().prefWidth(25).textAlign(CENTER).padding(0, 3));
            buttons.add(zoomInButton = createButton("ibeeditor:textures/gui/zoom_in.png", "ibeeditor.gui.zoom_in").action(Minecraft.getClient().getScreenScaling()::scaleUp));
            buttons.fillHeight().spacing(2).prefHeight(16).align(CENTER_RIGHT);
        });
    }

    protected abstract Node createEditor();

    protected Node createFooter() {
        return hBox(footer -> {
            footer.spacing(20).align(CENTER);
            footer.add(cancelButton = button(translated("gui.cancel").red()).prefWidth(90));
            footer.add(doneButton = button(translated("gui.done").green()).prefWidth(90));
        });
    }

    protected TexturedButtonBuilder createButton(String id, String tooltipText) {
        return createButton(id, translated(tooltipText));
    }

    protected TexturedButtonBuilder createButton(String id, Text tooltipText) {
        return texturedButton(id, 16, 16, false).tooltip(tooltipText);
    }

    protected void onZoomUpdated() {
        zoomOutButton.setDisable(!Minecraft.getClient().getScreenScaling().canScaleDown());
        zoomLabel.setLabel(text(Minecraft.getClient().getScreenScaling().getScale() == 0 ? "Auto" : Integer.toString(Minecraft.getClient().getScreenScaling().getScale())));
        zoomInButton.setDisable(!Minecraft.getClient().getScreenScaling().canScaleUp());
    }

    @Override
    public VBox getRoot() {
        return root;
    }

    @Override
    public Button getCancelButton() {
        return cancelButton;
    }

    @Override
    public Button getDoneButton() {
        return doneButton;
    }
}

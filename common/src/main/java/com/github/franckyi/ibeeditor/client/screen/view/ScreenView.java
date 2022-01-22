package com.github.franckyi.ibeeditor.client.screen.view;

import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.*;
import com.github.franckyi.guapi.api.node.builder.TexturedButtonBuilder;
import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.ModTextures;
import com.github.franckyi.ibeeditor.client.util.ScreenScalingManager;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public abstract class ScreenView implements View {
    private VBox root;
    private Label headerLabel;
    private TexturedToggleButton saveVaultButton;
    private TexturedButton openEditorButton;
    private TexturedButton openNBTEditorButton;
    private TexturedButton openSNBTEditorButton;
    private TexturedButton zoomResetButton;
    private TexturedButton zoomOutButton;
    private TexturedButton zoomInButton;
    private Button cancelButton;
    private Button doneButton;
    private Label zoomLabel;
    protected HBox buttonBar, buttonBarLeft, editorButtons, buttonBarCenter, buttonBarRight;

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

    protected Node createHeader() {
        return hBox(header -> {
            header.add(hBox().prefWidth(16));
            header.add(headerLabel = label(getHeaderLabelText()).textAlign(CENTER).prefHeight(20), 1);
            header.add(createButton(ModTextures.SETTINGS, ModTexts.SETTINGS).action(ModScreenHandler::openSettingsScreen));
            header.align(CENTER);
        });
    }

    protected MutableComponent getHeaderLabelText() {
        return EMPTY_TEXT;
    }

    protected Node createMain() {
        return vBox(main -> {
            main.add(createButtonBar());
            main.add(createEditor(), 1);
            main.spacing(2).fillWidth();
        });
    }

    protected Node createButtonBar() {
        return buttonBar = hBox(buttons -> {
            buttons.add(buttonBarLeft = hBox(editorButtons = hBox().spacing(2)).align(CENTER_LEFT).spacing(10));
            buttons.add(buttonBarCenter = hBox().align(CENTER).spacing(10), 1);
            buttons.add(buttonBarRight = hBox().align(CENTER_RIGHT).spacing(10));
            buttonBarRight.getChildren().add(hBox(zoom -> {
                zoom.add(zoomResetButton = createButton(ModTextures.ZOOM_RESET, ModTexts.ZOOM_RESET).action(ScreenScalingManager.get()::restoreScale));
                zoom.add(zoomOutButton = createButton(ModTextures.ZOOM_OUT, ModTexts.ZOOM_OUT).action(ScreenScalingManager.get()::scaleDown));
                zoom.add(zoomLabel = label().prefWidth(25).textAlign(CENTER).padding(0, 3));
                zoom.add(zoomInButton = createButton(ModTextures.ZOOM_IN, ModTexts.ZOOM_IN).action(ScreenScalingManager.get()::scaleUp));
                zoom.spacing(2).align(CENTER);
            }));
            buttons.spacing(20).prefHeight(16);
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

    protected TexturedButtonBuilder createButton(ResourceLocation id, String tooltipText) {
        return createButton(id, translated(tooltipText));
    }

    protected TexturedButtonBuilder createButton(ResourceLocation id, MutableComponent tooltipText) {
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

    public Label getHeaderLabel() {
        return headerLabel;
    }

    public TexturedToggleButton getSaveVaultButton() {
        return saveVaultButton;
    }

    public TexturedButton getOpenEditorButton() {
        return openEditorButton;
    }

    public TexturedButton getOpenNBTEditorButton() {
        return openNBTEditorButton;
    }

    public TexturedButton getOpenSNBTEditorButton() {
        return openSNBTEditorButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public Button getDoneButton() {
        return doneButton;
    }

    public void addSaveVaultButton(MutableComponent arg) {
        editorButtons.getChildren().add(saveVaultButton = texturedToggleButton(ModTextures.SAVE, 16, 16, false).tooltip(ModTexts.SAVE_VAULT)
                .action(() -> saveVaultButton.getTooltip().setAll(saveVaultButton.isActive() ? ModTexts.savedVault(arg) : ModTexts.SAVE_VAULT)));
    }

    public void addOpenEditorButton(Runnable action) {
        editorButtons.getChildren().add(openEditorButton = texturedButton(ModTextures.EDITOR, 16, 16, false)
                .tooltip(ModTexts.OPEN_EDITOR).action(action));
    }

    public void addOpenNBTEditorButton(Runnable action) {
        editorButtons.getChildren().add(openNBTEditorButton = texturedButton(ModTextures.NBT_EDITOR, 16, 16, false)
                .tooltip(ModTexts.OPEN_NBT_EDITOR).action(action));
    }

    public void addOpenSNBTEditorButton(Runnable action) {
        editorButtons.getChildren().add(openSNBTEditorButton = texturedButton(ModTextures.SNBT_EDITOR, 16, 16, false)
                .tooltip(ModTexts.OPEN_SNBT_EDITOR).action(action));
    }
}

package com.github.franckyi.ibeeditor.impl.client.mvc.editor.view;

import com.github.franckyi.guapi.api.node.*;
import com.github.franckyi.guapi.api.node.builder.TexturedButtonBuilder;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.IBEEditorMVC;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.util.common.TextFormatting;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class EditorViewImpl implements EditorView {
    private final VBox root;
    private Label headerLabel;
    private TexturedButton zoomResetButton, zoomOutButton, zoomInButton;
    private Label zoomLabel;
    private ListView<CategoryModel> categoryList;
    private ListView<EntryModel> entryList;
    private Button doneButton;
    private Button cancelButton;

    public EditorViewImpl() {
        root = vBox(root -> {
            root.spacing(5).align(CENTER).padding(5).fillWidth();
            root.add(headerLabel = label(translatedText("ibeeditor.gui.editor", AQUA, BOLD), true).textAlign(CENTER).prefHeight(20));
            root.add(vBox(main -> {
                main.add(hBox(buttons -> {
                    buttons.add(zoomResetButton = createButton("ibeeditor:textures/gui/zoom_reset.png", "Reset Zoom").action(Minecraft.getClient().getScreenScaling()::restoreScale));
                    buttons.add(zoomOutButton = createButton("ibeeditor:textures/gui/zoom_out.png", "Zoom Out").action(Minecraft.getClient().getScreenScaling()::scaleDown));
                    buttons.add(zoomLabel = label().prefWidth(25).textAlign(CENTER).padding(0, 3));
                    buttons.add(zoomInButton = createButton("ibeeditor:textures/gui/zoom_in.png", "Zoom In").action(Minecraft.getClient().getScreenScaling()::scaleUp));
                    buttons.fillHeight().spacing(2).prefHeight(16).align(CENTER_RIGHT);
                }));
                main.add(hBox(editor -> {
                    editor.add(categoryList = listView(CategoryModel.class, left -> left.itemHeight(20).padding(5).renderer(item -> mvc(IBEEditorMVC.EDITOR_CATEGORY, item))), 1);
                    editor.add(entryList = listView(EntryModel.class, right -> right.itemHeight(20).padding(5).renderer(item -> mvc(IBEEditorMVC.EDITOR_ENTRY, item))), 2);
                    editor.spacing(10).fillHeight();
                }), 1);
                main.spacing(2).fillWidth();
            }), 1);
            root.add(hBox(footer -> {
                footer.spacing(20).align(CENTER);
                footer.add(doneButton = button(translatedText("gui.done", GREEN)).prefWidth(90));
                footer.add(cancelButton = button(translatedText("gui.cancel", RED)).prefWidth(90));
            }));
        });
        Minecraft.getClient().getScreenScaling().scaleProperty().addListener(this::onZoomUpdated);
        zoomResetButton.disableProperty().bind(Minecraft.getClient().getScreenScaling().canScaleBeResetProperty().not());
        onZoomUpdated();
    }

    private TexturedButtonBuilder createButton(String id, String tooltipText, TextFormatting... tooltipFormatting) {
        return texturedButton(id, 16, 16, false)
                .prefSize(16, 16)
                .tooltip(tooltipText, tooltipFormatting);
    }

    private void onZoomUpdated() {
        zoomOutButton.setDisable(!Minecraft.getClient().getScreenScaling().canScaleDown());
        zoomLabel.setLabel(text(Minecraft.getClient().getScreenScaling().getScale() == 0 ? "Auto" : Integer.toString(Minecraft.getClient().getScreenScaling().getScale())));
        zoomInButton.setDisable(!Minecraft.getClient().getScreenScaling().canScaleUp());
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public Label getHeaderLabel() {
        return headerLabel;
    }

    @Override
    public ListView<CategoryModel> getCategoryList() {
        return categoryList;
    }

    @Override
    public ListView<EntryModel> getEntryList() {
        return entryList;
    }

    @Override
    public Button getDoneButton() {
        return doneButton;
    }

    @Override
    public Button getCancelButton() {
        return cancelButton;
    }
}

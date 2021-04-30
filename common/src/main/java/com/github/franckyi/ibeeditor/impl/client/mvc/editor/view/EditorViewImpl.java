package com.github.franckyi.ibeeditor.impl.client.mvc.editor.view;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.node.*;
import com.github.franckyi.guapi.api.node.builder.TexturedButtonBuilder;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.IBEEditorMVC;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class EditorViewImpl implements EditorView {
    private final VBox root;
    private Label headerLabel;
    private HBox buttons;
    private TexturedButton zoomResetButton, zoomOutButton, zoomInButton;
    private Label zoomLabel;
    private ListView<CategoryModel> categoryList;
    private ListView<EntryModel> entryList;
    private Button doneButton;
    private Button cancelButton;
    private final HBox textButtons;
    private Consumer<TextButtonType> onTextButtonClick;
    private final BooleanProperty showTextButtonsProperty = DataBindings.getPropertyFactory().createBooleanProperty();

    public EditorViewImpl() {
        root = vBox(root -> {
            root.spacing(5).align(CENTER).padding(5).fillWidth();
            root.add(headerLabel = label(translated("ibeeditor.gui.editor").aqua().bold(), true).textAlign(CENTER).prefHeight(20));
            root.add(vBox(main -> {
                main.add(buttons = hBox(buttons -> {
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
                footer.add(doneButton = button(translated("gui.done").green()).prefWidth(90));
                footer.add(cancelButton = button(translated("gui.cancel").red()).prefWidth(90));
            }));
        });
        textButtons = hBox(buttons -> {
            /*buttons.add(hBox(left -> {
                left.add(createButton(TextButtonType.EDIT, "ibeeditor:textures/gui/text_edit.png", "Edit Text"));
                left.add(createButton(TextButtonType.RESET, "ibeeditor:textures/gui/text_reset.png", "Reset Text to Default").disable());
                left.spacing(2);
            }));*/
            buttons.add(hBox(middle -> {
                middle.add(createButton(TextButtonType.BOLD, "ibeeditor:textures/gui/text_bold.png", "Bold"));
                middle.add(createButton(TextButtonType.ITALIC, "ibeeditor:textures/gui/text_italic.png", "Italic"));
                middle.add(createButton(TextButtonType.UNDERLINE, "ibeeditor:textures/gui/text_underline.png", "Underline"));
                middle.add(createButton(TextButtonType.STRIKETHROUGH, "ibeeditor:textures/gui/text_strikethrough.png", "Strikethrough"));
                middle.add(createButton(TextButtonType.OBFUSCATED, "ibeeditor:textures/gui/text_obfuscated.png", "Obfuscated"));
                middle.spacing(2);
            }));
            buttons.add(hBox(right -> {
                right.add(vBox(colors -> {
                    colors.add(hBox(top -> {
                        top.add(createColorButton(TextButtonType.BLACK, "ibeeditor:textures/gui/color_black.png", text("Black").gray()));
                        top.add(createColorButton(TextButtonType.DARK_BLUE, "ibeeditor:textures/gui/color_dark_blue.png", text("Dark Blue").blue()));
                        top.add(createColorButton(TextButtonType.DARK_GREEN, "ibeeditor:textures/gui/color_dark_green.png", text("Dark Green").green()));
                        top.add(createColorButton(TextButtonType.DARK_AQUA, "ibeeditor:textures/gui/color_dark_aqua.png", text("Dark Aqua").aqua()));
                        top.add(createColorButton(TextButtonType.DARK_RED, "ibeeditor:textures/gui/color_dark_red.png", text("Dark Red").red()));
                        top.add(createColorButton(TextButtonType.DARK_PURPLE, "ibeeditor:textures/gui/color_dark_purple.png", text("Dark Purple").lightPurple()));
                        top.add(createColorButton(TextButtonType.GOLD, "ibeeditor:textures/gui/color_gold.png", text("Gold").yellow()));
                        top.add(createColorButton(TextButtonType.GRAY, "ibeeditor:textures/gui/color_gray.png", text("Gray").gray()));
                        top.spacing(2);
                    }));
                    colors.add(hBox(bottom -> {
                        bottom.add(createColorButton(TextButtonType.DARK_GRAY, "ibeeditor:textures/gui/color_dark_gray.png", text("Dark Gray").gray()));
                        bottom.add(createColorButton(TextButtonType.BLUE, "ibeeditor:textures/gui/color_blue.png", text("Blue").blue()));
                        bottom.add(createColorButton(TextButtonType.GREEN, "ibeeditor:textures/gui/color_green.png", text("Green").green()));
                        bottom.add(createColorButton(TextButtonType.AQUA, "ibeeditor:textures/gui/color_aqua.png", text("Aqua").aqua()));
                        bottom.add(createColorButton(TextButtonType.RED, "ibeeditor:textures/gui/color_red.png", text("Red").red()));
                        bottom.add(createColorButton(TextButtonType.LIGHT_PURPLE, "ibeeditor:textures/gui/color_light_purple.png", text("Light Purple").lightPurple()));
                        bottom.add(createColorButton(TextButtonType.YELLOW, "ibeeditor:textures/gui/color_yellow.png", text("Yellow").yellow()));
                        bottom.add(createColorButton(TextButtonType.WHITE, "ibeeditor:textures/gui/color_white.png", text("White").white()));
                        bottom.spacing(2);
                    }));
                    colors.spacing(2);
                }));
                right.add(createButton(TextButtonType.CUSTOM, "ibeeditor:textures/gui/color_custom.png", "Custom Color..."));
                right.spacing(2);
            }));
            buttons.spacing(12);
        });
        showTextButtonsProperty().addListener(newVal -> {
            if (newVal) {
                buttons.getChildren().add(0, textButtons);
                buttons.setWeight(textButtons, 1);
            } else {
                buttons.getChildren().remove(textButtons);
            }
        });
        Minecraft.getClient().getScreenScaling().scaleProperty().addListener(this::onZoomUpdated);
        zoomResetButton.disableProperty().bind(Minecraft.getClient().getScreenScaling().canScaleBeResetProperty().not());
        onZoomUpdated();
    }

    private TexturedButtonBuilder createButton(String id, String tooltipText) {
        return createButton(id, text(tooltipText));
    }

    private TexturedButtonBuilder createButton(String id, Text tooltipText) {
        return texturedButton(id, 16, 16, false)
                .prefSize(16, 16)
                .tooltip(tooltipText);
    }

    private TexturedButtonBuilder createButton(TextButtonType type, String id, String tooltipText) {
        return createButton(id, tooltipText)
                .action((e) -> {
                    if (onTextButtonClick != null) {
                        e.consume();
                        onTextButtonClick.accept(type);
                    }
                });
    }

    private TexturedButtonBuilder createColorButton(TextButtonType type, String id, Text tooltipText) {
        return texturedButton(id, 7, 7, false)
                .prefSize(7, 7)
                .tooltip(tooltipText)
                .action((e) -> {
                    if (onTextButtonClick != null) {
                        e.consume();
                        onTextButtonClick.accept(type);
                    }
                });
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

    @Override
    public BooleanProperty showTextButtonsProperty() {
        return showTextButtonsProperty;
    }

    @Override
    public void setOnTextButtonClick(Consumer<TextButtonType> action) {
        this.onTextButtonClick = action;
    }
}

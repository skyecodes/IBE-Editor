package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.view;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.builder.TexturedButtonBuilder;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.view.StandardEditorView;
import com.github.franckyi.ibeeditor.impl.client.EditorScreenHandler;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.AbstractListEditorView;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class StandardEditorViewImpl extends AbstractListEditorView implements StandardEditorView {
    private Label headerLabel;
    private HBox buttons;
    private final HBox textButtons;
    private Consumer<TextButtonType> onTextButtonClick;
    private final BooleanProperty showTextButtonsProperty = DataBindings.getPropertyFactory().createBooleanProperty();

    public StandardEditorViewImpl() {
        textButtons = hBox(buttons -> {
            buttons.add(hBox(middle -> {
                middle.add(createTextButton(TextButtonType.BOLD, "ibeeditor:textures/gui/text_bold.png", "Bold"));
                middle.add(createTextButton(TextButtonType.ITALIC, "ibeeditor:textures/gui/text_italic.png", "Italic"));
                middle.add(createTextButton(TextButtonType.UNDERLINE, "ibeeditor:textures/gui/text_underline.png", "Underline"));
                middle.add(createTextButton(TextButtonType.STRIKETHROUGH, "ibeeditor:textures/gui/text_strikethrough.png", "Strikethrough"));
                middle.add(createTextButton(TextButtonType.OBFUSCATED, "ibeeditor:textures/gui/text_obfuscated.png", "Obfuscated"));
                middle.spacing(2);
            }));
            buttons.add(hBox(right -> {
                right.add(vBox(colors -> {
                    colors.add(hBox(top -> {
                        top.add(createTextColorButton(TextButtonType.BLACK, "ibeeditor:textures/gui/color_black.png", text("Black").gray()));
                        top.add(createTextColorButton(TextButtonType.DARK_BLUE, "ibeeditor:textures/gui/color_dark_blue.png", text("Dark Blue").blue()));
                        top.add(createTextColorButton(TextButtonType.DARK_GREEN, "ibeeditor:textures/gui/color_dark_green.png", text("Dark Green").green()));
                        top.add(createTextColorButton(TextButtonType.DARK_AQUA, "ibeeditor:textures/gui/color_dark_aqua.png", text("Dark Aqua").aqua()));
                        top.add(createTextColorButton(TextButtonType.DARK_RED, "ibeeditor:textures/gui/color_dark_red.png", text("Dark Red").red()));
                        top.add(createTextColorButton(TextButtonType.DARK_PURPLE, "ibeeditor:textures/gui/color_dark_purple.png", text("Dark Purple").lightPurple()));
                        top.add(createTextColorButton(TextButtonType.GOLD, "ibeeditor:textures/gui/color_gold.png", text("Gold").yellow()));
                        top.add(createTextColorButton(TextButtonType.GRAY, "ibeeditor:textures/gui/color_gray.png", text("Gray").gray()));
                        top.spacing(2);
                    }));
                    colors.add(hBox(bottom -> {
                        bottom.add(createTextColorButton(TextButtonType.DARK_GRAY, "ibeeditor:textures/gui/color_dark_gray.png", text("Dark Gray").gray()));
                        bottom.add(createTextColorButton(TextButtonType.BLUE, "ibeeditor:textures/gui/color_blue.png", text("Blue").blue()));
                        bottom.add(createTextColorButton(TextButtonType.GREEN, "ibeeditor:textures/gui/color_green.png", text("Green").green()));
                        bottom.add(createTextColorButton(TextButtonType.AQUA, "ibeeditor:textures/gui/color_aqua.png", text("Aqua").aqua()));
                        bottom.add(createTextColorButton(TextButtonType.RED, "ibeeditor:textures/gui/color_red.png", text("Red").red()));
                        bottom.add(createTextColorButton(TextButtonType.LIGHT_PURPLE, "ibeeditor:textures/gui/color_light_purple.png", text("Light Purple").lightPurple()));
                        bottom.add(createTextColorButton(TextButtonType.YELLOW, "ibeeditor:textures/gui/color_yellow.png", text("Yellow").yellow()));
                        bottom.add(createTextColorButton(TextButtonType.WHITE, "ibeeditor:textures/gui/color_white.png", text("White").white()));
                        bottom.spacing(2);
                    }));
                    colors.spacing(2);
                }));
                right.add(createTextButton(TextButtonType.CUSTOM, "ibeeditor:textures/gui/color_custom.png", "Custom Color..."));
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
    }

    @Override
    protected Node createHeader() {
        return hBox(header -> {
            header.add(hBox().prefWidth(16));
            header.add(headerLabel = label().shadow().textAlign(CENTER).prefHeight(20), 1);
            header.add(createButton("ibeeditor:textures/gui/settings.png", "ibeeditor.gui.settings").action(EditorScreenHandler::openSettings));
        });
    }

    @Override
    protected Node createButtonBar() {
        return buttons = (HBox) super.createButtonBar();
    }

    private TexturedButtonBuilder createTextButton(TextButtonType type, String id, String tooltipText) {
        return createButton(id, tooltipText)
                .action((e) -> {
                    if (onTextButtonClick != null) {
                        e.consume();
                        onTextButtonClick.accept(type);
                    }
                });
    }

    private TexturedButtonBuilder createTextColorButton(TextButtonType type, String id, Text tooltipText) {
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

    @Override
    public Label getHeaderLabel() {
        return headerLabel;
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

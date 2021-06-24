package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.view;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.builder.TexturedButtonBuilder;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.view.StandardEditorView;
import com.github.franckyi.ibeeditor.impl.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.AbstractListEditorView;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.builder.TranslatedTextBuilder;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class StandardEditorViewImpl extends AbstractListEditorView implements StandardEditorView {
    private TranslatedTextBuilder headerText;
    private HBox buttons;
    private final HBox textButtons;
    private Consumer<TextButtonType> onTextButtonClick;
    private final BooleanProperty showTextButtonsProperty = DataBindings.getPropertyFactory().createBooleanProperty();

    public StandardEditorViewImpl() {
        textButtons = hBox(buttons -> {
            buttons.add(hBox(middle -> {
                middle.add(createTextButton(TextButtonType.BOLD, "ibeeditor:textures/gui/text_bold.png", "ibeeditor.gui.bold"));
                middle.add(createTextButton(TextButtonType.ITALIC, "ibeeditor:textures/gui/text_italic.png", "ibeeditor.gui.italic"));
                middle.add(createTextButton(TextButtonType.UNDERLINE, "ibeeditor:textures/gui/text_underline.png", "ibeeditor.gui.underline"));
                middle.add(createTextButton(TextButtonType.STRIKETHROUGH, "ibeeditor:textures/gui/text_strikethrough.png", "ibeeditor.gui.strikethough"));
                middle.add(createTextButton(TextButtonType.OBFUSCATED, "ibeeditor:textures/gui/text_obfuscated.png", "ibeeditor.gui.obfuscated"));
                middle.spacing(2);
            }));
            buttons.add(hBox(right -> {
                right.add(vBox(colors -> {
                    colors.add(hBox(top -> {
                        top.add(createTextColorButton(TextButtonType.BLACK, "ibeeditor:textures/gui/color_black.png", translated("ibeeditor.gui.black").gray()));
                        top.add(createTextColorButton(TextButtonType.DARK_BLUE, "ibeeditor:textures/gui/color_dark_blue.png", translated("ibeeditor.gui.dark_blue").blue()));
                        top.add(createTextColorButton(TextButtonType.DARK_GREEN, "ibeeditor:textures/gui/color_dark_green.png", translated("ibeeditor.gui.dark_green").green()));
                        top.add(createTextColorButton(TextButtonType.DARK_AQUA, "ibeeditor:textures/gui/color_dark_aqua.png", translated("ibeeditor.gui.dark_aqua").aqua()));
                        top.add(createTextColorButton(TextButtonType.DARK_RED, "ibeeditor:textures/gui/color_dark_red.png", translated("ibeeditor.gui.dark_red").red()));
                        top.add(createTextColorButton(TextButtonType.DARK_PURPLE, "ibeeditor:textures/gui/color_dark_purple.png", translated("ibeeditor.gui.dark_purple").lightPurple()));
                        top.add(createTextColorButton(TextButtonType.GOLD, "ibeeditor:textures/gui/color_gold.png", translated("ibeeditor.gui.gold").yellow()));
                        top.add(createTextColorButton(TextButtonType.GRAY, "ibeeditor:textures/gui/color_gray.png", translated("ibeeditor.gui.gray").gray()));
                        top.spacing(2);
                    }));
                    colors.add(hBox(bottom -> {
                        bottom.add(createTextColorButton(TextButtonType.DARK_GRAY, "ibeeditor:textures/gui/color_dark_gray.png", translated("ibeeditor.gui.dark_gray").gray()));
                        bottom.add(createTextColorButton(TextButtonType.BLUE, "ibeeditor:textures/gui/color_blue.png", translated("ibeeditor.gui.blue").blue()));
                        bottom.add(createTextColorButton(TextButtonType.GREEN, "ibeeditor:textures/gui/color_green.png", translated("ibeeditor.gui.green").green()));
                        bottom.add(createTextColorButton(TextButtonType.AQUA, "ibeeditor:textures/gui/color_aqua.png", translated("ibeeditor.gui.aqua").aqua()));
                        bottom.add(createTextColorButton(TextButtonType.RED, "ibeeditor:textures/gui/color_red.png", translated("ibeeditor.gui.ref").red()));
                        bottom.add(createTextColorButton(TextButtonType.LIGHT_PURPLE, "ibeeditor:textures/gui/color_light_purple.png", translated("ibeeditor.gui.light_purple").lightPurple()));
                        bottom.add(createTextColorButton(TextButtonType.YELLOW, "ibeeditor:textures/gui/color_yellow.png", translated("ibeeditor.gui.yellow").yellow()));
                        bottom.add(createTextColorButton(TextButtonType.WHITE, "ibeeditor:textures/gui/color_white.png", translated("ibeeditor.gui.white").white()));
                        bottom.spacing(2);
                    }));
                    colors.spacing(2);
                }));
                right.add(createTextButton(TextButtonType.CUSTOM, "ibeeditor:textures/gui/color_custom.png", "ibeeditor.gui.custom_color"));
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
            header.add(label(headerText = translated("ibeeditor.gui.editor_title").aqua().bold(), true).textAlign(CENTER).prefHeight(20), 1);
            header.add(createButton("ibeeditor:textures/gui/settings.png", "ibeeditor.gui.settings").action(ModScreenHandler::openSettingsScreen));
            header.align(CENTER);
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
    public TranslatedTextBuilder getHeaderText() {
        return headerText;
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

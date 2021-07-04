package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.view;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.node.TexturedToggleButton;
import com.github.franckyi.guapi.api.node.builder.TexturedButtonBuilder;
import com.github.franckyi.guapi.api.node.builder.TexturedToggleButtonBuilder;
import com.github.franckyi.guapi.util.Color;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.view.StandardEditorView;
import com.github.franckyi.ibeeditor.impl.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.AbstractListEditorView;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.builder.TranslatedTextBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class StandardEditorViewImpl extends AbstractListEditorView implements StandardEditorView {
    private TexturedToggleButton boldButton, italicButton, underlineButton, strikethroughButton, obfuscatedButton,
            blackButton, darkBlueButton, darkGreenButton, darkAquaButton, darkRedButton, darkPurpleButton, goldButton,
            grayButton, darkGrayButton, blueButton, greenButton, aquaButton, redButton, lightPurpleButton,
            yellowButton, whiteButton;
    private List<TexturedToggleButton> colorButtons;
    private TexturedButton customColorButton;
    private TranslatedTextBuilder headerText;
    private HBox buttons;
    private final HBox textButtons;
    private Consumer<TextButtonType> onTextButtonClick;
    private final BooleanProperty showTextButtonsProperty = DataBindings.getPropertyFactory().createBooleanProperty();

    public StandardEditorViewImpl() {
        int invertBorder = Color.rgba(0, 0, 0, 0.6);
        colorButtons = Arrays.asList(
                blackButton = createTextColorButton(TextButtonType.BLACK, "ibeeditor:textures/gui/color_black.png", translated("ibeeditor.gui.black").gray()),
                darkBlueButton = createTextColorButton(TextButtonType.DARK_BLUE, "ibeeditor:textures/gui/color_dark_blue.png", translated("ibeeditor.gui.dark_blue").blue()),
                darkGreenButton = createTextColorButton(TextButtonType.DARK_GREEN, "ibeeditor:textures/gui/color_dark_green.png", translated("ibeeditor.gui.dark_green").green()),
                darkAquaButton = createTextColorButton(TextButtonType.DARK_AQUA, "ibeeditor:textures/gui/color_dark_aqua.png", translated("ibeeditor.gui.dark_aqua").aqua()),
                darkRedButton = createTextColorButton(TextButtonType.DARK_RED, "ibeeditor:textures/gui/color_dark_red.png", translated("ibeeditor.gui.dark_red").red()),
                darkPurpleButton = createTextColorButton(TextButtonType.DARK_PURPLE, "ibeeditor:textures/gui/color_dark_purple.png", translated("ibeeditor.gui.dark_purple").lightPurple()),
                goldButton = createTextColorButton(TextButtonType.GOLD, "ibeeditor:textures/gui/color_gold.png", translated("ibeeditor.gui.gold").yellow()),
                grayButton = createTextColorButton(TextButtonType.GRAY, "ibeeditor:textures/gui/color_gray.png", translated("ibeeditor.gui.gray").gray()),
                darkGrayButton = createTextColorButton(TextButtonType.DARK_GRAY, "ibeeditor:textures/gui/color_dark_gray.png", translated("ibeeditor.gui.dark_gray").gray()),
                blueButton = createTextColorButton(TextButtonType.BLUE, "ibeeditor:textures/gui/color_blue.png", translated("ibeeditor.gui.blue").blue()),
                greenButton = createTextColorButton(TextButtonType.GREEN, "ibeeditor:textures/gui/color_green.png", translated("ibeeditor.gui.green").green()),
                aquaButton = createTextColorButton(TextButtonType.AQUA, "ibeeditor:textures/gui/color_aqua.png", translated("ibeeditor.gui.aqua").aqua()),
                redButton = createTextColorButton(TextButtonType.RED, "ibeeditor:textures/gui/color_red.png", translated("ibeeditor.gui.ref").red()),
                lightPurpleButton = createTextColorButton(TextButtonType.LIGHT_PURPLE, "ibeeditor:textures/gui/color_light_purple.png", translated("ibeeditor.gui.light_purple").lightPurple()),
                yellowButton = createTextColorButton(TextButtonType.YELLOW, "ibeeditor:textures/gui/color_yellow.png", translated("ibeeditor.gui.yellow").yellow()),
                whiteButton = createTextColorButton(TextButtonType.WHITE, "ibeeditor:textures/gui/color_white.png", translated("ibeeditor.gui.white").white()).borderColor(invertBorder)
        );
        textButtons = hBox(buttons -> {
            buttons.add(hBox(middle -> {
                middle.add(boldButton = createTextButton(TextButtonType.BOLD, "ibeeditor:textures/gui/text_bold.png", "ibeeditor.gui.bold"));
                middle.add(italicButton = createTextButton(TextButtonType.ITALIC, "ibeeditor:textures/gui/text_italic.png", "ibeeditor.gui.italic"));
                middle.add(underlineButton = createTextButton(TextButtonType.UNDERLINE, "ibeeditor:textures/gui/text_underline.png", "ibeeditor.gui.underline"));
                middle.add(strikethroughButton = createTextButton(TextButtonType.STRIKETHROUGH, "ibeeditor:textures/gui/text_strikethrough.png", "ibeeditor.gui.strikethough"));
                middle.add(obfuscatedButton = createTextButton(TextButtonType.OBFUSCATED, "ibeeditor:textures/gui/text_obfuscated.png", "ibeeditor.gui.obfuscated"));
                middle.spacing(2);
            }));
            buttons.add(hBox(right -> {
                right.add(vBox(colors -> {
                    colors.add(hBox(2, colorButtons.subList(0, colorButtons.size() / 2)));
                    colors.add(hBox(2, colorButtons.subList(colorButtons.size() / 2, colorButtons.size())));
                    colors.spacing(2);
                }));
                right.add(customColorButton = createCustomButton());
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

    private TexturedToggleButtonBuilder createTextButton(TextButtonType type, String id, String tooltipText) {
        return texturedToggleButton(id, 16, 16, false)
                .prefSize(16, 16)
                .tooltip(translated(tooltipText))
                .action(e -> onTextButtonClick(e, type));
    }

    private TexturedToggleButtonBuilder createTextColorButton(TextButtonType type, String id, Text tooltipText) {
        return texturedToggleButton(id, 7, 7, false)
                .prefSize(7, 7)
                .tooltip(tooltipText)
                .action(e -> onTextButtonClick(e, type));
    }

    private TexturedButtonBuilder createCustomButton() {
        return texturedButton("ibeeditor:textures/gui/color_custom.png", 16, 16, false)
                .prefSize(16, 16)
                .tooltip(translated("ibeeditor.gui.custom_color"))
                .action(e -> onTextButtonClick(e, TextButtonType.CUSTOM_COLOR));
    }

    private void onTextButtonClick(MouseButtonEvent e, TextButtonType type) {
        if (onTextButtonClick != null) {
            e.consume();
            if (type.isColor()) {
                colorButtons.stream().filter(button -> button != e.getTarget()).forEach(button -> button.setActive(false));
            }
            onTextButtonClick.accept(type);
        }
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

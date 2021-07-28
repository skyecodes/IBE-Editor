package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.text.builder.TranslatedTextBuilder;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.node.builder.TexturedButtonBuilder;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.util.texteditor.StyleType;
import com.github.franckyi.ibeeditor.base.client.util.texteditor.TextEditorActionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class StandardEditorView extends ListEditorView {
    private List<TexturedButton> colorButtons;
    private TexturedButton customColorButton;
    private TexturedButtonBuilder chooseCustomColorButton;
    private HBox textButtons;
    private TranslatedTextBuilder headerText;
    private HBox buttons;
    private final BooleanProperty showTextButtonsProperty = DataBindings.getPropertyFactory().createBooleanProperty();
    private Supplier<TextEditorActionHandler> textEditorSupplier;

    @Override
    public void build() {
        super.build();
        colorButtons = Arrays.asList(
                createTextColorButton(BLACK, "ibeeditor:textures/gui/color_black.png", translated("ibeeditor.gui.black").gray()),
                createTextColorButton(DARK_BLUE, "ibeeditor:textures/gui/color_dark_blue.png", translated("ibeeditor.gui.dark_blue").blue()),
                createTextColorButton(DARK_GREEN, "ibeeditor:textures/gui/color_dark_green.png", translated("ibeeditor.gui.dark_green").green()),
                createTextColorButton(DARK_AQUA, "ibeeditor:textures/gui/color_dark_aqua.png", translated("ibeeditor.gui.dark_aqua").aqua()),
                createTextColorButton(DARK_RED, "ibeeditor:textures/gui/color_dark_red.png", translated("ibeeditor.gui.dark_red").red()),
                createTextColorButton(DARK_PURPLE, "ibeeditor:textures/gui/color_dark_purple.png", translated("ibeeditor.gui.dark_purple").lightPurple()),
                createTextColorButton(GOLD, "ibeeditor:textures/gui/color_gold.png", translated("ibeeditor.gui.gold").yellow()),
                createTextColorButton(GRAY, "ibeeditor:textures/gui/color_gray.png", translated("ibeeditor.gui.gray").gray()),
                createTextColorButton(DARK_GRAY, "ibeeditor:textures/gui/color_dark_gray.png", translated("ibeeditor.gui.dark_gray").gray()),
                createTextColorButton(BLUE, "ibeeditor:textures/gui/color_blue.png", translated("ibeeditor.gui.blue").blue()),
                createTextColorButton(GREEN, "ibeeditor:textures/gui/color_green.png", translated("ibeeditor.gui.green").green()),
                createTextColorButton(AQUA, "ibeeditor:textures/gui/color_aqua.png", translated("ibeeditor.gui.aqua").aqua()),
                createTextColorButton(RED, "ibeeditor:textures/gui/color_red.png", translated("ibeeditor.gui.red").red()),
                createTextColorButton(LIGHT_PURPLE, "ibeeditor:textures/gui/color_light_purple.png", translated("ibeeditor.gui.light_purple").lightPurple()),
                createTextColorButton(YELLOW, "ibeeditor:textures/gui/color_yellow.png", translated("ibeeditor.gui.yellow").yellow()),
                createTextColorButton(WHITE, "ibeeditor:textures/gui/color_white.png", translated("ibeeditor.gui.white").white())
        );
        textButtons = hBox(buttons -> {
            buttons.add(hBox(middle -> {
                middle.add(createTextButton(StyleType.BOLD, "ibeeditor:textures/gui/text_bold.png", "ibeeditor.gui.bold"));
                middle.add(createTextButton(StyleType.ITALIC, "ibeeditor:textures/gui/text_italic.png", "ibeeditor.gui.italic"));
                middle.add(createTextButton(StyleType.UNDERLINED, "ibeeditor:textures/gui/text_underline.png", "ibeeditor.gui.underline"));
                middle.add(createTextButton(StyleType.STRIKETHROUGH, "ibeeditor:textures/gui/text_strikethrough.png", "ibeeditor.gui.strikethough"));
                middle.add(createTextButton(StyleType.OBFUSCATED, "ibeeditor:textures/gui/text_obfuscated.png", "ibeeditor.gui.obfuscated"));
                middle.spacing(2);
            }));
            buttons.add(hBox(right -> {
                right.add(texturedButton("ibeeditor:textures/gui/reset_color.png", 7, 16, false)
                        .tooltip(translated("ibeeditor.gui.reset_color"))
                        .action(e -> {
                            if (textEditorSupplier != null) {
                                e.consume();
                                textEditorSupplier.get().removeColorFormatting();
                            }
                        }));
                right.add(vBox(colors -> {
                    colors.add(hBox(2, colorButtons.subList(0, colorButtons.size() / 2)));
                    colors.add(hBox(2, colorButtons.subList(colorButtons.size() / 2, colorButtons.size())));
                    colors.spacing(2);
                }));
                right.add(customColorButton = texturedButton(null, 1, 1, false)
                        .prefSize(7, 16).visible(false)
                        .tooltip(translated("ibeeditor.gui.custom_color")));
                right.add(chooseCustomColorButton = texturedButton("ibeeditor:textures/gui/color_custom.png", 16, 16, false)
                        .tooltip(translated("ibeeditor.gui.choose_custom_color")));
                right.spacing(2);
            }));
            buttons.spacing(12).padding(right(20));
        }).align(CENTER_RIGHT);
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

    private TexturedButtonBuilder createTextButton(StyleType target, String id, String tooltipText) {
        return texturedButton(id, 16, 16, false)
                .tooltip(translated(tooltipText))
                .action(e -> {
                    if (textEditorSupplier != null) {
                        e.consume();
                        textEditorSupplier.get().addStyleFormatting(target);
                    }
                });
    }

    private TexturedButtonBuilder createTextColorButton(String color, String id, Text tooltipText) {
        return texturedButton(id, 7, 7, false)
                .tooltip(tooltipText)
                .action(e -> {
                    if (textEditorSupplier != null) {
                        e.consume();
                        textEditorSupplier.get().addColorFormatting(color);
                    }
                });
    }

    public TexturedButton getCustomColorButton() {
        return customColorButton;
    }

    public TexturedButton getChooseCustomColorButton() {
        return chooseCustomColorButton;
    }

    public TranslatedTextBuilder getHeaderText() {
        return headerText;
    }

    public boolean isShowTextButtons() {
        return showTextButtonsProperty().getValue();
    }

    public BooleanProperty showTextButtonsProperty() {
        return showTextButtonsProperty;
    }

    public void setShowTextButtons(boolean value) {
        showTextButtonsProperty().setValue(value);
    }

    public void setTextEditorSupplier(Supplier<TextEditorActionHandler> supplier) {
        textEditorSupplier = supplier;
    }
}

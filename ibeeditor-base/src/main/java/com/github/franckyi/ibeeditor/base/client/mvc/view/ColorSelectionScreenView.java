package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.Slider;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public abstract class ColorSelectionScreenView extends EditorView {
    private Slider redSlider, greenSlider, blueSlider;
    private TextField hexField;
    private HBox exampleBox;

    @Override
    protected IText getHeaderLabelText() {
        return ModTexts.title(ModTexts.choose(ModTexts.CUSTOM_COLOR));
    }

    @Override
    protected Node createEditor() {
        return hBox(editor -> {
            editor.add(vBox(), 1);
            editor.add(vBox(center -> {
                center.add(vBox(rgb -> {
                    rgb.add(hBox(labels -> {
                        labels.add(label(ModTexts.RED_COLOR), 1);
                        labels.add(label(ModTexts.GREEN_COLOR), 1);
                        labels.add(label(ModTexts.BLUE_COLOR), 1);
                        labels.spacing(4);
                    }));
                    rgb.add(hBox(fields -> {
                        fields.add(redSlider = colorSlider(), 1);
                        fields.add(greenSlider = colorSlider(), 1);
                        fields.add(blueSlider = colorSlider(), 1);
                        fields.spacing(4);
                    }));
                    rgb.fillWidth().spacing(4);
                }));
                center.add(vBox(hex -> {
                    hex.add(hBox(labels -> {
                        labels.add(label(ModTexts.Literal.HEX), 1);
                        labels.add(hBox(), 1);
                        labels.add(hBox(), 1);
                        labels.spacing(4);
                    }));
                    hex.add(hBox(fields -> {
                        fields.add(hexField = textField().prefHeight(16), 1);
                        fields.add(createExample(), 1);
                        fields.add(exampleBox = hBox().prefHeight(16), 1);
                        fields.spacing(4).align(CENTER);
                    }));
                    hex.fillWidth().spacing(4);
                }));
                center.fillWidth().spacing(10).align(CENTER);
            }), 4);
            editor.add(vBox(), 1);
            editor.fillHeight();
        });
    }

    private Slider colorSlider() {
        return slider(0, 0, 255, 1);
    }

    protected abstract Node createExample();

    public Slider getRedSlider() {
        return redSlider;
    }

    public Slider getGreenSlider() {
        return greenSlider;
    }

    public Slider getBlueSlider() {
        return blueSlider;
    }

    public TextField getHexField() {
        return hexField;
    }

    public HBox getExampleBox() {
        return exampleBox;
    }
}

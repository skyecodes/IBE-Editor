package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.node.TextArea;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractTextArea extends AbstractTextField implements TextArea {
    private final BooleanProperty wrapTextProperty = BooleanProperty.create();
    private final IntegerProperty scrollXProperty = IntegerProperty.create();
    private final IntegerProperty scrollYProperty = IntegerProperty.create();
    private List<String> lines;

    protected AbstractTextArea() {
        init();
    }

    protected AbstractTextArea(String value) {
        super(value);
        init();
    }

    protected AbstractTextArea(String label, String value) {
        super(label, value);
        init();
    }

    protected AbstractTextArea(IText label, String value) {
        super(label, value);
        init();
    }

    private void init() {
        textProperty().addListener(this::updateLines);
        updateLines();
    }

    private void updateLines() {
        lines = Arrays.asList(getText().split("\n"));
    }

    @Override
    public BooleanProperty wrapTextProperty() {
        return wrapTextProperty;
    }

    @Override
    public IntegerProperty scrollXProperty() {
        return scrollXProperty;
    }

    @Override
    public IntegerProperty scrollYProperty() {
        return scrollYProperty;
    }

    @Override
    public List<String> getLines() {
        return lines;
    }
}

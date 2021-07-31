package com.github.franckyi.guapi.base.node;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.TextArea;

public abstract class AbstractTextArea extends AbstractTextField implements TextArea {
    protected AbstractTextArea() {
    }

    protected AbstractTextArea(String value) {
        super(value);
    }

    protected AbstractTextArea(String label, String value) {
        super(label, value);
    }

    protected AbstractTextArea(Text label, String value) {
        super(label, value);
    }
}

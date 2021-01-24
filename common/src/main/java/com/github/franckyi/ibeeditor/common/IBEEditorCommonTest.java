package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.guapi.common.GUAPI;
import com.github.franckyi.guapi.common.Scene;
import com.github.franckyi.guapi.common.node.Label;

public class IBEEditorCommonTest {
    public static void openEditor(String text) {
        GUAPI.getScreenHandler().show(new Scene(new Label(text)));
    }
}

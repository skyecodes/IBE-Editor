package com.github.franckyi.ibeeditor.impl.client.mvc.editor;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.EntryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EntryView;

public class EntryMVCImpl implements EntryMVC {
    public static final EntryMVC INSTANCE = new EntryMVCImpl();

    @Override
    public EntryView createViewAndBind(EntryModel model) {
        return null;
    }
}

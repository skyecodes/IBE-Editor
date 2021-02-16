package com.github.franckyi.ibeeditor.api.client.mvc.model;

import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.ibeeditor.api.client.mvc.view.StringEntryView;

public interface StringEntryModel extends ValueEntryModel<String> {
    @Override
    StringProperty valueProperty();

    @Override
    default Class<? extends View> getDefaultViewType() {
        return StringEntryView.class;
    }
}

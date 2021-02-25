package com.github.franckyi.ibeeditor.api.client.mvc.model;

import com.github.franckyi.gamehooks.api.common.Text;
import com.github.franckyi.guapi.api.mvc.View;

public interface EntryModel {
    Text getLabel();

    Class<? extends View> getDefaultViewType();

    void apply();
}

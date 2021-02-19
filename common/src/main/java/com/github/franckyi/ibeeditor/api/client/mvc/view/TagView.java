package com.github.franckyi.ibeeditor.api.client.mvc.view;

import com.github.franckyi.gamehooks.util.common.tag.Tag;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.ImageView;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.TextField;

public interface TagView extends View {
    ImageView getIcon();

    TextField getNameField();

    Label getSeparator();

    TextField getValueField();

    void updateIconFromTag(Tag<?> tag);
}

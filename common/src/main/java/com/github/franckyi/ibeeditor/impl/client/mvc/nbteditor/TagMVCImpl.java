package com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor;

import com.github.franckyi.guapi.util.Predicates;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.TagMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.controller.TagController;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.TagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.view.TagView;
import com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.controller.TagControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.view.TagViewImpl;
import com.github.franckyi.minecraft.api.common.tag.Tag;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public final class TagMVCImpl implements TagMVC {
    public static final TagMVC INSTANCE = new TagMVCImpl();

    private TagMVCImpl() {
    }

    @Override
    public TagView createViewAndBind(TagModel model) {
        TagView view = createView(model.getTagType());
        TagController controller = new TagControllerImpl(model, view);
        controller.bind();
        return view;
    }

    private TagView createView(byte tagType) {
        switch (tagType) {
            case Tag.BYTE_ID:
                return new TagViewImpl("byte_tag", text("Byte").blue(), Predicates.IS_BYTE);
            case Tag.SHORT_ID:
                return new TagViewImpl("short_tag", text("Short").green(), Predicates.IS_SHORT);
            case Tag.INT_ID:
                return new TagViewImpl("int_tag", text("Int").aqua(), Predicates.IS_INT);
            case Tag.LONG_ID:
                return new TagViewImpl("long_tag", text("Long").red(), Predicates.IS_LONG);
            case Tag.FLOAT_ID:
                return new TagViewImpl("float_tag", text("Float").lightPurple(), Predicates.IS_FLOAT);
            case Tag.DOUBLE_ID:
                return new TagViewImpl("double_tag", text("Double").yellow(), Predicates.IS_DOUBLE);
            case Tag.BYTE_ARRAY_ID:
                return new TagViewImpl("byte_array_tag", text("Byte Array").blue());
            case Tag.STRING_ID:
                return new TagViewImpl("string_tag", text("String").gray());
            case Tag.LIST_ID:
                return new TagViewImpl("list_tag", text("List").green());
            case Tag.COMPOUND_ID:
                return new TagViewImpl("compound_tag", text("Compound").lightPurple());
            case Tag.INT_ARRAY_ID:
                return new TagViewImpl("int_array_tag", text("Int Array").aqua());
            case Tag.LONG_ARRAY_ID:
                return new TagViewImpl("long_array_tag", text("Long Array").red());
            default:
                return null;
        }
    }
}

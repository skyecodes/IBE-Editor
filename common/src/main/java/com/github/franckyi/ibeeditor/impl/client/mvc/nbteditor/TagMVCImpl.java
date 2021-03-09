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
                return new TagViewImpl("byte_tag", text("Byte", BLUE), Predicates.IS_BYTE);
            case Tag.SHORT_ID:
                return new TagViewImpl("short_tag", text("Short", GREEN), Predicates.IS_SHORT);
            case Tag.INT_ID:
                return new TagViewImpl("int_tag", text("Int", AQUA), Predicates.IS_INT);
            case Tag.LONG_ID:
                return new TagViewImpl("long_tag", text("Long", RED), Predicates.IS_LONG);
            case Tag.FLOAT_ID:
                return new TagViewImpl("float_tag", text("Float", LIGHT_PURPLE), Predicates.IS_FLOAT);
            case Tag.DOUBLE_ID:
                return new TagViewImpl("double_tag", text("Double", YELLOW), Predicates.IS_DOUBLE);
            case Tag.BYTE_ARRAY_ID:
                return new TagViewImpl("byte_array_tag", text("Byte Array", BLUE));
            case Tag.STRING_ID:
                return new TagViewImpl("string_tag", text("String", GRAY));
            case Tag.LIST_ID:
                return new TagViewImpl("list_tag", text("List", GREEN));
            case Tag.COMPOUND_ID:
                return new TagViewImpl("object_tag", text("Compound", LIGHT_PURPLE));
            case Tag.INT_ARRAY_ID:
                return new TagViewImpl("int_array_tag", text("Int Array", AQUA));
            case Tag.LONG_ARRAY_ID:
                return new TagViewImpl("long_array_tag", text("Long Array", RED));
            default:
                return null;
        }
    }
}

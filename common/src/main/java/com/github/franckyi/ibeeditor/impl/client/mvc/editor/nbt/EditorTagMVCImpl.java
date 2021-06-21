package com.github.franckyi.ibeeditor.impl.client.mvc.editor.nbt;

import com.github.franckyi.guapi.util.Predicates;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.EditorTagMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.controller.EditorTagController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.model.EditorTagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.view.EditorTagView;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.nbt.controller.EditorTagControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.nbt.view.EditorTagViewImpl;
import com.github.franckyi.minecraft.api.common.tag.Tag;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public final class EditorTagMVCImpl implements EditorTagMVC {
    public static final EditorTagMVC INSTANCE = new EditorTagMVCImpl();

    private EditorTagMVCImpl() {
    }

    @Override
    public EditorTagView createViewAndBind(EditorTagModel model) {
        EditorTagView view = createView(model.getTagType());
        EditorTagController controller = new EditorTagControllerImpl(model, view);
        controller.bind();
        return view;
    }

    private EditorTagView createView(byte tagType) {
        switch (tagType) {
            case Tag.BYTE_ID:
                return new EditorTagViewImpl("byte_tag", text("Byte").blue(), Predicates.IS_BYTE);
            case Tag.SHORT_ID:
                return new EditorTagViewImpl("short_tag", text("Short").green(), Predicates.IS_SHORT);
            case Tag.INT_ID:
                return new EditorTagViewImpl("int_tag", text("Int").aqua(), Predicates.IS_INT);
            case Tag.LONG_ID:
                return new EditorTagViewImpl("long_tag", text("Long").red(), Predicates.IS_LONG);
            case Tag.FLOAT_ID:
                return new EditorTagViewImpl("float_tag", text("Float").lightPurple(), Predicates.IS_FLOAT);
            case Tag.DOUBLE_ID:
                return new EditorTagViewImpl("double_tag", text("Double").yellow(), Predicates.IS_DOUBLE);
            case Tag.BYTE_ARRAY_ID:
                return new EditorTagViewImpl("byte_array_tag", text("Byte Array").blue());
            case Tag.STRING_ID:
                return new EditorTagViewImpl("string_tag", text("String").gray());
            case Tag.LIST_ID:
                return new EditorTagViewImpl("list_tag", text("List").green());
            case Tag.COMPOUND_ID:
                return new EditorTagViewImpl("compound_tag", text("Compound").lightPurple());
            case Tag.INT_ARRAY_ID:
                return new EditorTagViewImpl("int_array_tag", text("Int Array").aqua());
            case Tag.LONG_ARRAY_ID:
                return new EditorTagViewImpl("long_array_tag", text("Long Array").red());
            default:
                return null;
        }
    }
}

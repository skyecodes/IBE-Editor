package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.gameadapter.api.common.tag.Tag;
import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.guapi.api.util.Predicates;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.NBTTagController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.NBTTagModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.NBTTagView;

import static com.github.franckyi.guapi.GuapiHelper.*;

public final class NBTTagMVC implements MVC<NBTTagModel, NBTTagView, NBTTagController> {
    public static final NBTTagMVC INSTANCE = new NBTTagMVC();

    private NBTTagMVC() {
    }

    @Override
    public NBTTagView setup(NBTTagModel model) {
        return MVC.createViewAndBind(model, () -> createView(model.getTagType()), NBTTagController::new);
    }

    private NBTTagView createView(byte tagType) {
        switch (tagType) {
            case Tag.BYTE_ID:
                return new NBTTagView("byte_tag", text("Byte").blue(), Predicates.IS_BYTE);
            case Tag.SHORT_ID:
                return new NBTTagView("short_tag", text("Short").green(), Predicates.IS_SHORT);
            case Tag.INT_ID:
                return new NBTTagView("int_tag", text("Int").aqua(), Predicates.IS_INT);
            case Tag.LONG_ID:
                return new NBTTagView("long_tag", text("Long").red(), Predicates.IS_LONG);
            case Tag.FLOAT_ID:
                return new NBTTagView("float_tag", text("Float").lightPurple(), Predicates.IS_FLOAT);
            case Tag.DOUBLE_ID:
                return new NBTTagView("double_tag", text("Double").yellow(), Predicates.IS_DOUBLE);
            case Tag.BYTE_ARRAY_ID:
                return new NBTTagView("byte_array_tag", text("Byte Array").blue());
            case Tag.STRING_ID:
                return new NBTTagView("string_tag", text("String").gray());
            case Tag.LIST_ID:
                return new NBTTagView("list_tag", text("List").green());
            case Tag.COMPOUND_ID:
                return new NBTTagView("compound_tag", text("Compound").lightPurple());
            case Tag.INT_ARRAY_ID:
                return new NBTTagView("int_array_tag", text("Int Array").aqua());
            case Tag.LONG_ARRAY_ID:
                return new NBTTagView("long_array_tag", text("Long Array").red());
            default:
                return null;
        }
    }
}

package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.guapi.api.util.Predicates;
import com.github.franckyi.ibeeditor.base.client.ModTextures;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.NBTTagController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.NBTTagModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.NBTTagView;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import com.github.franckyi.ibeeditor.base.common.TagHelper;

public final class NBTTagMVC implements MVC<NBTTagModel, NBTTagView, NBTTagController> {
    public static final NBTTagMVC INSTANCE = new NBTTagMVC();

    private NBTTagMVC() {
    }

    @Override
    public NBTTagView setup(NBTTagModel model) {
        return MVC.createViewAndBind(model, () -> createView(model.getTagType()), NBTTagController::new);
    }

    private NBTTagView createView(byte tagType) {
        return switch (tagType) {
            case TagHelper.BYTE_ID -> new NBTTagView(ModTextures.BYTE_TAG, ModTexts.Literal.BYTE, Predicates.IS_BYTE);
            case TagHelper.SHORT_ID -> new NBTTagView(ModTextures.SHORT_TAG, ModTexts.Literal.SHORT, Predicates.IS_SHORT);
            case TagHelper.INT_ID -> new NBTTagView(ModTextures.INT_TAG, ModTexts.Literal.INT, Predicates.IS_INT);
            case TagHelper.LONG_ID -> new NBTTagView(ModTextures.LONG_TAG, ModTexts.Literal.LONG, Predicates.IS_LONG);
            case TagHelper.FLOAT_ID -> new NBTTagView(ModTextures.FLOAT_TAG, ModTexts.Literal.FLOAT, Predicates.IS_FLOAT);
            case TagHelper.DOUBLE_ID -> new NBTTagView(ModTextures.DOUBLE_TAG, ModTexts.Literal.DOUBLE, Predicates.IS_DOUBLE);
            case TagHelper.BYTE_ARRAY_ID -> new NBTTagView(ModTextures.BYTE_ARRAY_TAG, ModTexts.Literal.BYTE_ARRAY);
            case TagHelper.STRING_ID -> new NBTTagView(ModTextures.STRING_TAG, ModTexts.Literal.STRING);
            case TagHelper.LIST_ID -> new NBTTagView(ModTextures.LIST_TAG, ModTexts.Literal.GREEN);
            case TagHelper.COMPOUND_ID -> new NBTTagView(ModTextures.COMPOUND_TAG, ModTexts.Literal.COMPOUND);
            case TagHelper.INT_ARRAY_ID -> new NBTTagView(ModTextures.INT_ARRAY_TAG, ModTexts.Literal.INT_ARRAY);
            case TagHelper.LONG_ARRAY_ID -> new NBTTagView(ModTextures.LONG_ARRAY_TAG, ModTexts.Literal.LONG_ARRAY);
            default -> null;
        };
    }
}

package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.gameadapter.api.common.tag.ITag;
import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.guapi.api.util.Predicates;
import com.github.franckyi.ibeeditor.base.client.ModTextures;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.NBTTagController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.NBTTagModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.NBTTagView;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

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
            case ITag.BYTE_ID:
                return new NBTTagView(ModTextures.BYTE_TAG, ModTexts.Literal.BYTE, Predicates.IS_BYTE);
            case ITag.SHORT_ID:
                return new NBTTagView(ModTextures.SHORT_TAG, ModTexts.Literal.SHORT, Predicates.IS_SHORT);
            case ITag.INT_ID:
                return new NBTTagView(ModTextures.INT_TAG, ModTexts.Literal.INT, Predicates.IS_INT);
            case ITag.LONG_ID:
                return new NBTTagView(ModTextures.LONG_TAG, ModTexts.Literal.LONG, Predicates.IS_LONG);
            case ITag.FLOAT_ID:
                return new NBTTagView(ModTextures.FLOAT_TAG, ModTexts.Literal.FLOAT, Predicates.IS_FLOAT);
            case ITag.DOUBLE_ID:
                return new NBTTagView(ModTextures.DOUBLE_TAG, ModTexts.Literal.DOUBLE, Predicates.IS_DOUBLE);
            case ITag.BYTE_ARRAY_ID:
                return new NBTTagView(ModTextures.BYTE_ARRAY_TAG, ModTexts.Literal.BYTE_ARRAY);
            case ITag.STRING_ID:
                return new NBTTagView(ModTextures.STRING_TAG, ModTexts.Literal.STRING);
            case ITag.LIST_ID:
                return new NBTTagView(ModTextures.LIST_TAG, ModTexts.Literal.GREEN);
            case ITag.COMPOUND_ID:
                return new NBTTagView(ModTextures.COMPOUND_TAG, ModTexts.Literal.COMPOUND);
            case ITag.INT_ARRAY_ID:
                return new NBTTagView(ModTextures.INT_ARRAY_TAG, ModTexts.Literal.INT_ARRAY);
            case ITag.LONG_ARRAY_ID:
                return new NBTTagView(ModTextures.LONG_ARRAY_TAG, ModTexts.Literal.LONG_ARRAY);
            default:
                return null;
        }
    }
}

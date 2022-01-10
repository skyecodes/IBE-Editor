package com.github.franckyi.ibeeditor.client.screen.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.guapi.api.util.Predicates;
import com.github.franckyi.ibeeditor.client.ModTextures;
import com.github.franckyi.ibeeditor.client.screen.controller.NBTTagController;
import com.github.franckyi.ibeeditor.client.screen.model.NBTTagModel;
import com.github.franckyi.ibeeditor.client.screen.view.NBTTagView;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.Tag;

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
            case Tag.TAG_BYTE -> new NBTTagView(ModTextures.BYTE_TAG, ModTexts.Literal.BYTE, Predicates.IS_BYTE);
            case Tag.TAG_SHORT -> new NBTTagView(ModTextures.SHORT_TAG, ModTexts.Literal.SHORT, Predicates.IS_SHORT);
            case Tag.TAG_INT -> new NBTTagView(ModTextures.INT_TAG, ModTexts.Literal.INT, Predicates.IS_INT);
            case Tag.TAG_LONG -> new NBTTagView(ModTextures.LONG_TAG, ModTexts.Literal.LONG, Predicates.IS_LONG);
            case Tag.TAG_FLOAT -> new NBTTagView(ModTextures.FLOAT_TAG, ModTexts.Literal.FLOAT, Predicates.IS_FLOAT);
            case Tag.TAG_DOUBLE -> new NBTTagView(ModTextures.DOUBLE_TAG, ModTexts.Literal.DOUBLE, Predicates.IS_DOUBLE);
            case Tag.TAG_BYTE_ARRAY -> new NBTTagView(ModTextures.BYTE_ARRAY_TAG, ModTexts.Literal.BYTE_ARRAY);
            case Tag.TAG_STRING -> new NBTTagView(ModTextures.STRING_TAG, ModTexts.Literal.STRING);
            case Tag.TAG_LIST -> new NBTTagView(ModTextures.LIST_TAG, ModTexts.Literal.GREEN);
            case Tag.TAG_COMPOUND -> new NBTTagView(ModTextures.COMPOUND_TAG, ModTexts.Literal.COMPOUND);
            case Tag.TAG_INT_ARRAY -> new NBTTagView(ModTextures.INT_ARRAY_TAG, ModTexts.Literal.INT_ARRAY);
            case Tag.TAG_LONG_ARRAY -> new NBTTagView(ModTextures.LONG_ARRAY_TAG, ModTexts.Literal.LONG_ARRAY);
            default -> null;
        };
    }
}

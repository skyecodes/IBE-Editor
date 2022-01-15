package com.github.franckyi.ibeeditor.client.screen.model.category.block;

import com.github.franckyi.ibeeditor.client.screen.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.StringEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.TextEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

public class ContainerCategoryModel extends BlockEditorCategoryModel {
    public ContainerCategoryModel(BlockEditorModel editor) {
        super(ModTexts.CONTAINER, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(
                new TextEntryModel(this, ModTexts.CUSTOM_NAME, getCustomName(), this::setCustomName),
                new StringEntryModel(this, ModTexts.LOCK_CODE, getData().getString("Lock"), this::setLockCode)
        );
    }

    private TextComponent getCustomName() {
        String s = getData().getString("CustomName");
        return s.isEmpty() ? null : (TextComponent) Component.Serializer.fromJson(s);
    }

    private void setCustomName(TextComponent value) {
        if (!value.getString().isEmpty()) {
            getData().putString("CustomName", Component.Serializer.toJson(value));
        } else {
            getData().remove("CustomName");
        }
    }

    private void setLockCode(String value) {
        if (value != null && !value.isEmpty()) {
            getData().putString("Lock", value);
        } else {
            getData().remove("Lock");
        }
    }
}

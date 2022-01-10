package com.github.franckyi.ibeeditor.client.screen.model.category.block;

import com.github.franckyi.ibeeditor.client.screen.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.StringEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.TextEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;

public class ContainerCategoryModel extends BlockCategoryModel {
    private final BaseContainerBlockEntity blockEntity;

    public ContainerCategoryModel(BlockEditorModel editor, BaseContainerBlockEntity blockEntity) {
        super(ModTexts.CONTAINER, editor);
        this.blockEntity = blockEntity;
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(
                new TextEntryModel(this, ModTexts.CUSTOM_NAME, (TextComponent) blockEntity.getCustomName(), this::setCustomName),
                new StringEntryModel(this, ModTexts.LOCK_CODE, getBaseTag().getString("Lock"), this::setLockCode)
        );
    }

    private void setCustomName(TextComponent value) {
        if (!value.getString().isEmpty()) {
            getNewTag().putString("CustomName", Component.Serializer.toJson(value));
        } else {
            getNewTag().remove("CustomName");
        }
    }

    private void setLockCode(String value) {
        if (value != null && !value.isEmpty()) {
            getNewTag().putString("Lock", value);
        } else {
            getNewTag().remove("Lock");
        }
    }
}

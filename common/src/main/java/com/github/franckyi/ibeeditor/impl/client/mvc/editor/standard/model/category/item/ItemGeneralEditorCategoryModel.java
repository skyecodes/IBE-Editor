package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.item;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.IntegerEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.StringEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.TextEditorEntryModel;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.tag.Tag;
import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Item;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class ItemGeneralEditorCategoryModel extends AbstractItemEditorCategoryModel {
    public ItemGeneralEditorCategoryModel(StandardEditorModel editor, Item item) {
        super("ibeeditor.gui.editor.category.general", editor);
        getEntries().addAll(
                new StringEditorEntryModel(this, translated("ibeeditor.gui.editor.item.entry.item_id"), item.getData().getString("id"), value -> getNbt().putString("id", value)),
                new IntegerEditorEntryModel(this, translated("ibeeditor.gui.editor.item.entry.count"), item.getData().getInt("Count"), value -> getNbt().putInt("Count", value)),
                new TextEditorEntryModel(this, translated("ibeeditor.gui.editor.item.entry.custom_name"), getItemName(item), this::setItemName)
        );
    }

    private PlainText getItemName(Item item) {
        String s = item.getData().getCompound("tag").getCompound("display").getString("Name");
        return s.isEmpty() ? null : (PlainText) Text.Serializer.GSON.fromJson(s, Text.class);
    }

    private void setItemName(PlainText value) {
        if (!value.getRawText().isEmpty()) {
            fixText(value);
            getDisplay().putString("Name", Text.Serializer.GSON.toJson(value));
        } else if (getTag().contains("display", Tag.COMPOUND_ID)) {
            getDisplay().remove("Name");
        }
    }

    private void fixText(PlainText value) {
        if (value.getExtra() != null && !value.getExtra().isEmpty()) {
            Text firstText = value.getExtra().get(0);
            if (firstText.isItalic()) {
                value.getExtra().add(0, text("").italic(false));
            } else {
                firstText.setItalic(false);
            }
        }
    }

    @Override
    public void apply(CompoundTag nbt) {
        super.apply(nbt);
        if (getDisplay().isEmpty()) {
            getTag().remove("display");
        }
    }

    private CompoundTag getDisplay() {
        return getTag().getOrCreateCompound("display");
    }
}

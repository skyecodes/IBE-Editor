package com.github.franckyi.ibeeditor.client.screen.model.category.entity;

import com.github.franckyi.ibeeditor.client.screen.model.EntityEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.BooleanEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.FloatEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.TextEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

public class EntityGeneralCategoryModel extends EntityCategoryModel {
    public EntityGeneralCategoryModel(EntityEditorModel model) {
        super(ModTexts.GENERAL, model);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(
                new FloatEntryModel(this, ModTexts.HEALTH, getData().getFloat("Health"), f -> getData().putFloat("Health", f)),
                new TextEntryModel(this, ModTexts.CUSTOM_NAME, getCustomName(), this::setCustomName),
                new BooleanEntryModel(this, ModTexts.ALWAYS_SHOW_NAME, getData().getBoolean("CustomNameVisible"), b -> putBooleanOrRemove("CustomNameVisible", b)),
                new BooleanEntryModel(this, ModTexts.INVULNERABLE, getData().getBoolean("Invulnerable"), b -> getData().putBoolean("Invulnerable", b)),
                new BooleanEntryModel(this, ModTexts.SILENT, getData().getBoolean("Silent"), b -> putBooleanOrRemove("Silent", b)),
                new BooleanEntryModel(this, ModTexts.NO_GRAVITY, getData().getBoolean("NoGravity"), b -> putBooleanOrRemove("NoGravity", b)),
                new BooleanEntryModel(this, ModTexts.GLOWING, getData().getBoolean("Glowing"), b -> putBooleanOrRemove("Glowing", b)),
                new IntegerEntryModel(this, ModTexts.FIRE, getData().getShort("Fire"), s -> getData().putShort("Fire", s.shortValue()))
        );
    }

    private TextComponent getCustomName() {
        String s = getData().getString("CustomName");
        return s.isEmpty() ? null : (TextComponent) Component.Serializer.fromJson(s);
    }

    private void setCustomName(TextComponent value) {
        if (!value.getString().isEmpty()) {
            getData().putString("CustomName", Component.Serializer.toJson(value));
        } else if (getData().getString("CustomName").isEmpty()) {
            getData().remove("CustomName");
        } else {
            getData().putString("CustomName", "");
        }
    }

    private void putBooleanOrRemove(String tagName, boolean value) {
        if (value) {
            getData().putBoolean(tagName, true);
        } else {
            getData().remove(tagName);
        }
    }
}

package com.github.franckyi.ibeeditor.client.screen.model.category.config;

import com.github.franckyi.ibeeditor.client.screen.model.ConfigEditorScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.ActionEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.BooleanEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.common.CommonConfiguration;
import com.github.franckyi.ibeeditor.common.ModTexts;

public class CommonConfigCategoryModel extends ConfigCategoryModel {
    private IntegerEntryModel permissionLevelEntry;
    private BooleanEntryModel creativeOnly;

    public CommonConfigCategoryModel(ConfigEditorScreenModel editor) {
        super(ModTexts.COMMON, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(
                permissionLevelEntry = new IntegerEntryModel(this, ModTexts.PERMISSION_LEVEL, CommonConfiguration.INSTANCE.getPermissionLevel(), CommonConfiguration.INSTANCE::setPermissionLevel).withWeight(2),
                creativeOnly = new BooleanEntryModel(this, ModTexts.CREATIVE_ONLY, CommonConfiguration.INSTANCE.isCreativeOnly(), CommonConfiguration.INSTANCE::setCreativeOnly).withWeight(2),
                new ActionEntryModel(this, ModTexts.RELOAD_CONFIG, this::reload)
        );
    }

    private void reload() {
        CommonConfiguration.load();
        syncEntries();
    }

    public void syncEntries() {
        permissionLevelEntry.setValue(CommonConfiguration.INSTANCE.getPermissionLevel());
        creativeOnly.setValue(CommonConfiguration.INSTANCE.isCreativeOnly());
    }

    @Override
    public void apply() {
        super.apply();
        CommonConfiguration.save();
    }
}

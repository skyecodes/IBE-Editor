package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ActionEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.BooleanEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.base.common.CommonConfiguration;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

public class CommonConfigCategoryModel extends ConfigCategoryModel {
    private IntegerEntryModel permissionLevelEntry;
    private BooleanEntryModel creativeOnly;

    public CommonConfigCategoryModel(ConfigEditorModel editor) {
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

    @Override
    public void initalize() {
        super.initalize();
        syncEntries();
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

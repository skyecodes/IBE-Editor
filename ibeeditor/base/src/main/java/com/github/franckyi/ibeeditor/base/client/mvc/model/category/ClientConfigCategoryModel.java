package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.guapi.api.util.DebugMode;
import com.github.franckyi.ibeeditor.base.client.ClientConfiguration;
import com.github.franckyi.ibeeditor.base.client.ClientInit;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ActionEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnumEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.StringEntryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

public class ClientConfigCategoryModel extends ConfigCategoryModel {
    private StringEntryModel guapiThemeEntry;
    private EnumEntryModel<DebugMode> guapiDebugModeEntry;

    public ClientConfigCategoryModel(ConfigEditorModel editor) {
        super(ModTexts.CLIENT, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(
                guapiThemeEntry = new StringEntryModel(this, ModTexts.THEME, ClientConfiguration.INSTANCE.getGuapiTheme(), ClientConfiguration.INSTANCE::setGuapiTheme),
                guapiDebugModeEntry = new EnumEntryModel<>(this, ModTexts.DEBUG_MODE, ClientConfiguration.INSTANCE.getGuapiDebugMode(), ClientConfiguration.INSTANCE::setGuapiDebugMode),
                new ActionEntryModel(this, ModTexts.RELOAD_CONFIG, this::reload)
        );
    }

    @Override
    public void initalize() {
        super.initalize();
        syncEntries();
    }

    private void reload() {
        ClientConfiguration.load();
        syncEntries();
    }

    public void syncEntries() {
        guapiThemeEntry.setValue(ClientConfiguration.INSTANCE.getGuapiTheme());
        guapiDebugModeEntry.setValue(ClientConfiguration.INSTANCE.getGuapiDebugMode());
    }

    @Override
    public void apply() {
        super.apply();
        ClientConfiguration.save();
        ClientInit.syncGuapiConfig();
    }
}

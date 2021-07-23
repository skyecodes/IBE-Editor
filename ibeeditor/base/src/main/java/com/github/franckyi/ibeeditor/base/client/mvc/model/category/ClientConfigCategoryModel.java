package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.guapi.api.util.DebugMode;
import com.github.franckyi.ibeeditor.base.client.ClientConfiguration;
import com.github.franckyi.ibeeditor.base.client.ClientInit;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ActionEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnumEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.StringEntryModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ClientConfigCategoryModel extends ConfigCategoryModel {
    private StringEntryModel guapiThemeEntry;
    private EnumEntryModel<DebugMode> guapiDebugModeEntry;

    public ClientConfigCategoryModel(ConfigEditorModel editor) {
        super("ibeeditor.gui.client", editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(
                guapiThemeEntry = new StringEntryModel(this, translated("ibeeditor.gui.theme"), ClientConfiguration.INSTANCE.getGuapiTheme(), ClientConfiguration.INSTANCE::setGuapiTheme),
                guapiDebugModeEntry = new EnumEntryModel<>(this, translated("ibeeditor.gui.debug_mode"), ClientConfiguration.INSTANCE.getGuapiDebugMode(), ClientConfiguration.INSTANCE::setGuapiDebugMode),
                new ActionEntryModel(this, translated("ibeeditor.gui.reload_config").yellow(), this::reload)
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

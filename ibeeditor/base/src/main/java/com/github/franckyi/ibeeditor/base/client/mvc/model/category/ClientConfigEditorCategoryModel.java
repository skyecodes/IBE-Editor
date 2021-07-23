package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.guapi.api.util.DebugMode;
import com.github.franckyi.ibeeditor.base.client.ClientConfiguration;
import com.github.franckyi.ibeeditor.base.client.ClientInit;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ActionEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnumEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.StringEditorEntryModel;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ClientConfigEditorCategoryModel extends ConfigEditorCategoryModel {
    private StringEditorEntryModel guapiThemeEntry;
    private EnumEditorEntryModel<DebugMode> guapiDebugModeEntry;

    public ClientConfigEditorCategoryModel(ConfigEditorModel editor) {
        super("ibeeditor.gui.config.category.client", editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(
                guapiThemeEntry = new StringEditorEntryModel(this, translated("ibeeditor.gui.config.entry.theme"), ClientConfiguration.INSTANCE.getGuapiTheme(), ClientConfiguration.INSTANCE::setGuapiTheme),
                guapiDebugModeEntry = new EnumEditorEntryModel<>(this, translated("ibeeditor.gui.config.entry.debug_mode"), ClientConfiguration.INSTANCE.getGuapiDebugMode(), ClientConfiguration.INSTANCE::setGuapiDebugMode),
                new ActionEditorEntryModel(this, translated("ibeeditor.gui.config.entry.reload_config").yellow(), this::reload)
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

package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.guapi.api.util.DebugMode;
import com.github.franckyi.ibeeditor.base.client.ClientConfiguration;
import com.github.franckyi.ibeeditor.base.client.ClientInit;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ActionEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EnumEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.StringEntryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

public class ClientConfigCategoryModel extends ConfigCategoryModel {
    private StringEntryModel guapiThemeEntry;
    private EnumEntryModel<DebugMode> guapiDebugModeEntry;
    private IntegerEntryModel selectionScreenMaxItemsEntry;

    public ClientConfigCategoryModel(ConfigEditorModel editor) {
        super(ModTexts.CLIENT, editor);
    }

    @Override
    protected void setupEntries() {
        getEntries().addAll(
                guapiThemeEntry = new StringEntryModel(this, ModTexts.THEME, ClientConfiguration.INSTANCE.getGuapiTheme(), ClientConfiguration.INSTANCE::setGuapiTheme).withWeight(2),
                guapiDebugModeEntry = new EnumEntryModel<>(this, ModTexts.DEBUG_MODE, ClientConfiguration.INSTANCE.getGuapiDebugMode(), ClientConfiguration.INSTANCE::setGuapiDebugMode).withWeight(2),
                selectionScreenMaxItemsEntry = new IntegerEntryModel(this, ModTexts.SELECTION_SCREEN_MAX_ITEMS, ClientConfiguration.INSTANCE.getSelectionScreenMaxItems(), ClientConfiguration.INSTANCE::setSelectionScreenMaxItems).withWeight(2),
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
        selectionScreenMaxItemsEntry.setValue(ClientConfiguration.INSTANCE.getSelectionScreenMaxItems());
    }

    @Override
    public void apply() {
        super.apply();
        ClientConfiguration.save();
        ClientInit.syncGuapiConfig();
    }
}

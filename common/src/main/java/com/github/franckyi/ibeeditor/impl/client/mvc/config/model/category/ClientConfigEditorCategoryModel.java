package com.github.franckyi.ibeeditor.impl.client.mvc.config.model.category;

import com.github.franckyi.guapi.util.DebugMode;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.config.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.impl.client.ClientConfiguration;
import com.github.franckyi.ibeeditor.impl.client.ClientInit;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.ActionEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.EnumEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.StringEditorEntryModel;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class ClientConfigEditorCategoryModel extends AbstractConfigEditorCategoryModel {
    private final StringEditorEntryModel guapiTheme;
    private final EnumEditorEntryModel<DebugMode> guapiDebugMode;

    public ClientConfigEditorCategoryModel(ConfigEditorModel editor) {
        super("Client", editor);
        getEntries().addAll(
                guapiTheme = new StringEditorEntryModel(this, text("Theme"), ClientConfiguration.INSTANCE.getGuapiTheme(), ClientConfiguration.INSTANCE::setGuapiTheme),
                guapiDebugMode = new EnumEditorEntryModel<>(this, text("Debug Mode"), ClientConfiguration.INSTANCE.getGuapiDebugMode(), ClientConfiguration.INSTANCE::setGuapiDebugMode),
                new ActionEditorEntryModel(this, text("Reload configuration from disk").yellow(), this::reload)
        );
    }

    private void reload() {
        ClientConfiguration.load();
        syncEntries();
    }

    public void syncEntries() {
        guapiTheme.setValue(ClientConfiguration.INSTANCE.getGuapiTheme());
        guapiDebugMode.setValue(ClientConfiguration.INSTANCE.getGuapiDebugMode());
    }

    @Override
    public void apply() {
        getEntries().forEach(EditorEntryModel::apply);
        ClientConfiguration.save();
        ClientInit.syncGuapiConfig();
    }
}

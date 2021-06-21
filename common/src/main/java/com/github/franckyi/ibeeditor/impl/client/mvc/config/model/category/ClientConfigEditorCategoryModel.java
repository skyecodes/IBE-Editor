package com.github.franckyi.ibeeditor.impl.client.mvc.config.model.category;

import com.github.franckyi.guapi.util.DebugMode;
import com.github.franckyi.ibeeditor.api.client.mvc.config.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.impl.client.ClientConfiguration;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.EnumEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.IntegerEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.StringEditorEntryModel;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class ClientConfigEditorCategoryModel extends AbstractConfigEditorCategoryModel {
    private final IntegerEditorEntryModel editorScale;
    private final StringEditorEntryModel guapiTheme;
    private final EnumEditorEntryModel<DebugMode> guapiDebugMode;

    public ClientConfigEditorCategoryModel(ConfigEditorModel editor) {
        super("Client", editor);
        getEntries().addAll(
                editorScale = new IntegerEditorEntryModel(this, text("Editor Scale"), ClientConfiguration.INSTANCE.getEditorScale(), ClientConfiguration.INSTANCE::setEditorScale),
                guapiTheme = new StringEditorEntryModel(this, text("GUAPI Theme"), ClientConfiguration.INSTANCE.getGuapiTheme(), ClientConfiguration.INSTANCE::setGuapiTheme),
                guapiDebugMode = new EnumEditorEntryModel<>(this, text("GUAPI Debug Mode"), ClientConfiguration.INSTANCE.getGuapiDebugMode(), ClientConfiguration.INSTANCE::setGuapiDebugMode)
        );
    }

    @Override
    public void apply() {

    }
}

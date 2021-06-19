package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.config;

import com.github.franckyi.guapi.util.DebugMode;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.impl.client.ClientConfiguration;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.EnumEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.StringEntryModel;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class ClientConfigCategoryModel extends AbstractConfigCategoryModel {
    private final IntegerEntryModel editorScale;
    private final StringEntryModel guapiTheme;
    private final EnumEntryModel<DebugMode> guapiDebugMode;

    public ClientConfigCategoryModel(EditorModel editor) {
        super("Client", editor);
        getEntries().addAll(
                editorScale = new IntegerEntryModel(this, text("Editor Scale"), ClientConfiguration.INSTANCE.getEditorScale(), ClientConfiguration.INSTANCE::setEditorScale),
                guapiTheme = new StringEntryModel(this, text("GUAPI Theme"), ClientConfiguration.INSTANCE.getGuapiTheme(), ClientConfiguration.INSTANCE::setGuapiTheme),
                guapiDebugMode = new EnumEntryModel<>(this, text("GUAPI Debug Mode"), ClientConfiguration.INSTANCE.getGuapiDebugMode(), ClientConfiguration.INSTANCE::setGuapiDebugMode)
        );
    }

    @Override
    public void apply() {

    }
}

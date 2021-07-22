package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.ibeeditor.api.client.mvc.base.EditorCategoryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.base.EditorEntryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.config.ConfigEditorMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.EditorTagMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.NBTEditorMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.StandardEditorMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.base.EditorCategoryMVCImpl;
import com.github.franckyi.ibeeditor.base.client.mvc.base.EditorEntryMVCImpl;
import com.github.franckyi.ibeeditor.base.client.mvc.config.ConfigEditorMVCImpl;
import com.github.franckyi.ibeeditor.base.client.mvc.editor.nbt.EditorTagMVCImpl;
import com.github.franckyi.ibeeditor.base.client.mvc.editor.nbt.NBTEditorMVCImpl;
import com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.StandardEditorMVCImpl;

public final class IBEEditorMVC {
    public static final StandardEditorMVC STANDARD_EDITOR = StandardEditorMVCImpl.INSTANCE;
    public static final ConfigEditorMVC CONFIG_EDITOR = ConfigEditorMVCImpl.INSTANCE;
    public static final NBTEditorMVC NBT_EDITOR = NBTEditorMVCImpl.INSTANCE;
    // TODO raw NBT editor
    public static final EditorCategoryMVC EDITOR_CATEGORY = EditorCategoryMVCImpl.INSTANCE;
    public static final EditorEntryMVC EDITOR_ENTRY = EditorEntryMVCImpl.INSTANCE;
    public static final EditorTagMVC EDITOR_TAG = EditorTagMVCImpl.INSTANCE;
}

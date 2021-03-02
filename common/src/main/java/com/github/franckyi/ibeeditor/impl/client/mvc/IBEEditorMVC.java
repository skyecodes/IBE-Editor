package com.github.franckyi.ibeeditor.impl.client.mvc;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.CategoryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.EditorMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.EntryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.NBTEditorMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.TagMVC;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.CategoryMVCImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.EditorMVCImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.EntryMVCImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.NBTEditorMVCImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.TagMVCImpl;

public final class IBEEditorMVC {
    public static final NBTEditorMVC NBT_EDITOR = NBTEditorMVCImpl.INSTANCE;
    public static final TagMVC NBT_TAG = TagMVCImpl.INSTANCE;
    public static final EditorMVC EDITOR = EditorMVCImpl.INSTANCE;
    public static final CategoryMVC EDITOR_CATEGORY = CategoryMVCImpl.INSTANCE;
    public static final EntryMVC EDITOR_ENTRY = EntryMVCImpl.INSTANCE;
}

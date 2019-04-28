package com.github.franckyi.ibeeditor.client.editor.entity;

import com.github.franckyi.ibeeditor.client.clipboard.logic.IBEClipboard;
import com.github.franckyi.ibeeditor.client.editor.common.category.ToolsCategory;
import com.github.franckyi.ibeeditor.client.util.ClientUtils;
import net.minecraft.entity.Entity;

public class ToolsEntityCategory extends ToolsCategory {

    private final Entity entity;

    public ToolsEntityCategory(Entity entity) {
        this.entity = entity;
        this.addCommand("summon", this::copySummonCommand, this::copySummonCommandWithoutFormatting);
        this.addSimple("Copy to IBE clipboard", this::copyToClipboard);
    }

    private void copyToClipboard() {
        IBEClipboard.getInstance().addEntity(entity);
    }

    private void copySummonCommand() {
        ClientUtils.copySummonCommand(entity);
    }

    private void copySummonCommandWithoutFormatting() {
        ClientUtils.copySummonCommandWithoutFormatting(entity);
    }
}

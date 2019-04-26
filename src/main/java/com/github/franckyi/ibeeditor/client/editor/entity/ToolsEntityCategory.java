package com.github.franckyi.ibeeditor.client.editor.entity;

import com.github.franckyi.ibeeditor.client.clipboard.logic.IBEClipboard;
import com.github.franckyi.ibeeditor.client.editor.Category;
import com.github.franckyi.ibeeditor.client.editor.property.ButtonProperty;
import com.github.franckyi.ibeeditor.client.util.ClientUtils;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.TextFormatting;

public class ToolsEntityCategory extends Category {

    private final Entity entity;

    public ToolsEntityCategory(Entity entity) {
        this.entity = entity;
        this.addAll(
                new ButtonProperty("Copy /summon command", this::copySummonCommand, TextFormatting.RED +
                        "Be careful !", "The text formatting won't be copied", "because it doesn't work with commands."),
                new ButtonProperty("Copy to IBE clipboard", this::copyToClipboard)
        );
    }

    private void copyToClipboard() {
        IBEClipboard.getInstance().addEntity(entity);
    }

    private void copySummonCommand() {
        ClientUtils.copySummonCommand(entity);
    }
}

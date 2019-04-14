package com.github.franckyi.ibeeditor.client.entity;

import com.github.franckyi.guapi.node.CheckBox;
import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.client.Category;
import com.github.franckyi.ibeeditor.client.property.PropertyBoolean;
import com.github.franckyi.ibeeditor.client.property.PropertyFormattedText;
import com.github.franckyi.ibeeditor.network.entity.EntityEditorMessage;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.TextComponentString;

public class GeneralEntityCategory extends Category {

    private final Entity entity;

    public GeneralEntityCategory(Entity entity) {
        this.entity = entity;
        PropertyFormattedText name;
        PropertyBoolean showDefaultName;
        this.addAll(
                name = new PropertyFormattedText("Name", entity.hasCustomName() ?
                        entity.getCustomName().getFormattedText() : "", this::setName),
                showDefaultName = new PropertyBoolean("Show default name", entity.isCustomNameVisible(), entity::setCustomNameVisible),
                new PropertyBoolean("Invisible", entity.isInvisible(), entity::setInvisible),
                new PropertyBoolean("Invulnerable", entity.isInvulnerable(), entity::setInvulnerable),
                new PropertyBoolean("Silent", entity.isSilent(), entity::setSilent),
                new PropertyBoolean("No gravity", entity.hasNoGravity(), entity::setNoGravity)
        );
        name.getOnValueChangedListeners().add((s, s2) ->
                ((CheckBox) showDefaultName.getNode().getChildren().get(0)).setDisabled(!s2.isEmpty()));
        ((CheckBox) showDefaultName.getNode().getChildren().get(0)).setDisabled(!name.getValue().isEmpty());
    }

    private void setName(String s) {
        if (s.equals("§r")) {
            entity.setCustomName(null);
        } else {
            entity.setCustomName(new TextComponentString(s));
        }
    }

    @Override
    public void apply() {
        super.apply();
        IBEEditorMod.CHANNEL.sendToServer(new EntityEditorMessage(entity));
    }
}

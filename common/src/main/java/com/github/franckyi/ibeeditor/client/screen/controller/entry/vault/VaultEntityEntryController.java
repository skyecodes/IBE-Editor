package com.github.franckyi.ibeeditor.client.screen.controller.entry.vault;

import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.context.EntityEditorContext;
import com.github.franckyi.ibeeditor.client.screen.controller.entry.EntryController;
import com.github.franckyi.ibeeditor.client.screen.model.entry.vault.VaultEntityEntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.vault.VaultEntityEntryView;
import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class VaultEntityEntryController extends EntryController<VaultEntityEntryModel, VaultEntityEntryView> {
    public VaultEntityEntryController(VaultEntityEntryModel model, VaultEntityEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getImageView().setTextureId(getEntityIconTexture());
        view.getLabel().labelProperty().bind(model.entityProperty().map(Entity::getName));
        view.getButtonBox().getChildren().remove(view.getResetButton());
        view.getOpenEditorButton().onAction(() -> openEditor(EditorType.STANDARD));
        view.getOpenNBTEditorButton().onAction(() -> openEditor(EditorType.NBT));
        view.getOpenSNBTEditorButton().onAction(() -> openEditor(EditorType.SNBT));
    }

    private void openEditor(EditorType editorType) {
        ModScreenHandler.openEditor(editorType, new EntityEditorContext(model.getData(),
                null, false, context -> model.setData(context.getTag())));
    }

    private ResourceLocation getEntityIconTexture() {
        var entityTexture = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(model.getEntity()).getTextureLocation(model.getEntity());
        return new ResourceLocation(entityTexture.getNamespace(), entityTexture.getPath().replace("/entity/", "/entity_icon/"));
    }
}

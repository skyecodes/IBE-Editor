package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.guapi.api.mvc.Controller;
import com.github.franckyi.ibeeditor.client.screen.model.EditorModel;
import com.github.franckyi.ibeeditor.client.screen.view.ScreenView;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.Component;

public interface EditorController<M extends EditorModel, V extends ScreenView> extends Controller<M, V> {
    @Override
    default void bind() {
        getModel().validProperty().addListener(this::updateDoneButton);
        if (getModel().getContext().canSaveToVault()) {
            getView().addSaveVaultButton();
            getView().getSaveVaultButton().setActive(getModel().getContext().isSaveToVault());
            getView().getSaveVaultButton().activeProperty().addListener(getModel().getContext()::setSaveToVault);
            getView().getSaveVaultButton().activeProperty().addListener(this::updateDoneButton);
        }
        updateDoneButton();
    }

    default void updateDoneButton() {
        if (getModel().getContext().hasPermission()) {
            getView().getDoneButton().setDisable(!getModel().isValid());
            if (getModel().isValid()) {
                getView().getDoneButton().getTooltip().clear();
            } else {
                getView().getDoneButton().getTooltip().setAll(ModTexts.FIX_ERRORS);
            }
        } else {
            boolean disable = true;
            Component label = ModTexts.SAVE_VAULT_GREEN;
            Component tooltip = null;
            if (getModel().getContext().isSaveToVault()) {
                if (getModel().isValid()) {
                    disable = false;
                } else {
                    tooltip = ModTexts.FIX_ERRORS;
                }
            } else {
                label = ModTexts.DONE;
                tooltip = getModel().getContext().getErrorTooltip();
            }
            getView().getDoneButton().setDisable(disable);
            getView().getDoneButton().setLabel(label);
            if (tooltip == null) {
                getView().getDoneButton().getTooltip().clear();
            } else {
                getView().getDoneButton().getTooltip().setAll(tooltip);
            }
        }
    }
}

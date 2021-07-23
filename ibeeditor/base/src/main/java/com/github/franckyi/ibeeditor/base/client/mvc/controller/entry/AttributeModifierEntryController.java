package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.AttributeModifierEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.AttributeModifierEntryView;

public class AttributeModifierEntryController extends EntryController<AttributeModifierEntryModel, AttributeModifierEntryView> {
    public AttributeModifierEntryController(AttributeModifierEntryModel model, AttributeModifierEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        softBind(view.getAttributeNameField().textProperty(), model.attributeNameProperty());
        softBind(view.getSlotButton().valueProperty(), model.slotProperty());
        softBind(view.getOperationButton().valueProperty(), model.operationProperty());
        view.getAmountField().textProperty().addListener(value -> {
            if (view.getAmountField().isValid()) {
                model.setAmount(Double.parseDouble(value));
                model.setValid(true);
            }
        });
        model.amountProperty().addListener(value -> view.getAmountField().setText(Double.toString(value)));
        view.getAttributeNameField().setText(model.getAttributeName());
        view.getSlotButton().setValue(model.getSlot());
        view.getOperationButton().setValue(model.getOperation());
        view.getAmountField().setText(Double.toString(model.getAmount()));
        view.getAmountField().validProperty().addListener(model::setValid);
        view.getAttributeNameField().getSuggestions().setAll(Game.getCommon().getRegistries().getAttributeSuggestions());
        view.getAttributeListButton().onAction(this::openAttributeList);
    }

    private void openAttributeList() {
        ModScreenHandler.openAttributeScreen(model.getAttributeName().contains(":") ? model.getAttributeName()
                : "minecraft:" + model.getAttributeName(), model::setAttributeName);
    }
}

package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.ClientCache;
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
            }
        });
        model.amountProperty().addListener(value -> view.getAmountField().setText(Double.toString(value)));
        view.getAttributeNameField().setText(model.getAttributeName());
        view.getSlotButton().setValue(model.getSlot());
        view.getOperationButton().setValue(model.getOperation());
        view.getAmountField().setText(Double.toString(model.getAmount()));
        view.getAmountField().validProperty().addListener(model::setValid);
        view.getAttributeNameField().getSuggestions().setAll(ClientCache.getAttributeSuggestions());
        view.getAttributeListButton().onAction(this::openAttributeList);
    }

    private void openAttributeList() {
        ModScreenHandler.openListSelectionScreen("ibeeditor.gui.attribute",
                model.getAttributeName().contains(":") ? model.getAttributeName() : "minecraft:" + model.getAttributeName(),
                ClientCache.getAttributeSelectionItems(), model::setAttributeName);
    }

}

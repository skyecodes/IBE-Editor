package com.github.franckyi.ibeeditor.client.screen.controller.entry.item;

import com.github.franckyi.ibeeditor.client.ClientCache;
import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.screen.controller.entry.EntryController;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.AttributeModifierEntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.item.AttributeModifierEntryView;
import com.github.franckyi.ibeeditor.common.ModTexts;

public class AttributeModifierEntryController extends EntryController<AttributeModifierEntryModel, AttributeModifierEntryView> {

    public AttributeModifierEntryController(AttributeModifierEntryModel model, AttributeModifierEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getAmountField().textProperty().addListener(value -> {
            if (view.getAmountField().isValid()) {
                model.setAmount(Double.parseDouble(value));
            }
        });
        model.amountProperty().addListener(value -> view.getAmountField().setText(Double.toString(value)));
        view.getAttributeNameField().textProperty().bindBidirectional(model.attributeNameProperty());
        view.getSlotButton().valueProperty().bindBidirectional(model.slotProperty());
        view.getOperationButton().valueProperty().bindBidirectional(model.operationProperty());
        view.getAmountField().setText(Double.toString(model.getAmount()));
        view.getAmountField().validProperty().addListener(model::setValid);
        view.getAttributeNameField().getSuggestions().setAll(ClientCache.getAttributeSuggestions());
        view.getAttributeListButton().onAction(this::openAttributeList);
    }

    private void openAttributeList() {
        ModScreenHandler.openListSelectionScreen(ModTexts.ATTRIBUTE,
                model.getAttributeName().contains(":") ? model.getAttributeName() : "minecraft:" + model.getAttributeName(),
                ClientCache.getAttributeSelectionItems(), model::setAttributeName);
    }

}

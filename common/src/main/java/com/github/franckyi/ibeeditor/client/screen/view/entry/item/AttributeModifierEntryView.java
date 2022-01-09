package com.github.franckyi.ibeeditor.client.screen.view.entry.item;

import com.github.franckyi.guapi.api.node.EnumButton;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.util.Predicates;
import com.github.franckyi.ibeeditor.client.ModTextures;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.AttributeModifierEntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.EntryView;
import com.github.franckyi.ibeeditor.common.ModTexts;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class AttributeModifierEntryView extends EntryView {
    private TextField attributeNameField;
    private TexturedButton attributeListButton;
    private EnumButton<AttributeModifierEntryModel.Slot> slotButton;
    private EnumButton<AttributeModifierEntryModel.Operation> operationButton;
    private TextField amountField;

    @Override
    public void build() {
        super.build();
        operationButton.valueProperty().addListener(this::updateOperationTooltip);
        updateOperationTooltip();
    }

    private void updateOperationTooltip() {
        operationButton.getTooltip().setAll(operationButton.getValue().getTooltip());
    }

    @Override
    protected Node createContent() {
        return vBox(content -> {
            content.add(hBox(top -> {
                top.add(attributeNameField = textField().prefHeight(16)
                        .placeholder(ModTexts.ATTRIBUTE_NAME), 1);
                top.add(attributeListButton = texturedButton(ModTextures.SEARCH, 16, 16, false)
                        .tooltip(ModTexts.choose(ModTexts.ATTRIBUTE)));
                top.align(CENTER).spacing(2);
            }));
            content.add(hBox(bottom -> {
                bottom.add(slotButton = enumButton(AttributeModifierEntryModel.Slot.values())
                        .textFactory(AttributeModifierEntryModel.Slot::getText)
                        .tooltip(ModTexts.SLOT));
                bottom.add(operationButton = enumButton(AttributeModifierEntryModel.Operation.values())
                        .textFactory(AttributeModifierEntryModel.Operation::getText));
                bottom.add(amountField = textField().validator(Predicates.IS_DOUBLE)
                        .tooltip(ModTexts.AMOUNT).prefHeight(16), 1);
                bottom.align(CENTER).spacing(2);
            }));
            content.spacing(2).align(CENTER).fillWidth();
        });
    }

    public TextField getAttributeNameField() {
        return attributeNameField;
    }

    public TexturedButton getAttributeListButton() {
        return attributeListButton;
    }

    public EnumButton<AttributeModifierEntryModel.Slot> getSlotButton() {
        return slotButton;
    }

    public EnumButton<AttributeModifierEntryModel.Operation> getOperationButton() {
        return operationButton;
    }

    public TextField getAmountField() {
        return amountField;
    }
}

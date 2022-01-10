package com.github.franckyi.ibeeditor.client.screen.controller.selection.element;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.client.screen.model.selection.element.ListSelectionElementModel;
import com.github.franckyi.ibeeditor.client.screen.view.selection.element.ListSelectionElementView;
import net.minecraft.ChatFormatting;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class ListSelectionElementController<M extends ListSelectionElementModel, V extends ListSelectionElementView> extends AbstractController<M, V> {
    public ListSelectionElementController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getNameLabel().setLabel(translated(model.getName()).withStyle(ChatFormatting.BOLD));
        view.getIdLabel().setLabel(text(model.getId().toString()).withStyle(ChatFormatting.ITALIC));
    }
}

package com.github.franckyi.ibeeditor.client.clipboard;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.ListExtended;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.IResizable;
import com.github.franckyi.ibeeditor.client.clipboard.logic.EntityClipboardEntry;
import com.github.franckyi.ibeeditor.client.clipboard.logic.IClipboardEntry;
import com.github.franckyi.ibeeditor.client.clipboard.logic.ItemClipboardEntry;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;
import java.util.function.Consumer;

public class SelectionClipboard<T extends IClipboardEntry> extends AbstractClipboard {

    private final Button cancelButton;
    private final Consumer<T> afterSelection;

    public SelectionClipboard(Filter filter, Consumer<T> afterSelection) {
        super("IBE Clipboard : Select an " + filter);
        this.afterSelection = afterSelection;
        footer.getChildren().add(cancelButton = new Button(ChatFormatting.RED + "Cancel"));
        cancelButton.setPrefWidth(80);
        cancelButton.getOnMouseClickedListeners().add(event -> this.close());
        this.setFilter(filter);
        this.show();
    }

    @Override
    protected void newItemEntry(ItemClipboardEntry item) {
        body.getChildren().add(new ItemView(item));
    }

    @Override
    protected void newEntityEntry(EntityClipboardEntry entity) {

    }

    private class ItemView extends ListExtended.NodeEntry<HBox> implements IResizable {
        private final Label nameLabel;
        private final Button selectButton;

        public ItemView(ItemClipboardEntry item) {
            super(new HBox(10));
            this.getNode().setAlignment(Pos.LEFT);
            ItemStack itemStack = item.getItemStack();
            List<Node> children = this.getNode().getChildren();
            children.add(new TexturedButton(itemStack));
            children.add(nameLabel = new Label(itemStack.getDisplayName().getFormattedText()));
            children.add(selectButton = new Button(TextFormatting.GREEN + "Select"));
            selectButton.getOnMouseClickedListeners().add(e -> {
                afterSelection.accept(((T) item));
                SelectionClipboard.this.close();
            });
        }

        @Override
        public void updateSize(int listWidth) {
            nameLabel.setPrefWidth(listWidth - 115);
        }
    }
}

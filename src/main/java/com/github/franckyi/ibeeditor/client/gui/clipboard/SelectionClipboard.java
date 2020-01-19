package com.github.franckyi.ibeeditor.client.gui.clipboard;

import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.ibeeditor.client.logic.clipboard.EntityClipboardEntry;
import com.github.franckyi.ibeeditor.client.logic.clipboard.IClipboardEntry;
import com.github.franckyi.ibeeditor.client.logic.clipboard.ItemClipboardEntry;
import net.minecraft.util.text.TextFormatting;

import java.util.function.Consumer;

public class SelectionClipboard<T extends IClipboardEntry> extends AbstractClipboard {

    private final Button cancelButton;
    private final Consumer<T> afterSelection;

    public SelectionClipboard(Filter filter, Consumer<T> afterSelection) {
        super("IBE Clipboard : Select an " + filter);
        this.afterSelection = afterSelection;
        footer.getChildren().add(cancelButton = new Button(TextFormatting.RED + "Cancel"));
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
        body.getChildren().add(new EntityView(entity));
    }

    private class ItemView extends ItemViewBase {
        private final Button selectButton;

        public ItemView(ItemClipboardEntry item) {
            super(item);
            children.add(selectButton = new Button(TextFormatting.GREEN + "Select"));
            selectButton.setPrefWidth(80);
            selectButton.getOnMouseClickedListeners().add(e -> {
                afterSelection.accept(((T) item));
                SelectionClipboard.this.close();
            });
        }

        @Override
        public void updateSize(int listWidth) {
            nameLabel.setPrefWidth(listWidth - OFFSET - 159);
        }
    }

    private class EntityView extends EntityViewBase {
        private final Button selectButton;

        public EntityView(EntityClipboardEntry entity) {
            super(entity);
            children.add(selectButton = new Button(TextFormatting.GREEN + "Select"));
            selectButton.setPrefWidth(80);
            selectButton.getOnMouseClickedListeners().add(e -> {
                afterSelection.accept(((T) entity));
                SelectionClipboard.this.close();
            });
        }

        @Override
        public void updateSize(int listWidth) {
            nameLabel.setPrefWidth(listWidth - OFFSET - 159);
        }
    }

}

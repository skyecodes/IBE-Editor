package com.github.franckyi.ibeeditor.client.gui.clipboard;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.EnumButton;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.clipboard.EntityClipboardEntry;
import com.github.franckyi.ibeeditor.client.clipboard.IBEClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.ItemClipboardEntry;
import com.github.franckyi.ibeeditor.client.gui.editor.entity.EntityEditor;
import com.github.franckyi.ibeeditor.client.gui.editor.item.ItemEditor;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class ViewClipboard extends AbstractClipboard {

    protected final HBox filterBox;
    protected final Label filterLabel;
    protected final EnumButton<Filter> filterButton;
    protected final Button closeButton;
    protected final HBox blankBox;

    public ViewClipboard() {
        super("IBE Clipboard");
        footer.getChildren().add(filterBox = new HBox(5));
        filterBox.getChildren().add(filterLabel = new Label("Filter :"));
        filterLabel.setPrefHeight(20);
        filterBox.getChildren().add(filterButton = new EnumButton<>(Filter.values()));
        filterButton.getOnValueChangedListeners().add((oldVal, newVal) -> this.setFilter(newVal));
        filterButton.setValue(Filter.ITEM);
        footer.getChildren().add(closeButton = new Button(TextFormatting.GREEN + "Close"));
        closeButton.getOnMouseClickedListeners().add(event -> this.close());
        closeButton.setPrefWidth(80);
        footer.getChildren().add(blankBox = new HBox());
        this.show();
    }

    @Override
    protected void scaleChildrenSize() {
        super.scaleChildrenSize();
        blankBox.setPrefWidth(filterBox.getWidth());
    }

    @Override
    protected void newItemEntry(ItemClipboardEntry item) {
        body.getChildren().add(new ItemView(item));
    }

    @Override
    protected void newEntityEntry(EntityClipboardEntry entity) {
        body.getChildren().add(new EntityView(entity));
    }

    protected class ItemView extends ItemViewBase {

        private final Button editButton;
        private final TexturedButton removeButton;

        public ItemView(ItemClipboardEntry item) {
            super(item);
            List<Node> children = this.getNode().getChildren();
            children.add(editButton = new Button("Open in Item Editor"));
            editButton.setPrefWidth(170);
            editButton.getOnMouseClickedListeners().add(event -> new ItemEditor(itemStack, stack -> {
                List<ItemClipboardEntry> items = IBEClipboard.getInstance().getItems();
                items.set(items.indexOf(item), new ItemClipboardEntry(stack));
                IBEClipboard.getInstance().save();
                setFilter(filter);
            }));
            children.add(removeButton =
                    new TexturedButton("delete.png", TextFormatting.RED + "Remove"));
            removeButton.getOnMouseClickedListeners().add(event -> {
                IBEClipboard.getInstance().removeItem(item);
                setFilter(filter);
            });
        }

        @Override
        public void updateSize(int listWidth) {
            nameLabel.setPrefWidth(listWidth - OFFSET - 281);
        }
    }

    protected class EntityView extends EntityViewBase {

        private final Button editButton;
        private final TexturedButton removeButton;

        public EntityView(EntityClipboardEntry entity) {
            super(entity);
            List<Node> children = this.getNode().getChildren();
            children.add(editButton = new Button("Open in Entity Editor"));
            editButton.setPrefWidth(170);
            editButton.getOnMouseClickedListeners().add(event -> new EntityEditor(this.entity, entity0 -> {
                List<EntityClipboardEntry> entities = IBEClipboard.getInstance().getEntities();
                entities.set(entities.indexOf(entity), new EntityClipboardEntry(entity0));
                IBEClipboard.getInstance().save();
                setFilter(filter);
            }));
            children.add(removeButton =
                    new TexturedButton("delete.png", TextFormatting.RED + "Remove"));
            removeButton.getOnMouseClickedListeners().add(event -> {
                IBEClipboard.getInstance().removeEntity(entity);
                setFilter(filter);
            });
        }

        @Override
        public void updateSize(int listWidth) {
            nameLabel.setPrefWidth(listWidth - OFFSET - 281);
        }

    }
}

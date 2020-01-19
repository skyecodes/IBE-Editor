package com.github.franckyi.ibeeditor.client.gui.clipboard;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.Scene;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.group.VBox;
import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.ListExtended;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.guapi.scene.IBackground;
import com.github.franckyi.ibeeditor.client.EntityIcons;
import com.github.franckyi.ibeeditor.client.clipboard.EntityClipboardEntry;
import com.github.franckyi.ibeeditor.client.clipboard.IBEClipboard;
import com.github.franckyi.ibeeditor.client.clipboard.ItemClipboardEntry;
import com.github.franckyi.ibeeditor.client.gui.IResizable;
import com.github.franckyi.ibeeditor.common.IBEConfiguration;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public abstract class AbstractClipboard extends Scene {

    protected final VBox content;
    protected final Label header;
    protected final ListExtended<ListExtended.NodeEntry<?>> body;
    protected final HBox footer;
    protected Filter filter;

    public AbstractClipboard(String headerText) {
        super(new VBox());
        content = (VBox) this.getContent();
        header = new Label(TextFormatting.UNDERLINE + headerText);
        header.setPrefHeight(30);
        header.setCentered(true);
        body = new ListExtended<>(25);
        body.setOffset(new Insets(0, 10, 10, 10));
        footer = new HBox(20);
        footer.setAlignment(Pos.CENTER);
        footer.setPrefHeight(20);
        content.getChildren().add(header);
        content.getChildren().add(body);
        content.getChildren().add(footer);
        this.setContentFullScreen();
        this.setBackground(IBackground.texturedBackground(1));
        this.getOnInitGuiListeners().add(e -> {
            this.setContentFullScreen();
            this.scaleChildrenSize();
        });
        this.setGuiPauseGame(IBEConfiguration.CLIENT.doesGuiPauseGame.get());
    }

    protected void scaleChildrenSize() {
        header.setPrefWidth(content.getWidth());
        footer.setPrefWidth(content.getWidth());
        body.setPrefSize(content.getWidth(), content.getHeight() - 60);
        this.scaleEntriesSize();
    }

    private void scaleEntriesSize() {
        body.getChildren().stream()
                .filter(node -> node instanceof IResizable)
                .map(node -> (IResizable) node)
                .forEach(resizable -> resizable.updateSize(content.getWidth()));
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        body.render(mouseX, mouseY, partialTicks);
        header.render(mouseX, mouseY, partialTicks);
        footer.render(mouseX, mouseY, partialTicks);
        if (body.getChildren().isEmpty()) {
            this.getScreen().drawCenteredString(mc.fontRenderer, "The selection is empty !", this.getScreen().width / 2, body.getY() + body.getHeight() / 2 - 4, TextFormatting.DARK_RED.getColor());
        }
    }

    protected void setFilter(Filter filter) {
        this.filter = filter;
        IBEClipboard clipboard = IBEClipboard.getInstance();
        body.getChildren().clear();
        switch (filter) {
            case ALL:
                clipboard.getItems().forEach(this::newItemEntry);
                clipboard.getEntities().forEach(this::newEntityEntry);
                break;
            case ITEM:
                clipboard.getItems().forEach(this::newItemEntry);
                break;
            case ENTITY:
                clipboard.getEntities().forEach(this::newEntityEntry);
                break;
        }
        this.scaleEntriesSize();
    }

    protected abstract void newItemEntry(ItemClipboardEntry item);

    protected abstract void newEntityEntry(EntityClipboardEntry entity);

    public enum Filter {
        ALL("All"),
        ITEM("Item"),
        ENTITY("Entity");

        private final String s;

        Filter(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return s;
        }
    }

    protected abstract static class ItemViewBase extends ListExtended.NodeEntry<HBox> implements IResizable {

        protected final TexturedButton itemButton;
        protected final Label nameLabel;

        protected ItemStack itemStack;
        protected List<Node> children;

        public ItemViewBase(ItemClipboardEntry item) {
            super(new HBox(10));
            this.getNode().setAlignment(Pos.LEFT);
            itemStack = item.getItemStack();
            children = this.getNode().getChildren();
            children.add(itemButton = new TexturedButton(itemStack));
            itemButton.setMargin(Insets.left(5));
            children.add(nameLabel = new Label(itemStack.getDisplayName().getFormattedText()));
        }

        public abstract void updateSize(int listWidth);
    }

    protected abstract static class EntityViewBase extends ListExtended.NodeEntry<HBox> implements IResizable {

        protected final TexturedButton entityButton;
        protected final Label nameLabel;

        protected Entity entity;
        protected List<Node> children;

        public EntityViewBase(EntityClipboardEntry entity) {
            super(new HBox(10));
            this.getNode().setAlignment(Pos.LEFT);
            this.entity = entity.getEntity();
            children = this.getNode().getChildren();
            children.add(entityButton = new TexturedButton(EntityIcons.getHeadFromEntityType(entity.getEntityType())));
            entityButton.setMargin(Insets.left(5));
            children.add(nameLabel = new Label(entity.getEntityType().getName().getFormattedText()));
        }
    }

}

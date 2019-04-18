package com.github.franckyi.ibeeditor.client.clipboard;

import com.github.franckyi.guapi.Scene;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.group.VBox;
import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.ListExtended;
import com.github.franckyi.guapi.scene.Background;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

import java.util.ArrayList;
import java.util.List;

import static io.netty.buffer.Unpooled.buffer;

public class Clipboard extends Scene {

    private static Clipboard instance;
    private final List<ItemStack> clipboardItems;

    private final VBox content;

    private final Label header;
    private final ListExtended body;
    private final HBox footer;

    private Clipboard() {
        super(new VBox());
        clipboardItems = new ArrayList<>();
        content = (VBox) this.getContent();
        header = new Label("IBE Clipboard");
        header.setPrefHeight(30);
        header.setCentered(true);
        body = new ListExtended(25);
        body.setOffset(Insets.horizontal(10));
        footer = new HBox(20);
        footer.setPrefHeight(20);
        content.getChildren().add(header);
        content.getChildren().add(body);
        content.getChildren().add(footer);
        this.setContentFullScreen();
        this.setBackground(Background.texturedBackground(1));
        this.getOnInitGuiListeners().add(e -> {
            this.setContentFullScreen();
            this.scaleChildrenSize();
        });
    }

    public static Clipboard getInstance() {
        return instance == null ? instance = new Clipboard() : instance;
    }

    private void scaleChildrenSize() {
        header.setPrefWidth(this.getContent().getWidth());
        body.setPrefWidth(this.getContent().getWidth());
        footer.setPrefWidth(this.getContent().getWidth());
    }

    public List<ItemStack> getClipboardItems() {
        return clipboardItems;
    }

    public void showRead() {
        this.show();
    }

    public void showSelect() {
        this.show();
    }

    public void load() {
        PacketBuffer buf = new PacketBuffer(buffer());
    }

    public void save() {

    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        body.render(mouseX, mouseY, partialTicks);
        header.render(mouseX, mouseY, partialTicks);
        footer.render(mouseX, mouseY, partialTicks);
    }
}

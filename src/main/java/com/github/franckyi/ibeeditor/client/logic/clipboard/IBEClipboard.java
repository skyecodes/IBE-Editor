package com.github.franckyi.ibeeditor.client.logic.clipboard;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.client.IBENotification;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TextFormatting;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static io.netty.buffer.Unpooled.buffer;

public class IBEClipboard {

    private static IBEClipboard instance;

    private boolean disabled;

    private final Path path;
    private final PacketBuffer buffer;

    private final List<ItemClipboardEntry> items;
    private final List<EntityClipboardEntry> entities;

    private IBEClipboard() {
        instance = this;
        path = Paths.get("config", IBEEditorMod.MODID + "-clipboard.dat");
        buffer = new PacketBuffer(buffer());
        entities = new ArrayList<>();
        items = new ArrayList<>();
    }

    public static IBEClipboard getInstance() {
        return instance == null ? new IBEClipboard() : instance;
    }

    public void addEntity(Entity entity) {
        EntityClipboardEntry entry = new EntityClipboardEntry(entity);
        if (entities.contains(entry)) {
            IBENotification.show(IBENotification.Type.EDITOR, 3,
                    TextFormatting.YELLOW + "This entity is already saved to the clipboard.");
        } else {
            entities.add(entry);
            IBENotification.show(IBENotification.Type.EDITOR, 3,
                    TextFormatting.GREEN + "Entity copied to clipboard.");
            this.save();
        }
    }

    public void removeEntity(EntityClipboardEntry entry) {
        if (entities.remove(entry)) {
            IBENotification.show(IBENotification.Type.EDITOR, 3,
                    TextFormatting.GREEN + "Entity successfully removed from clipboard.");
            this.save();
        } else {
            IBENotification.show(IBENotification.Type.EDITOR, 3,
                    TextFormatting.YELLOW + "This entity isn't saved to the clipboard.");
        }
    }

    public void addItem(ItemStack item) {
        ItemClipboardEntry entry = new ItemClipboardEntry(item);
        if (items.contains(entry)) {
            IBENotification.show(IBENotification.Type.EDITOR, 3,
                    TextFormatting.YELLOW + "This item is already saved to the clipboard.");
        } else {
            items.add(entry);
            IBENotification.show(IBENotification.Type.EDITOR, 3,
                    TextFormatting.GREEN + "Item copied to clipboard.");
            this.save();
        }
    }

    public void removeItem(ItemClipboardEntry entry) {
        if (items.remove(entry)) {
            IBENotification.show(IBENotification.Type.EDITOR, 3,
                    TextFormatting.GREEN + "Item successfully removed from clipboard.");
            this.save();
        } else {
            IBENotification.show(IBENotification.Type.EDITOR, 3,
                    TextFormatting.YELLOW + "This item isn't saved to the clipboard.");
        }
    }

    public List<ItemClipboardEntry> getItems() {
        return items;
    }

    public List<EntityClipboardEntry> getEntities() {
        return entities;
    }

    public void save() {
        save(false);
    }

    private void save(boolean firstInit) {
        if (!disabled) {
            try {
                buffer.writeInt(items.size());
                items.forEach(entry -> entry.write(buffer));
                buffer.writeInt(entities.size());
                entities.forEach(entry -> entry.write(buffer));
                OutputStream output = Files.newOutputStream(path);
                buffer.readBytes(output, buffer.readableBytes());
                output.close();
                if (firstInit) {
                    IBEEditorMod.LOGGER.info("Clipboard created successfully.");
                    IBENotification.show(IBENotification.Type.EDITOR, 3,
                            TextFormatting.GREEN + "Thanks for using IBE Editor !");
                } else {
                    IBENotification.show(IBENotification.Type.CLIPBOARD, 3,
                            TextFormatting.GREEN + "IBE Clipboard successfully saved to disk.");
                }
            } catch (Exception e) {
                if (firstInit) {
                    IBENotification.show(IBENotification.Type.EDITOR, 3,
                            TextFormatting.RED + TextFormatting.BOLD.toString() +
                                    "IBE Clipboard first initialisation failed. See logs for details.", TextFormatting.RED + e.toString());
                } else {
                    IBENotification.show(IBENotification.Type.CLIPBOARD, 3,
                            TextFormatting.RED + TextFormatting.BOLD.toString() +
                                    "IBE Clipboard saving failed. See logs for details.", TextFormatting.RED + e.toString());
                }
                IBEEditorMod.LOGGER.throwing(e);
            }
        } else {
            IBENotification.show(IBENotification.Type.CLIPBOARD, 3,
                    TextFormatting.RED + "The IBE Clipboard is disabled.",
                    TextFormatting.RED + "An error occured on startup. See logs for details.");
        }
    }

    public void load() {
        try {
            entities.clear();
            items.clear();
            if (Files.exists(path)) {
                InputStream input = Files.newInputStream(path);
                buffer.writeBytes(input, input.available());
                input.close();
                int itemsSize = buffer.readInt();
                for (int i = 0; i < itemsSize; i++) {
                    items.add(new ItemClipboardEntry(buffer));
                }
                int entitiesSize = buffer.readInt();
                for (int i = 0; i < entitiesSize; i++) {
                    entities.add(new EntityClipboardEntry(buffer));
                }
                IBENotification.show(IBENotification.Type.CLIPBOARD, 3,
                        TextFormatting.GREEN + "IBE Clipboard successfully loaded from disk.");
            } else {
                IBEEditorMod.LOGGER.info("Clipboard doesn't exist. Creating one.");
                this.save(true);
            }
        } catch (Exception e) {
            disabled = true;
            IBENotification.show(IBENotification.Type.CLIPBOARD, 3,
                    TextFormatting.RED + TextFormatting.BOLD.toString() +
                            "IBE Clipboard corrupted. See logs for details.", TextFormatting.RED + e.toString());
            IBEEditorMod.LOGGER.throwing(e);
        }
    }
}

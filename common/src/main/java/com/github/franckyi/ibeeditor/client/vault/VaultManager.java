package com.github.franckyi.ibeeditor.client.vault;

import com.github.franckyi.ibeeditor.PlatformUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

public final class VaultManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Path FILE = PlatformUtil.getConfigDir().resolve("ibeeditor-vault.dat");
    private static Multimap<VaultItemType, VaultItemModel> items;

    private VaultManager() {
    }

    public static Multimap<VaultItemType, VaultItemModel> getItems() {
        return items;
    }

    public static void load() {
        Multimap<VaultItemType, VaultItemModel> newItems = ArrayListMultimap.create();
        if (!Files.exists(FILE)) {
            items = newItems;
            save();
            return;
        }
        try {
            byte[] data = Files.readAllBytes(FILE);
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(data));
            for (VaultItemType type : VaultItemType.values()) {
                int count = buf.readInt();
                for (int i = 0; i < count; i++) {
                    VaultItemModel item = type.read(buf);
                    items.put(type, item);
                }
            }
            items = newItems;
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public static void save() {
        try {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            for (VaultItemType type : VaultItemType.values()) {
                Collection<VaultItemModel> typeItems = items.get(type);
                buf.writeInt(typeItems.size());
                typeItems.forEach(item -> item.write(buf));
            }
            Files.write(FILE, buf.array());
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}

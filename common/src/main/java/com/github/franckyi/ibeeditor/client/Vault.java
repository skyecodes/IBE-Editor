package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.PlatformUtil;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.nbt.*;
import net.minecraft.network.FriendlyByteBuf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class Vault {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int CURRENT_VERSION = 0;
    private static final Path VAULT_FILE_OLD = PlatformUtil.getConfigDir().resolve("ibeeditor-clipboard.dat");
    private static final Path VAULT_FILE = PlatformUtil.getConfigDir().resolve("ibeeditor-vault.dat");
    private static final FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
    private static Vault INSTANCE;

    private int version;
    private List<CompoundTag> items;
    private List<CompoundTag> entities;

    private Vault() {
        version = CURRENT_VERSION;
        items = new ArrayList<>();
        entities = new ArrayList<>();
    }

    public List<CompoundTag> getItems() {
        return items;
    }

    public List<CompoundTag> getEntities() {
        return entities;
    }

    public boolean saveItem(CompoundTag tag) {
        if (items.contains(tag)) {
            return false;
        }
        items.add(tag);
        save();
        return true;
    }

    public boolean saveEntity(CompoundTag tag) {
        if (entities.contains(tag)) {
            return false;
        }
        entities.add(tag);
        save();
        return true;
    }

    public void clear() {
        items.clear();
        entities.clear();
    }

    public static void load() {
        if (Files.exists(VAULT_FILE)) {
            loadFromFile(VAULT_FILE);
        } else if (Files.exists(VAULT_FILE_OLD)) {
            LOGGER.info("Detected old clipboard file, converting it to vault file");
            loadFromFile(VAULT_FILE_OLD);
            try {
                Files.delete(VAULT_FILE_OLD);
            } catch (IOException e) {
                LOGGER.error("Error while deleting the old clipboard file", e);
            }
            INSTANCE.version = 0;
            save();
        } else {
            LOGGER.info("Generating empty vault");
            INSTANCE = new Vault();
            save();
        }
    }

    private static void loadFromFile(Path path) {
        INSTANCE = new Vault();
        try (var is = Files.newInputStream(path)) {
            buffer.writeBytes(is, is.available());
            INSTANCE.version = buffer.readInt();
            IntStream.range(0, buffer.readInt()).forEach(i -> INSTANCE.items.add(safeReadNbt()));
            IntStream.range(0, buffer.readInt()).forEach(i -> INSTANCE.entities.add(safeReadNbt()));
            LOGGER.info("Vault loaded");
        } catch (IOException e) {
            LOGGER.error("Error while loading vault", e);
        }
    }

    private static CompoundTag safeReadNbt() {
        var i = buffer.readerIndex();
        try {
            var result = buffer.readNbt();
            if (result == null || result.isEmpty()) throw new RuntimeException("Tag is empty, this must be an error");
            return result;
        } catch (Exception e) {
            buffer.readerIndex(i);
            byte b0 = buffer.readByte();
            if (b0 != Tag.TAG_COMPOUND) {
                throw e;
            } else {
                buffer.readerIndex(i);
                try {
                    return NbtIo.read(new ByteBufInputStream(buffer));
                } catch (IOException e0) {
                    throw new RuntimeException(e0);
                }
            }
        }
    }

    public static void save() {
        try (var os = Files.newOutputStream(VAULT_FILE)) {
            buffer.writeInt(INSTANCE.version);
            buffer.writeInt(INSTANCE.items.size());
            INSTANCE.items.forEach(buffer::writeNbt);
            buffer.writeInt(INSTANCE.entities.size());
            INSTANCE.entities.forEach(buffer::writeNbt);
            buffer.readBytes(os, buffer.readableBytes());
            LOGGER.info("Vault saved");
        } catch (IOException e) {
            LOGGER.error("Error while saving vault", e);
        }
    }

    public static Vault getInstance() {
        return INSTANCE;
    }
}

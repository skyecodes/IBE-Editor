package com.github.franckyi.ibeeditor.client.clipboard;

import net.minecraft.network.PacketBuffer;

public interface IClipboardEntry {

    void write(PacketBuffer buffer);

}

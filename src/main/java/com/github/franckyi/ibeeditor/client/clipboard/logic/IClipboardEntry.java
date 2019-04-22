package com.github.franckyi.ibeeditor.client.clipboard.logic;

import net.minecraft.network.PacketBuffer;

public interface IClipboardEntry {

    void write(PacketBuffer buffer);

}

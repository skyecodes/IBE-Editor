package com.github.franckyi.ibeeditor.base.common;

import com.github.franckyi.gameadapter.api.common.IPacketBuffer;

public interface Packet {
    void write(IPacketBuffer buffer);
}

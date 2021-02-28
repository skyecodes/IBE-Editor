package com.github.franckyi.gamehooks.api.common.network;

import com.github.franckyi.gamehooks.api.common.BlockPos;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public interface Buffer {
    <B> B getBuffer();

    ObjectTag readTag();

    void writeTag(ObjectTag tag);

    int readInt();

    void writeInt(int i);

    BlockPos readPos();

    void writePos(BlockPos blockPos);

    boolean readBoolean();

    void writeBoolean(boolean bl);

    byte readByte();

    void writeByte(byte b);
}

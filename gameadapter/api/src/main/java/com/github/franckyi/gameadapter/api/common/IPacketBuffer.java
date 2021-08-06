package com.github.franckyi.gameadapter.api.common;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import io.netty.buffer.ByteBuf;

public interface IPacketBuffer {
    ICompoundTag readTag();

    void writeTag(ICompoundTag tag);

    int readInt();

    ByteBuf writeInt(int i);

    IBlockPos readPos();

    void writePos(IBlockPos blockPos);

    byte readByte();

    ByteBuf writeByte(int i);

    IBlockState readBlockState();

    void writeBlockState(IBlockState blockState);
}

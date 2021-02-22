package com.github.franckyi.gamehooks.api.common.network;

import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public interface Buffer<B> {
    B getBuffer();

    ObjectTag readTag();

    void writeTag(ObjectTag tag);

    int readInt();

    void writeInt(int i);

    Pos readBlockPos();

    void writeBlockPos(Pos pos);
}

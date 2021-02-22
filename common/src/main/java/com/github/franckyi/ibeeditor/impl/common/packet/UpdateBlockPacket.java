package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.network.Buffer;
import com.github.franckyi.gamehooks.api.common.network.Packet;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public class UpdateBlockPacket implements Packet {
    private final Pos pos;
    private final ObjectTag tag;

    public UpdateBlockPacket(Pos pos, ObjectTag tag) {
        this.pos = pos;
        this.tag = tag;
    }

    public UpdateBlockPacket(Buffer buffer) {
        this(buffer.readPos(), buffer.readTag());
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writePos(pos);
        buffer.writeTag(tag);
    }

    public Pos getPos() {
        return pos;
    }

    public ObjectTag getTag() {
        return tag;
    }
}

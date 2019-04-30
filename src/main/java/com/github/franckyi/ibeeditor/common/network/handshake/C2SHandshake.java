package com.github.franckyi.ibeeditor.common.network.handshake;

import com.github.franckyi.ibeeditor.IBECommand;
import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.common.network.EmptyMessage;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class C2SHandshake extends EmptyMessage {

    public C2SHandshake() {
    }

    public C2SHandshake(PacketBuffer buffer) {
        super(buffer);
    }

    @Override
    public void handle(NetworkEvent.Context ctx) {
        IBEEditorMod.LOGGER.debug("Recieved handshake from client. The mod is installed on the client.");
        IBECommand.addAllowedPlayer(ctx.getSender());
    }
}

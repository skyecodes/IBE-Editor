package com.github.franckyi.ibeeditor.common.network.handshake;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.client.EditorHelper;
import com.github.franckyi.ibeeditor.common.network.EmptyMessage;
import com.github.franckyi.ibeeditor.common.network.IBENetworkHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class S2CHandshake extends EmptyMessage {

    public S2CHandshake() {
    }

    public S2CHandshake(PacketBuffer buffer) {
        super(buffer);
    }

    @Override
    public void handle(NetworkEvent.Context ctx) {
        IBEEditorMod.LOGGER.debug("Recieved handshake from server. The mod is installed on the server.");
        EditorHelper.enableServer();
        IBEEditorMod.LOGGER.debug("Notifying the server that we also have installed the mod.");
        IBENetworkHandler.getModChannel().sendToServer(new C2SHandshake());
    }
}

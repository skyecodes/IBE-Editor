package com.github.franckyi.ibeeditor.network;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class IBEHandshakeMessages {

    abstract static class IBEHandshake implements IPacket {
        private int loginIndex;

        public int getLoginIndex() {
            return loginIndex;
        }

        public void setLoginIndex(int loginIndex) {
            this.loginIndex = loginIndex;
        }
    }

    public static class S2CHandshake extends IBEHandshake {

        private boolean flag;

        public S2CHandshake() {
            flag = true;
        }

        public S2CHandshake(PacketBuffer buffer) {
            flag = buffer.readBoolean();
        }

        @Override
        public void write(PacketBuffer buffer) {
            buffer.writeBoolean(true);
        }

        @Override
        public void handle(Supplier<NetworkEvent.Context> c) {
            if (flag) {
                c.get().setPacketHandled(true);
                IBEEditorMod.LOGGER.debug("Client handshake OK");
                IBENetworkHandler.getHandshakeChannel().reply(new C2SHandshake(), c.get());
            }
        }
    }

    public static class C2SHandshake extends IBEHandshake {

        private boolean flag;

        public C2SHandshake() {
            flag = true;
        }

        public C2SHandshake(PacketBuffer buffer) {
            flag = buffer.readBoolean();
        }

        @Override
        public void write(PacketBuffer buffer) {
            buffer.writeBoolean(true);
        }

        @Override
        public void handle(Supplier<NetworkEvent.Context> c) {
            if (flag) {
                c.get().setPacketHandled(true);
                IBEEditorMod.LOGGER.debug("Server handshake OK");
            }
        }
    }

}

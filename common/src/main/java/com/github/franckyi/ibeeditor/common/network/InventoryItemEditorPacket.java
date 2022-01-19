package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.network.FriendlyByteBuf;

public abstract class InventoryItemEditorPacket extends ItemEditorPacket {
    protected abstract static class RequestData {
        private int slot;

        protected RequestData() {
        }

        protected RequestData(int slot) {
            this.slot = slot;
        }

        protected void setSlot(int slot) {
            this.slot = slot;
        }

        public int getSlot() {
            return slot;
        }

        protected abstract static class Serializer<T extends RequestData> implements ImprovedPacketSerializer<T> {
            @Override
            public void write(T obj, FriendlyByteBuf buf) {
                buf.writeVarInt(obj.getSlot());
            }

            @Override
            public void read(T obj, FriendlyByteBuf buf) {
                obj.setSlot(buf.readVarInt());
            }
        }
    }

    protected abstract static class Request<REQ extends RequestData> extends AbstractEditorRequest<REQ> {
        protected Request() {
        }

        protected Request(EditorType editorType, REQ requestData) {
            super(editorType, requestData);
        }

        public int getSlot() {
            return getRequestData().getSlot();
        }
    }

    protected abstract static class Response<REQ extends RequestData> extends ItemEditorPacket.Response<REQ> {
        protected Response() {
        }

        protected Response(AbstractEditorRequest<REQ> request, boolean permission, ResponseData responseData) {
            super(request, permission, responseData);
        }

        public int getSlot() {
            return getRequestData().getSlot();
        }
    }

    protected abstract static class Update<REQ extends RequestData> extends ItemEditorPacket.Update<REQ> {
        protected Update() {
        }

        protected Update(REQ requestData, ResponseData responseData) {
            super(requestData, responseData);
        }

        public int getSlot() {
            return getRequestData().getSlot();
        }
    }
}

package com.github.franckyi.ibeeditor.common.network;

import net.minecraft.network.FriendlyByteBuf;

public abstract class AbstractEditorUpdate<REQ, RES> {
    private REQ requestData;
    private RES responseData;

    protected AbstractEditorUpdate() {
    }

    protected AbstractEditorUpdate(REQ requestData, RES responseData) {
        this.requestData = requestData;
        this.responseData = responseData;
    }

    protected REQ getRequestData() {
        return requestData;
    }

    protected void setRequestData(REQ requestData) {
        this.requestData = requestData;
    }

    protected RES getResponseData() {
        return responseData;
    }

    protected void setResponseData(RES responseData) {
        this.responseData = responseData;
    }

    protected static abstract class Serializer<T extends AbstractEditorUpdate<REQ, RES>, REQ, RES> implements ImprovedPacketSerializer<T> {
        @Override
        public void write(T obj, FriendlyByteBuf buf) {
            getRequestDataSerializer().write(obj.getRequestData(), buf);
            getResponseDataSerializer().write(obj.getResponseData(), buf);

        }

        @Override
        public void read(T obj, FriendlyByteBuf buf) {
            obj.setRequestData(getRequestDataSerializer().read(buf));
            obj.setResponseData(getResponseDataSerializer().read(buf));
        }

        protected abstract PacketSerializer<REQ> getRequestDataSerializer();

        protected abstract PacketSerializer<RES> getResponseDataSerializer();
    }
}

package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.network.FriendlyByteBuf;

public abstract class AbstractEditorRequest<REQ> {
    private EditorType editorType;
    private REQ requestData;

    protected AbstractEditorRequest() {
    }

    protected AbstractEditorRequest(EditorType editorType, REQ requestData) {
        this.editorType = editorType;
        this.requestData = requestData;
    }

    public EditorType getEditorType() {
        return editorType;
    }

    protected void setEditorType(EditorType editorType) {
        this.editorType = editorType;
    }

    protected REQ getRequestData() {
        return requestData;
    }

    protected void setRequestData(REQ requestData) {
        this.requestData = requestData;
    }

    protected static abstract class Serializer<T extends AbstractEditorRequest<REQ>, REQ> implements ImprovedPacketSerializer<T> {
        @Override
        public void write(T obj, FriendlyByteBuf buf) {
            buf.writeEnum(obj.getEditorType());
            getRequestDataSerializer().write(obj.getRequestData(), buf);
        }

        @Override
        public void read(T obj, FriendlyByteBuf buf) {
            obj.setEditorType(buf.readEnum(EditorType.class));
            obj.setRequestData(getRequestDataSerializer().read(buf));
        }

        protected abstract PacketSerializer<REQ> getRequestDataSerializer();
    }
}

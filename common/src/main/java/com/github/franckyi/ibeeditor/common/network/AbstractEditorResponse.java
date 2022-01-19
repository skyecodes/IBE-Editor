package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.network.FriendlyByteBuf;

public abstract class AbstractEditorResponse<REQ, RES> extends AbstractEditorRequest<REQ> {
    private boolean permission;
    private RES responseData;

    protected AbstractEditorResponse() {
    }

    protected AbstractEditorResponse(AbstractEditorRequest<REQ> request, boolean permission, RES responseData) {
        this(request.getEditorType(), request.getRequestData(), permission, responseData);
    }

    protected AbstractEditorResponse(EditorType editorType, REQ requestData, boolean permission, RES responseData) {
        super(editorType, requestData);
        this.permission = permission;
        this.responseData = responseData;
    }

    public boolean hasPermission() {
        return permission;
    }

    public void setPermission(boolean hasPermission) {
        this.permission = hasPermission;
    }

    public RES getResponseData() {
        return responseData;
    }

    public void setResponseData(RES responseData) {
        this.responseData = responseData;
    }

    protected static abstract class Serializer<T extends AbstractEditorResponse<REQ, RES>, REQ, RES> extends AbstractEditorRequest.Serializer<T, REQ> {
        @Override
        public void write(T obj, FriendlyByteBuf buf) {
            super.write(obj, buf);
            buf.writeBoolean(obj.hasPermission());
            getResponseDataSerializer().write(obj.getResponseData(), buf);
        }

        @Override
        public void read(T obj, FriendlyByteBuf buf) {
            super.read(obj, buf);
            obj.setPermission(buf.readBoolean());
            obj.setResponseData(getResponseDataSerializer().read(buf));
        }

        protected abstract PacketSerializer<RES> getResponseDataSerializer();
    }
}

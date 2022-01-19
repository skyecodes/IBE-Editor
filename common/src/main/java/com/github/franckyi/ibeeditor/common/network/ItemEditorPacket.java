package com.github.franckyi.ibeeditor.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public abstract class ItemEditorPacket {
    protected record ResponseData(ItemStack itemStack) {
        public static final PacketSerializer<ResponseData> SERIALIZER = new PacketSerializer<>() {
            @Override
            public void write(ResponseData obj, FriendlyByteBuf buf) {
                buf.writeItem(obj.itemStack());
            }

            @Override
            public ResponseData read(FriendlyByteBuf buf) {
                return new ResponseData(buf.readItem());
            }
        };
    }

    protected abstract static class Response<REQ> extends AbstractEditorResponse<REQ, ResponseData> {
        protected Response() {
        }

        protected Response(AbstractEditorRequest<REQ> request, boolean permission, ResponseData responseData) {
            super(request, permission, responseData);
        }

        public ItemStack getItemStack() {
            return getResponseData().itemStack();
        }
    }

    protected abstract static class Update<REQ> extends AbstractEditorUpdate<REQ, ResponseData> {
        protected Update() {
        }

        protected Update(REQ requestData, ResponseData responseData) {
            super(requestData, responseData);
        }

        public ItemStack getItemStack() {
            return getResponseData().itemStack();
        }
    }
}

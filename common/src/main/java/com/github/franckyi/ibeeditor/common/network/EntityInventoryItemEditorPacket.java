package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public final class EntityInventoryItemEditorPacket extends InventoryItemEditorPacket {
    public static class RequestData extends InventoryItemEditorPacket.RequestData {
        public static final PacketSerializer<RequestData> SERIALIZER = new Serializer<>() {
            @Override
            public void write(RequestData obj, FriendlyByteBuf buf) {
                super.write(obj, buf);
                buf.writeVarInt(obj.getEntityId());
            }

            @Override
            public void read(RequestData obj, FriendlyByteBuf buf) {
                super.read(obj, buf);
                obj.setEntityId(buf.readVarInt());
            }

            @Override
            public RequestData createInstance() {
                return new RequestData();
            }
        };

        private int entityId;

        private RequestData() {
        }

        public RequestData(int slot, int entityId) {
            super(slot);
            this.entityId = entityId;
        }

        public int getEntityId() {
            return entityId;
        }

        public void setEntityId(int entityId) {
            this.entityId = entityId;
        }
    }

    public static class Request extends InventoryItemEditorPacket.Request<RequestData> {
        public static final PacketSerializer<Request> SERIALIZER = new Serializer<>() {
            @Override
            public Request createInstance() {
                return new Request();
            }

            @Override
            protected PacketSerializer<RequestData> getRequestDataSerializer() {
                return RequestData.SERIALIZER;
            }
        };

        private Request() {
        }

        public Request(EditorType editorType, int slot, int entityId) {
            super(editorType, new RequestData(slot, entityId));
        }

        public int getEntityId() {
            return getRequestData().getEntityId();
        }
    }

    public static class Response extends InventoryItemEditorPacket.Response<RequestData> {
        public static final PacketSerializer<Response> SERIALIZER = new Serializer<>() {
            @Override
            public Response createInstance() {
                return new Response();
            }

            @Override
            protected PacketSerializer<ResponseData> getResponseDataSerializer() {
                return ResponseData.SERIALIZER;
            }

            @Override
            protected PacketSerializer<RequestData> getRequestDataSerializer() {
                return RequestData.SERIALIZER;
            }
        };

        private Response() {
        }

        public Response(Request request, boolean hasPermission, ItemStack item) {
            super(request, hasPermission, new ResponseData(item));
        }
    }

    public static class Update extends InventoryItemEditorPacket.Update<RequestData> {
        public static final PacketSerializer<Update> SERIALIZER = new Serializer<>() {
            @Override
            public Update createInstance() {
                return new Update();
            }

            @Override
            protected PacketSerializer<RequestData> getRequestDataSerializer() {
                return RequestData.SERIALIZER;
            }

            @Override
            protected PacketSerializer<ResponseData> getResponseDataSerializer() {
                return ResponseData.SERIALIZER;
            }
        };

        private Update() {
        }

        public Update(Response response, ItemStack itemStack) {
            super(response.getRequestData(), new ResponseData(itemStack));
        }

        public int getEntityId() {
            return getRequestData().getEntityId();
        }
    }
}

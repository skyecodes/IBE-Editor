package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public final class EntityEditorPacket {
    public record RequestData(int entityId) {
        public static final PacketSerializer<RequestData> SERIALIZER = new PacketSerializer<>() {
            @Override
            public void write(RequestData obj, FriendlyByteBuf buf) {
                buf.writeVarInt(obj.entityId());
            }

            @Override
            public RequestData read(FriendlyByteBuf buf) {
                return new RequestData(buf.readVarInt());
            }
        };
    }

    public record ResponseData(CompoundTag tag) {
        public static final PacketSerializer<ResponseData> SERIALIZER = new PacketSerializer<>() {
            @Override
            public void write(ResponseData obj, FriendlyByteBuf buf) {
                buf.writeNbt(obj.tag());
            }

            @Override
            public ResponseData read(FriendlyByteBuf buf) {
                return new ResponseData(buf.readNbt());
            }
        };
    }

    public static class Request extends AbstractEditorRequest<RequestData> {
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

        public Request(EditorType editorType, int entityId) {
            super(editorType, new RequestData(entityId));
        }

        public int getEntityId() {
            return getRequestData().entityId();
        }
    }

    public static class Response extends AbstractEditorResponse<RequestData, ResponseData> {
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

        public Response(Request request, boolean hasPermission, CompoundTag tag) {
            super(request, hasPermission, new ResponseData(tag));
        }

        public CompoundTag getTag() {
            return getResponseData().tag();
        }
    }

    public static class Update extends AbstractEditorUpdate<RequestData, ResponseData> {
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

        public Update(Response response, CompoundTag tag) {
            super(response.getRequestData(), new ResponseData(tag));
        }

        public int getEntityId() {
            return getRequestData().entityId();
        }

        public CompoundTag getTag() {
            return getResponseData().tag();
        }
    }
}

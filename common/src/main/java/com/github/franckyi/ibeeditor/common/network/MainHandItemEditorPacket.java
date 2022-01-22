package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.world.item.ItemStack;

public final class MainHandItemEditorPacket extends ItemEditorPacket {
    public record RequestData() {
        public static final RequestData INSTANCE = new RequestData();
        public static final PacketSerializer<RequestData> SERIALIZER = PacketSerializer.empty(INSTANCE);
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

        public Request(EditorType editorType) {
            super(editorType, RequestData.INSTANCE);
        }
    }

    public static class Response extends ItemEditorPacket.Response<RequestData> {
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

        public Response(Request request, boolean permission, ItemStack itemStack) {
            super(request, permission, new ResponseData(itemStack));
        }
    }

    public static class Update extends ItemEditorPacket.Update<RequestData> {
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
    }
}

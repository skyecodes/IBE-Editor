package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public class BlockInventoryItemEditorPacket extends InventoryItemEditorPacket {
    public static class RequestData extends InventoryItemEditorPacket.RequestData {
        private BlockPos blockPos;

        public RequestData() {
        }

        public RequestData(int slot, BlockPos blockPos) {
            super(slot);
            this.blockPos = blockPos;
        }

        public BlockPos getBlockPos() {
            return blockPos;
        }

        public void setBlockPos(BlockPos blockPos) {
            this.blockPos = blockPos;
        }

        public static final PacketSerializer<RequestData> SERIALIZER = new Serializer<>() {
            @Override
            public void write(RequestData obj, FriendlyByteBuf buf) {
                super.write(obj, buf);
                buf.writeBlockPos(obj.getBlockPos());
            }

            @Override
            public void read(RequestData obj, FriendlyByteBuf buf) {
                super.read(obj, buf);
                obj.setBlockPos(buf.readBlockPos());
            }

            @Override
            public RequestData createInstance() {
                return new RequestData();
            }
        };
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

        public Request() {
        }

        public Request(EditorType editorType, int slot, BlockPos blockPos) {
            super(editorType, new RequestData(slot, blockPos));
        }

        public BlockPos getBlockPos() {
            return getRequestData().getBlockPos();
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

        public Response() {
        }

        public Response(Request request, boolean hasPermission, ItemStack item) {
            super(request, hasPermission, new ResponseData(item));
        }

        public BlockPos getBlockPos() {
            return getRequestData().getBlockPos();
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

        public Update() {
        }

        public Update(Response response, ItemStack itemStack) {
            super(response.getRequestData(), new ResponseData(itemStack));
        }

        public BlockPos getBlockPos() {
            return getRequestData().getBlockPos();
        }
    }
}

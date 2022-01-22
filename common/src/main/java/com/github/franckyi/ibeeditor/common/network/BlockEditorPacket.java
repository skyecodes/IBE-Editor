package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.state.BlockState;

public final class BlockEditorPacket {
    protected record RequestData(BlockPos blockPos) {
        public static final PacketSerializer<RequestData> SERIALIZER = new PacketSerializer<>() {
            @Override
            public void write(RequestData obj, FriendlyByteBuf buf) {
                buf.writeBlockPos(obj.blockPos());
            }

            @Override
            public RequestData read(FriendlyByteBuf buf) {
                return new RequestData(buf.readBlockPos());
            }
        };
    }

    protected record ResponseData(BlockState blockState, CompoundTag tag) {
        public static final PacketSerializer<ResponseData> SERIALIZER = new PacketSerializer<>() {
            @Override
            public void write(ResponseData obj, FriendlyByteBuf buf) {
                buf.writeWithCodec(BlockState.CODEC, obj.blockState());
                buf.writeNbt(obj.tag());
            }

            @Override
            public ResponseData read(FriendlyByteBuf buf) {
                return new ResponseData(buf.readWithCodec(BlockState.CODEC), buf.readNbt());
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

        public Request(EditorType editorType, BlockPos blockPos) {
            super(editorType, new RequestData(blockPos));
        }

        public BlockPos getBlockPos() {
            return getRequestData().blockPos();
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

        public Response(Request request, boolean hasPermission, BlockState blockState, CompoundTag tag) {
            super(request, hasPermission, new ResponseData(blockState, tag));
        }

        public BlockPos getBlockPos() {
            return getRequestData().blockPos();
        }

        public BlockState getBlockState() {
            return getResponseData().blockState();
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

        public Update(Response response, BlockState blockState, CompoundTag tag) {
            super(response.getRequestData(), new ResponseData(blockState, tag));
        }

        public BlockPos getBlockPos() {
            return getRequestData().blockPos();
        }

        public BlockState getBlockState() {
            return getResponseData().blockState();
        }

        public CompoundTag getTag() {
            return getResponseData().tag();
        }
    }
}

package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.world.IEntity;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.util.Nameable;
import org.spongepowered.asm.mixin.*;

@Mixin(Entity.class)
@Implements(@Interface(iface = IEntity.class, prefix = "proxy$"))
public abstract class FabricEntityMixin implements Nameable, CommandOutput {
    @Shadow
    public abstract boolean saveSelfNbt(NbtCompound nbt);

    @Shadow
    public abstract NbtCompound writeNbt(NbtCompound nbt);

    @Shadow
    public abstract int getEntityId();

    public ICompoundTag proxy$getData() {
        NbtCompound compound = new NbtCompound();
        if (!saveSelfNbt(compound)) {
            writeNbt(compound);
        }
        return (ICompoundTag) compound;
    }

    @Intrinsic
    public int proxy$getEntityId() {
        return getEntityId();
    }
}

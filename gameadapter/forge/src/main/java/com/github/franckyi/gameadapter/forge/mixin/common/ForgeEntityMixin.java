package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IEntity;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import net.minecraft.command.ICommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.INameable;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Entity.class)
public abstract class ForgeEntityMixin extends CapabilityProvider<Entity> implements INameable, ICommandSource, IEntity {
    @Shadow
    public abstract boolean saveAsPassenger(CompoundNBT p_184198_1_);

    @Shadow
    public abstract CompoundNBT saveWithoutId(CompoundNBT p_189511_1_);

    @Shadow
    public abstract int getId();

    protected ForgeEntityMixin(Class<Entity> baseClass) {
        super(baseClass);
    }

    @Override
    public ICompoundTag getData() {
        CompoundNBT compound = new CompoundNBT();
        if (!saveAsPassenger(compound)) {
            saveWithoutId(compound);
        }
        return (ICompoundTag) compound;
    }

    @Override
    public int getEntityId() {
        return getId();
    }
}

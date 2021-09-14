package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.world.IEntity;
import net.minecraft.commands.CommandSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Entity.class)
public abstract class ForgeEntityMixin extends CapabilityProvider<Entity> implements Nameable, CommandSource, IEntity {
    @Shadow
    public abstract boolean saveAsPassenger(CompoundTag p_184198_1_);

    @Shadow
    public abstract CompoundTag saveWithoutId(CompoundTag p_189511_1_);

    @Shadow
    public abstract int getId();

    protected ForgeEntityMixin(Class<Entity> baseClass) {
        super(baseClass);
    }

    @Override
    public ICompoundTag getData() {
        CompoundTag compound = new CompoundTag();
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

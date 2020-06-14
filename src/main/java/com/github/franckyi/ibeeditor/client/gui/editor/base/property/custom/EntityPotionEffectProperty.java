package com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom;

import com.github.franckyi.ibeeditor.client.gui.editor.base.category.EditableCategory;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class EntityPotionEffectProperty extends BasePotionEffectProperty<EffectInstance> {

    public EntityPotionEffectProperty(EditableCategory<?> category, int index, EffectInstance initialValue, Consumer<EffectInstance> action) {
        super(category, index, initialValue, action);
    }

    @Override
    protected EffectInstance getValue() {
        return new EffectInstance(ForgeRegistries.POTIONS.getValue(ResourceLocation.tryCreate(name.getValue())),
                duration.getValue(), amplifier.getValue(), false, false, false);
    }
}

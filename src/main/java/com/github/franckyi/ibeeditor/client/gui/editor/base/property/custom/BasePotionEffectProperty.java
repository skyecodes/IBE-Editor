package com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom;

import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.IntegerField;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.IEditableCategoryProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.custom.model.PotionEffectModel;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public abstract class BasePotionEffectProperty<T extends EffectInstance> extends AbstractProperty<T> implements IEditableCategoryProperty {

    protected final PropertyControls controls;

    protected TextField name;
    protected IntegerField amplifier;
    protected IntegerField duration;

    public BasePotionEffectProperty(EditableCategory<?> category, int index, T initialValue, Consumer<T> action) {
        super(initialValue, action);
        controls = new PropertyControls(category, index);
        IEditableCategoryProperty.super.build();
    }

    @Override
    protected void setValue(T value) {
        name.setValue(value.getPotion().getRegistryName().toString());
        amplifier.setValue(value.getAmplifier());
        duration.setValue(value.getDuration());
    }

    @Override
    public PropertyControls getControls() {
        return controls;
    }

    @Override
    public void build() {
        this.getNode().setAlignment(Pos.LEFT);
        this.addAll(
                name = new TextField(),
                amplifier = new IntegerField(),
                duration = new IntegerField()
        );
        amplifier.setPrefWidth(20);
        duration.setPrefWidth(32);
        name.getTooltipText().add("Potion name");
        amplifier.getTooltipText().add("Amplifier (0 => level 1)");
        duration.getTooltipText().add("Duration (in ticks)");
    }

    @Override
    public void updateSize(int listWidth) {
        name.setPrefWidth(listWidth - OFFSET - 159);
    }
}

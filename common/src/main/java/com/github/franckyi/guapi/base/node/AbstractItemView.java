package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.ItemView;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractItemView extends AbstractControl implements ItemView {
    private final ObjectProperty<ItemStack> itemProperty = ObjectProperty.create();
    private final BooleanProperty drawDecorationsProperty = BooleanProperty.create(false);
    private final BooleanProperty drawTooltipProperty = BooleanProperty.create(true);

    protected AbstractItemView() {
    }

    protected AbstractItemView(ItemStack itemStack) {
        setItem(itemStack);
    }

    @Override
    public ObjectProperty<ItemStack> itemProperty() {
        return itemProperty;
    }

    @Override
    public BooleanProperty drawDecorationsProperty() {
        return drawDecorationsProperty;
    }

    @Override
    public BooleanProperty drawTooltipProperty() {
        return drawTooltipProperty;
    }
}

package com.github.franckyi.ibeeditor.client.context;

import com.github.franckyi.ibeeditor.client.ClientUtil;
import com.github.franckyi.ibeeditor.client.Vault;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class ItemEditorContext extends EditorContext<ItemEditorContext> {
    private ItemStack itemStack;

    public ItemEditorContext(ItemStack itemStack, Component errorTooltip, boolean canSaveToVault, Consumer<ItemEditorContext> action) {
        super(itemStack.save(new CompoundTag()), errorTooltip, canSaveToVault, action);
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public void update() {
        itemStack = ItemStack.of(getTag());
        super.update();
    }

    @Override
    public void saveToVault() {
        Vault.getInstance().saveItem(getTag());
        ClientUtil.showMessage(ModTexts.Messages.successSavedVault(ModTexts.ITEM));
    }

    @Override
    public MutableComponent getTargetName() {
        return ModTexts.ITEM;
    }

    @Override
    public String getCommandName() {
        return "/give";
    }

    @Override
    protected String getCommand() {
        return String.format("/give @p %s%s %s", getItemStack().getItem(), getTag().get("tag"), getItemStack().getCount());
    }
}

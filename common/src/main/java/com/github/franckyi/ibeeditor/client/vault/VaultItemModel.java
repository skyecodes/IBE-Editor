package com.github.franckyi.ibeeditor.client.vault;

import com.github.franckyi.guapi.api.mvc.Model;
import net.minecraft.network.FriendlyByteBuf;

public abstract class VaultItemModel implements Model {
    public abstract void write(FriendlyByteBuf buf);
}

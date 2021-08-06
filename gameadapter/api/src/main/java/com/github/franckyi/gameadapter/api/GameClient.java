package com.github.franckyi.gameadapter.api;

import com.github.franckyi.gameadapter.api.client.IKeyBinding;
import com.github.franckyi.gameadapter.api.client.IRenderer;
import com.github.franckyi.gameadapter.api.client.ISprite;
import com.github.franckyi.gameadapter.api.common.*;

public interface GameClient {
    IRenderer getRenderer();

    IKeyBinding registerKeyBinding(String name, int keyCode, String category);

    IPlayer getPlayer();

    IWorld getWorld();

    IEntity getEntityMouseOver();

    WorldBlockData getBlockMouseOver();

    ISprite getEffectSprite(IIdentifier effectId);

    void updateInventoryItem(IItemStack itemStack, int slotId);

    @Deprecated
    void unlockCursor();
}

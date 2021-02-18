package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.util.common.text.Text;

public interface Player {
    Item getItemMainHand();

    void sendMessage(Text message, boolean actionBar);

    Entity getPlayerEntity();
}

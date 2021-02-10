package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.api.common.text.Text;

public interface Player {
    Item getItemMainHand();

    void sendMessage(Text message, boolean actionBar);

    <P> P getPlayerEntity();
}

package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.util.common.text.Text;

public interface Item {
    <I> I createItemStack();

    String getId();

    void setId(String value);

    Text getName();

    void setName(Text value);
}

package com.github.franckyi.minecraft.api.common.world;

import com.github.franckyi.minecraft.api.common.tag.CompoundTag;
import com.github.franckyi.minecraft.api.common.text.Text;

public interface Item {
    CompoundTag getData();

    <S> S get();
}

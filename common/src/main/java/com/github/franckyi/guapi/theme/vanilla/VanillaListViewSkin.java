package com.github.franckyi.guapi.theme.vanilla;

import com.github.franckyi.guapi.node.ListView;
import com.github.franckyi.guapi.theme.AbstractSkin;
import com.github.franckyi.guapi.theme.Skin;

@SuppressWarnings("rawtypes")
public class VanillaListViewSkin extends AbstractSkin<ListView> {
    public static final Skin<ListView> INSTANCE = new VanillaListViewSkin();

    private VanillaListViewSkin() {
    }

    @Override
    public int computeWidth(ListView node) {
        return 0;
    }

    @Override
    public int computeHeight(ListView node) {
        return 0;
    }
}

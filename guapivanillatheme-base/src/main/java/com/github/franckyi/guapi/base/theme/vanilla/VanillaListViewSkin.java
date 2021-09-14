package com.github.franckyi.guapi.base.theme.vanilla;

import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.theme.DelegatedRenderer;
import com.github.franckyi.guapi.base.theme.DelegatedSkin;

@SuppressWarnings("rawtypes")
public class VanillaListViewSkin extends DelegatedSkin<ListView> {
    public VanillaListViewSkin(DelegatedRenderer delegatedRenderer) {
        super(delegatedRenderer);
    }

    @Override
    public int computeWidth(ListView node) {
        return 100;
    }

    @Override
    public int computeHeight(ListView node) {
        return node.getItemHeight() * node.getItems().size();
    }
}

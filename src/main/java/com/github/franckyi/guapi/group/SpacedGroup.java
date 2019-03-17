package com.github.franckyi.guapi.group;

import com.github.franckyi.guapi.Group;

public abstract class SpacedGroup extends Group {

    private int spacing;

    protected SpacedGroup() {
        this.spacing = 0;
    }

    protected SpacedGroup(int spacing) {
        this.spacing = spacing;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }
}

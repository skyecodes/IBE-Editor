package com.github.franckyi.guapi.group;

import com.github.franckyi.guapi.Group;
import com.github.franckyi.guapi.Node;

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

    protected int getStartX() {
        int start = this.getX() + this.getPadding().getLeft();
        int space = this.getWidth() - this.getComputedWidth();
        switch (this.getAlignment().getHorizontalPos()) {
            case LEFT:
                return start;
            case CENTER:
                return start + space / 2;
            case RIGHT:
                return start + space;
        }
        return 0;
    }

    protected int getStartY() {
        int start = this.getY() + this.getPadding().getTop();
        int space = this.getHeight() - this.getComputedHeight();
        switch (this.getAlignment().getVerticalPos()) {
            case TOP:
                return start;
            case CENTER:
                return start + space / 2;
            case BOTTOM:
                return start + space;
        }
        return 0;
    }

    protected int getChildX(Node child) {
        int start = this.getX() + this.getPadding().getLeft();
        int space = this.getWidth() - child.getWidth();
        switch (this.getAlignment().getHorizontalPos()) {
            case LEFT:
                return start + child.getMargin().getLeft();
            case CENTER:
                return start + space / 2;
            case RIGHT:
                return start + space - child.getMargin().getRight();
        }
        return 0;
    }

    protected int getChildY(Node child) {
        int start = this.getY() + this.getPadding().getTop();
        int space = this.getHeight() - child.getHeight();
        switch (this.getAlignment().getVerticalPos()) {
            case TOP:
                return start + child.getMargin().getTop();
            case CENTER:
                return start + space / 2;
            case BOTTOM:
                return start + space - child.getMargin().getBottom();
        }
        return 0;
    }
}

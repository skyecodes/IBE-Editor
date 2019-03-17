package com.github.franckyi.guapi.group;

import com.github.franckyi.guapi.Node;

public class HBox extends SpacedGroup {

    public HBox() {
    }

    public HBox(int spacing) {
        super(spacing);
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(this.getChildren().stream().mapToInt(node -> node.getWidth() + node.getMargin().getHorizontal()).sum() + this.getPadding().getHorizontal() + this.getSpacing() * (this.getChildren().size() - 1));
    }

    @Override
    protected void computeHeight() {
        this.getChildren().stream().mapToInt(node -> node.getHeight() + node.getMargin().getVertical()).max().ifPresent(computedHeight -> this.setComputedHeight(computedHeight + this.getPadding().getVertical()));
    }

    @Override
    public void updateChildrenPos() {
        this.computeSize();
        this.updateSize();
        int x = this.getAlignment().getStartX(this);
        int y = this.getAlignment().getStartY(this);
        for (Node node : this.getChildren()) {
            node.setPosition(x + node.getMargin().getLeft(), y + node.getMargin().getTop());
            x += node.getWidth() + node.getMargin().getHorizontal() + this.getSpacing();
        }
    }

}

package com.github.franckyi.guapi.group;

import com.github.franckyi.guapi.Node;

public class VBox extends SpacedGroup {

    public VBox() {
    }

    public VBox(int spacing) {
        super(spacing);
    }

    @Override
    protected void computeWidth() {
        this.getChildren().stream().mapToInt(node -> node.getWidth() + node.getMargin().getHorizontal()).max().ifPresent(computedWidth -> this.setComputedWidth(computedWidth + this.getPadding().getHorizontal()));
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(this.getChildren().stream().mapToInt(node -> node.getHeight() + node.getMargin().getVertical()).sum() + this.getPadding().getVertical() + this.getSpacing() * (this.getChildren().size() - 1));
    }

    @Override
    public void updateChildrenPos() {
        this.computeSize();
        this.updateSize();
        int x = this.getAlignment().getStartX(this);
        int y = this.getAlignment().getStartY(this);
        for (Node node : this.getChildren()) {
            node.setPosition(x + node.getMargin().getLeft(), y + node.getMargin().getTop());
            y += node.getHeight() + node.getMargin().getVertical() + this.getSpacing();
        }
    }

}

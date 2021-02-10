package com.github.franckyi.guapi.api.node;

public interface Weighted extends Group {
    void setWeight(Node node, int value);

    void resetWeight(Node node);

    int getWeight(Node node);
}

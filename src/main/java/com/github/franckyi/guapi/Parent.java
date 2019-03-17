package com.github.franckyi.guapi;

import java.util.List;

public interface Parent {

    void updateChildrenPos();

    List<? extends ScreenEventListener> getChildren();

}

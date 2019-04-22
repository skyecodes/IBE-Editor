package com.github.franckyi.guapi;

import java.util.List;

public interface IParent {

    void updateChildrenPos();

    List<? extends IScreenEventListener> getChildren();

}

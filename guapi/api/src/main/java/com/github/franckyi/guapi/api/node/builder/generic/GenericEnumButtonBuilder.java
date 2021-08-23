package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.node.EnumButton;

import java.util.function.Function;

public interface GenericEnumButtonBuilder<E extends Enum<E>, N extends EnumButton<E>> extends EnumButton<E>, GenericButtonBuilder<N> {
    default N value(E value) {
        return with(n -> n.setValue(value));
    }

    default N valueIndex(int value) {
        return with(n -> n.setValueIndex(value));
    }

    default N textFactory(Function<E, IText> value) {
        return with(n -> n.setTextFactory(value));
    }
}

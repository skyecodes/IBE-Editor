package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.EnumButton;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Function;

public interface GenericEnumButtonBuilder<E extends Enum<E>, N extends EnumButton<E>> extends EnumButton<E>, GenericButtonBuilder<N> {
    default N value(E value) {
        return with(n -> n.setValue(value));
    }

    default N textFactory(Function<E, Text> value) {
        return with(n -> n.setTextFactory(value));
    }
}

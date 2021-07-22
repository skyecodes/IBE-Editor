package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.builder.EnumButtonBuilder;
import com.github.franckyi.guapi.api.util.NodeType;

public class EnumButtonImpl<E extends Enum<E>> extends AbstractEnumButton<E> implements EnumButtonBuilder<E> {
    public EnumButtonImpl(Class<? extends E> enumClass) {
        super(enumClass);
    }

    public EnumButtonImpl(E value) {
        super(value);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.ENUM_BUTTON;
    }
}

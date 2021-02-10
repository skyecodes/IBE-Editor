package com.github.franckyi.databindings.api.event;

import java.util.List;

public interface ListChangeEvent<E> {
    List<ChangeEntry<E>> getAllChanged();

    List<SimpleChangeEntry<E>> getAdded(boolean andReplaced);

    List<SimpleChangeEntry<E>> getRemoved(boolean andReplaced);

    List<ChangeEntry<E>> getReplaced();

    interface ChangeEntry<E> {
        int getIndex();

        E getOldValue();

        E getNewValue();

        default boolean isAdd() {
            return getOldValue() == null && getNewValue() != null;
        }

        default boolean isAddOrReplace() {
            return isAdd() || isReplace();
        }

        default boolean isRemove() {
            return getOldValue() != null && getNewValue() == null;
        }

        default boolean isRemoveOrReplace() {
            return isRemove() || isReplace();
        }

        default boolean isReplace() {
            return getOldValue() != null && getNewValue() != null;
        }
    }

    interface SimpleChangeEntry<E> {
        int getIndex();

        E getValue();
    }
}

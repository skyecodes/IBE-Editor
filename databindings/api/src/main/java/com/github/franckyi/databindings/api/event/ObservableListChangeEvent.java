package com.github.franckyi.databindings.api.event;

import com.github.franckyi.databindings.api.ObservableList;

import java.util.List;

/**
 * This event is triggered whenever an {@link ObservableList} is modified.
 *
 * @param <E> The type of elements in the list
 */
public interface ObservableListChangeEvent<E> {
    /**
     * @return The elements that were changed in this update
     */
    List<ChangeEntry<E>> getAllChanged();

    /**
     * @param andReplaced Whether or not to include elements that were added by a "set" action
     * @return The elements that were added in this update
     */
    List<SimpleChangeEntry<E>> getAdded(boolean andReplaced);

    /**
     * @param andReplaced Whether or not to include elements that were removed by a "set" action
     * @return The elements that were removed in this update
     */
    List<SimpleChangeEntry<E>> getRemoved(boolean andReplaced);

    /**
     * @return The elements that were replaced by a "set" action in this update
     */
    List<ChangeEntry<E>> getReplaced();

    /**
     * A change entry which contains the index of the element that was changed,
     * its old value (before the change) and its new value (after the change).
     *
     * @param <E> The type of elements in the list
     */
    interface ChangeEntry<E> {
        /**
         * @return The index of the changed element
         */
        int getIndex();

        /**
         * @return The old element (before the change), or {@code null} if it's an "add" action
         */
        E getOldValue();

        /**
         * @return The new element (after the change), or {@code null} if it's a "remove" action
         */
        E getNewValue();

        /**
         * @return Whether or not the element was added to the list by an "add" action
         */
        default boolean wasAdded() {
            return getOldValue() == null && getNewValue() != null;
        }

        /**
         * @return Whether or not the element was added to the list by an "add" or "set" action
         */
        default boolean wasAddedOrReplaced() {
            return wasAdded() || wasReplaced();
        }

        /**
         * @return Whether or not the element was removed from the list by a "remove" action
         */
        default boolean wasRemoved() {
            return getOldValue() != null && getNewValue() == null;
        }

        /**
         * @return Whether or not the element was removed from the list by a "removed" or "set" action
         */
        default boolean wasRemovedOrReplaced() {
            return wasRemoved() || wasReplaced();
        }

        /**
         * @return Whether or not the element was replaced in the list by a "set" action
         */
        default boolean wasReplaced() {
            return getOldValue() != null && getNewValue() != null;
        }
    }

    /**
     * A simple change entry in case of an "add" or "remove" action.
     *
     * @param <E> The type of elements in the list
     */
    interface SimpleChangeEntry<E> {
        /**
         * @return The index of the changed element
         */
        int getIndex();

        /**
         * @return The element that was added or removed from the list
         */
        E getValue();
    }
}

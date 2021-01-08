package com.github.franckyi.guapi

class Property<T>(_value: T) {
    var value = _value
        set(value) {
            if (field != value) {
                field = value
                listeners.forEach { it(value) }
            }
        }

    private val listeners = mutableSetOf<(T) -> Unit>()
    fun addListener(listener: (T) -> Unit) = listeners.add(listener)
    fun removeListener(listener: (T) -> Unit) = listeners.remove(listener)
}

class PropertyList<T>(private val value: MutableList<T> = mutableListOf()) {
    operator fun plusAssign(t: T) {
        value += t
        listeners.forEach { it() }
    }

    operator fun minusAssign(t: T) {
        value -= t
        listeners.forEach { it() }
    }

    private val listeners = mutableSetOf<() -> Unit>()
    fun addListener(listener: () -> Unit) = listeners.add(listener)
    fun removeListener(listener: () -> Unit) = listeners.remove(listener)
}
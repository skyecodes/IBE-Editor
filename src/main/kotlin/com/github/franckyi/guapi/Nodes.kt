package com.github.franckyi.guapi

import net.minecraft.client.MinecraftClient

abstract class Node : Drawable {
    companion object {
        const val INFINITE = Double.MAX_VALUE
        const val COMPUTED = -1.0
        val mc: MinecraftClient = MinecraftClient.getInstance()
    }

    val width = Property(0.0)
    val height = Property(0.0)
    val x = Property(0.0f)
    val y = Property(0.0f)

    val computedWidth = Property(0.0)
    val computedHeight = Property(0.0)
    val minWidth = Property(0.0)
    val minHeight = Property(0.0)
    val prefWidth = Property(COMPUTED)
    val prefHeight = Property(COMPUTED)
    val maxWidth = Property(INFINITE)
    val maxHeight = Property(INFINITE)

    val parent = Property<Node?>(null)

    abstract fun computeWidth(): Double
    abstract fun computeHeight(): Double

    protected fun updateWidth() {
        updateSize(width, minWidth, prefWidth, maxWidth, computedWidth, ::computeWidth)
    }

    protected fun updateHeight() {
        updateSize(height, minHeight, prefHeight, maxHeight, computedHeight, ::computeHeight)
    }

    private fun updateSize(
        value: Property<Double>, min: Property<Double>, pref: Property<Double>,
        max: Property<Double>, computed: Property<Double>, compute: () -> Double
    ) {
        var new: Double
        if (pref.value == COMPUTED) {
            new = compute()
            computed.value = new
        } else {
            new = pref.value
        }
        if (new > max.value) {
            new = max.value
        } else if (new < min.value) {
            new = min.value
        }
        value.value = new
    }
}

abstract class Group : Node() {
    private val children = PropertyList<Node>()

    init {
        children.addListener(::updateChildrenPos)
    }

    operator fun plusAssign(node: Node) {
        children += node
    }

    operator fun minusAssign(node: Node) {
        children -= node
    }

    abstract fun updateChildrenPos()
}

class Label(_text: String = "") : Node() {
    val text = Property(_text)

    init {
        text.addListener { updateWidth() }
    }

    override fun computeWidth(): Double {
        return mc.textRenderer.getWidth(text.value).toDouble()
    }

    override fun computeHeight(): Double {
        return mc.textRenderer.fontHeight.toDouble()
    }

    override fun render(context: RendererContext) {
        renderLabel(this, context)
    }
}

fun label(text: String = "", op: (Label) -> Unit = {}) = Label(text).also { op(it) }
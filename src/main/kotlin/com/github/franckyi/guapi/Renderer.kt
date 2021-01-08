package com.github.franckyi.guapi

import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Formatting

interface Drawable {
    fun render(context: RendererContext)
}

data class RendererContext(
    val mc: MinecraftClient,
    val mouseX: Int,
    val mouseY: Int,
    val delta: Float,
    val matrices: MatrixStack?
)

fun renderLabel(label: Label, ctx: RendererContext) {
    ctx.mc.textRenderer.draw(
        ctx.matrices,
        label.text.value,
        label.x.value,
        label.y.value,
        Formatting.WHITE.colorValue!!
    )
}
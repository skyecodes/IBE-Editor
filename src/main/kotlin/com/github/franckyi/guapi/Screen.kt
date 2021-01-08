package com.github.franckyi.guapi

import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.LiteralText
import net.minecraft.text.Text

class GuapiScreen(var root: Node, title: Text? = LiteralText("Screen")) : Screen(title) {
    override fun render(matrices: MatrixStack?, mouseX: Int, mouseY: Int, delta: Float) {
        root.render(RendererContext(client!!, mouseX, mouseY, delta, matrices))
    }
}
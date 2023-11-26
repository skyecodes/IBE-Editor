/*
 * Copyright (c) 2023 skyecodes
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.skyecodes.ibeeditor.gui.widget

import com.skyecodes.ibeeditor.modScreen
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
import net.minecraft.client.gui.widget.ClickableWidget
import net.minecraft.client.sound.SoundManager
import net.minecraft.item.ItemStack

/**
 * A 16x16 widget that displays an item.
 * @param stack The item to display
 * @param textRenderer The text renderer
 * @param renderItemTooltip Whether to render the item tooltip when the widget is hovered, defaults to true
 */
class ItemViewWidget(
    stack: ItemStack,
    private val textRenderer: TextRenderer,
    private val renderItemTooltip: Boolean = true
) : ClickableWidget(0, 0, 16, 16, stack.name) {
    var stack: ItemStack = stack
        set(value) {
            field = value
            message = value.name
        }

    override fun renderButton(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        context.drawItem(stack, x, y)
        context.drawItemInSlot(textRenderer, stack, x, y)
        if (hovered && renderItemTooltip) modScreen.hoveredItem = stack
    }

    override fun appendClickableNarrations(builder: NarrationMessageBuilder) {
    }

    override fun playDownSound(soundManager: SoundManager?) {
    }
}
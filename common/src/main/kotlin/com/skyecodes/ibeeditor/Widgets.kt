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

package com.skyecodes.ibeeditor

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.tooltip.Tooltip
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.TextIconButtonWidget
import net.minecraft.item.Item
import net.minecraft.text.Text

fun buttonWidget(
    message: Text,
    onPress: (ButtonWidget) -> Unit,
    builder: ButtonWidget.Builder.() -> Unit
): ButtonWidget = ButtonWidget.builder(message, onPress).apply(builder).build()

fun buttonWidget(message: Text, onPress: () -> Unit, builder: ButtonWidget.Builder.() -> Unit): ButtonWidget =
    buttonWidget(message, { _ -> onPress() }, builder)

fun textIconButtonWidget(
    text: Text,
    onPress: (ButtonWidget) -> Unit,
    hideLabel: Boolean,
    builder: TextIconButtonWidget.Builder.() -> Unit
) = TextIconButtonWidget.builder(text, onPress, hideLabel).apply(builder).build()

fun textIconButtonWidget(
    text: Text,
    onPress: () -> Unit,
    hideLabel: Boolean,
    builder: TextIconButtonWidget.Builder.() -> Unit
) = textIconButtonWidget(text, { _ -> onPress() }, hideLabel, builder)

fun textItemButtonWidget(
    text: Text,
    onPress: (ButtonWidget) -> Unit,
    hideLabel: Boolean,
    builder: TextItemButtonWidget.Builder.() -> Unit
) = TextItemButtonWidget.builder(text, onPress, hideLabel).apply(builder).build()

fun textItemButtonWidget(
    text: Text,
    onPress: () -> Unit,
    hideLabel: Boolean,
    builder: TextItemButtonWidget.Builder.() -> Unit
) = textItemButtonWidget(text, { _ -> onPress() }, hideLabel, builder)

abstract class TextItemButtonWidget(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    message: Text?,
    item: Item,
    onPress: PressAction?
) :
    ButtonWidget(x, y, width, height, message, onPress, DEFAULT_NARRATION_SUPPLIER) {
    val item = item.defaultStack

    class Builder(private val text: Text, private val onPress: PressAction, private val hideText: Boolean) {
        private var x = 0
        private var y = 0
        private var width = 150
        private var height = 20
        private var item: Item? = null
        private var tooltip: Tooltip? = null
        fun position(x: Int, y: Int): Builder {
            this.x = x
            this.y = y
            return this
        }

        fun width(width: Int): Builder {
            this.width = width
            return this
        }

        fun size(width: Int, height: Int): Builder {
            this.width = width
            this.height = height
            return this
        }

        fun dimensions(x: Int, y: Int, width: Int, height: Int): Builder {
            return position(x, y).size(width, height)
        }

        fun item(item: Item): Builder {
            this.item = item
            return this
        }

        fun tooltip(tooltip: Tooltip?): Builder {
            this.tooltip = tooltip
            return this
        }

        fun build(): TextItemButtonWidget {
            val it = checkNotNull(item) { "Item not set" }
            return (if (hideText) IconOnly(x, y, width, height, text, it, onPress)
            else WithText(x, y, width, height, text, it, onPress)).also { it.tooltip = tooltip }
        }
    }

    @Environment(EnvType.CLIENT)
    open class IconOnly(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        text: Text,
        item: Item,
        pressAction: PressAction
    ) : TextItemButtonWidget(x, y, width, height, text, item, pressAction) {
        public override fun renderButton(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
            super.renderButton(context, mouseX, mouseY, delta)
            val i = x + (getWidth() - 16) / 2
            val j = y + (getHeight() - 16) / 2
            context.drawItem(item, i, j)
        }

        override fun drawMessage(context: DrawContext, textRenderer: TextRenderer, color: Int) {}
    }

    @Environment(EnvType.CLIENT)
    open class WithText(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        text: Text,
        item: Item,
        pressAction: PressAction
    ) :
        TextItemButtonWidget(x, y, width, height, text, item, pressAction) {
        public override fun renderButton(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
            super.renderButton(context, mouseX, mouseY, delta)
            val i = x + getWidth() - 16 - 2
            val j = y + getHeight() / 2 - 16 / 2
            context.drawItem(item, i, j)
        }

        override fun drawMessage(context: DrawContext, textRenderer: TextRenderer, color: Int) {
            val i = x + 2
            val j = x + getWidth() - 16 - 4
            val k = x + getWidth() / 2
            drawScrollableText(
                context, textRenderer,
                message, k, i, y, j, y + getHeight(), color
            )
        }
    }

    companion object {
        fun builder(text: Text, onPress: PressAction, hideLabel: Boolean): Builder {
            return Builder(text, onPress, hideLabel)
        }
    }
}

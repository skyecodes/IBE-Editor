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

import com.skyecodes.ibeeditor.TEXT_SEARCH
import com.skyecodes.ibeeditor.mc
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.sound.PositionedSoundInstance
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.Identifier

private val SEARCH_ICON = Identifier("icon/search")

class SearchFieldWidget(textRenderer: TextRenderer, width: Int, height: Int, text: Text) : TextFieldWidgetExt(textRenderer, width, height, text) {
    private val buttonX get() = right - 16
    private val buttonY get() = y + 4

    var onSearchButtonClicked = {}

    override fun onClick(mouseX: Double, mouseY: Double) {
        if (isButtonHovered(mouseX.toInt(), mouseY.toInt())) {
            mc.soundManager.play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F))
            onSearchButtonClicked()
        } else {
            super.onClick(mouseX, mouseY)
        }
    }

    override fun renderWidget(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.renderWidget(context, mouseX, mouseY, delta)
        if (isButtonHovered(mouseX, mouseY)) {
            context.drawGuiTexture(SEARCH_ICON, buttonX, buttonY, 12, 12)
            context.drawTooltip(mc.textRenderer, TEXT_SEARCH, mouseX, mouseY)
        }
        else context.drawSprite(buttonX, buttonY, 0, 12, 12, mc.guiAtlasManager.getSprite(SEARCH_ICON), 1f, 1f, 1f, 0.5f)
    }

    private fun isButtonHovered(mouseX: Int, mouseY: Int) = mouseX >= buttonX && mouseX <= buttonX + 12 && mouseY >= buttonY && mouseY <= buttonY + 12
}
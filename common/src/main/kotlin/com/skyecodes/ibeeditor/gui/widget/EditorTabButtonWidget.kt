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

import com.skyecodes.ibeeditor.gui.tab.EditorTab
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
import net.minecraft.client.gui.screen.narration.NarrationPart
import net.minecraft.client.gui.tab.TabManager
import net.minecraft.client.gui.tooltip.Tooltip
import net.minecraft.client.sound.SoundManager
import net.minecraft.text.Text

class EditorTabButtonWidget(private val tabManager: TabManager, val tab: EditorTab, width: Int, height: Int) :
    TextItemButtonWidget.WithText(0, 0, width, height, tab.title, tab.icon, {}) {
    private val isCurrentTab: Boolean get() = tabManager.currentTab === tab

    init {
        tooltip = Tooltip.of(message)
    }

    override fun renderButton(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        active = !isCurrentTab
        super.renderButton(context, mouseX, mouseY, delta)
    }

    override fun appendClickableNarrations(builder: NarrationMessageBuilder) {
        builder.put(NarrationPart.TITLE, Text.translatable("gui.narrate.tab", tab.title))
    }

    override fun playDownSound(soundManager: SoundManager?) {
    }
}

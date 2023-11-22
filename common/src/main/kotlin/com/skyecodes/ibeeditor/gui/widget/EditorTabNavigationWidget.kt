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
import net.minecraft.client.gui.*
import net.minecraft.client.gui.navigation.GuiNavigation
import net.minecraft.client.gui.navigation.GuiNavigationPath
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
import net.minecraft.client.gui.screen.narration.NarrationPart
import net.minecraft.client.gui.tab.TabManager
import net.minecraft.client.gui.widget.ClickableWidget
import net.minecraft.client.gui.widget.GridWidget
import net.minecraft.client.gui.widget.SimplePositioningWidget
import net.minecraft.text.Text
import net.minecraft.util.math.MathHelper

class EditorTabNavigationWidget(private val tabManager: TabManager, private val tabs: List<EditorTab>) :
    AbstractParentElement(), Drawable, Selectable {
    private val USAGE_NARRATION_TEXT: Text = Text.translatable("narration.tab_navigation.usage")
    private val grid = GridWidget(10, 0).apply {
        mainPositioner.alignHorizontalCenter().alignVerticalCenter()
        setRowSpacing(5)
    }
    private val tabButtons: List<EditorTabButtonWidget> = buildList {
        tabs.forEachIndexed { i, tab -> add(grid.add(EditorTabButtonWidget(tabManager, tab, 20, 20), i, 0)) }
    }
    private val currentTabIndex: Int
        get() {
            val tab = tabManager.currentTab
            val i = tabs.indexOf(tab)
            return if (i != -1) i else -1
        }
    private val currentTabButton: EditorTabButtonWidget?
        get() {
            val i = currentTabIndex
            return if (i != -1) tabButtons[i] else null
        }

    override fun setFocused(focused: Boolean) {
        super.setFocused(focused)
        if (this.focused != null) {
            this.focused!!.isFocused = focused
        }
    }

    override fun setFocused(focused: Element?) {
        super.setFocused(focused)
        if (focused is EditorTabButtonWidget) {
            tabManager.setCurrentTab(focused.tab, true)
        }
    }

    override fun getNavigationPath(navigation: GuiNavigation?): GuiNavigationPath? {
        if (!isFocused) {
            val tabButtonWidget = currentTabButton
            if (tabButtonWidget != null) {
                return GuiNavigationPath.of(this, GuiNavigationPath.of(tabButtonWidget))
            }
        }

        return if (navigation is GuiNavigation.Tab) null else super.getNavigationPath(navigation)
    }

    override fun children(): List<Element> = tabButtons

    override fun getType(): Selectable.SelectionType =
        tabButtons.stream().map(ClickableWidget::getType).max(Comparator.naturalOrder())
            .orElse(Selectable.SelectionType.NONE)

    override fun appendNarrations(builder: NarrationMessageBuilder?) {
        val tabButton = tabButtons.firstOrNull(ClickableWidget::isHovered) ?: currentTabButton
        tabButton?.also {
            appendNarrations(builder!!.nextMessage(), it)
            it.appendNarrations(builder)
        }
        if (isFocused) {
            builder!!.put(NarrationPart.USAGE, USAGE_NARRATION_TEXT)
        }
    }

    private fun appendNarrations(builder: NarrationMessageBuilder, button: EditorTabButtonWidget) {
        if (tabs.size > 1) {
            val i = tabButtons.indexOf(button)
            if (i != -1) {
                builder.put(NarrationPart.POSITION, Text.translatable("narrator.position.tab", i + 1, tabs.size))
            }
        }
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        for (tabButtonWidget in tabButtons) {
            tabButtonWidget.render(context, mouseX, mouseY, delta)
        }
    }

    override fun getNavigationFocus(): ScreenRect = grid.navigationFocus

    fun init(height: Int) {
        grid.refreshPositions()
        SimplePositioningWidget.setPos(grid, 0, 0, 40, height)
    }

    fun selectTab(index: Int, clickSound: Boolean) {
        if (this.isFocused) {
            this.focused = tabButtons[index]
        } else {
            tabManager.setCurrentTab(tabs[index], clickSound)
        }
    }

    fun trySwitchTabsWithKey(keyCode: Int): Boolean {
        if (Screen.hasControlDown()) {
            val i = getTabForKey(keyCode)
            if (i != -1) {
                selectTab(MathHelper.clamp(i, 0, tabs.size - 1), true)
                return true
            }
        }
        return false
    }

    private fun getTabForKey(keyCode: Int): Int {
        return if (keyCode in 49..57) {
            keyCode - 49
        } else {
            if (keyCode == 258) {
                val i = currentTabIndex
                if (i != -1) {
                    val j = if (Screen.hasShiftDown()) i - 1 else i + 1
                    return Math.floorMod(j, tabs.size)
                }
            }
            -1
        }
    }
}
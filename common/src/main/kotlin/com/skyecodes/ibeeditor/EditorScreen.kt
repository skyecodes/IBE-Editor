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

import net.minecraft.client.gui.ScreenRect
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.tab.TabManager
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.GridWidget
import net.minecraft.client.gui.widget.SimplePositioningWidget
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW

abstract class EditorScreen(title: Text, private val parent: Screen? = null) : Screen(title) {
    private val tabManager = TabManager(::addDrawableChild, ::remove)
    private lateinit var grid: GridWidget
    private lateinit var tabNavigation: EditorTabNavigationWidget

    override fun init() {
        tabNavigation = EditorTabNavigationWidget(tabManager, width, getTabs())
        addDrawableChild(tabNavigation)
        grid = GridWidget().setColumnSpacing(10)
        val adder = grid.createAdder(2)
        adder.add(ButtonWidget.builder(ScreenTexts.DONE) { onApply() }.build())
        adder.add(ButtonWidget.builder(ScreenTexts.CANCEL) { onClose() }.build())
        grid.forEachChild {
            it.navigationOrder = 1
            addDrawableChild(it)
        }
        tabNavigation.selectTab(0, false)
        initTabNavigation()
    }

    override fun initTabNavigation() {
        tabNavigation.width = width
        tabNavigation.init()
        grid.refreshPositions()
        SimplePositioningWidget.setPos(grid, 0, height - 36, width, 36)
        val screenRect = ScreenRect(0, 36, width, height - 72)
        tabManager.setTabArea(screenRect)
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        return when {
            tabNavigation.trySwitchTabsWithKey(keyCode) -> true
            super.keyPressed(keyCode, scanCode, modifiers) -> true
            keyCode != GLFW.GLFW_KEY_ENTER && keyCode != GLFW.GLFW_KEY_KP_ENTER -> false
            else -> {
                onApply()
                true
            }
        }
    }

    override fun close() = onClose()

    private fun onClose() {
        client!!.setScreen(parent)
    }

    private fun onApply() {
        apply()
        onClose()
    }

    abstract fun getTabs(): List<EditorTab>

    abstract fun apply()
}


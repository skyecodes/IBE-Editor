/*
 * Copyright (c) 2023 skyecodes
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the “Software”), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.skyecodes.ibeeditor.gui.screen

import com.skyecodes.ibeeditor.gui.Validable
import com.skyecodes.ibeeditor.gui.tab.EditorTab
import com.skyecodes.ibeeditor.gui.widget.EditorTabNavigationWidget
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.ScreenRect
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.tab.TabManager
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.GridWidget
import net.minecraft.client.gui.widget.SimplePositioningWidget
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text

/**
 * Base class for all editor screens.
 * @param title The screen title
 * @param parent The parent screen to be opened when the editor is closed
 */
abstract class EditorScreen(title: Text, private val parent: Screen? = null) : IBEEditorScreenExt(title), Validable {
    private val tabManager = TabManager(::addDrawableChild, ::remove)
    protected lateinit var headerGrid: GridWidget
    private lateinit var tabNavigation: EditorTabNavigationWidget
    private lateinit var footerGrid: GridWidget
    private lateinit var doneButton: ButtonWidget

    override val isValid: Boolean
        get() = tabNavigation.tabs.all { it.isValid }

    override fun init() {
        initHeader()
        tabNavigation = EditorTabNavigationWidget(tabManager, initTabs())
        addDrawableChild(tabNavigation)
        initFooter()
        tabNavigation.selectTab(0, false)
        initTabNavigation()
    }

    /**
     * Initializes the header of the screen (title, etc.).
     */
    abstract fun initHeader()

    /**
     * Initializes the footer of the screen (done/cancel buttons).
     */
    open fun initFooter() {
        footerGrid = GridWidget().setColumnSpacing(10)
        val adder = footerGrid.createAdder(2)
        doneButton = adder.add(ButtonWidget.builder(ScreenTexts.DONE) { onApply() }.build())
        adder.add(ButtonWidget.builder(ScreenTexts.CANCEL) { onClose() }.build())
        footerGrid.forEachChild {
            it.navigationOrder = 1
            addDrawableChild(it)
        }
    }

    override fun initTabNavigation() {
        headerGrid.refreshPositions()
        SimplePositioningWidget.setPos(headerGrid, 0, 0, width, 36)
        tabNavigation.init(height)
        val screenRect = ScreenRect(40, 36, width - 50, height - 72)
        tabManager.setTabArea(screenRect)
        footerGrid.refreshPositions()
        SimplePositioningWidget.setPos(footerGrid, 0, height - 36, width, 36)
    }

    /**
     * Updates the editor data and view (for example, in Item Editor,
     * updates the stack data and the item view at the top).
     *
     * Should be called everytime a field is updated.
     */
    abstract fun updateEditor()

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        doneButton.active = isValid
        super.render(context, mouseX, mouseY, delta)
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        return when {
            tabNavigation.trySwitchTabsWithKey(keyCode) -> true
            super.keyPressed(keyCode, scanCode, modifiers) -> true
            else -> false
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

    /**
     * Returns the list of editor tabs. Called during screen initialization.
     */
    abstract fun initTabs(): List<EditorTab>

    /**
     * Applies the changes made in the editor. Called when clicking on Done.
     */
    abstract fun apply()
}


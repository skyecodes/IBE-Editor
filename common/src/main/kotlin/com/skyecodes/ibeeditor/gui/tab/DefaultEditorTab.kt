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

package com.skyecodes.ibeeditor.gui.tab

import com.skyecodes.ibeeditor.gui.screen.EditorScreen
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.ScreenRect
import net.minecraft.client.gui.widget.*
import net.minecraft.item.Item
import net.minecraft.text.Text

/**
 * Editor tab that consists of 2 columns: labels and fields.
 * @param title The text shown in the tab tooltip
 * @param icon The item icon shown in the tab button
 * @param textRenderer The text renderer
 * @param editorScreen The editor screen
 */
abstract class DefaultEditorTab(
    title: Text,
    icon: Item,
    private val textRenderer: TextRenderer,
    private val editorScreen: EditorScreen
) : EditorTab(title, icon) {
    private lateinit var adder: GridWidget.Adder
    protected open val leftElements = mutableListOf<ClickableWidget>()
    protected open val rightElements = mutableListOf<ClickableWidget>()
    protected open val rowSpacing = 10
    protected open val columnSpacing = 10
    protected open val leftWidth: Int = 100
    protected open val rightWidth get() = editorScreen.width - (leftWidth + columnSpacing + 50)

    open fun initColumns() {
        adder = createAdder()
        grid.mainPositioner.alignVerticalCenter()
    }

    open fun createAdder(): GridWidget.Adder =
        grid.setRowSpacing(rowSpacing).setColumnSpacing(columnSpacing).createAdder(2)

    open fun updateElementsSize() {
        leftElements.forEach { it.width = leftWidth }
        rightElements.forEach { it.width = rightWidth }
    }

    /**
     * Updates the editor data and view (for example, in Item Editor,
     * updates the stack data and the item view at the top).
     *
     * Should be called everytime a field is updated.
     */
    abstract fun updateEditor()

    override fun refreshGrid(tabArea: ScreenRect) {
        updateElementsSize()
        grid.refreshPositions()
        SimplePositioningWidget.setPos(grid, tabArea)
    }

    fun textField(label: Text, value: () -> String, listener: (String) -> Unit) {
        leftElements += adder.add(TextWidget(leftWidth, textRenderer.fontHeight, label, textRenderer).alignRight())
        rightElements += adder.add(TextFieldWidget(textRenderer, rightWidth, 20, label).apply {
            text = value()
            setChangedListener {
                listener(it)
                updateEditor()
            }
        })
    }
}
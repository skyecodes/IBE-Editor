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
import net.minecraft.client.gui.tab.GridScreenTab
import net.minecraft.client.gui.widget.GridWidget
import net.minecraft.client.gui.widget.SimplePositioningWidget
import net.minecraft.client.gui.widget.TextFieldWidget
import net.minecraft.client.gui.widget.TextWidget
import net.minecraft.item.Item
import net.minecraft.text.Text

abstract class EditorTab(
    title: Text,
    val icon: Item,
    private val textRenderer: TextRenderer,
    private val editorScreen: EditorScreen
) : GridScreenTab(title) {
    lateinit var adder: GridWidget.Adder
    var rowSpacing = 10
    var columnSpacing = 5
    var leftWidth: Int = 100
    var rightWidth: Int = 0

    open fun initColumns() {
        adder = createAdder()
        grid.mainPositioner.alignVerticalCenter()
        rightWidth = editorScreen.width - (leftWidth + columnSpacing + 50)
    }

    open fun createAdder(): GridWidget.Adder =
        grid.setRowSpacing(rowSpacing).setColumnSpacing(columnSpacing).createAdder(2)

    abstract fun updateView()

    override fun refreshGrid(tabArea: ScreenRect) {
        grid.refreshPositions()
        SimplePositioningWidget.setPos(grid, tabArea)
    }

    fun textField(label: Text, value: () -> String, listener: (String) -> Unit) {
        adder.add(TextWidget(leftWidth, textRenderer.fontHeight, label, textRenderer).alignRight())
        adder.add(TextFieldWidget(textRenderer, rightWidth, 20, label).apply {
            text = value()
            setChangedListener {
                listener(it)
                updateView()
            }
        })
    }
}
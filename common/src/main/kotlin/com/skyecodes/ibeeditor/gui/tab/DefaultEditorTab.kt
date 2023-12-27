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

package com.skyecodes.ibeeditor.gui.tab

import com.skyecodes.ibeeditor.SearchCache
import com.skyecodes.ibeeditor.gui.screen.EditorScreen
import com.skyecodes.ibeeditor.gui.widget.DoubleFieldWidget
import com.skyecodes.ibeeditor.gui.widget.IntFieldWidget
import com.skyecodes.ibeeditor.gui.widget.SearchFieldWidget
import com.skyecodes.ibeeditor.gui.widget.TextFieldWidgetExt
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.ScreenRect
import net.minecraft.client.gui.widget.ClickableWidget
import net.minecraft.client.gui.widget.GridWidget
import net.minecraft.client.gui.widget.SimplePositioningWidget
import net.minecraft.client.gui.widget.TextWidget
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
    open fun updateEditor() = editorScreen.updateEditor()

    override fun refreshGrid(tabArea: ScreenRect) {
        updateElementsSize()
        grid.refreshPositions()
        SimplePositioningWidget.setPos(grid, tabArea)
    }

    fun <T> searchField(label: Text, builder: SearchFieldBuilder<T>.() -> Unit) = SearchFieldBuilder<T>(label).apply(builder).build()

    fun textField(label: Text, builder: TextFieldBuilder.() -> Unit) = TextFieldBuilder(label).apply(builder).build()

    fun intField(label: Text, builder: IntFieldBuilder.() -> Unit) = IntFieldBuilder(label).apply(builder).build()

    fun doubleField(label: Text, builder: DoubleFieldBuilder.() -> Unit) = DoubleFieldBuilder(label).apply(builder).build()

    abstract inner class AbstractTextFieldBuilder<T>(val label: Text) {
        lateinit var getter: () -> T
        lateinit var setter: (T) -> Unit

        abstract fun buildNode(textRenderer: TextRenderer, width: Int, height: Int): ClickableWidget

        internal fun build() {
            leftElements += adder.add(TextWidget(leftWidth, textRenderer.fontHeight, label, textRenderer).alignRight())
            rightElements += adder.add(buildNode(textRenderer, rightWidth, 20))
        }
    }

    inner class TextFieldBuilder(label: Text) : AbstractTextFieldBuilder<String>(label) {
        override fun buildNode(textRenderer: TextRenderer, width: Int, height: Int) = TextFieldWidgetExt(textRenderer, width, height, label).apply {
            text = getter()
            setChangedListener {
                if (isValid) {
                    setter(it)
                    updateEditor()
                }
            }
        }
    }

    inner class IntFieldBuilder(label: Text) : AbstractTextFieldBuilder<Int>(label) {
        var min: Int = Int.MIN_VALUE
        var max: Int = Int.MAX_VALUE

        override fun buildNode(textRenderer: TextRenderer, width: Int, height: Int) = IntFieldWidget(textRenderer, width, height, label).apply {
            minValue = min
            maxValue = max
            intValue = getter()
            setChangedListener {
                if (isValid) {
                    setter(intValue)
                    updateEditor()
                }
            }
        }
    }

    inner class DoubleFieldBuilder(label: Text) : AbstractTextFieldBuilder<Double>(label) {
        var min: Double = Double.NEGATIVE_INFINITY
        var max: Double = Double.POSITIVE_INFINITY

        override fun buildNode(textRenderer: TextRenderer, width: Int, height: Int) = DoubleFieldWidget(textRenderer, width, height, label).apply {
            minValue = min
            maxValue = max
            doubleValue = getter()
            setChangedListener {
                if (isValid) {
                    setter(doubleValue)
                    updateEditor()
                }
            }
        }
    }

    inner class SearchFieldBuilder<T>(label: Text) : AbstractTextFieldBuilder<String>(label) {
        lateinit var elements: List<SearchCache.Entry<T>>
        lateinit var suggestions: List<String>

        override fun buildNode(textRenderer: TextRenderer, width: Int, height: Int): ClickableWidget = SearchFieldWidget(textRenderer, width, height, label).apply {
            text = getter()
            suggestions = this@SearchFieldBuilder.suggestions
            onSearchButtonClicked = { println("YOLO") }
            setChangedListener {
                if (isValid) {
                    setter(it)
                    updateEditor()
                }
            }
        }
    }
}
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

package com.skyecodes.ibeeditor.gui.screen

import com.skyecodes.ibeeditor.SearchCache
import com.skyecodes.ibeeditor.TEXT_SEARCH
import com.skyecodes.ibeeditor.TEXT_SELECT
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.TextFieldWidget
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text
import net.minecraft.util.Util

class SelectionScreen<T>(title: Text, parent: Screen? = null, private val initialValue: String, private val elements: List<SearchCache.CacheEntry<T>>, private val onSelect: (SearchCache.CacheEntry<T>) -> Unit) : IBEEditorScreenExt(title, parent) {
    private lateinit var selectionList: SelectionListWidget
    private lateinit var searchBar: TextFieldWidget
    private lateinit var selectButton: ButtonWidget

    override fun init() {
        selectButton = addDrawableChild(ButtonWidget.builder(TEXT_SELECT) { onSelect() }.position(width / 2 - 155, height - 26).build().apply { active = false })
        selectionList = addDrawableChild(SelectionListWidget(client!!))
        searchBar = addDrawableChild(TextFieldWidget(textRenderer, width / 2 - 100, 32, 200, 20, TEXT_SEARCH).apply {
            setPlaceholder(TEXT_SEARCH)
            setChangedListener { selectionList.onFilterChanged(it) }
        })
        addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL) { close() }.position(width / 2 + 5, height - 26).build())
    }

    private fun onSelect() {
        onSelect(selectionList.selectedOrNull!!.cacheEntry)
        close()
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 16, 16777215)
    }

    inner class SelectionListWidget(client: MinecraftClient) : AlwaysSelectedEntryListWidget<SelectionListWidget.Entry>(client, this@SelectionScreen.width, this@SelectionScreen.height - 96, 64, 30) {
        init {
            setRenderBackground(false)
            elements.forEach {
                val entry = Entry(it)
                addEntry(entry)
                if (it.id.toString() == initialValue) setSelected(entry)
            }
            centerScrollOn(selectedOrNull)
        }

        override fun getScrollbarPositionX() = super.getScrollbarPositionX() + 20

        override fun getRowWidth() = super.getRowWidth() + 50

        fun onFilterChanged(filter: String) {
            val selected = selectedOrNull
            clearEntries()
            elements.filter { it.name.string.contains(filter, true) || it.id.toString().contains(filter, true) }.forEach {
                val entry = Entry(it)
                addEntry(entry)
                if (it === selected?.cacheEntry) setSelected(entry)
            }
        }

        override fun clearEntries() {
            super.clearEntries()
            selectButton.active = false
        }

        override fun setSelected(entry: Entry?) {
            super.setSelected(entry)
            selectButton.active = entry != null
        }

        inner class Entry(val cacheEntry: SearchCache.CacheEntry<T>) : AlwaysSelectedEntryListWidget.Entry<Entry>() {
            private var clickTime: Long = 0

            override fun render(context: DrawContext, index: Int, y: Int, x: Int, entryWidth: Int, entryHeight: Int, mouseX: Int, mouseY: Int, hovered: Boolean, tickDelta: Float) {
                cacheEntry.render(textRenderer, context, index, y, x, entryWidth, entryHeight, mouseX, mouseY, hovered, tickDelta)
            }

            override fun getNarration(): Text = cacheEntry.name

            override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
                this.onPressed()
                if (Util.getMeasuringTimeMs() - this.clickTime < 250L) {
                    this@SelectionScreen.onSelect()
                }
                this.clickTime = Util.getMeasuringTimeMs()
                return true
            }

            private fun onPressed() {
                this@SelectionListWidget.setSelected(this)
            }
        }
    }


}
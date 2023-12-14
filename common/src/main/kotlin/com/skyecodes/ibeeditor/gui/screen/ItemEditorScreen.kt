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

import com.skyecodes.ibeeditor.*
import com.skyecodes.ibeeditor.gui.tab.DefaultEditorTab
import com.skyecodes.ibeeditor.gui.widget.ItemViewWidget
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.GridWidget
import net.minecraft.client.gui.widget.TextWidget
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.Formatting

class ItemEditorScreen(
    private var stack: ItemStack,
    private val applyFunction: (ItemStack) -> Unit,
    parent: Screen? = null
) : EditorScreen(TEXT_ITEM_EDITOR, parent) {
    private var nbt = stack.writeNbt(NbtCompound())
    private lateinit var itemViewWidget: ItemViewWidget

    override fun initTabs(): List<DefaultEditorTab> = buildList {
        add(GeneralTab())
        add(DisplayTab())
        add(EnchantmentsTab())
        add(AttributeModifiersTab())
        add(CanDestroyTab())
    }

    override fun initHeader() {
        headerGrid = GridWidget().setColumnSpacing(10)
        headerGrid.mainPositioner.alignVerticalCenter()
        val adder = headerGrid.createAdder(2)
        adder.add(TextWidget(TEXT_ITEM_EDITOR.copy().formatted(Formatting.BOLD, Formatting.AQUA), textRenderer))
        itemViewWidget = adder.add(ItemViewWidget(stack, textRenderer))
        headerGrid.forEachChild {
            it.navigationOrder = 1
            addDrawableChild(it)
        }
    }

    override fun apply() = applyFunction(stack)

    private inner class GeneralTab : DefaultEditorTab(TEXT_GENERAL, Items.PAPER, textRenderer, this) {
        init {
            initColumns()
            textField(TEXT_ITEM_ID, { nbt.getString("id") }) { nbt.putString("id", it) }
            textField(TEXT_COUNT, { nbt.getInt("Count").toString() }) {
                it.toIntOrNull()?.let { nbt.putInt("Count", it) }
            }
        }
    }

    private inner class DisplayTab : DefaultEditorTab(TEXT_DISPLAY, Items.OAK_SIGN, textRenderer, this) {
    }

    private inner class EnchantmentsTab :
        DefaultEditorTab(TEXT_ENCHANTMENTS, Items.ENCHANTED_BOOK, textRenderer, this) {
    }

    private inner class AttributeModifiersTab :
        DefaultEditorTab(TEXT_ATTRIBUTE_MODIFIERS, Items.SUGAR, textRenderer, this) {
    }

    private inner class CanDestroyTab : DefaultEditorTab(TEXT_CAN_DESTROY, Items.IRON_PICKAXE, textRenderer, this) {
    }

    override fun updateEditor() {
        val stack = ItemStack.fromNbt(nbt)
        if (!stack.isEmpty) {
            this.stack = stack
            itemViewWidget.stack = stack
        }
    }
}


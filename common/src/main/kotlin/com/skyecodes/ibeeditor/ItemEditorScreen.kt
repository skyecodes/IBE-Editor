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

import net.minecraft.client.gui.screen.Screen
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

class ItemEditorScreen(
    private var stack: ItemStack,
    private val applyFunction: (ItemStack) -> Unit,
    parent: Screen? = null
) : EditorScreen(TEXT_ITEM_EDITOR, parent) {
    override fun getTabs() = buildList {
        add(GeneralTab())
        add(DisplayTab())
        add(EnchantmentsTab())
        add(AttributeModifiersTab())
        add(CanDestroyTab())
    }

    override fun apply() {
        applyFunction(stack.copyWithCount(stack.count + 1))
    }
}

private class GeneralTab : EditorTab(TEXT_GENERAL, Items.PAPER)

private class DisplayTab : EditorTab(TEXT_DISPLAY, Items.OAK_SIGN)

private class EnchantmentsTab : EditorTab(TEXT_ENCHANTMENTS, Items.ENCHANTED_BOOK)

private class AttributeModifiersTab : EditorTab(TEXT_ATTRIBUTE_MODIFIERS, Items.SUGAR)

private class CanDestroyTab : EditorTab(TEXT_CAN_DESTROY, Items.IRON_PICKAXE)


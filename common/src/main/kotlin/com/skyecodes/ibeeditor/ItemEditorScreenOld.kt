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
/*
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.tooltip.Tooltip
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.ClickableWidget
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text
import net.minecraft.util.Formatting

class ItemEditorScreenOld(private var stack: ItemStack, private val applyFunction: (ItemStack) -> Unit) : Screen(
    TEXT_ITEM_EDITOR
) {
    private lateinit var doneButton: ButtonWidget
    private lateinit var cancelButton: ButtonWidget
    private val tabs = mutableListOf<EditorTab>()
    private var selectedTabIndex = 0
    private val selectedTab get() = tabs[selectedTabIndex]

    init {
        tabs += GeneralEditorTab
        tabs += DisplayEditorTab
        tabs += EnchantmentsEditorTab
        tabs += AttributeModifiersEditorTab
    }

    override fun init() {
        var y = 30
        for (tab in tabs) {
            tab.button = textItemButtonWidget(tab.title, { -> selectTab(tab) }, true) {
                item(tab.icon)
                tooltip(Tooltip.of(tab.title))
                dimensions(10, y, 20, 20)
            }

            addDrawableChild(tab.button)
            y += 25
        }

        doneButton = buttonWidget(ScreenTexts.DONE, ::onDone) {
            dimensions(width / 2 - 160, height - 30, 150, 20)
        }
        cancelButton = buttonWidget(ScreenTexts.CANCEL, ::close) {
            dimensions(width / 2 + 10, height - 30, 150, 20)
        }
        addDrawableChild(doneButton)
        addDrawableChild(cancelButton)

        refreshTab()
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        val titleText = TEXT_ITEM_EDITOR.copy().formatted(Formatting.AQUA, Formatting.BOLD)
        val titleWidth = textRenderer.getWidth(titleText) + 10 + 16
        context.drawTextWithShadow(textRenderer, titleText, width / 2 - titleWidth / 2, 10, 0xffffff)
        context.drawItem(stack, width / 2 + titleWidth / 2 - 16, 7)
    }

    private fun refreshTab() {

    }

    private fun selectTab(tab: EditorTab) {
        selectedTabIndex = tabs.indexOf(tab)
        refreshTab()
    }

    private fun onDone() {
        applyFunction(stack.copyWithCount(stack.count + 1))
        close()
    }
}

abstract class EditorTab(title: Text, val icon: Item): Screen(title) {
    lateinit var button: ClickableWidget
}

private object GeneralEditorTab : EditorTab(TEXT_GENERAL, Items.PAPER) {
    override fun init() {
        super.init()
    }
}

private object DisplayEditorTab : EditorTab(TEXT_DISPLAY, Items.OAK_SIGN) {
    override fun init() {
        super.init()
    }
}

private object EnchantmentsEditorTab : EditorTab(TEXT_ENCHANTMENTS, Items.ENCHANTED_BOOK) {
    override fun init() {
        super.init()
    }
}

private object AttributeModifiersEditorTab : EditorTab(TEXT_ATTRIBUTE_MODIFIERS, Items.SUGAR) {
    override fun init() {
        super.init()
    }
}*/
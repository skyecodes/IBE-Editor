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

import com.skyecodes.ibeeditor.mixin.HandledScreenMixin
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import org.slf4j.LoggerFactory

object IBEEditorClient {
    private val LOGGER = LoggerFactory.getLogger(this::class.java)
    private val KEY_OPEN_EDITOR = KeyBinding(
        "key.ibeeditor.open_editor",
        InputUtil.Type.KEYSYM,
        InputUtil.GLFW_KEY_I,
        "key.categories.ibeeditor"
    )

    private val ALL_KEYS = listOf(KEY_OPEN_EDITOR)

    fun init() {
        LOGGER.info("Initializing IBE Editor (Client)")
    }

    fun registerKeyBindings(registerFunction: (KeyBinding) -> Unit) {
        LOGGER.debug("Registering IBE Editor keymappings")
        ALL_KEYS.forEach(registerFunction)
    }

    fun onTick() {
        if (!KEY_OPEN_EDITOR.wasPressed()) return
        val stack = player.mainHandStack
        if (stack.isEmpty) return

        openItemEditor(stack) {
            if (player.isCreative) {
                mc.interactionManager!!.clickCreativeStack(it, 36 + player.inventory.selectedSlot)
            } else {
                // TODO send update packet
            }
        }
    }

    fun onInventoryScreenKeyPressed(screen: AbstractInventoryScreen<*>, keyCode: Int, scanCode: Int) {
        if (!KEY_OPEN_EDITOR.matchesKey(keyCode, scanCode)) return
        val focusedSlot = (screen as HandledScreenMixin).focusedSlot
        if (focusedSlot == null || focusedSlot.inventory !is PlayerInventory) return
        val stack = focusedSlot.stack
        if (stack.isEmpty) return

        openItemEditor(stack, screen) {
            if (player.isCreative) {
                focusedSlot.stack = it
            } else {
                // TODO send update packet
            }
        }
    }

    private fun openItemEditor(stack: ItemStack, screen: Screen? = null, applyFunction: (ItemStack) -> Unit) {
        LOGGER.info("Opening Item Editor for item {}", stack)
        mc.setScreen(ItemEditorScreen(stack, applyFunction, screen))
    }
}


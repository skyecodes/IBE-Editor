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

package com.skyecodes.ibeeditor.fabric

import com.skyecodes.ibeeditor.IBEEditor
import com.skyecodes.ibeeditor.IBEEditorClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen

/**
 * IBE Editor's entry point class for Fabric.
 */
object IBEEditorFabric : ModInitializer, ClientModInitializer {
    /**
     * IBE Editor's common entry point method for Fabric.
     */
    override fun onInitialize() {
        IBEEditor.init()
    }

    /**
     * IBE Editor's client entry point method for Fabric. Registers key bindings and event handlers.
     */
    override fun onInitializeClient() {
        IBEEditorClient.init()
        IBEEditorClient.registerKeyBindings(KeyBindingHelper::registerKeyBinding)
        ClientTickEvents.START_CLIENT_TICK.register { IBEEditorClient.onTick() }
        ScreenEvents.BEFORE_INIT.register { _, screen, _, _ ->
            if (screen is AbstractInventoryScreen<*>) {
                ScreenKeyboardEvents.beforeKeyPress(screen).register { _, key, scancode, _ ->
                    IBEEditorClient.onInventoryScreenKeyPressed(
                        screen,
                        key,
                        scancode
                    )
                }
            }
        }
    }
}
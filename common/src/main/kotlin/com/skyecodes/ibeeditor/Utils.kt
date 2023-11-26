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

import com.skyecodes.ibeeditor.gui.screen.IBEEditorScreenExt
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity

/**
 * The current [MinecraftClient] instance
 * @see MinecraftClient.getInstance
 */
val mc: MinecraftClient get() = MinecraftClient.getInstance()

/**
 * The current client player
 * @see MinecraftClient.player
 */
val player: ClientPlayerEntity get() = mc.player!!

/**
 * The current [IBEEditorScreenExt] screen
 * @see MinecraftClient.currentScreen
 */
var modScreen: IBEEditorScreenExt
    get() = mc.currentScreen as IBEEditorScreenExt
    set(value) = mc.setScreen(value)

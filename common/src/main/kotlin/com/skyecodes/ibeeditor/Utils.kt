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

package com.skyecodes.ibeeditor

import com.skyecodes.ibeeditor.gui.screen.IBEEditorScreenExt
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.util.Identifier

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

fun String.toIdentifier() = Identifier(this)

fun rgba(r: Double, g: Double, b: Double, a: Double): Int = rgba((r * 255).toInt(), (g * 255).toInt(), (b * 255).toInt(), (a * 255).toInt())

fun rgba(r: Int, g: Int, b: Int, a: Int): Int {
    checkValues(r, g, b, a)
    return ((a and 0xff) shl 24) or
            ((r and 0xff) shl 16) or
            ((g and 0xff) shl 8) or
            (b and 0xff)
}

fun checkValues(r: Int, g: Int, b: Int, a: Int) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255 || a < 0 || a > 255) {
        throw IllegalArgumentException("RGBA values must be between 0 and 255")
    }
}


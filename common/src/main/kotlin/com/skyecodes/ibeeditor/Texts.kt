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

import net.minecraft.text.MutableText
import net.minecraft.text.Text

val TEXT_ITEM_EDITOR = gui("item_editor")
val TEXT_GENERAL = gui("general")
val TEXT_ITEM_ID = gui("item_id")
val TEXT_COUNT = gui("count")
val TEXT_DAMAGE = gui("damage")
val TEXT_UNBREAKABLE = gui("unbreakable")
val TEXT_DISPLAY = gui("display")
val TEXT_ENCHANTMENTS = gui("enchantments")
val TEXT_ATTRIBUTE_MODIFIERS = gui("attribute_modifiers")
val TEXT_CAN_DESTROY = gui("can_destroy")
val TEXT_SELECT = gui("select")
val TEXT_SELECT_ITEM = gui("select_item")
val TEXT_SEARCH = "gui.recipebook.search_hint".translatable()

fun String.translatable(): MutableText = Text.translatable(this)

fun String.literal(): MutableText = Text.literal(this)

private fun gui(key: String): MutableText = "gui.ibeeditor.$key".translatable()
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

package com.skyecodes.ibeeditor.gui.widget

import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.TextIconButtonWidget
import net.minecraft.text.Text

fun buttonWidget(
    message: Text,
    onPress: (ButtonWidget) -> Unit,
    builder: ButtonWidget.Builder.() -> Unit
): ButtonWidget = ButtonWidget.builder(message, onPress).apply(builder).build()

fun buttonWidget(message: Text, onPress: () -> Unit, builder: ButtonWidget.Builder.() -> Unit): ButtonWidget =
    buttonWidget(message, { _ -> onPress() }, builder)

fun textIconButtonWidget(
    text: Text,
    onPress: (ButtonWidget) -> Unit,
    hideLabel: Boolean,
    builder: TextIconButtonWidget.Builder.() -> Unit
) = TextIconButtonWidget.builder(text, onPress, hideLabel).apply(builder).build()

fun textIconButtonWidget(
    text: Text,
    onPress: () -> Unit,
    hideLabel: Boolean,
    builder: TextIconButtonWidget.Builder.() -> Unit
) = textIconButtonWidget(text, { _ -> onPress() }, hideLabel, builder)

fun textItemButtonWidget(
    text: Text,
    onPress: (ButtonWidget) -> Unit,
    hideLabel: Boolean,
    builder: TextItemButtonWidget.Builder.() -> Unit
) = TextItemButtonWidget.builder(text, onPress, hideLabel).apply(builder).build()

fun textItemButtonWidget(
    text: Text,
    onPress: () -> Unit,
    hideLabel: Boolean,
    builder: TextItemButtonWidget.Builder.() -> Unit
) = textItemButtonWidget(text, { _ -> onPress() }, hideLabel, builder)


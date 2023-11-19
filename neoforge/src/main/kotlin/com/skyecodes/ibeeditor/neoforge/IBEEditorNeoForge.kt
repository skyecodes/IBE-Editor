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

package com.skyecodes.ibeeditor.neoforge

import com.skyecodes.ibeeditor.IBEEditor
import com.skyecodes.ibeeditor.IBEEditorClient
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent
import net.neoforged.neoforge.client.event.ScreenEvent
import net.neoforged.neoforge.event.TickEvent
import org.slf4j.LoggerFactory
import thedarkcolour.kotlinforforge.neoforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(IBEEditor.MOD_ID)
class IBEEditorNeoForge {
    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    init {
        LOGGER.debug("Initializing IBE Editor (NeoForge)")
        MOD_BUS.register(this)
        FORGE_BUS.register(IBEEditorForgeEvents)
    }

    @SubscribeEvent
    fun onRegisterKeyMapping(event: RegisterKeyMappingsEvent) {
        IBEEditorClient.registerKeyBindings(event::register)
    }

    @SubscribeEvent
    fun onClientSetup(event: FMLClientSetupEvent) {
        IBEEditorClient.init()
    }

    @SubscribeEvent
    fun onCommonSetup(event: FMLCommonSetupEvent) {
        IBEEditor.init()
    }
}

object IBEEditorForgeEvents {
    @SubscribeEvent
    fun onClientTick(event: TickEvent.ClientTickEvent) {
        if (event.phase == TickEvent.Phase.START) {
            IBEEditorClient.onTick()
        }
    }

    @SubscribeEvent
    fun onKeyPressed(event: ScreenEvent.KeyPressed.Pre) {
        val screen = event.screen
        if (screen is AbstractInventoryScreen<*>) {
            IBEEditorClient.onInventoryScreenKeyPressed(screen, event.keyCode, event.scanCode)
        }
    }
}
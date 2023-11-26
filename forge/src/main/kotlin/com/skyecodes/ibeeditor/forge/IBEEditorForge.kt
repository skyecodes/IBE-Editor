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

package com.skyecodes.ibeeditor.forge

import com.skyecodes.ibeeditor.IBEEditor
import com.skyecodes.ibeeditor.IBEEditorClient
import net.minecraftforge.client.event.RegisterKeyMappingsEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS

/**
 * IBE Editor's entry point class for Forge. This class is registered to the mod event bus.
 * [IBEEditorForgeEvents] is registered to the Forge event bus.
 */
@Mod(IBEEditor.MOD_ID)
class IBEEditorForge {
    init {
        MOD_BUS.register(this)
        FORGE_BUS.register(IBEEditorForgeEvents)
    }

    /**
     * @see RegisterKeyMappingsEvent
     */
    @SubscribeEvent
    fun onRegisterKeyMapping(event: RegisterKeyMappingsEvent) {
        IBEEditorClient.registerKeyBindings(event::register)
    }

    /**
     * @see FMLClientSetupEvent
     */
    @SubscribeEvent
    fun onClientSetup(event: FMLClientSetupEvent) {
        IBEEditorClient.init()
    }

    /**
     * @see FMLCommonSetupEvent
     */
    @SubscribeEvent
    fun onCommonSetup(event: FMLCommonSetupEvent) {
        IBEEditor.init()
    }
}


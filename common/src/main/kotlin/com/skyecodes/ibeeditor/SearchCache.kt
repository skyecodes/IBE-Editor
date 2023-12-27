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

import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors

object SearchCache {
    private val LOGGER = LoggerFactory.getLogger(this::class.java)
    lateinit var ITEMS: List<Entry<Item>>
    lateinit var ITEM_IDS: List<String>
    lateinit var ENCHANTMENTS: List<Entry<Enchantment>>
    lateinit var ENCHANTMENT_IDS: List<String>
    lateinit var EFFECTS: List<Entry<StatusEffect>>
    lateinit var EFFECT_IDS: List<String>
    lateinit var ATTRIBUTES: List<Entry<EntityAttribute>>
    lateinit var ATTRIBUTE_IDS: List<String>

    fun init() {
        Executors.newFixedThreadPool(4) { Thread(it, "IBE Editor cache") }.run {
            submit { initItemCache() }
            submit { initEnchantmentCache() }
            submit { initEffectCache() }
            submit { initAttributeCache() }
            shutdown()
        }
    }

    private fun initItemCache() {
        ITEMS = Registries.ITEM.entrySet.map { (key, value) -> Entry(value.name, key.value, value) }
        ITEM_IDS = ITEMS.map { it.id.toString() }
        LOGGER.info("Registered {} items into cache", ITEMS.size)
    }

    private fun initEnchantmentCache() {
        ENCHANTMENTS = Registries.ENCHANTMENT.entrySet.map { (key, value) -> Entry(value.getName(1), key.value, value) }
        ENCHANTMENT_IDS = ENCHANTMENTS.map { it.id.toString() }
        LOGGER.info("Registered {} enchantments into cache", ENCHANTMENTS.size)
    }

    private fun initEffectCache() {
        EFFECTS = Registries.STATUS_EFFECT.entrySet.map { (key, value) -> Entry(value.name, key.value, value) }
        EFFECT_IDS = EFFECTS.map { it.id.toString() }
        LOGGER.info("Registered {} effects into cache", EFFECTS.size)
    }

    private fun initAttributeCache() {
        ATTRIBUTES = Registries.ATTRIBUTE.entrySet.map { (key, value) -> Entry(Text.translatable(value.translationKey), key.value, value) }
        ATTRIBUTE_IDS = ATTRIBUTES.map { it.id.toString() }
        LOGGER.info("Registered {} attributes into cache", ATTRIBUTES.size)
    }

    data class Entry<T>(val name: Text, val id: Identifier, val value: T)
}

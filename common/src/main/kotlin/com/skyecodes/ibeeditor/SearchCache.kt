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

import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors

object SearchCache {
    private val LOGGER = LoggerFactory.getLogger(this::class.java)
    lateinit var ITEMS: List<ItemCacheEntry>
    lateinit var ITEM_IDS: List<String>
    lateinit var ENCHANTMENTS: List<EnchantmentCacheEntry>
    lateinit var ENCHANTMENT_IDS: List<String>
    lateinit var EFFECTS: List<EffectCacheEntry>
    lateinit var EFFECT_IDS: List<String>
    lateinit var ATTRIBUTES: List<AttributeCacheEntry>
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
        val time = System.currentTimeMillis()
        ITEMS = Registries.ITEM.entrySet.map { (key, value) -> ItemCacheEntry(value.name, key.value, Registries.ITEM.getRawId(value), value) }.sorted()
        ITEM_IDS = ITEMS.extractIds()
        LOGGER.info("Registered {} items into cache in {}ms", ITEMS.size, System.currentTimeMillis() - time)
    }

    private fun initEnchantmentCache() {
        val time = System.currentTimeMillis()
        ENCHANTMENTS = Registries.ENCHANTMENT.entrySet.map { (key, value) -> EnchantmentCacheEntry(value.getName(1), key.value, Registries.ENCHANTMENT.getRawId(value), value) }.sorted()
        ENCHANTMENT_IDS = ENCHANTMENTS.extractIds()
        LOGGER.info("Registered {} enchantments into cache in {}ms", ENCHANTMENTS.size, System.currentTimeMillis() - time)
    }

    private fun initEffectCache() {
        val time = System.currentTimeMillis()
        EFFECTS = Registries.STATUS_EFFECT.entrySet.map { (key, value) -> EffectCacheEntry(value.name, key.value, Registries.STATUS_EFFECT.getRawId(value), value) }.sorted()
        EFFECT_IDS = EFFECTS.extractIds()
        LOGGER.info("Registered {} effects into cache in {}ms", EFFECTS.size, System.currentTimeMillis() - time)
    }

    private fun initAttributeCache() {
        val time = System.currentTimeMillis()
        ATTRIBUTES = Registries.ATTRIBUTE.entrySet.map { (key, value) -> AttributeCacheEntry(Text.translatable(value.translationKey), key.value, Registries.ATTRIBUTE.getRawId(value), value) }.sorted()
        ATTRIBUTE_IDS = ATTRIBUTES.extractIds()
        LOGGER.info("Registered {} attributes into cache in {}ms", ATTRIBUTES.size, System.currentTimeMillis() - time)
    }

    private fun List<CacheEntry<*>>.extractIds() = map { it.id }.flatMap { if (it.namespace == "minecraft") listOf(it.toString(), it.path) else listOf(it.toString()) }

    abstract class CacheEntry<T>(val name: Text, val id: Identifier, private val rawId: Int, val value: T) : Comparable<CacheEntry<T>> {
        abstract fun render(textRenderer: TextRenderer, context: DrawContext, index: Int, y: Int, x: Int, entryWidth: Int, entryHeight: Int, mouseX: Int, mouseY: Int, hovered: Boolean, tickDelta: Float)

        override fun compareTo(other: CacheEntry<T>): Int = rawId.compareTo(other.rawId)
    }

    class ItemCacheEntry(name: Text, id: Identifier, rawId: Int, value: Item) : CacheEntry<Item>(name, id, rawId, value) {
        private val stack = value.defaultStack
        private val idText = id.toString().literal().formatted(Formatting.DARK_GRAY)

        override fun render(textRenderer: TextRenderer, context: DrawContext, index: Int, y: Int, x: Int, entryWidth: Int, entryHeight: Int, mouseX: Int, mouseY: Int, hovered: Boolean, tickDelta: Float) {
            context.drawItem(stack, x + 5, y + (entryHeight / 2) - 8)
            context.drawTextWithShadow(textRenderer, name, x + 28, y + 3, 0xffffff)
            context.drawTextWithShadow(textRenderer, idText, x + 28, y + entryHeight - textRenderer.fontHeight - 2, 0xffffff)
        }
    }

    class EnchantmentCacheEntry(name: Text, id: Identifier, rawId: Int, value: Enchantment) : CacheEntry<Enchantment>(name, id, rawId, value) {
        override fun render(textRenderer: TextRenderer, context: DrawContext, index: Int, y: Int, x: Int, entryWidth: Int, entryHeight: Int, mouseX: Int, mouseY: Int, hovered: Boolean, tickDelta: Float) {
            TODO("Not yet implemented")
        }
    }

    class EffectCacheEntry(name: Text, id: Identifier, rawId: Int, value: StatusEffect) : CacheEntry<StatusEffect>(name, id, rawId, value) {
        override fun render(textRenderer: TextRenderer, context: DrawContext, index: Int, y: Int, x: Int, entryWidth: Int, entryHeight: Int, mouseX: Int, mouseY: Int, hovered: Boolean, tickDelta: Float) {
            TODO("Not yet implemented")
        }
    }

    class AttributeCacheEntry(name: Text, id: Identifier, rawId: Int, value: EntityAttribute) : CacheEntry<EntityAttribute>(name, id, rawId, value) {
        override fun render(textRenderer: TextRenderer, context: DrawContext, index: Int, y: Int, x: Int, entryWidth: Int, entryHeight: Int, mouseX: Int, mouseY: Int, hovered: Boolean, tickDelta: Float) {
            TODO("Not yet implemented")
        }
    }
}

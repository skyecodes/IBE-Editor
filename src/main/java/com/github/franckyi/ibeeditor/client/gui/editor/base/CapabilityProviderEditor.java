package com.github.franckyi.ibeeditor.client.gui.editor.base;

import com.github.franckyi.ibeeditor.client.EditorHelper;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.capability.ItemHandlerCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class CapabilityProviderEditor extends AbstractEditor {

    public CapabilityProviderEditor(String headerText) {
        super(headerText);
    }

    protected List<? extends CapabilityProviderEditorConfiguration<Object>> getCapabilityConfigurations() {
        return CapabilityProviderEditorConfiguration.config;
    }

    protected static class CapabilityProviderEditorConfiguration<T> extends AbstractEditorConfiguration<CapabilityProvider<?>, T> {

        private static final List<? extends CapabilityProviderEditorConfiguration<Object>> config;

        static {
            config = build();
        }

        private CapabilityProviderEditorConfiguration(Predicate<CapabilityProvider<?>> condition, Function<CapabilityProvider<?>, T> caster, String name, Function<T, AbstractCategory> categoryBuilder) {
            super(condition, caster, name, categoryBuilder);
        }

        @SuppressWarnings("unchecked")
        private static List<CapabilityProviderEditorConfiguration<Object>> build() {
            return Arrays.asList(
                    createCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, "Inventory", ItemHandlerCategory::new)
            );
        }

        private static <T> CapabilityProviderEditorConfiguration createCapability(Capability<T> capability, String name, Function<T, AbstractCategory> categoryBuilder) {
            return new CapabilityProviderEditorConfiguration<>(provider -> EditorHelper.isServerEnabled() && provider.getCapability(capability).isPresent(), provider -> provider.getCapability(capability).orElse(null), name, categoryBuilder);
        }

    }
}

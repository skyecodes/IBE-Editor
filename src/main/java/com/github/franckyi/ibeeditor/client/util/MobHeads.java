package com.github.franckyi.ibeeditor.client.util;

import net.minecraft.entity.EntityType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public final class MobHeads {

    private static final Map<EntityType<?>, ItemStack> map;

    static {
        map = new HashMap<>();
        register(EntityType.BLAZE, "MHF_Blaze");
        register(EntityType.CAVE_SPIDER, "MHF_CaveSpider");
        register(EntityType.CHICKEN, "MHF_Chicken");
        register(EntityType.COW, "MHF_Cow");
        register(EntityType.CREEPER, "MHF_Creeper");
        register(EntityType.ENDERMAN, "MHF_Enderman");
        register(EntityType.GHAST, "MHF_Ghast");
        register(EntityType.IRON_GOLEM, "MHF_Golem");
        register(EntityType.MAGMA_CUBE, "MHF_LavaSlime");
        register(EntityType.MOOSHROOM, "MHF_MushroomCow");
        register(EntityType.OCELOT, "MHF_Ocelot");
        register(EntityType.PIG, "MHF_Pig");
        register(EntityType.ZOMBIE_PIGMAN, "MHF_PigZombie");
        register(EntityType.SHEEP, "MHF_Sheep");
        register(EntityType.SKELETON, "MHF_Skeleton");
        register(EntityType.SLIME, "MHF_Slime");
        register(EntityType.SPIDER, "MHF_Spider");
        register(EntityType.SQUID, "MHF_Squid");
        register(EntityType.VILLAGER, "MHF_Villager");
        register(EntityType.WITHER_SKELETON, "MHF_WSkeleton");
        register(EntityType.ZOMBIE, "MHF_Zombie");
    }

    private static void register(EntityType<?> entityType, String playerName) {
        ItemStack stack = new ItemStack(Items.PLAYER_HEAD);
        stack.getOrCreateTag().putString("SkullOwner", playerName);
        stack.setDisplayName(ClientUtils.reset(entityType.getName()));
        map.put(entityType, stack);
    }

    public static ItemStack getHeadFromEntity(EntityType<?> entityType) {
        return map.getOrDefault(entityType, ItemStack.EMPTY);
    }

}

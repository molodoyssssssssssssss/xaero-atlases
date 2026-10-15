package org.molodoyss.xaeroatlases.loot;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.molodoyss.xaeroatlases.ModItems;
import org.molodoyss.xaeroatlases.Xaeroatlases;

public class ModLootTableModifier {
    public static final ResourceLocation CARTOGRAPHER_VILLAGE_ID = ResourceLocation.withDefaultNamespace("chests/village/village_cartographer");
    public static final ResourceLocation BURIED_TREASURE_ID = ResourceLocation.withDefaultNamespace("chests/buried_treasure");
    public static final ResourceLocation SHIPWRECK_SUPPLY_ID = ResourceLocation.withDefaultNamespace("chests/shipwreck_supply");
    public static final ResourceLocation SHIPWRECK_TREASURE_ID = ResourceLocation.withDefaultNamespace("chests/shipwreck_treasure");
    public static final ResourceLocation SHIPWRECK_MAP_ID = ResourceLocation.withDefaultNamespace("chests/shipwreck_map");


    public static void modify() {
        LootTableEvents.MODIFY.register(((key, builder, source, provider) -> {
            if (!source.isBuiltin()) return;

            if (key.location().equals(CARTOGRAPHER_VILLAGE_ID)) {
                LootPool.Builder poolBuilder = LootPool.lootPool().add(LootItem.lootTableItem(ModItems.ATLAS));
                builder.withPool(poolBuilder);
                Xaeroatlases.LOGGER.info("Modified Loot Table %s!".formatted(source.toString()));
            } else if(key.location().equals(BURIED_TREASURE_ID)) {
                LootPool.Builder poolBuilder = LootPool.lootPool().add(LootItem.lootTableItem(ModItems.ATLAS));
                builder.withPool(poolBuilder);
                Xaeroatlases.LOGGER.info("Modified Loot Table %s!".formatted(key.location().toString()));
            } else if ((key.location().equals(SHIPWRECK_MAP_ID) || key.location().equals(SHIPWRECK_SUPPLY_ID)|| key.location().equals(SHIPWRECK_TREASURE_ID))) {
                LootPool.Builder poolBuilder = LootPool.lootPool().add(LootItem.lootTableItem(ModItems.ATLAS));
                builder.withPool(poolBuilder);
                Xaeroatlases.LOGGER.info("Modified Loot Table %s!".formatted(key.location().toString()));
            }
        }));
    }
}

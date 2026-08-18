package org.molodoyss.xaeroatlases.loot;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.molodoyss.xaeroatlases.ModItems;
import org.molodoyss.xaeroatlases.Xaeroatlases;

public class ModLootTableModifier {
    public static final ResourceKey<LootTable> CARTOGRAPHER_VILLAGE_ID = ResourceKey.create(Registries.LOOT_TABLE, Identifier.withDefaultNamespace("chests/village/village_cartographer"));
    public static final ResourceKey<LootTable> BURIED_TREASURE_ID = ResourceKey.create(Registries.LOOT_TABLE, Identifier.withDefaultNamespace("chests/buried_treasure"));
    public static final ResourceKey<LootTable> SHIPWRECK_SUPPLY_ID = ResourceKey.create(Registries.LOOT_TABLE, Identifier.withDefaultNamespace("chests/shipwreck_supply"));
    public static final ResourceKey<LootTable> SHIPWRECK_TREASURE_ID = ResourceKey.create(Registries.LOOT_TABLE, Identifier.withDefaultNamespace("chests/shipwreck_treasure"));
    public static final ResourceKey<LootTable> SHIPWRECK_MAP_ID = ResourceKey.create(Registries.LOOT_TABLE, Identifier.withDefaultNamespace("chests/shipwreck_map"));

    public static void modify() {
        LootTableEvents.MODIFY.register(((key, tableBuilder, source, holder) -> {
            if (source.isBuiltin() && key.equals(CARTOGRAPHER_VILLAGE_ID)) {
                LootPool.Builder poolBuilder = LootPool.lootPool().add(LootItem.lootTableItem(ModItems.ATLAS));
                tableBuilder.withPool(poolBuilder);
            } else if(source.isBuiltin() && key.equals(BURIED_TREASURE_ID)) {
                LootPool.Builder poolBuilder = LootPool.lootPool().add(LootItem.lootTableItem(ModItems.ATLAS));
                tableBuilder.withPool(poolBuilder);
            } else if (source.isBuiltin() && (key.equals(SHIPWRECK_MAP_ID) || key.equals(SHIPWRECK_SUPPLY_ID)|| key.equals(SHIPWRECK_TREASURE_ID))) {
                LootPool.Builder poolBuilder = LootPool.lootPool().add(LootItem.lootTableItem(ModItems.ATLAS));
                tableBuilder.withPool(poolBuilder);
            }
            Xaeroatlases.LOGGER.info("Modified Loot Table %s!".formatted(key.identifier().toString()));
        }));
    }
}

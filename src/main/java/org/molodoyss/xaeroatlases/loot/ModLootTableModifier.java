package org.molodoyss.xaeroatlases.loot;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.molodoyss.xaeroatlases.ModItems;
import org.molodoyss.xaeroatlases.Xaeroatlases;

public class ModLootTableModifier {
    public static final ResourceLocation CARTOGRAPHER_VILLAGE_ID = new ResourceLocation("chests/village/village_cartographer");
    public static final ResourceLocation BURIED_TREASURE_ID = new ResourceLocation("chests/buried_treasure");
    public static final ResourceLocation SHIPWRECK_SUPPLY_ID = new ResourceLocation("chests/shipwreck_supply");
    public static final ResourceLocation SHIPWRECK_TREASURE_ID = new ResourceLocation("chests/shipwreck_treasure");
    public static final ResourceLocation SHIPWRECK_MAP_ID = new ResourceLocation("chests/shipwreck_map");


    public static void modify() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

        });
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (!source.isBuiltin()) return;

            if (id.equals(CARTOGRAPHER_VILLAGE_ID)) {
                LootPool.Builder poolBuilder = LootPool.lootPool().add(LootItem.lootTableItem(ModItems.ATLAS));
                tableBuilder.withPool(poolBuilder);
                Xaeroatlases.LOGGER.info("Modified Loot Table %s!".formatted(source.toString()));
            } else if(id.equals(BURIED_TREASURE_ID)) {
                LootPool.Builder poolBuilder = LootPool.lootPool().add(LootItem.lootTableItem(ModItems.ATLAS));
                tableBuilder.withPool(poolBuilder);
                Xaeroatlases.LOGGER.info("Modified Loot Table %s!".formatted(id.toString()));
            } else if ((id.equals(SHIPWRECK_MAP_ID) || id.equals(SHIPWRECK_SUPPLY_ID)|| id.equals(SHIPWRECK_TREASURE_ID))) {
                LootPool.Builder poolBuilder = LootPool.lootPool().add(LootItem.lootTableItem(ModItems.ATLAS));
                tableBuilder.withPool(poolBuilder);
                Xaeroatlases.LOGGER.info("Modified Loot Table %s!".formatted(id.toString()));
            }
        }));
    }
}

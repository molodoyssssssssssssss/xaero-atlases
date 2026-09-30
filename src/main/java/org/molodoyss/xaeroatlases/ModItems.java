package org.molodoyss.xaeroatlases;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import org.molodoyss.xaeroatlases.impl.item.AtlasItem;
import org.molodoyss.xaeroatlases.impl.item.ShowingCoordsAtlasItem;

import java.util.function.Function;

public class ModItems {
    public static final AtlasItem ATLAS = register(new Item.Properties().stacksTo(1), AtlasItem::new, "atlas", CreativeModeTabs.TOOLS_AND_UTILITIES);
    public static final ShowingCoordsAtlasItem ATLAS_WITH_COMPASS = register(new Item.Properties().stacksTo(1), ShowingCoordsAtlasItem::new, "atlas_with_compass", CreativeModeTabs.TOOLS_AND_UTILITIES);

    public static void init() {}

    public static <T extends Item> T register(Item.Properties properties, Function<Item.Properties, T> factory, String id) {
        return Registry.register(BuiltInRegistries.ITEM, Xaeroatlases.id(id), factory.apply(properties.setId(ResourceKey.create(Registries.ITEM, Xaeroatlases.id(id)))));
    }
    public static <T extends Item> T register(Item.Properties properties, Function<Item.Properties, T> factory, String id, ResourceKey<CreativeModeTab> tab) {
        T item = register(properties, factory, id);
        CreativeModeTabEvents.modifyOutputEvent(tab).register(output -> {
            output.accept(item);
        });

        return item;
    }
}

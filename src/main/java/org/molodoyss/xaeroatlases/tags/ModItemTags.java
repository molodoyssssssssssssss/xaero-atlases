package org.molodoyss.xaeroatlases.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.molodoyss.xaeroatlases.Xaeroatlases;

public class ModItemTags {
    public static final TagKey<Item> IS_SHOWING_COORDS = TagKey.create(Registries.ITEM, Xaeroatlases.id("is_showing_coords"));
    public static final TagKey<Item> ATLASES = TagKey.create(Registries.ITEM, Xaeroatlases.id("atlases"));

    public static void init() {}
}

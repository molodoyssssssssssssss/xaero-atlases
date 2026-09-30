package org.molodoyss.xaeroatlases.api;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.function.Predicate;

public class Utils {
    public static final char PARAGRAPH = '§';

    public static String getWithBigLetterInTheBeginning(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static boolean isItemInHand(Player player, Predicate<Holder<Item>> item) {
        return player.getItemInHand(InteractionHand.MAIN_HAND).is(item) || player.getItemInHand(InteractionHand.OFF_HAND).is(item);
    }
    public static boolean isItemInHand(Player player, Item item) {
        return player.getItemInHand(InteractionHand.MAIN_HAND).is(item) || player.getItemInHand(InteractionHand.OFF_HAND).is(item);
    }
    public static boolean isItemInHand(Player player, TagKey<Item> tag) {
        return player.getItemInHand(InteractionHand.MAIN_HAND).is(tag) || player.getItemInHand(InteractionHand.OFF_HAND).is(tag);
    }
    public static boolean isItemInHand(Player player, Holder<Item> item) {
        return player.getItemInHand(InteractionHand.MAIN_HAND).is(item) || player.getItemInHand(InteractionHand.OFF_HAND).is(item);
    }
    public static boolean isItemInHand(Player player, HolderSet<Item> item) {
        return player.getItemInHand(InteractionHand.MAIN_HAND).is(item) || player.getItemInHand(InteractionHand.OFF_HAND).is(item);
    }
    public static boolean isItemInHand(Player player, ResourceKey<Item> item) {
        return player.getItemInHand(InteractionHand.MAIN_HAND).is(item) || player.getItemInHand(InteractionHand.OFF_HAND).is(item);
    }

    public static String format(String str) {
        return str.replace('&', PARAGRAPH);
    }
}

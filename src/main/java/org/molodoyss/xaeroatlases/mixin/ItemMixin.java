package org.molodoyss.xaeroatlases.mixin;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.molodoyss.xaeroatlases.config.ModConfig;
import org.molodoyss.xaeroatlases.tags.ModItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

@Mixin(Item.class)
public abstract class ItemMixin implements ItemLike, FeatureElement, FabricItem {
    @Inject(method = "appendHoverText", at = @At("TAIL"))
    private void appendIsShowingCoordsText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag, CallbackInfo ci) {
        if (!itemStack.is(ModItemTags.IS_SHOWING_COORDS)) return;
        if (!ModConfig.isEnabledShowingCoordsWithCompass()) return;

        MutableComponent line_1 = Component.translatable("item.xaero_atlases.showing_coords_item.tooltip_1").withStyle(ChatFormatting.GRAY);
        MutableComponent line_2 = Component.translatable("item.xaero_atlases.showing_coords_item.tooltip_2").withStyle(ChatFormatting.GRAY);
        MutableComponent line_3 = Component.translatable("item.xaero_atlases.showing_coords_item.tooltip_3").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.BOLD);
        list.add(Component.empty());
        list.add(line_1);
        list.add(line_2);
        list.add(line_3);

    }
}

package org.molodoyss.xaeroatlases.mixin;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.ARGB;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.ItemLike;
import org.molodoyss.xaeroatlases.config.ModConfig;
import org.molodoyss.xaeroatlases.tags.ModItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.function.Consumer;

@Mixin(Item.class)
public abstract class ItemMixin implements ItemLike, FeatureElement, FabricItem {
    @Inject(method = "appendHoverText", at = @At("TAIL"))
    private void appendIsShowingCoordsText(ItemStack itemStack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag, CallbackInfo ci) {
        if (!itemStack.is(ModItemTags.IS_SHOWING_COORDS)) return;
        if (!ModConfig.isEnabledShowingCoordsWithCompass()) return;

        MutableComponent line_1 = Component.translatable("item.xaero_atlases.showing_coords_item.tooltip_1").withColor(TextColor.GRAY);
        MutableComponent line_2 = Component.translatable("item.xaero_atlases.showing_coords_item.tooltip_2").withColor(TextColor.GRAY);
        MutableComponent line_3 = Component.translatable("item.xaero_atlases.showing_coords_item.tooltip_3").withColor(TextColor.AQUA).withStyle(ChatFormatting.BOLD);
        builder.accept(Component.empty());
        builder.accept(line_1);
        builder.accept(line_2);
        builder.accept(line_3);

    }
}

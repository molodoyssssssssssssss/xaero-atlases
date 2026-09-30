package org.molodoyss.xaeroatlases.client.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.controls.KeyBindsList;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import org.molodoyss.xaeroatlases.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.map.MapProcessor;
import xaero.map.controls.ControlsHandler;
import xaero.map.gui.GuiMap;

@Mixin(ControlsHandler.class)
public class ControlsHandlerMixin {
    @Shadow private MapProcessor mapProcessor;

    @Inject(method = "keyDown", at = @At("HEAD"), cancellable = true)
    private void useMap(KeyMapping kb, boolean tickEnd, boolean isRepeat, CallbackInfo ci) {
        ci.cancel();
    }
}

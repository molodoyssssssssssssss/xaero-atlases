package org.molodoyss.xaeroatlases.client.mixin;

import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.map.MapProcessor;
import xaero.map.controls.ControlsHandler;
import xaero.map.gui.GuiMap;

@Mixin(value = ControlsHandler.class, remap = false)
public class ControlsHandlerMixin {
    @Shadow private MapProcessor mapProcessor;

    @Inject(method = "keyDown", at = @At("HEAD"), cancellable = true)
    private void useMap(KeyMapping kb, boolean tickEnd, boolean isRepeat, CallbackInfo ci) {
        ci.cancel();
    }
}

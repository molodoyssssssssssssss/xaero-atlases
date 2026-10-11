package org.molodoyss.xaeroatlases.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.hud.minimap.controls.key.function.AddWaypointFunction;
import xaero.hud.minimap.controls.key.function.WaypointMenuFunction;

@Mixin(value = WaypointMenuFunction.class, remap = false)
public class WaypointMenuFunctionMixin {
    @Inject(at = @At("HEAD"), method = "onPress", cancellable = true)
    private void cancel(CallbackInfo ci) {
        ci.cancel();
    }

}

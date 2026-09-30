package org.molodoyss.xaeroatlases.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import org.molodoyss.xaeroatlases.api.Utils;
import org.molodoyss.xaeroatlases.tags.ModItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.hud.minimap.controls.key.function.AddWaypointFunction;

@Mixin(AddWaypointFunction.class)
public class AddWaypointFunctionMixin {
    @Inject(at = @At("HEAD"), method = "onPress", cancellable = true)
    private void cancel(CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) ci.cancel();
        if (!Utils.isItemInHand(player, ModItemTags.ATLASES)) ci.cancel();
    }

}

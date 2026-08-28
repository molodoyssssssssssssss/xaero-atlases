package org.molodoyss.xaeroatlases.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.molodoyss.xaeroatlases.api.FormattingManager;
import org.molodoyss.xaeroatlases.api.Utils;
import org.molodoyss.xaeroatlases.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {
    public ServerPlayerMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        boolean isCompassInHand = this.equipment.get(EquipmentSlot.MAINHAND).is(Items.COMPASS) || this.equipment.get(EquipmentSlot.OFFHAND).is(Items.COMPASS);
        if (isCompassInHand && ModConfig.isEnabledShowingCoordsWithCompass()) {
            String valueFormat = FormattingManager.getFormattingValueText();
            String defaultTextFormat = FormattingManager.getFormattingDefaultText();

            this.sendOverlayMessage(Component.literal("%s%d§r%s | §r%s%d§r%s | §r%s%d§r%s | §r%s%s".formatted(valueFormat, blockPosition().getX(), defaultTextFormat, valueFormat, blockPosition().getY(), defaultTextFormat, valueFormat, blockPosition().getZ(), defaultTextFormat, valueFormat, Utils.getWithBigLetterInTheBeginning(getDirection().toString()))));
        }
    }
}

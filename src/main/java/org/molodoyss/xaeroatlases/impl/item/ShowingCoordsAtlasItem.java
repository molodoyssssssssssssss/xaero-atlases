package org.molodoyss.xaeroatlases.impl.item;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.molodoyss.xaeroatlases.ModNetworkingConstants;
import org.molodoyss.xaeroatlases.payloads.ClientboundShowWorldMapPayload;

import java.util.List;

public class ShowingCoordsAtlasItem extends CompassItem {
    public ShowingCoordsAtlasItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (level.isClientSide()) return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
        ClientboundShowWorldMapPayload.send(List.of((ServerPlayer) player), true);


        player.swing(interactionHand);

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(interactionHand), true);
    }
}

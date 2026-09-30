package org.molodoyss.xaeroatlases.impl.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.level.Level;
import org.molodoyss.xaeroatlases.payload.ClientboundShowWorldmapPacket;

import java.util.List;

public class ShowingCoordsAtlasItem extends CompassItem {
    public ShowingCoordsAtlasItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) return InteractionResult.PASS;
        ClientboundShowWorldmapPacket.broadcast(List.of((ServerPlayer) player), true);

        player.swing(hand, true);

        return InteractionResult.SUCCESS;
    }
}

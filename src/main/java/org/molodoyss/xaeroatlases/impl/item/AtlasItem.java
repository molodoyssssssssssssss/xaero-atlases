package org.molodoyss.xaeroatlases.impl.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.level.Level;
import org.molodoyss.xaeroatlases.payload.ClientboundShowWorldmapPacket;

import java.util.List;

public class AtlasItem extends Item {
    public AtlasItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) return InteractionResult.PASS;
        ClientboundShowWorldmapPacket.broadcast(List.of((ServerPlayer) player), false);

        player.swing(hand, SwingAnimation.DEFAULT, true);

        return InteractionResult.SUCCESS;
    }
}

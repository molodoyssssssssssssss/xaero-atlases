package org.molodoyss.xaeroatlases.client.commands;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.molodoyss.xaeroatlases.client.config.ConfigModMenuIntegration;

public class ModClientCommands {
    public static void init() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, buildContext) -> {
            dispatcher.register(
                    ClientCommandManager.literal("xaero-atlases-client")
                            .then(ClientCommandManager.literal("config")
                                    .then(ClientCommandManager.literal("open")
                                            .executes(context -> {
                                                Screen configScreen = new ConfigModMenuIntegration().getModConfigScreenFactory().create(null);
                                                Minecraft.getInstance().execute(() -> Minecraft.getInstance().setScreen(configScreen));
                                                return 1;
                                            })
                                    )
                            )
            );
        });
    }
}

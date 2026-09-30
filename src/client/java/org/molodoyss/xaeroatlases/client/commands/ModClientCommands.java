package org.molodoyss.xaeroatlases.client.commands;

import com.terraformersmc.modmenu.ModMenu;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.impl.YetAnotherConfigLibImpl;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.commands.Commands;
import org.molodoyss.xaeroatlases.Xaeroatlases;
import org.molodoyss.xaeroatlases.client.config.ConfigModMenuIntegration;

public class ModClientCommands {
    public static void init() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, buildContext) -> {
            dispatcher.register(
                    ClientCommands.literal("xaero-atlases-client")
                            .then(ClientCommands.literal("config")
                                    .then(ClientCommands.literal("open")
                                            .executes(context -> {
                                                Screen configScreen = new ConfigModMenuIntegration().getModConfigScreenFactory().create(null);
                                                Minecraft.getInstance().execute(() -> Minecraft.getInstance().gui.setScreen(configScreen));
                                                return 1;
                                            })
                                    )
                            )
            );
        });
    }
}

package org.molodoyss.xaeroatlases.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import org.molodoyss.xaeroatlases.Xaeroatlases;

public class ModKeybinds {
    public static final KeyMapping DEBUG_MODE = KeyBindingHelper.registerKeyBinding(new KeyMapping(
        "key.xaero_atlases.debug_mode",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_PAGE_UP,
        KeyMapping.CATEGORY_MISC
    ));

    public static void handleDebugModeKey() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            if (DEBUG_MODE.consumeClick()) {
                boolean value = Xaeroatlases.switchDebugMode();
                if (value) {
                    client.player.sendSystemMessage(Component.translatable("text.xaero-atlases.debug_mode.enabled"));
                    return;
                }
                client.player.sendSystemMessage(Component.translatable("text.xaero-atlases.debug_mode.disabled"));
            }
        });
    }

    public static void init() {
        handleDebugModeKey();
    }
}

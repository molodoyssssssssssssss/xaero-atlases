package org.molodoyss.xaeroatlases.config;

import de.marhali.json5.Json5;
import de.marhali.json5.Json5Object;
import de.marhali.json5.Json5Primitive;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import org.molodoyss.xaeroatlases.ModNetworkingConstants;
import org.molodoyss.xaeroatlases.Xaeroatlases;
import org.molodoyss.xaeroatlases.api.FormattingManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class ModConfig {
    public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("xaeroatlases.json5");

    private static boolean enableShowingCoordsWithCompass = true;
    private static boolean reducedDebugInfo = true;

    private static MinecraftServer server;

    public static boolean isEnabledShowingCoordsWithCompass() {
        return enableShowingCoordsWithCompass;
    }
    public static void setEnabledShowingCoordsWithCompass(boolean value) {
        enableShowingCoordsWithCompass = value;
        server.getPlayerList().getPlayers().forEach(player -> {
            ServerPlayNetworking.send(player, ModNetworkingConstants.CLIENTBOUND_ENABLED_COMPASS_COORDS, new FriendlyByteBuf(PacketByteBufs.create().writeBoolean(enableShowingCoordsWithCompass)));
        });
        save(CONFIG_PATH.toFile());
    }

    public static boolean isReducedDebugInfo() {
        return reducedDebugInfo;
    }
    public static void setReducedDebugInfo(boolean value) {
        reducedDebugInfo = value;
        if (server != null) {
            server.getGameRules().getRule(GameRules.RULE_REDUCEDDEBUGINFO).set(value, server);
            Xaeroatlases.LOGGER.info("Updated value of gamerule 'reduced_debug_info' (singleplayer)");
            server.getPlayerList().getPlayers().forEach(player -> {
                ServerPlayNetworking.send(player, ModNetworkingConstants.CLIENTBOUND_REDUCED_DEBUG_INFO_VALUE, new FriendlyByteBuf(PacketByteBufs.create().writeBoolean(value)));
            });
        }

        save(CONFIG_PATH.toFile());
    }


    public static void init(MinecraftServer server) {
        Xaeroatlases.LOGGER.info("Initializing server config...");
        ModConfig.server = server;
        setReducedDebugInfo(reducedDebugInfo);
        File file = CONFIG_PATH.toFile();
        if (!file.exists()) {
            save(file);
            return;
        }
        load(file);
    }

    public static void reload() {
        ModConfig.init(server);
    }

    public static void save(File file) {
        try {
            FileOutputStream stream = new FileOutputStream(file);

            Json5 json5 = Json5.builder(builder -> builder
                    .quoteless()
                    .quoteSingle()
                    .parseComments()
                    .writeComments()
                    .prettyPrinting()
                    .build());
            Json5Object obj = new Json5Object();
            Json5Primitive boolValue = Json5Primitive.fromBoolean(enableShowingCoordsWithCompass);
            Json5Primitive boolValue1 = Json5Primitive.fromBoolean(reducedDebugInfo);
            Json5Primitive stringValue = Json5Primitive.fromString(FormattingManager.getFormattingDefaultTextRaw());
            Json5Primitive stringValue1 = Json5Primitive.fromString(FormattingManager.getFormattingValueTextRaw());
            stringValue.setComment("sets formatting in action bar, when showing coords of DEFAULT text");
            stringValue1.setComment("sets formatting in action bar, when showing coords of VALUE text");
            boolValue.setComment("allows to showing coordinates in action bar when player have compass in hand.");
            boolValue1.setComment("sets value of gamerule 'reduced_debug_info'.");
            obj.add("enable_coords_with_compass", boolValue);
            obj.add("reduced_debug_info", boolValue1);
            obj.add("formatting_default_text", stringValue);
            obj.add("formatting_value_text", stringValue1);
            stream.write(json5.serialize(obj).getBytes(StandardCharsets.UTF_8));
            stream.close();
            Xaeroatlases.LOGGER.info("Successfully updated config file!");

        }  catch (Exception e) {
            Xaeroatlases.LOGGER.error("An problem is occurred, when config is creating:\n\t%s".formatted(e.toString()));
        }
    }

    public static void load(File file) {
        try {
            FileInputStream stream = new FileInputStream(file);
            String json = new String(stream.readAllBytes());
            Json5 json5 = Json5.builder(builder -> builder
                    .quoteless()
                    .quoteSingle()
                    .parseComments()
                    .writeComments()
                    .prettyPrinting()
                    .build()
            );
            Json5Object obj = json5.parse(json).getAsJson5Object();
            enableShowingCoordsWithCompass = obj.get("enable_coords_with_compass").getAsBoolean();
            reducedDebugInfo = obj.get("reduced_debug_info").getAsBoolean();
            FormattingManager.setFormattingDefaultText(obj.get("formatting_default_text").getAsString());
            FormattingManager.setFormattingValueText(obj.get("formatting_value_text").getAsString());
            if (server != null) {
                server.getGameRules().getRule(GameRules.RULE_REDUCEDDEBUGINFO).set(reducedDebugInfo, server);
                server.getPlayerList().getPlayers().forEach(player -> {
                    ServerPlayNetworking.send(player, ModNetworkingConstants.CLIENTBOUND_REDUCED_DEBUG_INFO_VALUE, new FriendlyByteBuf(PacketByteBufs.create().writeBoolean(reducedDebugInfo)));
                });
            }
            stream.close();
            Xaeroatlases.LOGGER.info("Loaded a values from config files!");
        } catch (Exception e) {
            Xaeroatlases.LOGGER.error("An problem is occurred, when config values is loading:\n\t%s".formatted(e.toString()));
            enableShowingCoordsWithCompass = false;
        }
    }
}

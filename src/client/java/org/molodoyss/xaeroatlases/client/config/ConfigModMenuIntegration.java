package org.molodoyss.xaeroatlases.client.config;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.impl.controller.TickBoxControllerBuilderImpl;
import net.minecraft.network.chat.Component;
import org.molodoyss.xaeroatlases.config.ModConfig;

public class ConfigModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> YetAnotherConfigLib.createBuilder()
                .title(Component.literal("Xaero Atlases"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.translatable("gui.xaero-atlases.config.category.basic"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.translatable("gui.xaero-atlases.config.option.compass-coords"))
                                .description(OptionDescription.createBuilder()
                                        .text(Component.translatable("gui.xaero-atlases.config.option.compass-coords.description"))
                                        .build()
                                )
                                .binding(true, ModConfig::isEnabledShowingCoordsWithCompass, ModConfig::setEnabledShowingCoordsWithCompass)
                                .controller(TickBoxControllerBuilderImpl::new)
                                .build()
                        )
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.translatable("gui.xaero-atlases.config.option.reduced_debug_info"))
                                .description(OptionDescription.createBuilder()
                                        .text(Component.translatable("gui.xaero-atlases.config.option.reduced_debug_info.description"))
                                        .build()
                                )
                                .binding(true, ModConfig::isReducedDebugInfo, ModConfig::setReducedDebugInfo)
                                .controller(TickBoxControllerBuilderImpl::new)
                                .build()
                        )
                        .build()
                )
                .build()
                .generateScreen(parentScreen);
    }
}

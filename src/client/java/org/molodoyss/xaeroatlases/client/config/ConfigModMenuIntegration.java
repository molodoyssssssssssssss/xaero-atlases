package org.molodoyss.xaeroatlases.client.config;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.gui.image.ImageRenderer;
import dev.isxander.yacl3.gui.image.ImageRendererFactory;
import dev.isxander.yacl3.gui.image.impl.ResourceTextureImage;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.TickBoxControllerBuilderImpl;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.molodoyss.xaeroatlases.Xaeroatlases;
import org.molodoyss.xaeroatlases.api.FormattingManager;
import org.molodoyss.xaeroatlases.client.commands.ModClientCommands;
import org.molodoyss.xaeroatlases.config.ModConfig;

public class ConfigModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> {
            try {
                return YetAnotherConfigLib.createBuilder()
                        .title(Component.literal("Xaero Atlases"))
                        .category(ConfigCategory.createBuilder()
                                .name(Component.translatable("gui.xaero-atlases.config.category.basic"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.translatable("gui.xaero-atlases.config.option.compass-coords"))
                                        .description(OptionDescription.createBuilder()
                                                .customImage(ResourceTextureImage.createFactory(Xaeroatlases.id("textures/options/show_coords_with_compass.png"), 0f, 0f, 853, 233, 853, 233).prepareImage().completeImage())
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
                                                .customImage(ResourceTextureImage.createFactory(Xaeroatlases.id("textures/options/reduced_debug_info.png"), 0f, 0f, 386, 79, 386, 79).prepareImage().completeImage())
                                                .text(Component.translatable("gui.xaero-atlases.config.option.reduced_debug_info.description"))
                                                .build()
                                        )
                                        .binding(true, ModConfig::isReducedDebugInfo, ModConfig::setReducedDebugInfo)
                                        .controller(TickBoxControllerBuilderImpl::new)
                                        .build()
                                )
                                .option(Option.<String>createBuilder()
                                        .name(Component.translatable("gui.xaero-atlases.config.option.formatting_default_text"))
                                        .description(OptionDescription.createBuilder()
                                                .customImage(ResourceTextureImage.createFactory(Xaeroatlases.id("textures/options/formatting_default_text.png"), 0f, 0f, 272, 32, 272, 32).prepareImage().completeImage())
                                                .text(Component.translatable("gui.xaero-atlases.config.option.formatting_default_text.description"))
                                                .build()
                                        )
                                        .binding("&3", FormattingManager::getFormattingDefaultTextRaw, FormattingManager::setFormattingDefaultText)
                                        .controller(StringControllerBuilderImpl::new)
                                        .build()
                                )
                                .option(Option.<String>createBuilder()
                                        .name(Component.translatable("gui.xaero-atlases.config.option.formatting_value_text"))
                                        .description(OptionDescription.createBuilder()
                                                .customImage(ResourceTextureImage.createFactory(Xaeroatlases.id("textures/options/formatting_value_text.png"), 0f, 0f, 289, 33, 289, 33).prepareImage().completeImage())
                                                .text(Component.translatable("gui.xaero-atlases.config.option.formatting_value_text.description"))
                                                .build()
                                        )
                                        .binding("&b&l", FormattingManager::getFormattingValueTextRaw, FormattingManager::setFormattingValueText)
                                        .controller(StringControllerBuilderImpl::new)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                        .generateScreen(parentScreen);
            } catch (Exception e) {
                Xaeroatlases.LOGGER.info("Failed to load options: %s".formatted(e.toString()));
            }
            return parentScreen;
        };
    }
}

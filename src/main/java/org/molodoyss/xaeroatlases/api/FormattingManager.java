package org.molodoyss.xaeroatlases.api;

import org.molodoyss.xaeroatlases.config.ModConfig;

public class FormattingManager {
    private static String formattingDefaultTextRaw = "&3";
    private static String formattingValueTextRaw = "&b&l";
    private static String formattingDefaultTextFormatted = "§3";
    private static String formattingValueTextFormatted = "§b§l";

    //SETTERS
    public static void setFormattingDefaultText(String formattingDefaultText) {
        FormattingManager.formattingDefaultTextFormatted = Utils.format(formattingDefaultText);
        FormattingManager.formattingDefaultTextRaw = formattingDefaultText;
        ModConfig.save(ModConfig.CONFIG_PATH.toFile());
    }
    public static void setFormattingValueText(String formattingValueText) {
        FormattingManager.formattingValueTextFormatted = Utils.format(formattingValueText);
        FormattingManager.formattingValueTextRaw = formattingValueText;
        ModConfig.save(ModConfig.CONFIG_PATH.toFile());
    }
    //GETTERS
    //FOR FORMATTING
    public static String getFormattingDefaultTextFormatted() {
        return formattingDefaultTextFormatted;
    }
    public static String getFormattingValueTextFormatted() {
        return formattingValueTextFormatted;
    }

    //FOR CONFIG
    public static String getFormattingDefaultTextRaw() {
        return formattingDefaultTextRaw;
    }
    public static String getFormattingValueTextRaw() {
        return formattingValueTextRaw;
    }

}

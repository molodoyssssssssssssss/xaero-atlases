package org.molodoyss.xaeroatlases.api;

public class FormattingManager {
    private static String formattingDefaultText = "§3";
    private static String formattingValueText = "§b§l";

    public static String getFormattingDefaultText() {
        return formattingDefaultText;
    }

    public static void setFormattingDefaultText(String formattingDefaultText) {
        FormattingManager.formattingDefaultText = formattingDefaultText;
    }

    public static String getFormattingValueText() {
        return formattingValueText;
    }

    public static void setFormattingValueText(String formattingValueText) {
        FormattingManager.formattingValueText = formattingValueText;
    }
}

package org.molodoyss.xaeroatlases.api;

public class Utils {
    public static String getWithBigLetterInTheBeginning(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}

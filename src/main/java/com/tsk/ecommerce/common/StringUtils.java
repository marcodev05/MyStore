package com.tsk.ecommerce.common;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    public static Boolean isBlank(String string){
        return string.isBlank() || string == null;
    }

    public static String toSlug(String input) {
        if (input == null) {
            return "";
        }
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}

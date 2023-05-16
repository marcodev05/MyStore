package com.tsk.ecommerce.common;

public class StringUtils {
    public static Boolean isBlank(String string){
        return string.isBlank() || string == null;
    }
}

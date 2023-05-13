package com.tsk.ecommerce.common;

public class StringUtils {
    public final Boolean isBlank(String string){
        return string.isBlank() || string == null;
    }
}

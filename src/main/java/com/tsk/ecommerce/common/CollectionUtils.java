package com.tsk.ecommerce.common;

import java.util.Collection;

public class CollectionUtils {

    public final Boolean isEmpty(Collection<?> collection){
        return collection.isEmpty() || collection == null;
    }
}

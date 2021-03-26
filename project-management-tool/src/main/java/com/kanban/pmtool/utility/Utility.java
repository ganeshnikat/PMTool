package com.kanban.pmtool.utility;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

public class Utility {

    public static boolean isObjectEmpty(@Nullable Object str) {
        return str == null || "".equals(str);
    }

    public static boolean isCollectionEmpty(@Nullable Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isMapEmpty(@Nullable Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}

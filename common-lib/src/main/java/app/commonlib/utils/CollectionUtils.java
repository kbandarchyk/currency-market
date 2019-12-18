package app.commonlib.utils;

import java.util.Collection;

public final class CollectionUtils {
    
    private CollectionUtils(){
    }
    
    public static boolean isEmptyCollection(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
    
    public static boolean isNotEmptyCollection(final Collection<?> collection) {
        return !isEmptyCollection(collection);
    }
}

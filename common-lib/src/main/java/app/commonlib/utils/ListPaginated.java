package app.commonlib.utils;

import java.util.List;

public class ListPaginated<T extends QueryDto> {
    
    private final List<T> sourceList;
    private final Long fullListSize;
    private final Pagination pagination;
    
    public ListPaginated(final List<T> sourceList
                        , final Long fullListSize
                        , final Pagination pagination ) {
        super();
        this.sourceList = sourceList;
        this.fullListSize = fullListSize;
        this.pagination = pagination;
    }
    
    public List<T> getSourceList() {
        return sourceList;
    }
    
    public Long getFullListSize() {
        return fullListSize;
    }
    
    public Pagination getPagination() {
        return pagination;
    }
    
    @Override
    public String toString() {
        return String.format( "ListPaginated [sourceList=%s, fullListSize=%s, pagination=%s]"
                            , sourceList
                            , fullListSize
                            , pagination );
    }
}

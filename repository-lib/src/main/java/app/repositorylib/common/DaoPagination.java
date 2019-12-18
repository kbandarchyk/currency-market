package app.repositorylib.common;

public class DaoPagination {
    
    private final int offset;
    private final int size;
    private final DaoSortField sortField;
    
    public DaoPagination( final int offset
                        , final int size
                        , final DaoSortField sortField ) {
        this.offset = offset;
        this.size = size;
        this.sortField = sortField;
    }
    
    public int getOffset() {
        return offset;
    }
    
    public int getSize() {
        return size;
    }
    
    public DaoSortField getSortField() {
        return sortField;
    }
    
}

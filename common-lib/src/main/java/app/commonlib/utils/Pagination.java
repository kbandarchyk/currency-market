package app.commonlib.utils;

public class Pagination {
    
    private final int offset;
    private final int size;
    private final String sortField;
    private final SortDirection sortDirection;

    public Pagination( final int page
                     , final int size
                     , final String sortField
                     , final SortDirection sortDirection) {
        this.offset = (page - 1) * size;
        this.size = size;
        this.sortField = sortField;
        this.sortDirection = sortDirection;
    }
    
    public int getOffset() {
        return offset;
    }
    
    public int getSize() {
        return size;
    }
    
    public String getSortField() {
        return sortField;
    }
    
    public SortDirection getSortDirection() {
        return sortDirection;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Pagination [offset=%s, size=%s, sortField=%s, sortDirection=%s]"
                , offset, size, sortField, sortDirection);
    }
}
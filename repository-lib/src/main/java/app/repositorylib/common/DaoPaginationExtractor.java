package app.repositorylib.common;

import app.commonlib.utils.Pagination;
import app.commonlib.utils.SortDirection;

import java.util.function.BiFunction;

public final class DaoPaginationExtractor {

    private DaoPaginationExtractor() {
    }

    public static DaoPagination extract( final Pagination pagination
                                       , final BiFunction< String, SortDirection, DaoSortField> sortFieldMapper ) {
        
        return new DaoPagination( pagination.getOffset()
                                , pagination.getSize()
                                , sortFieldMapper.apply( pagination.getSortField()
                                                       , pagination.getSortDirection() ) );
    }
}
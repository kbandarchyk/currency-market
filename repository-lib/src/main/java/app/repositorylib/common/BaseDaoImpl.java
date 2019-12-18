package app.repositorylib.common;

import app.commonlib.utils.Pagination;
import app.commonlib.utils.SortDirection;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.Table;

import java.util.List;
import java.util.Optional;

import static app.repositorylib.common.DaoPaginationExtractor.extract;

public abstract class BaseDaoImpl<T extends RepoCriteria, V extends Record> {
    
    protected final DSLContext dsl;
    
    public BaseDaoImpl( final DSLContext dsl ) {
        this.dsl = dsl;
    }
    
    protected abstract Table<V> getEntityName();
    
    protected abstract SelectQuery<Record> constructListQuery( final SelectQuery<Record> query, final T repoCriteria );
    
    protected abstract SelectQuery<Record> constructSingleQuery( final SelectQuery<Record> query, final T repoCriteria );
    
    protected abstract DaoSortField constructSortFieldMapper( final String sortFieldInput, final SortDirection sortDirection );
    
    public V create( final V record ){
        return dsl.insertInto( getEntityName() )
                  .columns( record.fields() )
                  .values( record.valuesRow() )
                  .returning()
                  .fetchOne()
                  .into( getEntityName() );
    }
    
    public V update( final V record ){
        return dsl.insertInto( getEntityName() )
                  .columns( record.fields() )
                  .values( record.valuesRow() )
                  .returning()
                  .fetchOne()
                  .into( getEntityName() );
    }
    
    public List<V> fetchListBy( final T repoCriteria, final Pagination pagination ) {
    
        final var daoPagination = extract( pagination, this::constructSortFieldMapper );
        final var query = dsl.selectQuery();
        
        query.addFrom( getEntityName() );
        query.addOrderBy( daoPagination.getSortField().getOrderField() );
        query.addLimit( daoPagination.getSize() );
        query.addOffset( daoPagination.getOffset() );
        constructListQuery( query, repoCriteria );
        
        return query.fetch()
                    .into( getEntityName() );
    }
    
    public Optional<V> fetchBy( final T repoCriteria ) {
        
        final var query = dsl.selectQuery();
        
        query.addFrom( getEntityName() );
        constructSingleQuery( query, repoCriteria );
        
        return query.fetchOptional()
                    .map( result -> result.into( getEntityName() ) );
    }
    
    public long fetchCount( final T repoCriteria, final Pagination pagination ) {
    
        final var daoPagination = extract( pagination, this::constructSortFieldMapper );
        final var query = dsl.selectQuery();
    
        query.addFrom( getEntityName() );
        query.addOrderBy( daoPagination.getSortField().getOrderField() );
        query.addLimit( daoPagination.getSize() );
        query.addOffset( daoPagination.getOffset() );
        constructListQuery( query, repoCriteria );
        
        return query.fetch()
                    .size();
    }
    
}

package app.repositorylib.dao.impl;

import app.commonlib.utils.SortDirection;
import app.repositorylib.common.BaseDaoImpl;
import app.repositorylib.common.DaoSortField;
import app.repositorylib.criteria.CurrencyRepoCriteria;
import app.repositorylib.dao.CurrencyDao;
import app.repositorylib.dao.Tables;
import app.repositorylib.dao.tables.records.CurrencyRecord;
import app.repositorylib.exception.SortFieldMissingException;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static app.commonlib.utils.CollectionUtils.isNotEmptyCollection;
import static app.repositorylib.dao.tables.Currency.CURRENCY;

@Service
public class CurrencyDaoImpl extends BaseDaoImpl<CurrencyRepoCriteria, CurrencyRecord> implements CurrencyDao {
    
    @Autowired
    public CurrencyDaoImpl( final DSLContext dsl ) {
        super( dsl );
    }
    
    @Override
    protected List<Field<?>> insertingFields() {
        return List.of( CURRENCY.NAME
                      , CURRENCY.CODE
                      , CURRENCY.DESCRIPTION );
    }
    
    @Override
    protected SelectQuery<Record> constructListQuery( final SelectQuery<Record> query, final CurrencyRepoCriteria repoCriteria ) {
        return commonSearchLogic( query, repoCriteria );
    }
    
    @Override
    protected SelectQuery<Record> constructSingleQuery( final SelectQuery<Record> query, final CurrencyRepoCriteria repoCriteria) {
        return commonSearchLogic( query, repoCriteria );
    }
    
    @Override
    protected DaoSortField constructSortFieldMapper( final String sortFieldInput, final SortDirection sortDirection ) {
        switch ( sortFieldInput ){
            case "ID" : return Optional.of( sortDirection == SortDirection.ASC )
                                       .filter( Boolean::booleanValue )
                                       .map( obj -> new DaoSortField( CURRENCY.ID.asc() ) )
                                       .orElseGet( () -> new DaoSortField( CURRENCY.ID.desc() ) );
            
            default: throw new SortFieldMissingException( "Unable to sort by {0} ", sortFieldInput );
        }
    }
    
    ////////////////
    // Util
    ////
    
    protected SelectQuery<Record> commonSearchLogic( final SelectQuery<Record> query
                                                   , final CurrencyRepoCriteria criteria ) {
        
        if( isNotEmptyCollection( criteria.getCurrencyIds() ) ) {
            query.addConditions( Tables.CURRENCY.ID.in( criteria.getCurrencyIds() ) );
        }
        
        if( criteria.getName() != null ){
            query.addConditions( Tables.CURRENCY.NAME.containsIgnoreCase( criteria.getName() ) );
        }
        
        return query;
    }
    
    protected Table<CurrencyRecord> getEntityName() {
        return CURRENCY;
    }
    
}

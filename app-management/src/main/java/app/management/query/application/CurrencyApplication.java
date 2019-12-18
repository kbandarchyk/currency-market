package app.management.query.application;

import app.commonlib.exception.NotFoundException;
import app.commonlib.utils.ListPaginated;
import app.commonlib.utils.Pagination;
import app.management.query.criteria.CurrencyQueryCriteria;
import app.management.query.dto.CurrencyDto;
import app.management.query.service.CurrencyService;
import app.repositorylib.criteria.CurrencyRepoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyApplication {
    
    private static final String CURRENCY_NOT_FOUND = "Currency not found by id : {0}";

    private CurrencyService currencyService;
    
    @Autowired
    public CurrencyApplication( final CurrencyService currencyService ) {
        this.currencyService = currencyService;
    }
    
    public ListPaginated<CurrencyDto> fetchListBy( final CurrencyQueryCriteria queryCriteria, final Pagination pagination ) {
        return new ListPaginated<>( currencyService.fetchListBy( constructRepoCriteria( queryCriteria ), pagination )
                                  , currencyService.fetchCount( constructRepoCriteria( queryCriteria ), pagination )
                                  , pagination );
    }
    
    public CurrencyDto fetchBy( final int currencyId ) {
        return currencyService.fetchBy( CurrencyRepoCriteria.builder()
                                                            .currencyIds( List.of( currencyId ) )
                                                            .build() )
                              .orElseThrow( NotFoundException.supplier( CURRENCY_NOT_FOUND, currencyId ) );
    }
    
    ////////////////
    // Util
    ////
    
    protected CurrencyRepoCriteria constructRepoCriteria( final CurrencyQueryCriteria queryCriteria ) {
    
        return CurrencyRepoCriteria.builder()
                                   .name( queryCriteria.getName() )
                                   .build();
    }
}

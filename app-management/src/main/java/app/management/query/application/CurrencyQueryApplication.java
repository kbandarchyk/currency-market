package app.management.query.application;

import app.commonlib.exception.NotFoundException;
import app.commonlib.utils.ListPaginated;
import app.commonlib.utils.Pagination;
import app.management.query.criteria.CurrencyQueryCriteria;
import app.management.query.dto.CurrencyQueryDto;
import app.management.query.service.CurrencyQueryService;
import app.repositorylib.criteria.CurrencyRepoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyQueryApplication {
    
    private static final String CURRENCY_NOT_FOUND = "Currency not found by id : {0}";

    private CurrencyQueryService currencyQueryService;
    
    @Autowired
    public CurrencyQueryApplication(final CurrencyQueryService currencyQueryService) {
        this.currencyQueryService = currencyQueryService;
    }
    
    public ListPaginated<CurrencyQueryDto> fetchListBy(final CurrencyQueryCriteria queryCriteria, final Pagination pagination ) {
        return new ListPaginated<>( currencyQueryService.fetchListBy( constructRepoCriteria( queryCriteria ), pagination )
                                  , currencyQueryService.fetchCount( constructRepoCriteria( queryCriteria ), pagination )
                                  , pagination );
    }
    
    public CurrencyQueryDto fetchBy(final int currencyId ) {
        return currencyQueryService.fetchBy( CurrencyRepoCriteria.builder()
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

package app.management.command.domain;

import app.commonlib.exception.NotFoundException;
import app.management.command.domain.domainservice.CurrencyDomainService;
import app.management.command.dto.CurrencyCommandDto;
import app.repositorylib.criteria.CurrencyRepoCriteria;
import app.repositorylib.repository.CurrencyRepository;
import app.repositorylib.repository.dto.CurrencyRepoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyFactory {

    private static final String CURRENCY_NOT_FOUND = "Currency not found by id: {0}";
    
    private CurrencyRepository currencyRepository;
    
    private CurrencyDomainService currencyDomainService;
    
    @Autowired
    public CurrencyFactory( final CurrencyRepository currencyRepository
                          , final CurrencyDomainService currencyDomainService ) {
        this.currencyRepository = currencyRepository;
        this.currencyDomainService = currencyDomainService;
    }
    
    public Currency constructNew( final CurrencyCommandDto commandDto ) {
        
        return new Currency( Optional.empty()
                           , commandDto.getName()
                           , commandDto.getCode()
                           , commandDto.getDescription()
                           , currencyDomainService );
    }
    
    public Currency fetchBy( final Integer currencyId ) {
        
        return currencyRepository.fetchBy( CurrencyRepoCriteria.builder()
                                                               .currencyIds( List.of( currencyId  ) )
                                                               .build() )
                                 .map( this::toCurrency )
                                 .orElseThrow( NotFoundException.supplier( CURRENCY_NOT_FOUND , currencyId ) )  ;
    }
    
    public void create( final Currency currency ){
        currencyRepository.create( toRepoDto( currency ) );
    }
    
    public void update( final Currency currency ){
        currencyRepository.update( toRepoDto( currency ) );
    }
    
    
    ////////////////
    // Util
    ////
    
    protected Currency toCurrency( final CurrencyRepoDto repoDto ) {
        
        return new Currency( Optional.of( repoDto.getId() )
                           , repoDto.getName()
                           , repoDto.getCode()
                           , repoDto.getDescription()
                           , currencyDomainService );
    }
    
    protected CurrencyRepoDto toRepoDto( final Currency currency ){
        
        return new CurrencyRepoDto( currency.getId().orElse( null )
                                  , currency.name
                                  , currency.description
                                  , currency.code );
    }
}

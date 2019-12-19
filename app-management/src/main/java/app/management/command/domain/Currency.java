package app.management.command.domain;

import app.commonlib.utils.DomainEntity;
import app.management.command.domain.domainservice.CurrencyDomainService;

import java.util.Optional;

public class Currency extends DomainEntity {
    
    protected final String name;
    protected final String code;
    protected final String description;
    
    private CurrencyDomainService domainService;
    
    public Currency( final Optional<Long> id
                   , final String name
                   , final String code
                   , final String description
                   , final CurrencyDomainService domainService ) {
        super( id );
        this.name = name;
        this.code = code;
        this.description = description;
        this.domainService = domainService;
        
        init();
    }
    
    private void init() {
        domainService.checkFields( name, code );
    }
    
    public Currency updateFrom( final Currency newCurrency ) {
        
        return new Currency( getId()
                           , newCurrency.name
                           , newCurrency.code
                           , newCurrency.description
                           , domainService );
    }
}

package app.management.command.application;

import app.management.command.domain.CurrencyFactory;
import app.management.command.dto.CurrencyCommandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyCommandApplication {

    private CurrencyFactory currencyFactory;
    
    @Autowired
    public CurrencyCommandApplication(final CurrencyFactory currencyFactory ) {
        this.currencyFactory = currencyFactory;
    }
    
    public void createCurrency( final CurrencyCommandDto commandDto ){
        currencyFactory.create( currencyFactory.constructNew( commandDto ) );
    }
    
    public void updateCurrency( final Integer currencyId
                              , final CurrencyCommandDto commandDto ){
        currencyFactory.update( currencyFactory.fetchBy( currencyId )
                                               .updateFrom( currencyFactory.constructNew( commandDto ) ) );
    }
}

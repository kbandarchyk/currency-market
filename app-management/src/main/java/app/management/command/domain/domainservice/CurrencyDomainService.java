package app.management.command.domain.domainservice;

import app.commonlib.exception.CreateInvalidObjectException;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang.StringUtils.isBlank;

@Service
public class CurrencyDomainService {

    public void checkFields( final String name
                           , final String code ) {
        
        if( isBlank( name ) ){
            throw new CreateInvalidObjectException( "Currency name can't be empty" );
        }
    
        if( isBlank( code ) ){
            throw new CreateInvalidObjectException( "Currency code can't be empty" );
        }
    }

}

package app.management.query.criteria;

import app.commonlib.utils.QueryCriteria;
import com.fasterxml.jackson.annotation.JsonCreator;

public class CurrencyQueryCriteria implements QueryCriteria {
    
    private final String name;
    
    @JsonCreator
    public CurrencyQueryCriteria( final String name ) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return String.format(
                "CurrencyQueryCriteria [name=%s]"
                , name);
    }
}

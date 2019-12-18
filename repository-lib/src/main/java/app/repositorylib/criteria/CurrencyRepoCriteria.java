package app.repositorylib.criteria;

import app.repositorylib.common.RepoCriteria;
import lombok.Builder;

import java.util.List;

@Builder( toBuilder = true )
public class CurrencyRepoCriteria implements RepoCriteria {

    private final List<Integer> currencyIds;
    private final String name;
    
    public CurrencyRepoCriteria(final List<Integer> currencyIds, final String name ) {
        this.currencyIds = currencyIds;
        this.name = name;
    }
    
    public List<Integer> getCurrencyIds() {
        return currencyIds;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return String.format(
                "CurrencyRepoCriteria [currencyIds=%s, name=%s]"
                , currencyIds, name);
    }
}

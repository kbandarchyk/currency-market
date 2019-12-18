package app.repositorylib.common;

import org.jooq.OrderField;

public class DaoSortField {
    
    private final OrderField<?> orderField;
    
    public DaoSortField( final OrderField<?> orderField ) {
        this.orderField = orderField;
    }
    
    public OrderField<?> getOrderField() {
        return orderField;
    }
}

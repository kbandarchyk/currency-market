package app.commonlib.utils;

import java.util.Optional;

public abstract class DomainEntity {

    private final Optional<Long> id;
    
    public DomainEntity( final Optional<Long> id ) {
        this.id = id;
    }
    
    public Optional<Long> getId() {
        return id;
    }
}

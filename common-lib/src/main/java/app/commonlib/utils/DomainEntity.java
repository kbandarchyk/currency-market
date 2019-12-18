package app.commonlib.utils;

import java.util.Optional;

public abstract class DomainEntity {

    private final Optional<Integer> id;
    
    public DomainEntity( final Optional<Integer> id ) {
        this.id = id;
    }
    
    public Optional<Integer> getId() {
        return id;
    }
}

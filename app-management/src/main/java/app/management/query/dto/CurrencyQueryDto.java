package app.management.query.dto;

import app.commonlib.utils.QueryDto;

public class CurrencyQueryDto implements QueryDto {
    
    private final Long id;
    private final String name;
    private final String code;
    private final String description;
    
    public CurrencyQueryDto( final Long id
                           , final String name
                           , final String code
                           , final String description ) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return String.format(
                "CurrencyRepoDto [id=%s, name=%s, code=%s, description=%s]"
                , id, name, code, description);
    }
}

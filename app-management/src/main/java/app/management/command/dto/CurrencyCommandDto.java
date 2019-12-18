package app.management.command.dto;

import app.commonlib.utils.CommandDto;

public class CurrencyCommandDto implements CommandDto {
    
    private final String name;
    private final String code;
    private final String description;
    
    public CurrencyCommandDto( final String name
                             , final String code
                             , final String description ) {
        this.name = name;
        this.code = code;
        this.description = description;
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
                "CurrencyCommandDto [name=%s, code=%s, description=%s]"
                , name, code, description);
    }
}

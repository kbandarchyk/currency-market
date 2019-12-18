package app.repositorylib.repository.dto;

import app.repositorylib.common.RepoDto;

public class CurrencyRepoDto implements RepoDto {
    
    private final Integer id;
    private final String name;
    private final String code;
    private final String description;
    
    public CurrencyRepoDto( final Integer id
                          , final String name
                          , final String code
                          , final String description ) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
    }
    
    public Integer getId() {
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

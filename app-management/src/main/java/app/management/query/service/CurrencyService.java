package app.management.query.service;

import app.commonlib.utils.Pagination;
import app.management.query.dto.CurrencyDto;
import app.repositorylib.criteria.CurrencyRepoCriteria;
import app.repositorylib.repository.CurrencyRepository;
import app.repositorylib.repository.dto.CurrencyRepoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toUnmodifiableList;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    
    @Autowired
    public CurrencyService(final CurrencyRepository currencyRepository ) {
        this.currencyRepository = currencyRepository;
    }
    
    public List<CurrencyDto> fetchListBy( final CurrencyRepoCriteria repoCriteria, final Pagination pagination ) {
        
        return currencyRepository.fetchListBy( repoCriteria, pagination )
                                 .stream()
                                 .map( this::toDto )
                                 .collect(toUnmodifiableList() );
    }
    
    public Optional<CurrencyDto> fetchBy( final CurrencyRepoCriteria repoCriteria ){
        
        return currencyRepository.fetchBy( repoCriteria )
                                 .map( this::toDto );
    }
    
    public long fetchCount( final CurrencyRepoCriteria repoCriteria, final Pagination pagination ) {
        
        return currencyRepository.fetchCount( repoCriteria, pagination );
    }
    
    ////////////////
    // Util
    ////
    
    protected CurrencyDto toDto( final CurrencyRepoDto repoDto ) {
        
        return new CurrencyDto( repoDto.getId()
                              , repoDto.getName()
                              , repoDto.getCode()
                              , repoDto.getDescription() );
    }
}

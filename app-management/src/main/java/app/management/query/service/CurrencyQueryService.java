package app.management.query.service;

import app.commonlib.utils.Pagination;
import app.management.query.dto.CurrencyQueryDto;
import app.repositorylib.criteria.CurrencyRepoCriteria;
import app.repositorylib.repository.CurrencyRepository;
import app.repositorylib.repository.dto.CurrencyRepoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toUnmodifiableList;

@Service
public class CurrencyQueryService {
    private final CurrencyRepository currencyRepository;
    
    @Autowired
    public CurrencyQueryService(final CurrencyRepository currencyRepository ) {
        this.currencyRepository = currencyRepository;
    }
    
    public List<CurrencyQueryDto> fetchListBy(final CurrencyRepoCriteria repoCriteria, final Pagination pagination ) {
        
        return currencyRepository.fetchListBy( repoCriteria, pagination )
                                 .stream()
                                 .map( this::toDto )
                                 .collect(toUnmodifiableList() );
    }
    
    public Optional<CurrencyQueryDto> fetchBy(final CurrencyRepoCriteria repoCriteria ){
        
        return currencyRepository.fetchBy( repoCriteria )
                                 .map( this::toDto );
    }
    
    public long fetchCount( final CurrencyRepoCriteria repoCriteria, final Pagination pagination ) {
        
        return currencyRepository.fetchCount( repoCriteria, pagination );
    }
    
    ////////////////
    // Util
    ////
    
    protected CurrencyQueryDto toDto(final CurrencyRepoDto repoDto ) {
        
        return new CurrencyQueryDto( repoDto.getId()
                                   , repoDto.getName()
                                   , repoDto.getCode()
                                   , repoDto.getDescription() );
    }
}

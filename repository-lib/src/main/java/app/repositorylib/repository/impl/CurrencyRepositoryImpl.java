package app.repositorylib.repository.impl;

import app.commonlib.utils.Pagination;
import app.repositorylib.criteria.CurrencyRepoCriteria;
import app.repositorylib.dao.CurrencyDao;
import app.repositorylib.dao.tables.records.CurrencyRecord;
import app.repositorylib.repository.CurrencyRepository;
import app.repositorylib.repository.dto.CurrencyRepoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toUnmodifiableList;

@Service
public class CurrencyRepositoryImpl implements CurrencyRepository {

    private final CurrencyDao currencyDao;
    
    @Autowired
    public CurrencyRepositoryImpl( final CurrencyDao currencyDao ) {
        this.currencyDao = currencyDao;
    }
    
    @Override
    public Optional<CurrencyRepoDto> fetchBy( final CurrencyRepoCriteria criteria ) {
        return currencyDao.fetchBy( criteria )
                          .map( this::toRepoDto );
    }
    
    @Override
    public List<CurrencyRepoDto> fetchListBy(final CurrencyRepoCriteria criteria, final Pagination pagination ) {
        return currencyDao.fetchListBy( criteria, pagination )
                          .stream()
                          .map( this::toRepoDto )
                          .collect( toUnmodifiableList() );
    }
    
    @Override
    public long fetchCount(final CurrencyRepoCriteria criteria, final Pagination pagination ) {
        return currencyDao.fetchCount( criteria, pagination );
    }
    
    @Override
    public CurrencyRepoDto create( final CurrencyRepoDto currency ) {
        return toRepoDto( currencyDao.create( toRecord( currency ) ) );
    }
    
    @Override
    public CurrencyRepoDto update( final CurrencyRepoDto currency ) {
        return toRepoDto( currencyDao.update( toRecord( currency ) ) );
    }
    
    
    ////////////////
    // Util
    ////
    
    protected CurrencyRepoDto toRepoDto( final CurrencyRecord record ) {
        
        return new CurrencyRepoDto( record.getId()
                                  , record.getName()
                                  , record.getCode()
                                  , record.getDescription() );
    }
    
    protected CurrencyRecord toRecord( final CurrencyRepoDto repoDto ){
        return new CurrencyRecord( repoDto.getId()
                                 , repoDto.getName()
                                 , repoDto.getCode()
                                 , repoDto.getDescription() );
    }
    
}

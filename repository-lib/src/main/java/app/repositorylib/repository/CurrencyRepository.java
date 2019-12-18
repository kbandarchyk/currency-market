package app.repositorylib.repository;

import app.commonlib.utils.Pagination;
import app.repositorylib.criteria.CurrencyRepoCriteria;
import app.repositorylib.repository.dto.CurrencyRepoDto;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository {
    
    Optional<CurrencyRepoDto> fetchBy( CurrencyRepoCriteria criteria );
    List<CurrencyRepoDto> fetchListBy(CurrencyRepoCriteria criteria, Pagination pagination );
    long fetchCount(CurrencyRepoCriteria criteria, Pagination pagination );
    CurrencyRepoDto create( CurrencyRepoDto currency );
    CurrencyRepoDto update( CurrencyRepoDto currency );
}

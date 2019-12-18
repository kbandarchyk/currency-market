package app.repositorylib.dao;

import app.commonlib.utils.Pagination;
import app.repositorylib.criteria.CurrencyRepoCriteria;
import app.repositorylib.dao.tables.records.CurrencyRecord;

import java.util.List;
import java.util.Optional;

public interface CurrencyDao {

    Optional<CurrencyRecord> fetchBy(CurrencyRepoCriteria criteria );
    List<CurrencyRecord> fetchListBy(CurrencyRepoCriteria criteria, Pagination pagination );
    long fetchCount(CurrencyRepoCriteria criteria, Pagination pagination );
    CurrencyRecord create( CurrencyRecord currency );
    CurrencyRecord update( CurrencyRecord currency );
}

package app.management.query.contoller;

import app.commonlib.exception.NotFoundException;
import app.commonlib.utils.ListPaginated;
import app.management.query.application.CurrencyApplication;
import app.management.query.criteria.CurrencyQueryCriteria;
import app.management.query.dto.CurrencyDto;
import app.repositorylib.exception.SortFieldMissingException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.commonlib.utils.PaginationExtractor.extract;

@RestController
@RequestMapping( "/api/v1.0/currency" )
public class CurrencyController {
    
    private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);
    
    private final CurrencyApplication currencyApplication;
    
    @Autowired
    public CurrencyController( final CurrencyApplication currencyApplication ) {
        this.currencyApplication = currencyApplication;
    }
    
    @PostMapping("/fetchPaginatedList")
    @ApiImplicitParams( { @ApiImplicitParam( required = true, name = "jwt-token", value = "Authorization token", dataType = "String", paramType = "header" )
                        , @ApiImplicitParam( required = true, name = "page", value = "page", dataType = "int", paramType = "query", defaultValue = "1", example = "1" )
                        , @ApiImplicitParam( required = true, name = "size", value = "size", dataType = "int", paramType = "query", defaultValue = "64", example = "64" )
                        , @ApiImplicitParam( required = true, name = "sortField", value = "sortField", dataType = "String", paramType = "query", allowableValues = "ID", defaultValue = "ID" )
                        , @ApiImplicitParam( required = true, name = "sortDirection", value = "sortDirection", dataType = "String", paramType = "query", allowableValues = "ASC, DESC", defaultValue = "DESC" ) } )
    public ListPaginated<CurrencyDto> fetchPaginatedListBy( final @RequestBody CurrencyQueryCriteria queryCriteria
                                                          , final HttpServletRequest httpServletRequest ) {
        return currencyApplication.fetchListBy( queryCriteria, extract( httpServletRequest ) );
    }
    
    @GetMapping("/{currencyId}/fetch")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt-token", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public CurrencyDto fetchBy( final @PathVariable("currencyId") Integer currencyId ) {
        return currencyApplication.fetchBy( currencyId );
    }
   
    @ExceptionHandler({ NotFoundException.class
                      , SortFieldMissingException.class })
    public void handleDefineException( final RuntimeException e
                                     , final HttpServletResponse httpServletResponse) throws IOException {
        logger.error("Defined exception occured inside CurrencyController: ", httpServletResponse, e);
        httpServletResponse.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
    }
    
    @ResponseStatus( code = HttpStatus.INTERNAL_SERVER_ERROR
                   , reason = "An exception occurred. See log for more information")
    @ExceptionHandler({RuntimeException.class})
    public void handleUnexpectedException(final RuntimeException e) {
        logger.error("Exception occured inside CurrencyController: ", e);
    }
}

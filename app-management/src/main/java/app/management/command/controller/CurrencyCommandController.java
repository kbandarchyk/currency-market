package app.management.command.controller;

import app.commonlib.exception.CreateInvalidObjectException;
import app.commonlib.exception.NotFoundException;
import app.management.command.application.CurrencyCommandApplication;
import app.management.command.dto.CurrencyCommandDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping( "/api/v1.0/currency" )
public class CurrencyCommandController {
    
    private static final Logger logger = LoggerFactory.getLogger(CurrencyCommandController.class);
    
    private final CurrencyCommandApplication currencyCommandApplication;
    
    @Autowired
    public CurrencyCommandController( final CurrencyCommandApplication currencyCommandApplication ) {
        this.currencyCommandApplication = currencyCommandApplication;
    }
    
    @PostMapping("/create")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt-token", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public void createCurrency( final @RequestBody CurrencyCommandDto commandDto ) {
        currencyCommandApplication.createCurrency( commandDto );
    }
    
    
    @PostMapping("/{currencyId}/update")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt-token", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public void createCurrency( final @PathVariable("currencyId") Integer currencyId
                              , final @RequestBody CurrencyCommandDto commandDto ) {
        currencyCommandApplication.updateCurrency( currencyId, commandDto );
    }
   
    @ExceptionHandler({ NotFoundException.class
                      , CreateInvalidObjectException.class })
    public void handleDefineException( final RuntimeException e
                                     , final HttpServletResponse httpServletResponse) throws IOException {
        logger.error("Defined exception occured inside CurrencyCommandController: ", httpServletResponse, e);
        httpServletResponse.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
    }
    
    @ResponseStatus( code = HttpStatus.INTERNAL_SERVER_ERROR
                   , reason = "An exception occurred. See log for more information")
    @ExceptionHandler({RuntimeException.class})
    public void handleUnexpectedException(final RuntimeException e) {
        logger.error("Exception occured inside CurrencyCommandController: ", e);
    }
}

package app.repositorylib.exception;

import java.text.MessageFormat;
import java.util.function.Supplier;

public class SortFieldMissingException extends RuntimeException{
    
    public SortFieldMissingException( final String message ) {
        super( message );
    }
    
    public SortFieldMissingException( final String templateMessage
                                    , final Object ... params) {
        super( MessageFormat.format( templateMessage, params ) );
    }
    
    public static Supplier<SortFieldMissingException> supplier( final String templateMessage
                                                              , final Object ... params ){
        return () -> new SortFieldMissingException( templateMessage, params );
    }
}

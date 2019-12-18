package app.commonlib.exception;

import java.text.MessageFormat;
import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException( final String tempalteMessage
                            , final Object ... params) {
        super(MessageFormat.format( tempalteMessage, params));
    }
    
    public static Supplier<NotFoundException> supplier(final String tempalteMessage
                                                      , final Object ... params){
        return () -> new NotFoundException(tempalteMessage, params);
    }
}

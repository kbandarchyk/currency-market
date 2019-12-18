package app.commonlib.exception;

public class CreateInvalidObjectException extends RuntimeException {
    
    private static final long serialVersionUID = 6285584632827217165L;
    
    public CreateInvalidObjectException(final String message) {
        super(message);
    }
}

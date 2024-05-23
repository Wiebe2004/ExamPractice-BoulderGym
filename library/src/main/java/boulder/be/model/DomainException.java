package boulder.be.model;

public class DomainException extends RuntimeException{
    public String field;

    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }
    public DomainException(String field, String message) {
        super(message);
        this.field = field;
    }

        
    
}

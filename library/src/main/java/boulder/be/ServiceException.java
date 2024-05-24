package boulder.be;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String errorCode;

    public ServiceException(String message) {
        super(message);
        this.errorCode = "UNKNOWN";
    }

    public ServiceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "UNKNOWN";
    }

    public ServiceException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

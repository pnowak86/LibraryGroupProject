package library.validator;

public class ErrorField {

    private String key;
    private String message;

    public ErrorField(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }
}

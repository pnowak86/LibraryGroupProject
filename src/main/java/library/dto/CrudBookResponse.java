package library.dto;

public class CrudBookResponse {

    private String message;

    public CrudBookResponse(String message) {
        this.message = message;
    }

    public CrudBookResponse() {
    }

    public String getMessage() {
        return message;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CrudBookResponse that = (CrudBookResponse) o;

        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }
}
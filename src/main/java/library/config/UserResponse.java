package library.config;

import java.util.Collections;
import java.util.List;

import library.validator.ErrorField;

public class UserResponse {

    private boolean success;
    private List<ErrorField> errorMessage;

    public UserResponse() {
        success = true;
    }

    public UserResponse(ErrorField errorField) {
        this.success = false;
        this.errorMessage = Collections.singletonList(errorField);
    }

    public UserResponse(List<ErrorField> errorMessage) {
        this.errorMessage = errorMessage;
        this.success = false;
    }

    public boolean isSuccess() {

        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ErrorField> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<ErrorField> errorMessage) {
        this.errorMessage = errorMessage;
    }
}


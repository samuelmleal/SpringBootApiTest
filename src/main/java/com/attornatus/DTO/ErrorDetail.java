package com.attornatus.DTO;

import java.util.List;
import java.util.Map;

public class ErrorDetail {

    private int code;

    private String message;

    private transient Map<String, List<String>> errorsList;

    private transient Map<String, Object> errorsMap;

    public ErrorDetail(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, List<String>> getErrorsList() {
        return errorsList;
    }

    public void setErrorsList(Map<String, List<String>> errorsList) {
        this.errorsList = errorsList;
    }

    public Map<String, Object> getErrorsMap() {
        return errorsMap;
    }

    public void setErrorsMap(Map<String, Object> errorsMap) {
        this.errorsMap = errorsMap;
    }

}

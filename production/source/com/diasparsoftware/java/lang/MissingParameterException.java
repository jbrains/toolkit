package com.diasparsoftware.java.lang;

public class MissingParameterException extends RuntimeException {
    private String parameterName;

    public MissingParameterException(String parameterName) {
        super("Missing parameter: " + parameterName);
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }
}

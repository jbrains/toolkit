package com.diasparsoftware.store;


public class DataStoreException extends RuntimeException {
    public DataStoreException() {
        super();
    }

    public DataStoreException(Exception cause) {
        super(cause);
    }

    public DataStoreException(String message, Exception cause) {
        super(message, cause);
    }

    public DataStoreException(String message) {
        super(message);
    }
}

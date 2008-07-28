package com.diasparsoftware.javax.jms;

public class MessagingException extends RuntimeException {
    public MessagingException(String message, Exception cause) {
        super(message, cause);
    }
}

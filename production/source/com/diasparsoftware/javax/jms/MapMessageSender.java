package com.diasparsoftware.javax.jms;

import java.util.Map;

public interface MapMessageSender {
    void sendMapMessage(
        String destinationQueueJndiName,
        Map messageContent)
        throws MessagingException;
}

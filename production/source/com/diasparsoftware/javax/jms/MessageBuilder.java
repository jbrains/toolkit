package com.diasparsoftware.javax.jms;

import java.util.Map;

import javax.jms.*;

import com.diasparsoftware.java.util.*;

public class MessageBuilder {
    public void buildMapMessage(
        final MapMessage mapMessage,
        Map messageContent) {

        try {
            CollectionUtil
                .forEachDo(
                    messageContent,
                    new ExceptionalMapEntryClosure() {

                protected void eachMapEntry(Object key, Object value)
                    throws JMSException {

                    mapMessage.setObject((String) key, value);
                }
            });
        }
        catch (Exception e) {
            throw new MessagingException(
                "Unable to build message from " + messageContent,
                e);
        }
    }
}

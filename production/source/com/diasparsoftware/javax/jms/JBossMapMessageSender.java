package com.diasparsoftware.javax.jms;

import java.util.Map;

import javax.jms.*;
import javax.naming.*;

import com.diasparsoftware.java.util.*;

public class JBossMapMessageSender implements MapMessageSender {
    public static final String QUEUE_CONNECTION_FACTORY_JNDI_NAME =
        "ConnectionFactory";

    public void sendMapMessage(
        String destinationQueueName,
        Map messageContent) {

        try {
            Context context = new InitialContext();
            QueueConnectionFactory queueConnectionFactory =
                (QueueConnectionFactory) context.lookup(
                    QUEUE_CONNECTION_FACTORY_JNDI_NAME);

            QueueConnection connection =
                queueConnectionFactory.createQueueConnection();

            Queue submitOrderQueue =
                (Queue) context.lookup(destinationQueueName);

            QueueSession session =
                connection.createQueueSession(
                    false,
                    QueueSession.AUTO_ACKNOWLEDGE);

            connection.start();

            final MapMessage message = session.createMapMessage();

            addMessageContentToMessage(messageContent, message);

            QueueSender sender = session.createSender(submitOrderQueue);
            sender.send(message);

            connection.stop();
            session.close();
            connection.close();
        }
        catch (NamingException e) {
            throw new MessagingException("Unable to send message", e);
        }
        catch (JMSException e) {
            throw new MessagingException("Unable to send message", e);
        }
    }

    private void addMessageContentToMessage(
        Map messageContent,
        final MapMessage message) {

        CollectionUtil
            .forEachDo(messageContent, new MapEntryClosure() {
            public void eachMapEntry(Object key, Object value) {
                try {
                    message.setObject((String) key, value);
                }
                catch (JMSException wrapped) {
                    IllegalArgumentException wrapper =
                        new IllegalArgumentException(
                            "Unable to set message property <"
                                + key
                                + "> with value <"
                                + value
                                + ">");
                    wrapper.initCause(wrapped);
                    throw wrapper;
                }
            }
        });
    }
}

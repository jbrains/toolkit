package com.diasparsoftware.javax.jms.test;

import java.util.Collections;

import javax.jms.*;
import javax.naming.InitialContext;

import junit.framework.TestCase;

import org.easymock.MockControl;
import org.mockejb.jndi.MockContextFactory;

import com.diasparsoftware.javax.jms.JBossMapMessageSender;
import com.sun.jms.MapMessageImpl;

public class SendMapMessageToJBossServerTest extends TestCase {
    public void testHappyPath() throws Exception {
        MockContextFactory.setAsInitial();

        String destinationQueueJndiName = "destinationQueue";
        MapMessage mapMessage = new MapMessageImpl();

        MockControl queueConnectionFactoryControl = MockControl
                .createControl(QueueConnectionFactory.class);

        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) queueConnectionFactoryControl
                .getMock();

        MockControl queueConnectionControl = MockControl
                .createControl(QueueConnection.class);

        QueueConnection queueConnection = (QueueConnection) queueConnectionControl
                .getMock();

        MockControl queueSessionControl = MockControl
                .createControl(QueueSession.class);

        QueueSession queueSession = (QueueSession) queueSessionControl
                .getMock();

        MockControl queueControl = MockControl.createControl(Queue.class);

        Queue queue = (Queue) queueControl.getMock();

        MockControl queueSenderControl = MockControl
                .createControl(QueueSender.class);

        QueueSender queueSender = (QueueSender) queueSenderControl.getMock();

        queueConnectionFactory.createQueueConnection();
        queueConnectionFactoryControl.setReturnValue(queueConnection);

        queueConnection
                .createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
        queueConnectionControl.setReturnValue(queueSession);

        queueConnection.start();
        queueConnectionControl.setVoidCallable();

        queueSession.createMapMessage();
        queueSessionControl.setReturnValue(mapMessage);

        queueSession.createSender(queue);
        queueSessionControl.setReturnValue(queueSender);

        queueSender.send(mapMessage);
        queueSenderControl.setVoidCallable();

        queueConnection.stop();
        queueConnectionControl.setVoidCallable();

        queueSession.close();
        queueSessionControl.setVoidCallable();

        queueConnection.close();
        queueConnectionControl.setVoidCallable();

        InitialContext rootContext = new InitialContext();
        rootContext.bind(
                JBossMapMessageSender.QUEUE_CONNECTION_FACTORY_JNDI_NAME,
                queueConnectionFactory);

        rootContext.bind(destinationQueueJndiName, queue);

        queueConnectionFactoryControl.replay();
        queueConnectionControl.replay();
        queueSessionControl.replay();
        queueSenderControl.replay();
        queueControl.replay();

        JBossMapMessageSender messageSender = new JBossMapMessageSender();

        messageSender.sendMapMessage(destinationQueueJndiName,
                Collections.EMPTY_MAP);

        queueConnectionFactoryControl.verify();
        queueConnectionControl.verify();
        queueSessionControl.verify();
        queueSenderControl.verify();
        queueControl.verify();
    }
}

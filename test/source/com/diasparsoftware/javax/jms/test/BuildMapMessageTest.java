package com.diasparsoftware.javax.jms.test;

import java.util.*;

import javax.jms.*;

import junit.framework.TestCase;

import com.diasparsoftware.javax.jms.*;
import com.sun.jms.MapMessageImpl;

public class BuildMapMessageTest extends TestCase {
    private MapMessageImpl mapMessage;
    private MessageBuilder messageBuilder;

    protected void setUp() throws Exception {
        mapMessage = new MapMessageImpl();
        messageBuilder = new MessageBuilder();
    }

    public void testEmptyMessage() throws Exception {
        Map empty = Collections.EMPTY_MAP;
        messageBuilder.buildMapMessage(mapMessage, empty);
        assertMessageEmpty(mapMessage);
    }

    public void testSingletonMap() throws Exception {
        Map singleton = Collections.singletonMap("a", new Long(762));
        messageBuilder.buildMapMessage(mapMessage, singleton);
        assertEquals(762, mapMessage.getLong("a"));
        assertEquals(1, countBodyContentItems(mapMessage));
    }

    public void testAllSupportedTypes() throws Exception {
        Map content = new HashMap() {
            {
                put("a", new Integer(0));
                put("b", new Long(5678));
                put("c", new Double(3.142));
                put("d", new Float(2.718));
                put("e", new Character('e'));
                put("f", new Byte((byte) 0xA3));
                put("g", "hello");
            }
        };
        
        messageBuilder.buildMapMessage(mapMessage, content);
        assertEquals(0, mapMessage.getInt("a"));
        assertEquals(5678, mapMessage.getLong("b"));
        assertEquals(3.142d, mapMessage.getDouble("c"), 0.0001d);
        assertEquals(2.718f, mapMessage.getFloat("d"), 0.0001f);
        assertEquals('e', mapMessage.getChar("e"));
        assertEquals((byte) 0xA3, mapMessage.getByte("f"));
        assertEquals("hello", mapMessage.getString("g"));

        assertEquals(7, countBodyContentItems(mapMessage));
    }

    public void testGenericObject() throws Exception {
        Map singleton = Collections.singletonMap("b", new ArrayList());
        try {
            messageBuilder.buildMapMessage(mapMessage, singleton);
            fail("Added a generic object to a MapMessage!");
        }
        catch (MessagingException expected) {
            Throwable throwable = expected.getCause();
            assertTrue(
                "Wrong exception type",
                throwable instanceof MessageFormatException);
        }
    }

    private int countBodyContentItems(MapMessage mapMessage)
        throws JMSException {

        int count = 0;
        Enumeration mapNames = mapMessage.getMapNames();
        while (mapNames.hasMoreElements()) {
            count++;
            mapNames.nextElement();
        }

        return count;
    }

    private void assertMessageEmpty(MapMessage mapMessage)
        throws JMSException {

        assertEquals(
            "MapMessage is not empty.",
            0,
            countBodyContentItems(mapMessage));
    }
}

package com.diasparsoftware.util.junit;

import java.util.*;

import junit.framework.*;

import org.apache.commons.collections.*;

import com.diasparsoftware.java.util.*;

public abstract class ValueObjectEqualsTest extends TestCase {
    private Object control;
    private Map differentObjects = new HashMap();

    private Object equalToControl;
    private Object equalToControl2;

    private static final int NUM_ITERATIONS = 20;

    /***
     * Creates the "control" instance of the class under test &mdash;
     * the object against which all the others are to be compared.
    */
    protected abstract Object createControlInstance() throws Exception;

    /***
     * Creates and returns an instance of the class under test that
     * differs from the control instance by having a different value
     * for the specified key property.
     */
    protected abstract Object createInstanceDiffersIn(String keyPropertyName)
        throws Exception;

    /***
     * The names of the key properties used to distinguish
     * unequal instances of this class.
     * 
     * @return
     */
    protected abstract List keyPropertyNames();

    protected void setUp() throws Exception {
        super.setUp();

        control = createControlInstance();
        equalToControl = createControlInstance();
        equalToControl2 = createControlInstance();

        CollectionUtil
            .forEachDo(keyPropertyNames(), new ExceptionalClosure() {
            public Object execute(Object each) throws Exception {
                String eachName = (String) each;
                differentObjects.put(
                    each,
                    createInstanceDiffersIn(eachName));
                return null;
            }
        });

        // We want these assertions to yield errors, not failures.
        try {
            assertNotNull(
                "createControlInstance() returned null",
                control);
            assertNotNull(
                "2nd createControlInstance() returned null",
                equalToControl);
            assertNotNull(
                "3rd createControlInstance() returned null",
                equalToControl2);

            eachDifferentObjectDo(new MapEntryClosure() {
                public void eachMapEntry(Object key, Object value) {
                    assertNotNull(nameOf(key) + "returned null", value);
                }
            });

            Assert.assertNotSame(control, equalToControl);
            Assert.assertNotSame(control, equalToControl2);

            eachDifferentObjectDo(new MapEntryClosure() {
                public void eachMapEntry(Object key, Object value) {
                    Assert.assertNotSame(
                        nameOf(key) + " same as control",
                        control,
                        value);
                    Assert.assertNotSame(
                        nameOf(key) + " same as equalToControl",
                        equalToControl,
                        value);
                    Assert.assertNotSame(
                        nameOf(key) + " same as equalToControl2",
                        equalToControl2,
                        value);
                }
            });

            Assert.assertNotSame(equalToControl, equalToControl2);

            assertEquals(
                "1st and 2nd equal instances of different classes",
                control.getClass(),
                equalToControl.getClass());
            assertEquals(
                "1st and 3rd equal instances of different classes",
                control.getClass(),
                equalToControl2.getClass());

            eachDifferentObjectDo(new MapEntryClosure() {
                public void eachMapEntry(Object key, Object value) {
                    assertEquals(
                        "control instance and "
                            + nameOf(key)
                            + " of different classes",
                        control.getClass(),
                        value.getClass());

                }
            });
        }
        catch (AssertionFailedError ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    /***
     * Tests whether <code>equals</code> holds up against a new
     * <code>Object</code> (should always be <code>false</code>).
     */
    public final void testEqualsAgainstNewObject() {
        final Object o = new Object();

        assertNotEquals(o, control);
        assertNotEquals(o, equalToControl);
        assertNotEquals(o, equalToControl2);

        eachDifferentObjectDo(new MapEntryClosure() {
            public void eachMapEntry(Object key, Object value) {
                assertNotEquals(o, value);
            }
        });
    }

    /***
     * Tests whether <code>equals</code> holds up against <code>null</code>.
     */
    public final void testEqualsAgainstNull() {
        assertNotEquals("null vs. 1st", null, control);
        assertNotEquals("null vs. 2nd", null, equalToControl);
        assertNotEquals("null vs. 3rd", null, equalToControl2);

        eachDifferentObjectDo(new MapEntryClosure() {
            public void eachMapEntry(Object key, Object value) {
                assertNotEquals("null vs. " + nameOf(key), null, value);
            }
        });
    }

    /***
     * Tests whether <code>equals</code> holds up against objects that should
     * not compare equal.
     */
    public final void testEqualsAgainstUnequalObjects() {
        eachDifferentObjectDo(new MapEntryClosure() {
            public void eachMapEntry(Object key, Object value) {
                assertNotEquals(
                    "1st vs. " + nameOf(key),
                    control,
                    value);
                assertNotEquals(
                    "2nd vs. " + nameOf(key),
                    equalToControl,
                    value);
                assertNotEquals(
                    "3rd vs. " + nameOf(key),
                    equalToControl2,
                    value);

                assertNotEquals(
                    nameOf(key) + " vs. 1st",
                    value,
                    control);
                assertNotEquals(
                    nameOf(key) + " vs. 2nd",
                    value,
                    equalToControl);
                assertNotEquals(
                    nameOf(key) + " vs. 3rd",
                    value,
                    equalToControl2);
            }
        });

    }

    /***
     * Tests whether <code>equals</code> is <em>consistent</em>.
     */
    public final void testEqualsIsConsistentAcrossInvocations() {
        for (int i = 0; i < NUM_ITERATIONS; ++i) {
            testEqualsAgainstNewObject();
            testEqualsAgainstNull();
            testEqualsAgainstUnequalObjects();
            testEqualsIsReflexive();
            testEqualsIsSymmetricAndTransitive();
        }
    }

    /***
     * Tests whether <code>equals</code> is <em>reflexive</em>.
     */
    public final void testEqualsIsReflexive() {
        assertEquals("1st equal instance", control, control);
        assertEquals(
            "2nd equal instance",
            equalToControl,
            equalToControl);
        assertEquals(
            "3rd equal instance",
            equalToControl2,
            equalToControl2);

        eachDifferentObjectDo(new MapEntryClosure() {
            public void eachMapEntry(Object key, Object value) {
                assertEquals(nameOf(key) + " instance", value, value);
            }
        });
    }

    /***
     * Tests whether <code>equals</code> is <em>symmetric</em> and
     * <em>transitive</em>.
     */
    public final void testEqualsIsSymmetricAndTransitive() {
        assertEquals("1st vs. 2nd", control, equalToControl);
        assertEquals("2nd vs. 1st", equalToControl, control);

        assertEquals("1st vs. 3rd", control, equalToControl2);
        assertEquals("3rd vs. 1st", equalToControl2, control);

        assertEquals("2nd vs. 3rd", equalToControl, equalToControl2);
        assertEquals("3rd vs. 2nd", equalToControl2, equalToControl);
    }

    /***
     * Tests the <code>hashCode</code> contract.
     */
    public final void testHashCodeContract() {
        assertEquals(
            "1st vs. 2nd",
            control.hashCode(),
            equalToControl.hashCode());
        assertEquals(
            "1st vs. 3rd",
            control.hashCode(),
            equalToControl2.hashCode());
        assertEquals(
            "2nd vs. 3rd",
            equalToControl.hashCode(),
            equalToControl2.hashCode());
    }

    /***
     * Tests the consistency of <code>hashCode</code>.
     */
    public final void testHashCodeIsConsistentAcrossInvocations() {
        int eq1Hash = control.hashCode();
        int eq2Hash = equalToControl.hashCode();
        int eq3Hash = equalToControl2.hashCode();

        final Map differentObjectsHashes = new HashMap();

        eachDifferentObjectDo(new MapEntryClosure() {
            public void eachMapEntry(Object key, Object value) {
                differentObjectsHashes.put(
                    key,
                    new Integer(value.hashCode()));
            }
        });

        for (int i = 0; i < NUM_ITERATIONS; ++i) {
            assertEquals(
                "1st equal instance",
                eq1Hash,
                control.hashCode());
            assertEquals(
                "2nd equal instance",
                eq2Hash,
                equalToControl.hashCode());
            assertEquals(
                "3rd equal instance",
                eq3Hash,
                equalToControl2.hashCode());

            eachDifferentObjectDo(new MapEntryClosure() {
                public void eachMapEntry(Object key, Object value) {
                    assertEquals(
                        nameOf(key) + " instance",
                        ((Integer) differentObjectsHashes.get(key))
                            .intValue(),
                        value.hashCode());
                }
            });
        }
    }

    protected static void assertNotEquals(Object lhs, Object rhs) {
        assertNotEquals(null, lhs, rhs);
    }

    protected static void assertNotEquals(
        String failureMessage,
        Object lhs,
        Object rhs) {
        if (lhs != null)
            assertFalse(failureMessage, lhs.equals(rhs));
    }

    private void eachDifferentObjectDo(Closure closure) {
        CollectionUtils.forAllDo(differentObjects.entrySet(), closure);
    }

    private final String nameOf(Object key) {
        return "objectDiffersBy('" + key + "')";
    }
}

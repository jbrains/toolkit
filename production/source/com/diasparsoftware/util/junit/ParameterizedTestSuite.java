package com.diasparsoftware.util.junit;

import java.lang.reflect.*;
import java.util.*;

import junit.framework.*;

import org.apache.commons.collections.*;

/***
 * Create a parameterized test suite from a collection of fixture
 * objects. Each fixture object is an instance of the test case for
 * which you wish to provide external fixture data. Use "fixturable"
 * test case classes: a test case class with an additional constructor
 * capable of accepting as parameters all the fixture data the test
 * needs.
 * 
 * To use a parameterized test suite, follow these instructions.
 * <ol>
 * <li>Add a constructor to accept your fixture data.</li>
 * <li>Implement <code>setFixture()</code>.</li>
 * <li>Override <code>getName()</code> to include the name
 * of your fixture.</li>
 * </ol> 
 * 
 */
public class ParameterizedTestSuite extends TestSuite {
    /***
	 * Creates a test suite of fixtured test cases from the specified
	 * fixtures.
	 * 
	 * Each fixtures must be an instance of "fixturable" <code>TestCase</code>.
	 * A test case class is fixturable if it defines the method <code>setFixture()</code>
	 * taking as a parameter an instance of itself.
	 * 
	 * @param fixtures
	 */
    public ParameterizedTestSuite(Collection fixtures) {

        CollectionUtils.forAllDo(fixtures, new Closure() {
            public void execute(Object input) {
                TestCase fixture = (TestCase) input;
                addFixturedTests(fixture);
            }
        });
    }

    private void addFixturedTests(TestCase fixture) {
        TestSuite unfixturedSuite = new TestSuite(fixture.getClass());

        Enumeration enumeration = unfixturedSuite.tests();
        while (enumeration.hasMoreElements()) {
            TestCase eachTestCase =
                (TestCase) enumeration.nextElement();

            addFixtureToTestCase(fixture, eachTestCase);
            addTest(eachTestCase);
        }
    }

    private void addFixtureToTestCase(
        TestCase fixture,
        TestCase eachTestCase) {

        Class fixtureClass = fixture.getClass();
        try {
            Method setFixtureMethod =
                fixtureClass.getMethod(
                    "setFixture",
                    new Class[] { fixtureClass });

            setFixtureMethod.invoke(
                eachTestCase,
                new Object[] { fixture });
        }
        catch (SecurityException e) {
            addTest(
                warning(
                    "Unable to invoke setFixture() in class "
                        + fixtureClass.getName()));
        }
        catch (NoSuchMethodException e) {
            addTest(
                warning(
                    "No method setFixture() in class "
                        + fixtureClass.getName()));
        }
        catch (IllegalArgumentException e) {
            addTest(
                warning(
                    "Bad arguments to setFixture() in class "
                        + fixtureClass.getName()));
        }
        catch (IllegalAccessException e) {
            addTest(
                warning(
                    "Insufficient access to invoke setFixture() in class "
                        + fixtureClass.getName()));
        }
        catch (InvocationTargetException reported) {
            addTest(
                warning(
                    "setFixture() threw exception "
                        + reported
                        + "in class "
                        + fixtureClass.getName()));
        }
    }

    public static Test warning(final String message) {
        // TODO Make this public in TestSuite!
        return new TestCase("warning") {
            protected void runTest() {
                fail(message);
            }
        };
    }
}

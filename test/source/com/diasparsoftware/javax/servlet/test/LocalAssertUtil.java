package com.diasparsoftware.javax.servlet.test;

import java.util.Arrays;

import junit.framework.ComparisonFailure;

public abstract class LocalAssertUtil {

	static void assertEqualsOneOf(final String[] expectedValues, final String actualValue) {
		assertEqualsOneOf(null, expectedValues, actualValue);
	}

	public static void assertEqualsOneOf(final String message, final String[] expectedValues, final String actualValue) {

		for (int evIdx = 0; evIdx < expectedValues.length; evIdx++) {
			final String expectedValue = expectedValues[evIdx];
			if (expectedValue == actualValue || expectedValue.equals(actualValue))
				return;
		}

		throw new ComparisonFailure(message, Arrays.toString(expectedValues), actualValue);
	}
}

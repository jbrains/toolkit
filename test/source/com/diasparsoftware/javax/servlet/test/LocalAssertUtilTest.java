package com.diasparsoftware.javax.servlet.test;

import junit.framework.ComparisonFailure;
import junit.framework.TestCase;

public class LocalAssertUtilTest extends TestCase {

	public void testAssertEqualsOneOf_OneValue() {
		LocalAssertUtil.assertEqualsOneOf(new String[] { "ValuesEqual" }, "ValuesEqual");
		LocalAssertUtil.assertEqualsOneOf("Should equal the one value.", new String[] { "ValuesEqual" }, "ValuesEqual");
	}

	public void testAssertEqualsOneOf_IsOneOf() {
		LocalAssertUtil.assertEqualsOneOf(new String[] { "A", "B", "C" }, "B");
		LocalAssertUtil.assertEqualsOneOf("{User Assertion Message}", new String[] { "A", "B", "C" }, "B");
	}

	public void testAssertEqualsOneOf_ExpectedNull() {
		LocalAssertUtil.assertEqualsOneOf(new String[] { "A", null, "C" }, null);
		LocalAssertUtil.assertEqualsOneOf("{User Assertion Message}", new String[] { "A", null, "C" }, null);
	}

	public void testAssertEqualsOneOf_NotOneOf() {
		expectComparisonFailure("expected:<[[A, B, C]]> but was:<[X]>", new Runnable() {
			// @Override
			public void run() {
				LocalAssertUtil.assertEqualsOneOf(new String[] { "A", "B", "C" }, "X");
			}
		});
	}

	public void testAssertEqualsOneOf_NotOneOf_WithMessage() {
		expectComparisonFailure("{User Assertion Message} expected:<[[A, B, C]]> but was:<[X]>", new Runnable() {
			// @Override
			public void run() {
				LocalAssertUtil.assertEqualsOneOf("{User Assertion Message}", new String[] { "A", "B", "C" }, "X");
			}
		});
	}

	private static void expectComparisonFailure(final String expectedExceptionMessage, final Runnable runnable) {
		try {
			runnable.run();
			fail("Expected ComparisonFailure.");
		} catch (final ComparisonFailure comparisonFailure) {
			assertEquals(expectedExceptionMessage, comparisonFailure.getMessage());
		}
	}

}

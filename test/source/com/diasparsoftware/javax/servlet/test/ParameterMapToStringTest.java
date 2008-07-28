package com.diasparsoftware.javax.servlet.test;

import junit.framework.TestCase;

import org.apache.catalina.connector.HttpRequestBase;

import com.diasparsoftware.javax.servlet.ServletUtil;

public class ParameterMapToStringTest extends TestCase {
	private HttpRequestBase request;

	protected void setUp() {
		request = new HttpRequestBase();
		// ACCIDENT If you don't clear the parameters, addParameter() will hurl
		request.clearParameters();
	}

	public void testEmptyRequest() throws Exception {
		assertEquals("{}", ServletUtil.parameterMapToString(request));
	}

	public void testOneParameterOneItem() throws Exception {
		request.addParameter("name", new String[] { "value" });
		assertEquals("{name=[value]}", ServletUtil.parameterMapToString(request));
	}

	public void testOneParameterTwoItems() throws Exception {
		request.addParameter("name", new String[] { "value1", "value2" });
		assertEquals("{name=[value1, value2]}", ServletUtil.parameterMapToString(request));
	}

	public void testTwoParameterTwoItems() throws Exception {
		// BRITTLE This test asserts on a string that we don't build
		request.addParameter("name1", new String[] { "value1", "value2" });
		request.addParameter("name2", new String[] { "value3", "value4" });

		LocalAssertUtil.assertEqualsOneOf(new String[] { //
						//
						"{name1=[value1, value2], name2=[value3, value4]}",
						"{name2=[value3, value4], name1=[value1, value2]}" }, //
				ServletUtil.parameterMapToString(request));
	}
}

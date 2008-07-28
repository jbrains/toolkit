package com.diasparsoftware.javax.servlet.test;

import javax.servlet.http.HttpSession;

import junit.framework.TestCase;

import com.diasparsoftware.javax.servlet.http.HttpUtil;

public class SessionToStringTest extends TestCase {
	public void testEmpty() {
		HttpSession session = new HardcodedHttpSession();
		assertEquals("HttpSession {}", HttpUtil.sessionToString(session));
	}

	public void testOneString() {
		HttpSession session = new HardcodedHttpSession();
		session.setAttribute("name", "value");
		assertEquals("HttpSession {name=value}", HttpUtil.sessionToString(session));
	}

	public void testTwoStrings() {
		HttpSession session = new HardcodedHttpSession();
		session.setAttribute("name", "value");
		session.setAttribute("name1", "value1");
		LocalAssertUtil.assertEqualsOneOf(new String[] { //
				"HttpSession {name=value,name1=value1}", "HttpSession {name1=value1,name=value}" }, //
				HttpUtil.sessionToString(session));
	}
}

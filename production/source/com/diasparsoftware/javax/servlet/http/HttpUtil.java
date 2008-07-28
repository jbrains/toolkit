package com.diasparsoftware.javax.servlet.http;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

import org.apache.catalina.connector.HttpRequestBase;

import com.diasparsoftware.java.util.*;

public class HttpUtil {
    /***
	 * Create an HttpServletRequest from the specified URI and request
	 * parameters.
	 * 
	 * @param uri
	 * @param parameters
	 * @return
	 */
    public static HttpServletRequest makeRequestIgnoreSession(
        String uri,
        Map parameters) {

        final HttpRequestBase httpServletRequest =
            new HttpRequestBase() {

            public HttpSession getSession(boolean create) {
                return new FakeHttpSession(Collections.EMPTY_MAP);
            }

            public RequestDispatcher getRequestDispatcher(String path) {
                return new RequestDispatcherAdapter();
            }
        };

        httpServletRequest.setRequestURI(uri);
        httpServletRequest.clearParameters();

        CollectionUtil.forEachDo(parameters, new MapEntryClosure() {
            public void eachMapEntry(Object key, Object value) {
                httpServletRequest.addParameter(
                    (String) key,
                    (String[]) value);
            }
        });

        return httpServletRequest;
    }

    /***
	 * Provides a human-readable string representation of an <code>HttpSession</code>.
	 * 
	 * @param session
	 * @return For now, just the session attributes.
	 */
    public static String sessionToString(HttpSession session) {
        StringBuffer buffer = new StringBuffer("HttpSession {");
        boolean needComma = false;

        for (Enumeration e = session.getAttributeNames();
            e.hasMoreElements();
            ) {

            String eachName = (String) e.nextElement();
            Object eachValue = session.getAttribute(eachName);

            if (needComma)
                buffer.append(",");

            buffer.append(eachName).append("=").append(eachValue);

            needComma = true;
        }

        buffer.append("}");
        return buffer.toString();
    }
}

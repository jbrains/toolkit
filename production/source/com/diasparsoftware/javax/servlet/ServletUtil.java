package com.diasparsoftware.javax.servlet;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.*;

public class ServletUtil {

    /***
     * Provides a nicer string representation of a
     * servlet request parameter map.
     * 
     * @param request  The request whose parameters you
     * want to display
     * @return   A nice string representation of the
     * parameters to the specified request
     */
    public static String parameterMapToString(HttpServletRequest request) {
        final Map parameters = new HashMap();

        Closure convertArrayToListClosure = new Closure() {
            public void execute(Object eachMapEntryAsObject) {
                Map.Entry eachMapEntry =
                    (Map.Entry) eachMapEntryAsObject;

                String name = (String) eachMapEntry.getKey();
                String[] values = (String[]) eachMapEntry.getValue();

                parameters.put(name, Arrays.asList(values));
            }
        };

        CollectionUtils.forAllDo(
            request.getParameterMap().entrySet(),
            convertArrayToListClosure);

        return parameters.toString();
    }

}

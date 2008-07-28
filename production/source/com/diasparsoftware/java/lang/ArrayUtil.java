package com.diasparsoftware.java.lang;

import java.util.*;

public class ArrayUtil {

    /***
	 * Convert a two-dimensional array into a Map.
	 * 
	 * @param entries
	 * @return
	 */
    public static Map asMap(Object[][] entries) {
        Map map = new HashMap();

        for (int i = 0; i < entries.length; i++) {
            Object[] eachEntry = entries[i];
            map.put(eachEntry[0], eachEntry[1]);
        }

        return map;
    }

    /***
	 * Convert a one-dimensional array into a <code>Set</code>.
	 * WARNING! Duplicate elements will be removed from the set.
	 * 
	 * @param entries
	 * @return
	 */
    public static Set asSet(Object[] entries) {
        return new HashSet(Arrays.asList(entries));
    }

    /***
	 * Convert a two-dimensional array into a <code>List</code> of
	 * <code>List</code>s, similar to a two-dimensional matrix.
	 */
    public static List asListOfLists(Object[][] entries) {
        List rows = new ArrayList();
        for (int i = 0; i < entries.length; i++) {
            rows.add(Arrays.asList(entries[i]));
        }
        return rows;
    }
}

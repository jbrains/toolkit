package com.diasparsoftware.java.util;

import java.util.*;

import org.apache.commons.collections.*;

public class CollectionUtil {

    /***
	 * Allows you to detect whether any item in a collection satisfies
	 * some acceptance criterion.
	 * 
	 * @param collection
	 *            The collection to search in
	 * @param predicate
	 *            A predicate that should evaluate to <code>true</code>
	 *            for the matching object.
	 * @return
	 */
    public static boolean detect(
        Collection collection,
        Predicate predicate) {

        return (CollectionUtils.find(collection, predicate) != null);
    }

    /***
	 * Searches a collection of <code>String</code> s for the
	 * specified search string, but ignoring case.
	 * 
	 * @param stringCollection
	 *            A collection of <code>String</code> s
	 * @param searchString
	 *            The string to forEachDoIgnoreExceptionsearchString
	 *            </code> in here if we ignore case?"
	 */
    public static boolean stringCollectionContainsIgnoreCase(
        Collection stringCollection,
        final String searchString) {

        return CollectionUtil
            .detect(stringCollection, new Predicate() {
            public boolean evaluate(Object parameters) {
                String eachString = (String) parameters;
                return eachString.equalsIgnoreCase(searchString);
            }
        });
    }

    /***
	 * Executes <code>closure</code> for each element in the
	 * specified collection, ignoring any thrown exceptions. This
	 * method is designed to be used by the <code>JdbcResourceRegistry</code>:
	 * at cleanup time, you can't recover from exceptions anyway.
	 * 
	 * @param collection
	 * @param closure
	 */
    public static void forEachDoIgnoreException(
        Collection collection,
        ExceptionalClosure closure) {

        for (Iterator i = collection.iterator(); i.hasNext();) {
            Object each = (Object) i.next();
            try {
                closure.execute(each);
            }
            catch (Exception ignored) {
            }
        }
    }

    /***
	 * The first exception stops the iteration.
	 * 
	 * @param collection
	 * @param closure
	 * @throws Exception
	 */
    public static void forEachDo(
        Collection collection,
        ExceptionalClosure closure)
        throws Exception {

        for (Iterator i = collection.iterator(); i.hasNext();) {
            Object each = (Object) i.next();
            closure.execute(each);
        }
    }

    /***
	 * Execute the specified closure for each item in the specified
	 * collection.
	 * 
	 * @param collection
	 * @param closure
	 * @throws Exception
	 */
    public static void forEachDo(
        Collection collection,
        Closure closure) {

        for (Iterator i = collection.iterator(); i.hasNext();) {
            Object each = (Object) i.next();
            closure.execute(each);
        }
    }

    /***
	 * Execute the specified closure for each item in the specified
	 * collection.
	 * 
	 * @param collection
	 * @param closure
	 * @throws Exception
	 */
    public static void forEachDo(Map map, MapEntryClosure closure) {
        forEachDo(map.entrySet(), closure);
    }

    /***
	 * Execute the specified closure for each item in the specified
	 * collection.
	 * 
	 * @param collection
	 * @param closure
	 * @throws Exception
	 */
    public static void forEachDo(
        Map map,
        ExceptionalMapEntryClosure closure)
        throws Exception {

        forEachDo(map.entrySet(), closure);
    }

    /***
     * Selects the first object that the <code>selector</code>
     * accepts, or <code>null</code> if the selector rejects
     * them all.
     * 
     * @param set
     * @param selector
     * @return
     */
    public static Object select(Set set, Selector selector) {
        for (Iterator i = set.iterator(); i.hasNext();) {
            Object element = (Object) i.next();
            if (selector.accept(element))
                return element;
        }
        
        return null;
    }
}

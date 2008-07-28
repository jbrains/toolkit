package com.diasparsoftware.jdbc;

import java.sql.*;
import java.util.*;
import java.util.Date;

import org.easymock.MockControl;

import com.diasparsoftware.java.util.*;

public class JdbcUtil {

    /***
	 * Provide a human-readable view of a JDBC result set.
	 * 
	 * Here is sample output, showing two rows and the column names.
	 * 
	 * <pre>
	 *  [[EMPLOYEE_NUMBER, NAME, PHONE], [019, Joe, 416 555-1212], [092, Sarah, 416 555-1212]] * </pre>
	 * 
	 * @param resultSet
	 * @return A string representation that looks like a <code>List</code>
	 *         of <code>List</code>s.
	 * @throws SQLException
	 */
    public static List resultSetAsTable(ResultSet resultSet)
            throws SQLException {

        List rows = new LinkedList();

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        List columnNames = new LinkedList();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = resultSetMetaData.getColumnName(i);
            columnNames.add(columnName);
        }

        rows.add(columnNames);
        int rowCount = 1;

        while (resultSet.next()) {
            List rowData = new LinkedList();
            for (int i = 1; i <= columnCount; i++) {
                Object columnData = resultSet.getObject(i);
                rowData.add(columnData);
            }

            rows.add(rowData);
            rowCount++;
        }

        return rows;
    }

    /***
	 * Creates a <code>Timestamp</code> object from the specified
	 * year, month, day, but at 12 noon local time.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
    public static Timestamp makeTimestamp(int year, int month, int day) {

        // Look at the hoops you have to go through to avoid deprecated
        // APIs!
        return new Timestamp(DateUtil.makeDate(year, month, day).getTime());
    }

    public static Timestamp makeTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    /***
	 * A way to make timestamps for normal people &mdash; none of this
	 * "year minus 1900" and "month minus 1" nonsense.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @param millisecond
	 * @return
	 */
    public static Timestamp makeTimestamp(int year, int month, int day,
            int hour, int minute, int second, int millisecond) {

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);

        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Date toJavaUtilDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }

    public static void setPreparedStatementParameters(
            PreparedStatement preparedStatement, List parameters) throws SQLException {

        int index = 1;
        for (Iterator i = parameters.iterator(); i.hasNext(); ) {
            Object each = (Object) i.next();
            preparedStatement.setObject(index, each);
            index++;
        }
    }

    public static ResultSet createFakeResultSet(Map rowData,
            final MockControl resultSetControl) {

        final ResultSet resultSet = (ResultSet) resultSetControl.getMock();

        CollectionUtil.forEachDo(rowData, new MapEntryClosure() {
            public void eachMapEntry(Object key, Object value) {
                try {
                    resultSet.getObject((String) key);
                    resultSetControl.setReturnValue(value,
                            MockControl.ONE_OR_MORE);
                }
                catch (SQLException e) {
                    throw new RuntimeException(
                            "Unable to create fake ResultSet", e);
                }
            }
        });

        return resultSet;
    }

    public static java.sql.Date makeDate(int year, int month, int day) {
        return new java.sql.Date(DateUtil.makeDate(year, month, day).getTime());
    }
}

package com.diasparsoftware.jdbc.test;

import java.sql.*;
import java.util.*;

import junit.framework.TestCase;

import com.diasparsoftware.java.sql.PreparedStatementData;
import com.diasparsoftware.jdbc.*;
import com.mockobjects.sql.*;

public class ExecuteSelectStatementTest extends TestCase {
    public void testReturnNoRows() throws Exception {
        PreparedStatementData preparedStatementData = new PreparedStatementData(
                "select * from A where 1=2", Collections.EMPTY_LIST);

        MockPreparedStatement preparedStatement = new MockPreparedStatement();
        preparedStatement.addResultSet(new MockMultiRowResultSet());

        MockConnection2 connection = new MockConnection2();
        connection.setupAddPreparedStatement(preparedStatementData.sqlString,
                preparedStatement);

        PreparedStatementExecuter executer = new JdbcQueryExecuter(connection);
        List rows = executer.executeSelectStatement(preparedStatementData,
                new JdbcRowMapper() {
                    public Object makeDomainObject(ResultSet row)
                            throws SQLException {

                        return null;
                    }
                });

        assertTrue(rows.isEmpty());
    }

    public void testReturnStringAsRow() throws Exception {
        PreparedStatementData selectStatementData = new PreparedStatementData(
                "select * from B", Collections.EMPTY_LIST);

        MockMultiRowResultSet expectedResultSet = new MockMultiRowResultSet();
        expectedResultSet.setupColumnNames(new String[]{"a"});
        expectedResultSet.setupRows(new Object[][]{{"hello"}, {"goodbye"}});

        MockPreparedStatement preparedStatement = new MockPreparedStatement();
        preparedStatement.addResultSet(expectedResultSet);
        preparedStatement.setExpectedClearParametersCalls(1);
        preparedStatement.setExpectedCloseCalls(1);

        MockConnection2 connection = new MockConnection2();
        connection.setupAddPreparedStatement(selectStatementData.sqlString,
                preparedStatement);

        List expectedObjects = new ArrayList() {
            {
                add("hello");
                add("goodbye");
            }
        };

        PreparedStatementExecuter executer = new JdbcQueryExecuter(connection);
        JdbcRowMapper simpleMapper = new JdbcRowMapper() {
            public Object makeDomainObject(ResultSet row) throws SQLException {
                return row.getObject("a");
            }
        };

        List actualObjects = executer.executeSelectStatement(
                selectStatementData, simpleMapper);

        assertEquals(expectedObjects, actualObjects);

        preparedStatement.verify();
        connection.verify();
    }

    public void testReturnFirstRow() throws Exception {
        PreparedStatementData selectStatementData = new PreparedStatementData(
                "select * from B where a='hello'", Collections.EMPTY_LIST);

        MockMultiRowResultSet expectedResultSet = new MockMultiRowResultSet();
        expectedResultSet.setupColumnNames(new String[]{"a"});
        expectedResultSet.setupRows(new Object[][]{{"hello"}, {"goodbye"}});

        MockPreparedStatement preparedStatement = new MockPreparedStatement();
        preparedStatement.addResultSet(expectedResultSet);
        preparedStatement.setExpectedClearParametersCalls(1);
        preparedStatement.setExpectedCloseCalls(1);

        MockConnection2 connection = new MockConnection2();
        connection.setupAddPreparedStatement(selectStatementData.sqlString,
                preparedStatement);

        String expectedObject = "hello";

        PreparedStatementExecuter executer = new JdbcQueryExecuter(connection);
        JdbcRowMapper simpleMapper = new JdbcRowMapper() {
            public Object makeDomainObject(ResultSet row) throws SQLException {
                return row.getObject("a");
            }
        };

        Object actualObject = executer.executeSingleRowSelectStatement(
                selectStatementData, simpleMapper);

        assertEquals(expectedObject, actualObject);

        preparedStatement.verify();
        connection.verify();
    }
}

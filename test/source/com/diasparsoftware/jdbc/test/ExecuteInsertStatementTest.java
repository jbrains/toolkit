package com.diasparsoftware.jdbc.test;

import java.util.Arrays;

import junit.framework.TestCase;

import com.diasparsoftware.java.sql.PreparedStatementData;
import com.diasparsoftware.java.util.DateUtil;
import com.diasparsoftware.jdbc.*;
import com.mockobjects.sql.*;

public class ExecuteInsertStatementTest extends TestCase {
    public void testExecuteInsertStatement() throws Exception {
        String insertSqlString = "insert into HELLO (A, B, C) values (?, ?, ?)";
        int expectedRowsInserted = 1;

        MockPreparedStatement mockInsertStatement = new MockPreparedStatement();

        mockInsertStatement.setExpectedClearParametersCalls(1);
        mockInsertStatement.setExpectedCloseCalls(1);
        mockInsertStatement.addUpdateCount(1);
        mockInsertStatement.addExpectedSetParameters(new Object[]{"a",
            new Integer(762), JdbcUtil.makeTimestamp(1974, 5, 4)});

        MockConnection2 mockConnection = new MockConnection2();
        mockConnection.setupAddPreparedStatement(insertSqlString,
                mockInsertStatement);

        PreparedStatementExecuter queryExecuter = new JdbcQueryExecuter(
                mockConnection);

        PreparedStatementData insertStatementData = new PreparedStatementData();
        insertStatementData.sqlString = insertSqlString;
        insertStatementData.parameters = Arrays.asList(new Object[]{"a",
            new Integer(762), DateUtil.makeDate(1974, 5, 4)});

        assertEquals(expectedRowsInserted, queryExecuter
                .executeInsertStatement(insertStatementData));

        mockInsertStatement.verify();
        mockConnection.verify();
    }
}

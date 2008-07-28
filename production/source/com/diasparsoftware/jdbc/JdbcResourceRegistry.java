package com.diasparsoftware.jdbc;

import java.sql.*;
import java.util.*;

import com.diasparsoftware.java.util.*;

/***
 * Incorporate one of these registries anywhere you would
 * like to ensure that JDBC resources are cleaned up.
 * 
 * @author <a href="jbr@diasparsoftware.com>J. B. Rainsberger</a>
 */
public class JdbcResourceRegistry {
    private List connectionsToClose = new LinkedList();
    private List statementsToClose = new LinkedList();
    private List resultSetsToClose = new LinkedList();

    public void registerStatement(Statement statement) {
        statementsToClose.add(statement);
    }

    public void registerResultSet(ResultSet resultSet) {
        resultSetsToClose.add(resultSet);
    }

    public void registerConnection(Connection connection) {
        connectionsToClose.add(connection);
    }

    public void cleanUp() {
        CollectionUtil
            .forEachDoIgnoreException(
                statementsToClose,
                new ExceptionalClosure() {

            public Object execute(Object each) throws Exception {
                ((Statement) each).close();
                return null;
            }
        });

        CollectionUtil
            .forEachDoIgnoreException(
                resultSetsToClose,
                new ExceptionalClosure() {

            public Object execute(Object each) throws Exception {
                ((ResultSet) each).close();
                return null;
            }
        });

        CollectionUtil
            .forEachDoIgnoreException(
                connectionsToClose,
                new ExceptionalClosure() {

            public Object execute(Object each) throws Exception {
                ((Connection) each).close();
                return null;
            }
        });

    }
}

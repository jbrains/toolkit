package com.diasparsoftware.jdbc;

import java.sql.*;
import java.util.*;

import com.diasparsoftware.java.sql.PreparedStatementData;
import com.diasparsoftware.store.DataStoreException;

public class JdbcQueryExecuter implements PreparedStatementExecuter {
    private static final RowCountRowMapper ROW_COUNT_ROW_MAPPER = new RowCountRowMapper();

    private Connection connection;

    public JdbcQueryExecuter(Connection connection) {
        this.connection = connection;
    }

    public void executeDeleteStatement(
            PreparedStatementData preparedStatementData) throws DataStoreException {

        PreparedStatement deleteStatement = null;

        try {
            deleteStatement = connection
                    .prepareStatement(preparedStatementData.sqlString);

            deleteStatement.clearParameters();

            int columnIndex = 1;
            for (Iterator i = preparedStatementData.parameters.iterator(); i
                    .hasNext(); columnIndex++) {

                Object eachParameter = (Object) i.next();
                deleteStatement.setObject(columnIndex, eachParameter);
            }

            deleteStatement.executeUpdate();
        }
        catch (SQLException rethrow) {
            throw new DataStoreException(rethrow);
        }
        finally {
            try {
                if (deleteStatement != null)
                    deleteStatement.close();
            }
            catch (SQLException ignored) {
            }
        }

    }

    public int executeUpdateStatement(
            PreparedStatementData preparedStatementData) {
        PreparedStatement updateStatement = null;

        try {
            updateStatement = connection
                    .prepareStatement(preparedStatementData.sqlString);

            updateStatement.clearParameters();

            int columnIndex = 1;
            for (Iterator i = preparedStatementData.parameters.iterator(); i
                    .hasNext(); columnIndex++) {

                Object eachParameter = (Object) i.next();
                updateStatement.setObject(columnIndex, eachParameter);
            }

            return updateStatement.executeUpdate();
        }
        catch (SQLException rethrow) {
            throw new DataStoreException(rethrow);
        }
        finally {
            try {
                if (updateStatement != null)
                    updateStatement.close();
            }
            catch (SQLException ignored) {
            }
        }

    }

    // TODO Another version of this method that accepts
    //       the expected number of rows inserted, and
    //       checks them.
    public int executeInsertStatement(PreparedStatementData insertStatementData) {
        PreparedStatement insertStatement = null;
        try {
            insertStatement = connection
                    .prepareStatement(insertStatementData.sqlString);

            insertStatement.clearParameters();

            int columnIndex = 1;
            for (Iterator i = insertStatementData.parameters.iterator(); i
                    .hasNext(); ) {

                Object each = (Object) i.next();
                insertStatement.setObject(columnIndex, each);

                columnIndex++;
            }

            return insertStatement.executeUpdate();
        }
        catch (SQLException rethrow) {
            throw new DataStoreException(rethrow);
        }
        finally {
            try {
                if (insertStatement != null)
                    insertStatement.close();
            }
            catch (SQLException ignored) {
            }
        }
    }

    public List executeSelectStatement(
            PreparedStatementData selectStatementData, JdbcRowMapper rowMapper) {

        List result = new ArrayList();

        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;

        try {
            selectStatement = connection
                    .prepareStatement(selectStatementData.sqlString);

            selectStatement.clearParameters();

            JdbcUtil.setPreparedStatementParameters(selectStatement,
                    selectStatementData.parameters);

            resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                result.add(rowMapper.makeDomainObject(resultSet));
            }
        }
        catch (SQLException rethrow) {
            throw new DataStoreException(rethrow);
        }
        finally {
            try {
                if (resultSet != null)
                    resultSet.close();

                if (selectStatement != null)
                    selectStatement.close();
            }
            catch (SQLException ignored) {
            }
        }

        return result;
    }

    /***
	 * Invoke this only for SELECT statements that count rows. This
	 * method assumes that the database returns only a single row for
	 * SELECT COUNT(...) statements.
	 * 
	 * @param countStatementData
	 *            A SELECT COUNT(...) statement.
	 * @return The number of rows determined by the COUNT statement.
	 */
    public int executeCountStatement(PreparedStatementData countStatementData) {
        List rowCountResults = executeSelectStatement(countStatementData,
                ROW_COUNT_ROW_MAPPER);

        Object rowCountAsObject = new LinkedList(rowCountResults).get(0);

        return ((Integer) rowCountAsObject).intValue();
    }

    public Object executeSingleRowSelectStatement(
            PreparedStatementData selectStatementData,
            JdbcRowMapper simpleMapper) {

        List rows = executeSelectStatement(selectStatementData, simpleMapper);
        if (rows.isEmpty()) {
            return null;
        }
        else {
            return rows.get(0);
        }
    }

    public void commit() {
        try {
            connection.commit();
        }
        catch (SQLException wrapped) {
            throw new DataStoreException("Unable to commit last operation",
                    wrapped);
        }
    }
}

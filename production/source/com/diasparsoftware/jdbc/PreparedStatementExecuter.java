package com.diasparsoftware.jdbc;

import java.util.List;

import com.diasparsoftware.java.sql.PreparedStatementData;
import com.diasparsoftware.store.DataStoreException;

public interface PreparedStatementExecuter {
    void executeDeleteStatement(PreparedStatementData preparedStatementData)
            throws DataStoreException;
    int executeUpdateStatement(PreparedStatementData preparedStatementData);
    int executeInsertStatement(PreparedStatementData insertStatementData);
    List executeSelectStatement(PreparedStatementData selectStatementData,
            JdbcRowMapper rowMapper);
    /***
	 * Invoke this only for SELECT statements that count rows. This
	 * method assumes that the database returns only a single row for
	 * SELECT COUNT(...) statements.
	 * 
	 * @param countStatementData
	 *            A SELECT COUNT(...) statement.
	 * @return The number of rows determined by the COUNT statement.
	 */
    int executeCountStatement(PreparedStatementData countStatementData);
    Object executeSingleRowSelectStatement(
            PreparedStatementData selectStatementData,
            JdbcRowMapper simpleMapper);
    
    void commit();
}

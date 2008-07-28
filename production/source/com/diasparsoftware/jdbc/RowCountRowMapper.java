package com.diasparsoftware.jdbc;

import java.sql.*;

/***
 * Extracts the row count from a SELECT COUNT(...) statement.
 */
public final class RowCountRowMapper extends JdbcRowMapper {
    private static final int ROW_COUNT_COLUMN_INDEX = 1;
    
    public Object makeDomainObject(ResultSet row) throws SQLException {
        return new Integer(row.getInt(ROW_COUNT_COLUMN_INDEX));
    }
}

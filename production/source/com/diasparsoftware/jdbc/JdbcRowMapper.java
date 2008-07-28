package com.diasparsoftware.jdbc;

import java.sql.*;

/***
 * A <code>JdbcQueryExecuter</code> uses this object to map
 * each row when executing a <code>SELECT</code> query.
 */
public abstract class JdbcRowMapper extends JdbcMapper 
{
    /***
     * Make a domain object from the row referred to by <code>row</code>.
     * 
     * @param row
     * @return
     * @throws SQLException
     */
    public abstract Object makeDomainObject(ResultSet row)
        throws SQLException;
}

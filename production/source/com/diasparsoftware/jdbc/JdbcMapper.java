package com.diasparsoftware.jdbc;

import java.sql.*;
import java.util.Date;



public class JdbcMapper {

    /***
     * A potentially safer version of ResultSet#getDate
     * which answers a timestamp at noon on the day in
     * question.
     * 
     * @param resultSet
     * @param columnName
     * @return
     * @throws SQLException
     */
    public Date getDate(ResultSet resultSet, String columnName) throws SQLException {
            
        return JdbcUtil.toJavaUtilDate(
            resultSet.getTimestamp(columnName));
    }

}

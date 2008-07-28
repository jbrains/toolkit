package com.diasparsoftware.jdbc;

import java.util.List;

import com.diasparsoftware.java.sql.PreparedStatementData;

public interface PreparedStatementBuilder {
    PreparedStatementData getPreparedStatementData(String statementName,
            List domainParameters);
}

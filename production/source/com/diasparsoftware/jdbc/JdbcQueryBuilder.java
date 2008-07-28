package com.diasparsoftware.jdbc;

import java.util.*;

import org.apache.commons.collections.*;

import com.diasparsoftware.java.sql.PreparedStatementData;
import com.gargoylesoftware.base.util.StringUtil;

public abstract class JdbcQueryBuilder implements PreparedStatementBuilder {
    public abstract List createPreparedStatementParameters(
            String statementName, List domainParameters);
    public abstract String getSqlString(String statementName);
    public abstract Set getSupportedStatements();

    public boolean supportsStatement(String statementName) {
        return getSupportedStatements().contains(statementName);
    }

    public PreparedStatementData getPreparedStatementData(String statementName,
            List domainParameters) {

        if (supportsStatement(statementName)) {

            PreparedStatementData preparedStatementData = new PreparedStatementData();

            preparedStatementData.sqlString = getSqlString(statementName);

            preparedStatementData.parameters = createPreparedStatementParameters(
                    statementName, domainParameters);

            return preparedStatementData;
        }
        else {
            throw new NoSuchElementException("I do not support the statement '"
                    + statementName + "'");
        }
    }

    public static String createInsertStatement(String tableName,
            List orderedColumnNames) {

        String[] markers = new String[orderedColumnNames.size()];
        Arrays.fill(markers, "?");

        StringBuffer statementBuffer = new StringBuffer("insert into "
                + tableName + " (");

        statementBuffer.append(StringUtil.join(orderedColumnNames, ", "));
        statementBuffer.append(") values (");
        statementBuffer.append(StringUtil.join(markers, ", "));
        statementBuffer.append(")");

        return statementBuffer.toString();
    }

    public static String createUpdateStatement(String tableName,
            List orderedColumnNames, String whereClause) {
        
        List columnSetClauses = (List) CollectionUtils.collect(
                orderedColumnNames, new Transformer() {
                    public Object transform(Object input) {
                        String eachColumnName = (String) input;
                        return eachColumnName + " = ?";
                    }
                });

        return "update " + tableName + " set "
                + StringUtil.join(columnSetClauses, ", ") + " where "
                + whereClause;
    }

}

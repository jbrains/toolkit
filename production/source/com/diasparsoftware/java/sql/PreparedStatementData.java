package com.diasparsoftware.java.sql;

import java.util.*;

public class PreparedStatementData {
    public String sqlString;
    public List parameters;

    public PreparedStatementData() {
        this.sqlString = "";
        this.parameters = Collections.EMPTY_LIST;
    }
    
    public PreparedStatementData(String sqlString, List parameters) {
        this.sqlString = sqlString;
        this.parameters = parameters;
    }

    public boolean equals(Object other) {
        if (other instanceof PreparedStatementData) {
            PreparedStatementData that = (PreparedStatementData) other;
            return this.sqlString.equals(that.sqlString)
                && this.parameters.equals(that.parameters);
        }
        else {
            return false;
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "PreparedStatementData["
            + sqlString
            + " with parameters "
            + parameters
            + "]";
    }

}

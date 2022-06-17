package tk.empee.myamazoon.model.dao;

import java.sql.*;

public final class DBConnection implements AutoCloseable {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String DB_URL = "jdbc:mysql://DB_MyAmazoon:3306/myAmazoon?allowPublicKeyRetrieval=true";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "admin";

    private final Connection connection;
    private Statement statement;

    public DBConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public void setPreparedStatement(String sql) throws SQLException {
        statement = connection.prepareStatement(sql);
    }

    public <K> K executePrepared(StatementFunction<PreparedStatement, K> action) throws SQLException {
        return action.accept( (PreparedStatement) statement);
    }
    public void executePrepared(StatementConsumer<PreparedStatement> action) throws SQLException {
        action.accept( (PreparedStatement) statement);
    }

    public <K> K execute(StatementFunction<Statement, K> action) throws SQLException {
        statement = connection.createStatement();
        return action.accept(statement);
    }
    public void execute(StatementConsumer<Statement> action) throws SQLException {
        statement = connection.createStatement();
        action.accept(statement);
    }

    @Override
    public void close() throws SQLException {
        if(connection != null) {
            connection.close();
        }

        if(statement != null) {
            statement.close();
        }
    }

    @FunctionalInterface
    public interface StatementFunction<T extends Statement, K> {
        K accept(T statement) throws SQLException;
    }

    @FunctionalInterface
    public interface StatementConsumer<T extends Statement> {
        void accept(T statement) throws SQLException;
    }

}

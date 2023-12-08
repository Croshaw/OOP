package db;

import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseApi {
    private final static String url = "jdbc:postgresql://ep-jolly-pond-03245931.eu-central-1.aws.neon.tech/carservdb?user=Croshaw&password=QxqO2vl5mrGD&sslmode=require";
    private static Connection connection;
    private static Statement statement;
    private static DatabaseMetaData databaseMetaData;

    private static void openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        databaseMetaData = connection.getMetaData();
    }

    private static void closeConnection() throws SQLException {
        statement.close();
        connection.close();
        databaseMetaData = null;
    }

    public static ArrayList<String> getTablesNames() throws SQLException, ClassNotFoundException {
        openConnection();
        ResultSet tables = databaseMetaData.getTables(null, null, "%", new String[]{"TABLE"});
        ArrayList<String> result = new ArrayList<>();
        while(tables.next()) {
            result.add(tables.getString("TABLE_NAME"));
        }
        closeConnection();
        return result;
    }

    public static void executeQuery(String query) throws SQLException, ClassNotFoundException {
        openConnection();
        ResultSet rs;
        try {
            rs = statement.executeQuery(query);
            rs.close();
        }
        catch (PSQLException e) {
            
        }
        closeConnection();
    }

    public static ArrayList<ArrayList<String>> getTable(String tableName) throws SQLException, ClassNotFoundException {
        openConnection();
        ResultSet rs = statement.executeQuery("SELECT * FROM %s".formatted(tableName));
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        int colCount = rs.getMetaData().getColumnCount();
        while(rs.next()) {
            ArrayList<String> temp = new ArrayList<>();
            for(int i = 1; i <= colCount; i++)
                temp.add(rs.getString(i));
            result.add(temp);
        }
        rs.close();
        closeConnection();
        return result;
    }

    public static String getData(String tableName, String columnName, String searchName, String searchValue) throws SQLException, ClassNotFoundException {
        openConnection();
        String result = "";
        ResultSet rs = statement.executeQuery("SELECT %s FROM %s WHERE %s=%s".formatted(columnName, tableName, searchName, searchValue));
        if(rs.first())
            result = rs.getString(1);
        rs.close();
        closeConnection();
        return result;
    }
}

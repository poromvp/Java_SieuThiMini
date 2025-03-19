package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnection1 {
    private static final String URL = "jdbc:mysql://localhost:3306/bookStore?useUnicode=true&characterEncoding=utf8";
    private static final String USER = "book_store";
    private static final String PASSWORD = "book_store";

    private String tableName = "default_table";
    private Map<String, Object> queryParams = new HashMap<>();
    private static Connection connection = null;

    public Connection openConnect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Loi khi ket noi co so du lieu: " + e.getMessage());
            }
        }
        return connection;
    }

    public void closeConnect() {
        connection = null;
    }

    public DBConnection1 buildQueryParams(Map<String, Object> params) {
        queryParams.clear();
        queryParams.putAll(params);
        return this;
    }

    private String buildCondition(String condition) {
        return (condition != null && !condition.isEmpty()) ? "WHERE " + condition : "";
    }

    public ResultSet query(String sql, List<Object> params) throws SQLException {
        if (connection == null) openConnect();
        PreparedStatement stmt = connection.prepareStatement(sql);
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }
        return stmt.executeQuery();
    }

    public List<Map<String, Object>> select() throws SQLException {
        if (connection == null) openConnect();
        String sql = "SELECT " + queryParams.getOrDefault("select", "*") + " FROM " + tableName;
        sql += " " + buildCondition((String) queryParams.get("where"));
        List<Object> params = (List<Object>) queryParams.getOrDefault("params", new ArrayList<>());
        ResultSet rs = query(sql, params);
        return convertResultSetToList(rs);
    }

    public Map<String, Object> selectOne() throws SQLException {
        queryParams.put("other", "LIMIT 1");
        List<Map<String, Object>> data = select();
        return data.isEmpty() ? new HashMap<>() : data.get(0);
    }

    public int insert() throws SQLException {
        if (connection == null) openConnect();
        Map<String, Object> fields = (Map<String, Object>) queryParams.get("field");
        String columns = String.join(", ", fields.keySet());
        String placeholders = String.join(", ", Collections.nCopies(fields.size(), "?"));
        String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";
        List<Object> params = new ArrayList<>(fields.values());
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        return rs.next() ? rs.getInt(1) : -1;
    }

    public int update() throws SQLException {
        if (connection == null) openConnect();
        Map<String, Object> values = (Map<String, Object>) queryParams.get("value");
        List<String> assignments = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            assignments.add(entry.getKey() + " = ?");
            params.add(entry.getValue());
        }
        String sql = "UPDATE " + tableName + " SET " + String.join(", ", assignments) + " " + buildCondition((String) queryParams.get("where"));
        PreparedStatement stmt = connection.prepareStatement(sql);
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }
        return stmt.executeUpdate();
    }

    public int delete() throws SQLException {
        if (connection == null) openConnect();
        String sql = "DELETE FROM " + tableName + " " + buildCondition((String) queryParams.get("where"));
        PreparedStatement stmt = connection.prepareStatement(sql);
        return stmt.executeUpdate();
    }

    private List<Map<String, Object>> convertResultSetToList(ResultSet rs) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i), rs.getObject(i));
            }
            resultList.add(row);
        }
        return resultList;
    }

    public static void main(String[] args) {
        DBConnection1 connection1 = new DBConnection1();
        System.out.print(connection1.openConnect());
    }
}

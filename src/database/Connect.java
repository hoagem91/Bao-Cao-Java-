/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.*;
/**
 *
 * @author Lenovo
 */
public class Connect {

    /**
     * @param args the command line arguments
     */
    private String server = "localhost";
    private String user = "sa";
    private String password = "123456";
    private String db = "TodoList";
    private int port = 1433;
    
    public Connection getConnection(){
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(server);
        ds.setUser(user);
        ds.setPassword(password);
        ds.setDatabaseName(db);
        ds.setPortNumber(port);
        ds.setEncrypt(false);
        ds.setTrustServerCertificate(true);
        try{
            Connection conn = ds.getConnection();
            System.out.println("Ket noi thanh cong.");
            return conn;
        }catch(SQLException e){
            System.out.println("Ket noi that bai " + e.getMessage());
            return null;
        }
    }
    
    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]); // Thiết lập tham số
        }
        return ps.executeQuery(); // Trả về ResultSet
    }
}

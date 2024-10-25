/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dgarc
 */
public class ConexionDataBase {
    
    private static final String URL = "jdbc:sqlserver://asbiblioteca-server.database.windows.net:1433;"
            + "database=AS_Biblioteca-database;user=DiegoG@asbiblioteca-server;password={your_password_here};"
            + "encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static final String USER = "DiegoG"; // Cambia por tu usuario
    private static final String PASSWORD = "Georgina1930"; // Cambia por tu contraseña

    public static Connection getConnection() throws SQLException {
        // Cargar el controlador SQL Server
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo encontrar el controlador SQL Server.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Maneja el error al cerrar
            }
        }
    }
    
}

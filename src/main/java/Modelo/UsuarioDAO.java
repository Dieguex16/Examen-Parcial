/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Conexion.ConexionDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dgarc
 */
public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }

    public void agregarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuarios (DNI, Nombre, Apellido, Correo, Telefono) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getDni());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setString(4, usuario.getCorreo());
            stmt.setString(5, usuario.getTelefono());
            stmt.executeUpdate();
        }
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getString("DNI"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("Correo"),
                        rs.getString("Telefono")
                ));
            }
        }
        return usuarios;
    }

    public void editarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuarios SET Nombre = ?, Apellido = ?, Correo = ?, Telefono = ? WHERE DNI = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getDni());
            stmt.executeUpdate();
        }
    }

    public void eliminarUsuario(String dni) throws SQLException {
        String sql = "DELETE FROM Usuarios WHERE DNI = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dni);
            stmt.executeUpdate();
        }
    }
}

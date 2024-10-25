/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dgarc
 */
public class PrestamoDAO {

    private Connection conn;

    public PrestamoDAO(Connection conn) {
        this.conn = conn;
    }

    public void agregarPrestamo(Prestamo prestamo) throws SQLException {
        String sql = "INSERT INTO Prestamos (UsuarioID, FechaDevolucionEsperada) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, prestamo.getUsuarioID());
            ps.setDate(2, (Date) prestamo.getFechaDevolucionEsperada());
            ps.executeUpdate();
        }
    }

    public List<Prestamo> listarPrestamos() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT PrestamoID, UsuarioID, FechaPrestamo, FechaDevolucionEsperada, FechaDevolucion FROM Prestamos";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int prestamoID = rs.getInt("PrestamoID");
                int usuarioID = rs.getInt("UsuarioID");
                Date fechaPrestamo = rs.getDate("FechaPrestamo"); // Verifica que este campo esté bien en la base de datos
                Date fechaDevolucionEsperada = rs.getDate("FechaDevolucionEsperada");
                Date fechaDevolucion = rs.getDate("FechaDevolucion");

                // Aquí está la fecha de préstamo que se debe estar asignando
                Prestamo prestamo = new Prestamo(prestamoID, usuarioID, fechaPrestamo, fechaDevolucionEsperada, fechaDevolucion);
                prestamos.add(prestamo);
            }
        }
        return prestamos;
    }

    public void editarPrestamo(Prestamo prestamo) throws SQLException {
        String sql = "UPDATE Prestamos SET UsuarioID = ?, FechaDevolucionEsperada = ?, FechaDevolucion = ? WHERE PrestamoID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, prestamo.getUsuarioID());
            ps.setDate(2, (Date) prestamo.getFechaDevolucionEsperada());
            ps.setDate(3, (Date) prestamo.getFechaDevolucion());
            ps.setInt(4, prestamo.getPrestamoID());
            ps.executeUpdate();
        }
    }

    public void eliminarPrestamo(int prestamoID) throws SQLException {
        String sql = "DELETE FROM Prestamos WHERE PrestamoID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, prestamoID);
            ps.executeUpdate();
        }
    }
}

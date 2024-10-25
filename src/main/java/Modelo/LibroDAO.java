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
public class LibroDAO {

    private Connection conn;

    public LibroDAO(Connection conn) {
        this.conn = conn;
    }

    public void agregarLibro(Libro libro) throws SQLException {
        String sql = "INSERT INTO Libros (ISBN, Titulo, Autor, Editorial, AnioPublicacion, CopiasDisponibles) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, libro.getIsbn());
            stmt.setString(2, libro.getTitulo());
            stmt.setString(3, libro.getAutor());
            stmt.setString(4, libro.getEditorial());
            stmt.setInt(5, libro.getAnioPublicacion());
            stmt.setInt(6, libro.getCopiasDisponibles());
            stmt.executeUpdate();
        }
    }

    public List<Libro> listarLibros() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM Libros";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                libros.add(new Libro(
                        rs.getString("ISBN"),
                        rs.getString("Titulo"),
                        rs.getString("Autor"),
                        rs.getString("Editorial"),
                        rs.getInt("AnioPublicacion"),
                        rs.getInt("CopiasDisponibles")
                ));
            }
        }
        return libros;
    }

    public void editarLibro(Libro libro) throws SQLException {
        String sql = "UPDATE Libros SET Titulo = ?, Autor = ?, Editorial = ?, AnioPublicacion = ?, CopiasDisponibles = ? WHERE ISBN = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getEditorial());
            stmt.setInt(4, libro.getAnioPublicacion());
            stmt.setInt(5, libro.getCopiasDisponibles());
            stmt.setString(6, libro.getIsbn());
            stmt.executeUpdate();
        }
    }

    public void eliminarLibro(String isbn) throws SQLException {
        String sql = "DELETE FROM Libros WHERE ISBN = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            stmt.executeUpdate();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Prestamo;
import Modelo.PrestamoDAO;
import Vista.PrestamoView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dgarc
 */
public class PrestamoController {

    private PrestamoView view;
    private PrestamoDAO prestamoDAO;
    private int prestamoIDSeleccionado; // Almacena el ID del préstamo seleccionado

    public PrestamoController(PrestamoView view, PrestamoDAO prestamoDAO) {
        this.view = view;
        this.prestamoDAO = prestamoDAO;

        this.view.btnAgregarPrestamo.addActionListener(e -> agregarPrestamo());
        this.view.btnListarPrestamo.addActionListener(e -> listarPrestamos());
        this.view.btnEditarPrestamo.addActionListener(e -> editarPrestamo());
        this.view.btnEliminarPrestamo.addActionListener(e -> eliminarPrestamo());
    }

    public void agregarPrestamo() {
        try {
            int usuarioID = Integer.parseInt(view.txt_IdUsuario.getText());
            Date fechaDevolucionEsperada = new Date(view.jDateChooserDevolucionPrestamo.getDate().getTime());

            Prestamo prestamo = new Prestamo(0, usuarioID, new Date(System.currentTimeMillis()), fechaDevolucionEsperada, null);
            prestamoDAO.agregarPrestamo(prestamo);
            JOptionPane.showMessageDialog(view, "Préstamo agregado exitosamente.");
            listarPrestamos(); // Refrescar tabla
            limpiarCampos(); // Limpiar campos después de agregar
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al agregar el préstamo: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error en los datos: " + ex.getMessage());
        }
    }

    public void listarPrestamos() {
        try {
            List<Prestamo> prestamos = prestamoDAO.listarPrestamos();
            view.mostrarPrestamos(prestamos);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al listar los préstamos: " + ex.getMessage());
        }
    }

    public void editarPrestamo() {
        try {
            int usuarioID = Integer.parseInt(view.txt_IdUsuario.getText());
            Date fechaDevolucionEsperada = new Date(view.jDateChooserDevolucionPrestamo.getDate().getTime());

            Prestamo prestamo = new Prestamo(prestamoIDSeleccionado, usuarioID, new Date(System.currentTimeMillis()), fechaDevolucionEsperada, null);
            prestamoDAO.editarPrestamo(prestamo);
            JOptionPane.showMessageDialog(view, "Préstamo editado exitosamente.");
            listarPrestamos(); // Refrescar tabla
            limpiarCampos(); // Limpiar campos después de editar
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al editar el préstamo: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error en los datos: " + ex.getMessage());
        }
    }

    public void eliminarPrestamo() {
        try {
            prestamoDAO.eliminarPrestamo(prestamoIDSeleccionado);
            JOptionPane.showMessageDialog(view, "Préstamo eliminado exitosamente.");
            listarPrestamos(); // Refrescar tabla
            limpiarCampos(); // Limpiar campos después de eliminar
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al eliminar el préstamo: " + ex.getMessage());
        }
    }

    // Método para limpiar campos
    private void limpiarCampos() {
        view.txt_IdUsuario.setText("");
        view.jDateChooserDevolucionPrestamo.setDate(null);
        // Limpiar cualquier otro campo necesario
    }

    // Método para setear el prestamoIDSeleccionado
    public void setPrestamoIDSeleccionado(int prestamoID) {
        this.prestamoIDSeleccionado = prestamoID;
    }
}

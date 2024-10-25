/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Vista.UsuarioView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dgarc
 */
public class UsuarioController {

    private UsuarioView view;
    private UsuarioDAO usuarioDAO;

    public UsuarioController(UsuarioView view, UsuarioDAO usuarioDAO) {
        this.view = view;
        this.usuarioDAO = usuarioDAO;

        // Vincular acciones de los botones a los métodos correspondientes
        this.view.btnAgregarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });

        this.view.btnListarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuarios();
            }
        });

        this.view.btnEditarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });

        this.view.btnEliminarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

        // Vincular el evento de selección de fila en la tabla para cargar datos
        this.view.jTableUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = view.jTableUsuario.getSelectedRow();
                cargarDatosUsuario(row);
            }
        });
    }

    public void agregarUsuario() {
        try {
            String dni = view.txt_dni.getText();
            String nombre = view.txt_Nombre.getText();
            String apellido = view.txt_Apellido.getText();
            String correo = view.txt_Correo.getText();
            String telefono = view.txt_Telefono.getText();

            Usuario usuario = new Usuario(dni, nombre, apellido, correo, telefono);
            usuarioDAO.agregarUsuario(usuario);

            JOptionPane.showMessageDialog(view, "Usuario agregado exitosamente.");
            listarUsuarios(); // Refrescar tabla
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al agregar el usuario: " + ex.getMessage());
        }
    }

    public void listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.listarUsuarios();
            view.mostrarUsuarios(usuarios); // Mostrar lista en la vista
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al listar los usuarios: " + ex.getMessage());
        }
    }

    public void editarUsuario() {
        try {
            String dni = view.txt_dni.getText();
            String nombre = view.txt_Nombre.getText();
            String apellido = view.txt_Apellido.getText();
            String correo = view.txt_Correo.getText();
            String telefono = view.txt_Telefono.getText();

            Usuario usuario = new Usuario(dni, nombre, apellido, correo, telefono);
            usuarioDAO.editarUsuario(usuario);

            JOptionPane.showMessageDialog(view, "Usuario editado exitosamente.");
            listarUsuarios(); // Refrescar tabla
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al editar el usuario: " + ex.getMessage());
        }
    }

    public void eliminarUsuario() {
        try {
            String dni = view.txt_dni.getText();
            usuarioDAO.eliminarUsuario(dni);

            JOptionPane.showMessageDialog(view, "Usuario eliminado exitosamente.");
            listarUsuarios(); // Refrescar tabla
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al eliminar el usuario: " + ex.getMessage());
        }
    }

    // Método para cargar datos de la fila seleccionada a los campos de texto
    private void cargarDatosUsuario(int row) {
        view.txt_dni.setText(view.jTableUsuario.getValueAt(row, 0).toString());
        view.txt_Nombre.setText(view.jTableUsuario.getValueAt(row, 1).toString());
        view.txt_Apellido.setText(view.jTableUsuario.getValueAt(row, 2).toString());
        view.txt_Correo.setText(view.jTableUsuario.getValueAt(row, 3).toString());
        view.txt_Telefono.setText(view.jTableUsuario.getValueAt(row, 4).toString());
    }

    // Método para limpiar los campos de texto después de cada operación
    private void limpiarCampos() {
        view.txt_dni.setText("");
        view.txt_Nombre.setText("");
        view.txt_Apellido.setText("");
        view.txt_Correo.setText("");
        view.txt_Telefono.setText("");
    }
}

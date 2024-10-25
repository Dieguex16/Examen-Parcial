/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Libro;
import Modelo.LibroDAO;
import Vista.LibroView;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author dgarc
 */
public class LibroController {

    private LibroView view;
    private LibroDAO libroDAO;

    public LibroController(LibroView view, LibroDAO libroDAO) {
        this.view = view;
        this.libroDAO = libroDAO;
    }

    public void agregarLibro() {
        try {
            String isbn = view.txt_ISBN.getText();
            String titulo = view.txt_Titulo.getText();
            String autor = view.txt_Autor.getText();
            String editorial = view.txt_Editorial.getText();
            int anio = view.jYearChooserLibro.getYear();
            int copias = Integer.parseInt(view.txt_CopiasDisp.getText());

            Libro libro = new Libro(isbn, titulo, autor, editorial, anio, copias);
            libroDAO.agregarLibro(libro);

            JOptionPane.showMessageDialog(view, "Libro agregado exitosamente.");
            listarLibros();
            view.limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al agregar el libro: " + ex.getMessage());
        }
    }

    public void listarLibros() {
        try {
            List<Libro> libros = libroDAO.listarLibros();
            view.mostrarLibros(libros);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al listar los libros: " + ex.getMessage());
        }
    }

    public void editarLibro() {
        try {
            String isbn = view.txt_ISBN.getText();
            String titulo = view.txt_Titulo.getText();
            String autor = view.txt_Autor.getText();
            String editorial = view.txt_Editorial.getText();
            int anio = view.jYearChooserLibro.getYear();
            int copias = Integer.parseInt(view.txt_CopiasDisp.getText());

            Libro libro = new Libro(isbn, titulo, autor, editorial, anio, copias);
            libroDAO.editarLibro(libro);

            JOptionPane.showMessageDialog(view, "Libro editado exitosamente.");
            listarLibros();
            view.limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al editar el libro: " + ex.getMessage());
        }
    }

    public void eliminarLibro() {
        try {
            String isbn = view.txt_ISBN.getText();
            libroDAO.eliminarLibro(isbn);

            JOptionPane.showMessageDialog(view, "Libro eliminado exitosamente.");
            listarLibros();
            view.limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al eliminar el libro: " + ex.getMessage());
        }
    }
    
}

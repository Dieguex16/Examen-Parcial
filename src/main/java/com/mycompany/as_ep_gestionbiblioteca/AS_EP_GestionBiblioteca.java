/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.as_ep_gestionbiblioteca;

import Conexion.ConexionDataBase;
import Vista.PrincipalView;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author dgarc
 */
public class AS_EP_GestionBiblioteca {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalView().setVisible(true);
            }
        });

        try (Connection conn = ConexionDataBase.getConnection()) {
            if (conn != null) {
                System.out.println("Conexion a la base de datos establecida correctamente.");
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir la excepción para diagnosticar problemas
        }

    }
}

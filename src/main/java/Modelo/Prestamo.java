/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author dgarc
 */
public class Prestamo {

    private int prestamoID;
    private int usuarioID;
    private Date fechaPrestamo;
    private Date fechaDevolucionEsperada;
    private Date fechaDevolucion;

    public Prestamo(int prestamoID, int usuarioID, Date fechaPrestamo, Date fechaDevolucionEsperada, Date fechaDevolucion) {
        this.prestamoID = prestamoID;
        this.usuarioID = usuarioID;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getPrestamoID() {
        return prestamoID;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucionEsperada() {
        return fechaDevolucionEsperada;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
}

package com.App001.Model;

import java.io.Serializable;

public class Alumno implements Serializable {
    private int Id;
    private String DocIdentidad;
    private String Apellidos;
    private String Nombres;
    private String FechaNac;
    private int AnioEstudios;
    private String Seccion;
    private double Promedio;
    private String Foto;

    public Alumno() {
    }

    public Alumno(int id, String docIdentidad, String apellidos, String nombres, String fechaNac, int anioEstudios, String seccion, double promedio, String foto) {
        Id = id;
        DocIdentidad = docIdentidad;
        Apellidos = apellidos;
        Nombres = nombres;
        FechaNac = fechaNac;
        AnioEstudios = anioEstudios;
        Seccion = seccion;
        Promedio = promedio;
        Foto = foto;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDocIdentidad() {
        return DocIdentidad;
    }

    public void setDocIdentidad(String docIdentidad) {
        DocIdentidad = docIdentidad;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(String fechaNac) {
        FechaNac = fechaNac;
    }

    public int getAnioEstudios() {
        return AnioEstudios;
    }

    public void setAnioEstudios(int anioEstudios) {
        AnioEstudios = anioEstudios;
    }

    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String seccion) {
        Seccion = seccion;
    }

    public double getPromedio() {
        return Promedio;
    }

    public void setPromedio(double promedio) {
        Promedio = promedio;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }
}

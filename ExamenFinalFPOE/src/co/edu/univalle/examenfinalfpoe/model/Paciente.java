package co.edu.univalle.examenfinalfpoe.model;

import java.util.ArrayList;

public class Paciente {
    private int id;
    private String nombre;
    private String Apellido;
    private String telefono;
    private String direccion;
    private ArrayList<String> alergiasList;
    
    public Paciente(int id, String nombre, String Apellido, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        alergiasList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public ArrayList<String> getAlergias() {
        return alergiasList;
    }
    
    public void setAlergias(ArrayList<String> alergias) {
        this.alergiasList = alergias;
    }

    @Override
    public String toString() {
        return "" + id + " | " + nombre + " | " + Apellido + " | " + telefono + " | " + direccion + " | " + alergiasList;
    }
    
}

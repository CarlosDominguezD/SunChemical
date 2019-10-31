/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloPersonas
{
    int Id;
    String Cedula;
    String Nombre;
    String Apellidos;
    String Estado;

    public ModeloPersonas() {
    }

    public ModeloPersonas(int Id, String Cedula, String Nombre, String Apellidos, String Estado) {
        this.Id = Id;
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Estado = Estado;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    
}

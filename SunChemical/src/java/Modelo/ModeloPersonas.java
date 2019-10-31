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

    public ModeloPersonas() {
    }

    public ModeloPersonas(int Id, String Cedula, String Nombre) {
        this.Id = Id;
        this.Cedula = Cedula;
        this.Nombre = Nombre;
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
    
}

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
public class ModeloPoliza 
{
    int Id;
    String Nombre;
    String Codigo;
    int Suma;

    public ModeloPoliza() {
    }   

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public int getSuma() {
        return Suma;
    }

    public void setSuma(int Suma) {
        this.Suma = Suma;
    }

    public ModeloPoliza(int Id, String Nombre, String Codigo, int Suma) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Codigo = Codigo;
        this.Suma = Suma;
    }
    
}

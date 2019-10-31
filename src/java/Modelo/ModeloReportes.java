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
public class ModeloReportes 
{
    int Id;
    String Nombre;
    String PlantillaURL;
    String Sql;

    public ModeloReportes() {
    }

    public ModeloReportes(int Id, String Nombre, String PlantillaURL, String Sql) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.PlantillaURL = PlantillaURL;
        this.Sql = Sql;
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

    public String getPlantillaURL() {
        return PlantillaURL;
    }

    public void setPlantillaURL(String PlantillaURL) {
        this.PlantillaURL = PlantillaURL;
    }

    public String getSql() {
        return Sql;
    }

    public void setSql(String Sql) {
        this.Sql = Sql;
    }
    
    
}

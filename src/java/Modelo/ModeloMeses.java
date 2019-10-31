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
public class ModeloMeses 
{
    int Id;
    String Codigo;
    String Descripcion;
    int Ano;
    int SumaMes;
    
    public ModeloMeses() {
    }

    public ModeloMeses(int Id, String Codigo, String Descripcion, int Ano, int SumaMes) {
        this.Id = Id;
        this.Codigo = Codigo;
        this.Descripcion = Descripcion;
        this.Ano = Ano;
        this.SumaMes = SumaMes;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int Ano) {
        this.Ano = Ano;
    }

    public int getSumaMes() {
        return SumaMes;
    }

    public void setSumaMes(int SumaMes) {
        this.SumaMes = SumaMes;
    }

    
}

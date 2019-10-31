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
public class ModeloPersonas_Polizas 
{
    int Id;
    int IdPersonas;
    int IdPoliza;
    String ValorPoliza;

    public ModeloPersonas_Polizas() {
    }

    public ModeloPersonas_Polizas(int Id, int IdPersonas, int IdPoliza, String ValorPoliza) {
        this.Id = Id;
        this.IdPersonas = IdPersonas;
        this.IdPoliza = IdPoliza;
        this.ValorPoliza = ValorPoliza;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdPersonas() {
        return IdPersonas;
    }

    public void setIdPersonas(int IdPersonas) {
        this.IdPersonas = IdPersonas;
    }

    public int getIdPoliza() {
        return IdPoliza;
    }

    public void setIdPoliza(int IdPoliza) {
        this.IdPoliza = IdPoliza;
    }

    public String getValorPoliza() {
        return ValorPoliza;
    }

    public void setValorPoliza(String ValorPoliza) {
        this.ValorPoliza = ValorPoliza;
    }
    
}

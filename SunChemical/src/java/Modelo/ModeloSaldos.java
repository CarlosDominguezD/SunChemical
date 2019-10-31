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
public class ModeloSaldos
{
    int Id;
    int IdPersonas;
    int Saldo;
    String Debaja;

    public ModeloSaldos() {
    }

    public ModeloSaldos(int Id, int IdPersonas, int Saldo, String Debaja) {
        this.Id = Id;
        this.IdPersonas = IdPersonas;
        this.Saldo = Saldo;
        this.Debaja = Debaja;
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

    public int getSaldo() {
        return Saldo;
    }

    public void setSaldo(int Saldo) {
        this.Saldo = Saldo;
    }

    public String getDebaja() {
        return Debaja;
    }

    public void setDebaja(String Debaja) {
        this.Debaja = Debaja;
    }
    
}

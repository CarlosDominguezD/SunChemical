/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.Date;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloSaldos
{
    int Id;
    int IdPersona;
    int Saldo;
    String Debaja;
    int IdPoliza;
    String FechaBaja;
    String UsuarioBaja;
    int idPoliza;
    int idMes;
    int valorDeBaja;

    public ModeloSaldos() {
    }

    public ModeloSaldos(int Id, int IdPersona, int Saldo, String Debaja, int IdPoliza, String FechaBaja, String UsuarioBaja, int idPoliza, int idMes, int valorDeBaja) {
        this.Id = Id;
        this.IdPersona = IdPersona;
        this.Saldo = Saldo;
        this.Debaja = Debaja;
        this.IdPoliza = IdPoliza;
        this.FechaBaja = FechaBaja;
        this.UsuarioBaja = UsuarioBaja;
        this.idPoliza = idPoliza;
        this.idMes = idMes;
        this.valorDeBaja = valorDeBaja;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int IdPersona) {
        this.IdPersona = IdPersona;
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

    public int getIdPoliza() {
        return IdPoliza;
    }

    public void setIdPoliza(int IdPoliza) {
        this.IdPoliza = IdPoliza;
    }

    public String getFechaBaja() {
        return FechaBaja;
    }

    public void setFechaBaja(String FechaBaja) {
        this.FechaBaja = FechaBaja;
    }

    public String getUsuarioBaja() {
        return UsuarioBaja;
    }

    public void setUsuarioBaja(String UsuarioBaja) {
        this.UsuarioBaja = UsuarioBaja;
    }

    public int getIdMes() {
        return idMes;
    }

    public void setIdMes(int idMes) {
        this.idMes = idMes;
    }

    public int getValorDeBaja() {
        return valorDeBaja;
    }

    public void setValorDeBaja(int valorDeBaja) {
        this.valorDeBaja = valorDeBaja;
    }


}

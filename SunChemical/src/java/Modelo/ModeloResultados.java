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
public class ModeloResultados
{
    int Id;
    int IdPersona;
    int IdPoliza;
    int IdPersona_Poliza;
    int IdMeses;
    String Ano;
    int Pago;
    int Diferencia;
    int IdSaldos;
    String Notas;
    String Observacion;

    public ModeloResultados() {
    }

    public ModeloResultados(int Id, int IdPersona, int IdPoliza, int IdPersona_Poliza, int IdMeses, String Ano, int Pago, int Diferencia, int IdSaldos, String Notas, String Observacion) {
        this.Id = Id;
        this.IdPersona = IdPersona;
        this.IdPoliza = IdPoliza;
        this.IdPersona_Poliza = IdPersona_Poliza;
        this.IdMeses = IdMeses;
        this.Ano = Ano;
        this.Pago = Pago;
        this.Diferencia = Diferencia;
        this.IdSaldos = IdSaldos;
        this.Notas = Notas;
        this.Observacion = Observacion;
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

    public int getIdPoliza() {
        return IdPoliza;
    }

    public void setIdPoliza(int IdPoliza) {
        this.IdPoliza = IdPoliza;
    }

    public int getIdPersona_Poliza() {
        return IdPersona_Poliza;
    }

    public void setIdPersona_Poliza(int IdPersona_Poliza) {
        this.IdPersona_Poliza = IdPersona_Poliza;
    }

    public int getIdMeses() {
        return IdMeses;
    }

    public void setIdMeses(int IdMeses) {
        this.IdMeses = IdMeses;
    }

    public String getAno() {
        return Ano;
    }

    public void setAno(String Ano) {
        this.Ano = Ano;
    }

    public int getPago() {
        return Pago;
    }

    public void setPago(int Pago) {
        this.Pago = Pago;
    }

    public int getDiferencia() {
        return Diferencia;
    }

    public void setDiferencia(int Diferencia) {
        this.Diferencia = Diferencia;
    }

    public int getIdSaldos() {
        return IdSaldos;
    }

    public void setIdSaldos(int IdSaldos) {
        this.IdSaldos = IdSaldos;
    }

    public String getNotas() {
        return Notas;
    }

    public void setNotas(String Notas) {
        this.Notas = Notas;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }
    
    
}

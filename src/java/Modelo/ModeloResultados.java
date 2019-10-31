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
public class ModeloResultados {

    int Id;
    ModeloPersonasPolizas IdPersona_Poliza;
    ModeloMeses IdMeses;
    int Pago;
    int Diferencia;
    ModeloSaldos IdSaldos;
    String Notas;
    String Observacion;
    int Deduccion;
    String Realizado;
    int ValorMes;
    ModeloPersonas IdPersona;
    ModeloPoliza IdPoliza;
    String Ano;

    public ModeloResultados() {
    }

    public ModeloResultados(int Id, ModeloPersonasPolizas IdPersona_Poliza, ModeloMeses IdMeses, int Pago, int Diferencia, ModeloSaldos IdSaldos, String Notas, String Observacion, int Deduccion, String Realizado, int ValorMes, ModeloPersonas IdPersona, ModeloPoliza IdPoliza, String Ano) {
        this.Id = Id;
        this.IdPersona_Poliza = IdPersona_Poliza;
        this.IdMeses = IdMeses;
        this.Pago = Pago;
        this.Diferencia = Diferencia;
        this.IdSaldos = IdSaldos;
        this.Notas = Notas;
        this.Observacion = Observacion;
        this.Deduccion = Deduccion;
        this.Realizado = Realizado;
        this.ValorMes = ValorMes;
        this.IdPersona = IdPersona;
        this.IdPoliza = IdPoliza;
        this.Ano = Ano;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public ModeloPersonasPolizas getIdPersona_Poliza() {
        return IdPersona_Poliza;
    }

    public void setIdPersona_Poliza(ModeloPersonasPolizas IdPersona_Poliza) {
        this.IdPersona_Poliza = IdPersona_Poliza;
    }

    public ModeloMeses getIdMeses() {
        return IdMeses;
    }

    public void setIdMeses(ModeloMeses IdMeses) {
        this.IdMeses = IdMeses;
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

    public ModeloSaldos getIdSaldos() {
        return IdSaldos;
    }

    public void setIdSaldos(ModeloSaldos IdSaldos) {
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

    public int getDeduccion() {
        return Deduccion;
    }

    public void setDeduccion(int Deduccion) {
        this.Deduccion = Deduccion;
    }

    public String getRealizado() {
        return Realizado;
    }

    public void setRealizado(String Realizado) {
        this.Realizado = Realizado;
    }

    public int getValorMes() {
        return ValorMes;
    }

    public void setValorMes(int ValorMes) {
        this.ValorMes = ValorMes;
    }

    public ModeloPersonas getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(ModeloPersonas IdPersona) {
        this.IdPersona = IdPersona;
    }

    public ModeloPoliza getIdPoliza() {
        return IdPoliza;
    }

    public void setIdPoliza(ModeloPoliza IdPoliza) {
        this.IdPoliza = IdPoliza;
    }

    public String getAno() {
        return Ano;
    }

    public void setAno(String Ano) {
        this.Ano = Ano;
    }
}

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
public class ModeloPersonasPolizas 
{
    int Id;
    ModeloPersonas modeloPersonas;
    ModeloPoliza modeloPoliza;
    String ValorPoliza;
    String FechaInicio;
    String FechaFin;
    String Observacion;
    String Activo;
    ModeloGrupoPolizas CodigoPoliza;
    

    public ModeloPersonasPolizas() {
    }

    public ModeloPersonasPolizas(int Id, ModeloPersonas modeloPersonas, ModeloPoliza modeloPoliza, String ValorPoliza, String FechaInicio, String FechaFin, String Observacion, String Activo, ModeloGrupoPolizas CodigoPoliza) {
        this.Id = Id;
        this.modeloPersonas = modeloPersonas;
        this.modeloPoliza = modeloPoliza;
        this.ValorPoliza = ValorPoliza;
        this.FechaInicio = FechaInicio;
        this.FechaFin = FechaFin;
        this.Observacion = Observacion;
        this.Activo = Activo;
        this.CodigoPoliza = CodigoPoliza;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public ModeloPersonas getModeloPersonas() {
        return modeloPersonas;
    }

    public void setModeloPersonas(ModeloPersonas modeloPersonas) {
        this.modeloPersonas = modeloPersonas;
    }

    public ModeloPoliza getModeloPoliza() {
        return modeloPoliza;
    }

    public void setModeloPoliza(ModeloPoliza modeloPoliza) {
        this.modeloPoliza = modeloPoliza;
    }

    public String getValorPoliza() {
        return ValorPoliza;
    }

    public void setValorPoliza(String ValorPoliza) {
        this.ValorPoliza = ValorPoliza;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String FechaFin) {
        this.FechaFin = FechaFin;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getActivo() {
        return Activo;
    }

    public void setActivo(String Activo) {
        this.Activo = Activo;
    }

    public ModeloGrupoPolizas getCodigoPoliza() {
        return CodigoPoliza;
    }

    public void setCodigoPoliza(ModeloGrupoPolizas CodigoPoliza) {
        this.CodigoPoliza = CodigoPoliza;
    }
    
}

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
public class ModeloGrupoPolizas {

    int Id;
    int IdPoliza;
    String Descripcion;
    String CodigoNomina;
    String CodigoReintegro;
    String CodigoCorredor;
    ModeloPoliza modeloPoliza;

    public ModeloGrupoPolizas() {
    }

    public ModeloGrupoPolizas(int Id, int IdPoliza, String Descripcion, String CodigoNomina, String CodigoReintegro, String CodigoCorredor, ModeloPoliza modeloPoliza) {
        this.Id = Id;
        this.IdPoliza = IdPoliza;
        this.Descripcion = Descripcion;
        this.CodigoNomina = CodigoNomina;
        this.CodigoReintegro = CodigoReintegro;
        this.CodigoCorredor = CodigoCorredor;
        this.modeloPoliza = modeloPoliza;
    }



    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdPoliza() {
        return IdPoliza;
    }

    public void setIdPoliza(int IdPoliza) {
        this.IdPoliza = IdPoliza;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getCodigoNomina() {
        return CodigoNomina;
    }

    public void setCodigoNomina(String CodigoNomina) {
        this.CodigoNomina = CodigoNomina;
    }

    public String getCodigoReintegro() {
        return CodigoReintegro;
    }

    public void setCodigoReintegro(String CodigoReintegro) {
        this.CodigoReintegro = CodigoReintegro;
    }

    public String getCodigoCorredor() {
        return CodigoCorredor;
    }

    public void setCodigoCorredor(String CodigoCorredor) {
        this.CodigoCorredor = CodigoCorredor;
    }

    public ModeloPoliza getModeloPoliza() {
        return modeloPoliza;
    }

    public void setModeloPoliza(ModeloPoliza modeloPoliza) {
        this.modeloPoliza = modeloPoliza;
    }

   
}

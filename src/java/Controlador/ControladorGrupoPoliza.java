/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloGrupoPolizas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorGrupoPoliza {

    public LinkedList<ModeloGrupoPolizas> Read() {
        LinkedList<ModeloGrupoPolizas> modeloGrupoPolizas = new LinkedList<ModeloGrupoPolizas>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        ControladorPoliza controladorPoliza = new ControladorPoliza();
        try
        {
            SQL = con.prepareStatement("SELECT Id, IdPoliza, Descripcion, CodigoNomina, CodigoCorredor, CodigoReintegro FROM grupo_poliza");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloGrupoPolizas modeloGrupoPoliza = new ModeloGrupoPolizas();
                modeloGrupoPoliza.setId(res.getInt("id"));
                modeloGrupoPoliza.setIdPoliza(res.getInt("IdPoliza"));
                modeloGrupoPoliza.setDescripcion(res.getString("Descripcion"));
                modeloGrupoPoliza.setCodigoNomina(res.getString("CodigoNomina"));
                modeloGrupoPoliza.setCodigoReintegro(res.getString("CodigoReintegro"));
                modeloGrupoPoliza.setCodigoCorredor(res.getString("CodigoCorredor"));
                modeloGrupoPoliza.setModeloPoliza(controladorPoliza.Select(res.getInt("IdPoliza")));
                modeloGrupoPolizas.add(modeloGrupoPoliza);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return modeloGrupoPolizas;
    }
}

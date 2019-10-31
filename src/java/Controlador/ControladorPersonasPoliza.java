/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloGrupoPolizas;
import Modelo.ModeloPersonas;
import Modelo.ModeloPersonasPolizas;
import Modelo.ModeloPoliza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorPersonasPoliza {

    public boolean Insert(ModeloPersonasPolizas modeloPersonasPolizas) {
        boolean resul = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement("INSERT INTO `personas_poliza`(`IdPersonas`,`IdPoliza`,`ValorPoliza`,"
                        + "`FechaInicio`,`FechaFin`,`Observacion`,`Activo`,`CodigoPoliza`) "
                        + "VALUE (?,?,?,?,?,?,?,?);");
                SQL.setInt(1, modeloPersonasPolizas.getModeloPersonas().getId());
                //SQL.setInt(2, modeloPersonasPolizas.getModeloPoliza().getId());
                SQL.setInt(2, modeloPersonasPolizas.getCodigoPoliza().getIdPoliza());
                SQL.setString(3, modeloPersonasPolizas.getValorPoliza());
                SQL.setString(4, modeloPersonasPolizas.getFechaInicio());
                SQL.setString(5, modeloPersonasPolizas.getFechaFin());
                SQL.setString(6, modeloPersonasPolizas.getObservacion());
                SQL.setString(7, modeloPersonasPolizas.getActivo());
                SQL.setInt(8, modeloPersonasPolizas.getCodigoPoliza().getId());
                if (SQL.executeUpdate() > 0) {
                    resul = true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return resul;
    }

    ModeloPersonasPolizas SelectIdPersonaIdPolizaNomina(String CedulaPersona, String CodigoPoliza) {
        ModeloPersonasPolizas modeloPersonasPolizas = new ModeloPersonasPolizas();
        ModeloPersonas modeloPesonas = new ModeloPersonas();
        ModeloPoliza modeloPoliza = new ModeloPoliza();
        ModeloGrupoPolizas modeloGrupoPolizas = new ModeloGrupoPolizas();
        ControladorPersonas controladorPersonas = new ControladorPersonas();
        ControladorPoliza controladorPoliza = new ControladorPoliza();
        ControladorGrupoPolizas controladorGrupoPolizas = new ControladorGrupoPolizas();

        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        modeloPesonas = controladorPersonas.SelectCedula(CedulaPersona);
        if (modeloPesonas != null) {
            modeloPersonasPolizas.setModeloPersonas(modeloPesonas);
            modeloGrupoPolizas = controladorGrupoPolizas.select("Select Id, IdPoliza, Descripcion, CodigoNomina, CodigoCorredor FROM grupo_poliza WHERE CodigoNomina = '" + CodigoPoliza + "'");
            if (modeloGrupoPolizas != null) {
                modeloPersonasPolizas.setCodigoPoliza(modeloGrupoPolizas);
                modeloPoliza = controladorPoliza.SelectCedulaa("SELECT `Id`, `Nombre` FROM `poliza` WHERE `Id` = " + modeloGrupoPolizas.getIdPoliza());
                try {
                    SQL = con.prepareStatement("SELECT `Id`,`IdPersonas`,`IdPoliza`,`ValorPoliza`,"
                            + "`FechaInicio`,`FechaFin`,`Observacion`,  `Activo`, `CodigoPoliza` "
                            + "FROM `personas_poliza` WHERE `IdPersonas`= ? AND `IdPoliza`= ?;");
                    SQL.setInt(1, modeloPesonas.getId());
                    SQL.setInt(2, modeloPoliza.getId());
                    ResultSet res = SQL.executeQuery();
                    if (res.next()) {
                        //modeloPersonasPolizas = new ModeloPersonasPolizas();
                        modeloPersonasPolizas.setId(res.getInt("id"));
                        //modeloPersonasPolizas.setModeloPersonas(modeloPesonas);
                        modeloPersonasPolizas.setModeloPoliza(modeloPoliza);
                        modeloPersonasPolizas.setValorPoliza(res.getString("ValorPoliza"));
                        modeloPersonasPolizas.setFechaInicio(res.getString("FechaInicio"));
                        modeloPersonasPolizas.setFechaFin(res.getString("FechaFin"));
                        modeloPersonasPolizas.setObservacion(res.getString("Observacion"));
                        modeloPersonasPolizas.setActivo(res.getString("Activo"));
                        //modeloPersonasPolizas.setCodigoPoliza(modeloGrupoPolizas);
                    } else {
                        modeloPersonasPolizas = null;
                    }
                    res.close();
                    SQL.close();
                    con.close();
                } catch (SQLException e) {

                }
            } else {
                //modeloPersonasPolizas = new ModeloPersonasPolizas();
                modeloPersonasPolizas.setCodigoPoliza(null);
            }
        } else {
            //modeloPersonasPolizas = new ModeloPersonasPolizas();
            modeloPersonasPolizas.setModeloPersonas(null);
        }
        return modeloPersonasPolizas;
    }

    public boolean Update(ModeloPersonasPolizas modeloPersonasPolizas) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("UPDATE `personas_poliza`  SET "
                    + "`ValorPoliza` = ?, `IdPoliza` = ?,  "
                    + "  `Observacion` = ?,  `Activo` = ?, CodigoPoliza = ?"
                    + " WHERE  `Id` = ?;");
            SQL.setString(1, modeloPersonasPolizas.getValorPoliza());
            //SQL.setInt(2, modeloPersonasPolizas.getModeloPoliza().getId());
            SQL.setInt(2, modeloPersonasPolizas.getCodigoPoliza().getIdPoliza());
            SQL.setString(3, modeloPersonasPolizas.getObservacion());
            SQL.setString(4, modeloPersonasPolizas.getActivo());
            SQL.setInt(5, modeloPersonasPolizas.getCodigoPoliza().getId());
            SQL.setInt(6, modeloPersonasPolizas.getId());
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        return resul;
    }

    public boolean Delete(ModeloPersonasPolizas modeloPersonasPolizas) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("DELETE FROM `personas_poliza` WHERE `Id` = ?;");
            SQL.setInt(1, modeloPersonasPolizas.getId());
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {

        }
        return resul;
    }

    public LinkedList<ModeloPersonasPolizas> Read() {
        LinkedList<ModeloPersonasPolizas> modeloPersonasPolizas = new LinkedList<ModeloPersonasPolizas>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        ControladorPersonas controladorPersonas = new ControladorPersonas();
        ControladorPoliza controladorPoliza = new ControladorPoliza();
        ControladorGrupoPolizas controladorGrupoPolizas = new ControladorGrupoPolizas();
        try {
            SQL = con.prepareStatement("SELECT `Id`,`IdPersonas`,`IdPoliza`,`ValorPoliza`,`FechaInicio`,"
                    + "`FechaFin`,`Observacion`,`Activo`,`CodigoPoliza`FROM `personas_poliza`;");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloPersonasPolizas modeloPersonasPoliza = new ModeloPersonasPolizas();
                modeloPersonasPoliza.setId(res.getInt("id"));
                modeloPersonasPoliza.setModeloPersonas(controladorPersonas.Select(res.getInt("IdPersonas")));
                modeloPersonasPoliza.setModeloPoliza(controladorPoliza.Select(res.getInt("IdPoliza")));
                modeloPersonasPoliza.setValorPoliza(res.getString("ValorPoliza"));
                modeloPersonasPoliza.setFechaInicio(res.getString("FechaInicio"));
                modeloPersonasPoliza.setFechaFin(res.getString("FechaFin"));
                modeloPersonasPoliza.setObservacion(res.getString("Observacion"));
                modeloPersonasPoliza.setActivo(res.getString("Activo"));
                modeloPersonasPoliza.setCodigoPoliza(controladorGrupoPolizas.select("SELECT Id, IdPoliza, Descripcion, CodigoNomina, CodigoCorredor FROM grupo_poliza where Id =" + res.getInt("CodigoPoliza")));
                modeloPersonasPolizas.add(modeloPersonasPoliza);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return modeloPersonasPolizas;
    }

    public ModeloPersonasPolizas Select(Integer CodigoBuscar) {
        ModeloPersonasPolizas modeloPersonasPolizas = new ModeloPersonasPolizas();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        ControladorPersonas controladorPersonas = new ControladorPersonas();
        ControladorPoliza controladorPoliza = new ControladorPoliza();
        ControladorGrupoPolizas controladorGrupoPolizas = new ControladorGrupoPolizas();
        try {
            SQL = con.prepareStatement("SELECT `Id`,`IdPersonas`,`IdPoliza`,`ValorPoliza`,`FechaInicio`,"
                    + "`FechaFin`,`Observacion`,`Activo`,`CodigoPoliza`FROM `personas_poliza` WHERE Id = ?");
            SQL.setInt(1, CodigoBuscar);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloPersonasPolizas.setId(res.getInt("id"));
                modeloPersonasPolizas.setModeloPersonas(controladorPersonas.Select(res.getInt("IdPersonas")));
                modeloPersonasPolizas.setModeloPoliza(controladorPoliza.Select(res.getInt("IdPoliza")));
                modeloPersonasPolizas.setValorPoliza(res.getString("ValorPoliza"));
                modeloPersonasPolizas.setFechaInicio(res.getString("FechaInicio"));
                modeloPersonasPolizas.setFechaFin(res.getString("FechaFin"));
                modeloPersonasPolizas.setObservacion(res.getString("Observacion"));
                modeloPersonasPolizas.setActivo(res.getString("Activo"));
                modeloPersonasPolizas.setCodigoPoliza(controladorGrupoPolizas.select("SELECT Id, IdPoliza, Descripcion, CodigoNomina, CodigoCorredor FROM grupo_poliza where Id =" + res.getInt("CodigoPoliza")));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {

        }
        return modeloPersonasPolizas;
    }

    ModeloPersonasPolizas SelectIdPersonaIdPoliza(String CedulaPersona, String CodigoPoliza) {
        ModeloPersonasPolizas modeloPersonasPolizas = new ModeloPersonasPolizas();
        ModeloPersonas modeloPesonas = new ModeloPersonas();
        ModeloPoliza modeloPoliza = new ModeloPoliza();
        ModeloGrupoPolizas modeloGrupoPolizas = new ModeloGrupoPolizas();
        ControladorPersonas controladorPersonas = new ControladorPersonas();
        ControladorPoliza controladorPoliza = new ControladorPoliza();
        ControladorGrupoPolizas controladorGrupoPolizas = new ControladorGrupoPolizas();

        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        modeloPesonas = controladorPersonas.SelectCedula(CedulaPersona);
        if (modeloPesonas != null) {
            modeloPersonasPolizas.setModeloPersonas(modeloPesonas);
            modeloGrupoPolizas = controladorGrupoPolizas.select("Select Id, IdPoliza, Descripcion, CodigoNomina, CodigoCorredor"
                    + " FROM grupo_poliza WHERE CodigoCorredor = '" + CodigoPoliza + "'");
            if (modeloGrupoPolizas != null) {
                modeloPersonasPolizas.setCodigoPoliza(modeloGrupoPolizas);
                modeloPoliza = controladorPoliza.SelectCedulaa("SELECT `Id`, `Nombre` FROM `poliza` WHERE `Id` = " + modeloGrupoPolizas.getIdPoliza());
                try {
                    SQL = con.prepareStatement("SELECT `Id`,`IdPersonas`,`IdPoliza`,`ValorPoliza`,"
                            + "`FechaInicio`,`FechaFin`,`Observacion`,  `Activo`, `CodigoPoliza` "
                            + "FROM `personas_poliza` WHERE `IdPersonas`= ? AND `IdPoliza`= ?;");
                    SQL.setInt(1, modeloPesonas.getId());
                    SQL.setInt(2, modeloPoliza.getId());
                    ResultSet res = SQL.executeQuery();
                    if (res.next()) {
                        //modeloPersonasPolizas = new ModeloPersonasPolizas();
                        modeloPersonasPolizas.setId(res.getInt("id"));
                        //modeloPersonasPolizas.setModeloPersonas(modeloPesonas);
                        modeloPersonasPolizas.setModeloPoliza(modeloPoliza);
                        modeloPersonasPolizas.setValorPoliza(res.getString("ValorPoliza"));
                        modeloPersonasPolizas.setFechaInicio(res.getString("FechaInicio"));
                        modeloPersonasPolizas.setFechaFin(res.getString("FechaFin"));
                        modeloPersonasPolizas.setObservacion(res.getString("Observacion"));
                        modeloPersonasPolizas.setActivo(res.getString("Activo"));
                        //modeloPersonasPolizas.setCodigoPoliza(modeloGrupoPolizas);
                        //modeloPersonasPolizas.setCodigoPoliza(controladorGrupoPolizas.select("SELECT Id, IdPoliza, Descripcion, CodigoNomina, CodigoCorredor FROM grupo_poliza where Id = " + res.getInt("CodigoPoliza")));
                    }

                    res.close();
                    SQL.close();
                    con.close();

                } catch (SQLException e) {
                    System.out.println(e);
                }
            } else {
                //modeloPersonasPolizas = new ModeloPersonasPolizas();
                modeloPersonasPolizas.setCodigoPoliza(null);
            }
        } else {
            //modeloPersonasPolizas = new ModeloPersonasPolizas();
            modeloPersonasPolizas.setModeloPersonas(null);
        }
        return modeloPersonasPolizas;
    }
    
    
    
    ModeloPersonasPolizas SelectPersonaPoliza(String CedulaPersona, String SqlCodigoPlano) {
        ModeloPersonasPolizas modeloPersonasPolizas = new ModeloPersonasPolizas();
        ModeloPersonas modeloPesonas = new ModeloPersonas();
        ModeloPoliza modeloPoliza = new ModeloPoliza();
        ModeloGrupoPolizas modeloGrupoPolizas = new ModeloGrupoPolizas();
        ControladorPersonas controladorPersonas = new ControladorPersonas();
        ControladorPoliza controladorPoliza = new ControladorPoliza();
        ControladorGrupoPolizas controladorGrupoPolizas = new ControladorGrupoPolizas();

        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        modeloPesonas = controladorPersonas.SelectCedula(CedulaPersona);
        if (modeloPesonas != null) {
            modeloPersonasPolizas.setModeloPersonas(modeloPesonas);
            //modeloGrupoPolizas = controladorGrupoPolizas.select("Select Id, IdPoliza, Descripcion, CodigoNomina, CodigoCorredor FROM grupo_poliza WHERE CodigoNomina = '" + CodigoPoliza + "'");
            modeloGrupoPolizas = controladorGrupoPolizas.select(SqlCodigoPlano);
            if (modeloGrupoPolizas != null) {
                modeloPersonasPolizas.setCodigoPoliza(modeloGrupoPolizas);
                modeloPoliza = controladorPoliza.SelectCedulaa("SELECT `Id`, `Nombre` FROM `poliza` WHERE `Id` = " + modeloGrupoPolizas.getIdPoliza());
                try {
                    SQL = con.prepareStatement("SELECT `Id`,`IdPersonas`,`IdPoliza`,`ValorPoliza`,"
                            + "`FechaInicio`,`FechaFin`,`Observacion`,  `Activo`, `CodigoPoliza` "
                            + "FROM `personas_poliza` WHERE `IdPersonas`= ? AND `IdPoliza`= ?;");
                    SQL.setInt(1, modeloPesonas.getId());
                    SQL.setInt(2, modeloPoliza.getId());
                    ResultSet res = SQL.executeQuery();
                    if (res.next()) {
                        //modeloPersonasPolizas = new ModeloPersonasPolizas();
                        modeloPersonasPolizas.setId(res.getInt("id"));
                        //modeloPersonasPolizas.setModeloPersonas(modeloPesonas);
                        modeloPersonasPolizas.setModeloPoliza(modeloPoliza);
                        modeloPersonasPolizas.setValorPoliza(res.getString("ValorPoliza"));
                        modeloPersonasPolizas.setFechaInicio(res.getString("FechaInicio"));
                        modeloPersonasPolizas.setFechaFin(res.getString("FechaFin"));
                        modeloPersonasPolizas.setObservacion(res.getString("Observacion"));
                        modeloPersonasPolizas.setActivo(res.getString("Activo"));
                        //modeloPersonasPolizas.setCodigoPoliza(modeloGrupoPolizas);
                    } else {
                        modeloPersonasPolizas = null;
                    }
                    res.close();
                    SQL.close();
                    con.close();
                } catch (SQLException e) {

                }
            } else {
                //modeloPersonasPolizas = new ModeloPersonasPolizas();
                modeloPersonasPolizas.setCodigoPoliza(null);
            }
        } else {
            //modeloPersonasPolizas = new ModeloPersonasPolizas();
            modeloPersonasPolizas.setModeloPersonas(null);
        }
        return modeloPersonasPolizas;
    }
    
    
    
    
    
    
}

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

/**
 *
 * @author Diego Fdo Guzmán B
 */
public class ControladorGrupoPolizas {

    public ModeloGrupoPolizas select(String Sql) {
        ModeloGrupoPolizas modeloGrupoPolizas = null;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloGrupoPolizas = new ModeloGrupoPolizas();
                modeloGrupoPolizas.setId(res.getInt("id"));
                modeloGrupoPolizas.setIdPoliza(res.getInt("IdPoliza"));
                modeloGrupoPolizas.setDescripcion(res.getString("Descripcion"));
                modeloGrupoPolizas.setCodigoNomina(res.getString("CodigoNomina"));
                modeloGrupoPolizas.setCodigoReintegro(res.getString("CodigoReintegro"));
                modeloGrupoPolizas.setCodigoCorredor(res.getString("CodigoCorredor"));
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

    public boolean Insert(ModeloGrupoPolizas modeloGrupoPolizas) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO grupo_poliza (IdPoliza, Descripcion, "
                        + "CodigoNomina, CodigoCorredor, CodigoReintegro)"
                        + " VALUE (?,?,?,?,?)");
                SQL.setInt(1, modeloGrupoPolizas.getModeloPoliza().getId());
                SQL.setString(2, modeloGrupoPolizas.getDescripcion());
                SQL.setString(3, modeloGrupoPolizas.getCodigoNomina());                
                SQL.setString(4, modeloGrupoPolizas.getCodigoCorredor());
                SQL.setString(5, modeloGrupoPolizas.getCodigoReintegro());
                if (SQL.executeUpdate() > 0)
                {
                    resul = true;
                }
            } catch (SQLException e)
            {
                System.out.println(e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return resul;
    }

    public boolean Update(ModeloGrupoPolizas modeloGrupoPolizas) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE grupo_poliza  SET IdPoliza = ?, "
                    + "Descripcion = ?, CodigoNomina = ?, "
                    + "CodigoCorredor = ?, CodigoReintegro = ? WHERE Id = ?;");
            SQL.setInt(1, modeloGrupoPolizas.getModeloPoliza().getId());
            SQL.setString(2, modeloGrupoPolizas.getDescripcion());
            SQL.setString(3, modeloGrupoPolizas.getCodigoNomina());
            SQL.setString(4, modeloGrupoPolizas.getCodigoCorredor());
            SQL.setString(5, modeloGrupoPolizas.getCodigoReintegro());
            SQL.setInt(6, modeloGrupoPolizas.getId());
            if (SQL.executeUpdate() > 0)
            {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return resul;
    }

    public boolean Delete(ModeloGrupoPolizas modeloGrupoPolizas) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("DELETE FROM `grupo_poliza` WHERE `Id` = ?;");
            SQL.setInt(1, modeloGrupoPolizas.getId());
            if (SQL.executeUpdate() > 0)
            {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return resul;
    }
}

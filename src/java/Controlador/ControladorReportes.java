/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloReportes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorReportes {

    public boolean Insert(ModeloReportes modeloReportes) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO `reportes`(`Nombre`,`PlantillaURL`,`Sql`) VALUE (?,?,?);");
                SQL.setString(1, modeloReportes.getNombre());
                SQL.setString(2, modeloReportes.getPlantillaURL());
                SQL.setString(3, modeloReportes.getSql());                
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

    public boolean Update(ModeloReportes modeloReportes) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `reportes`  SET `Nombre` = ?,`PlantillaURL` = ?,`Sql` = ? WHERE `Id` = ?;");
            SQL.setString(1, modeloReportes.getNombre());
            SQL.setString(2, modeloReportes.getPlantillaURL());
            SQL.setString(3, modeloReportes.getSql());
            SQL.setInt(4, modeloReportes.getId());            
            if (SQL.executeUpdate() > 0)
            {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return resul;
    }

    public boolean Delete(ModeloReportes modeloReportes) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("DELETE FROM `reportes` WHERE `Id` = ?;");
            SQL.setInt(1, modeloReportes.getId());
            if (SQL.executeUpdate() > 0)
            {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return resul;
    }

    public LinkedList<ModeloReportes> Read() {
        LinkedList<ModeloReportes> modeloReportes = new LinkedList<ModeloReportes>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Nombre`,`PlantillaURL`,`Sql`FROM `reportes`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloReportes modeloReporte = new ModeloReportes();
                modeloReporte.setId(res.getInt("id"));
                modeloReporte.setNombre(res.getString("Nombre"));
                modeloReporte.setPlantillaURL(res.getString("PlantillaURL"));
                modeloReporte.setSql(res.getString("Sql"));                
                modeloReportes.add(modeloReporte);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return modeloReportes;
    }

    public ModeloReportes Select(Integer Id) {
        ModeloReportes modeloReportes = new ModeloReportes();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Nombre`,`PlantillaURL`,`Sql`FROM `reportes` WHERE Id = ?");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloReportes.setId(res.getInt("id"));
                modeloReportes.setNombre(res.getString("Nombre"));
                modeloReportes.setPlantillaURL(res.getString("PlantillaURL"));
                modeloReportes.setSql(res.getString("Sql"));                
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return modeloReportes;
    }
}

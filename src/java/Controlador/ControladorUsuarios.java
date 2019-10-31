/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorUsuarios {

    public boolean Insert(ModeloUsuario modeloUsuario) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO `usuario`(`Usuario`,`Password`,`Nombre`,`Codigo`) "
                        + "VALUE (?,?,?,?);");
                SQL.setString(1, modeloUsuario.getUsuario());
                SQL.setString(2, modeloUsuario.getPassword());
                SQL.setString(3, modeloUsuario.getNombre());
                SQL.setString(4, modeloUsuario.getCodigo());
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

    public boolean Update(ModeloUsuario modeloUsuario) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `usuario`  SET `Usuario` = ?, "
                    + "`Password` = ?, `Nombre` = ?,`Codigo` = ? "
                    + "WHERE `Id` = ?;");
            SQL.setString(1, modeloUsuario.getUsuario());
            SQL.setString(2, modeloUsuario.getPassword());
            SQL.setString(3, modeloUsuario.getNombre());
            SQL.setString(4, modeloUsuario.getCodigo());
            SQL.setInt(5, modeloUsuario.getId());
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

    public boolean Delete(ModeloUsuario modeloUsuario) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("DELETE FROM `usuario` WHERE `Id` = ?;");
            SQL.setInt(1, modeloUsuario.getId());
            if (SQL.executeUpdate() > 0)
            {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al borrar la Dependencia " + e);
        }
        return resul;
    }

    public LinkedList<ModeloUsuario> Read() {
        LinkedList<ModeloUsuario> modeloUsuarios = new LinkedList<ModeloUsuario>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Usuario`,`Password`,`Nombre`,`Codigo`FROM `usuario`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloUsuario modeloUsuario = new ModeloUsuario();
                modeloUsuario.setId(res.getInt("id"));
                modeloUsuario.setUsuario(res.getString("Usuario"));
                modeloUsuario.setPassword(res.getString("Password"));
                modeloUsuario.setNombre(res.getString("Nombre"));
                modeloUsuario.setCodigo(res.getString("Codigo"));
                modeloUsuarios.add(modeloUsuario);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return modeloUsuarios;
    }

    public ModeloUsuario Select(String CodigoBuscar){
        ModeloUsuario modeloUsuario = new ModeloUsuario();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Usuario`,`Password`,`Nombre`,`Codigo` FROM `usuario` WHERE Codigo = ?");
            SQL.setString(1, CodigoBuscar);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloUsuario.setId(res.getInt("id"));
                modeloUsuario.setId(res.getInt("id"));
                modeloUsuario.setUsuario(res.getString("Usuario"));
                modeloUsuario.setPassword(res.getString("Password"));
                modeloUsuario.setNombre(res.getString("Nombre"));
                modeloUsuario.setCodigo(res.getString("Codigo"));                
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            
        }
        return modeloUsuario;
    }

    public ModeloUsuario Select(String User, String Password) 
    {
       ModeloUsuario modeloUsuario = new ModeloUsuario();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Usuario`,`Password`,`Nombre`,`Codigo` FROM `usuario` WHERE Usuario = ? AND Password = ?");
            SQL.setString(1, User);
            SQL.setString(2, Password);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloUsuario.setId(res.getInt("id"));
                modeloUsuario.setId(res.getInt("id"));
                modeloUsuario.setUsuario(res.getString("Usuario"));
                modeloUsuario.setPassword(res.getString("Password"));
                modeloUsuario.setNombre(res.getString("Nombre"));
                modeloUsuario.setCodigo(res.getString("Codigo"));                
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            
        }
        return modeloUsuario;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPersonas;
import Tools.Tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorPersonas {

    public boolean Insert(ModeloPersonas modeloPersonas) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO `personas`(`Cedula`,`Nombre`,`Apellidos`,`Estado`) "
                        + "VALUE (?,?,?,?)");
                SQL.setString(1, modeloPersonas.getCedula());
                SQL.setString(2, modeloPersonas.getNombre());
                SQL.setString(3, modeloPersonas.getApellidos());
                SQL.setString(4, modeloPersonas.getEstado());
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

    public boolean Update(ModeloPersonas modeloPersonas) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `personas`  SET `Cedula` = ?,`Nombre` = ?,`Apellidos` = ?,`Estado` = ? "
                    + "WHERE `Id` = ?;");
            SQL.setString(1, modeloPersonas.getCedula());
            SQL.setString(2, modeloPersonas.getNombre());
            SQL.setString(3, modeloPersonas.getApellidos());
            SQL.setString(4, modeloPersonas.getEstado());
            SQL.setInt(5, modeloPersonas.getId());
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

    public boolean Delete(ModeloPersonas modeloPersonas) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("DELETE FROM `personas` WHERE `Id` = ?;");
            SQL.setInt(1, modeloPersonas.getId());
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

    public LinkedList<ModeloPersonas> Read() {
        LinkedList<ModeloPersonas> modeloPersonas = new LinkedList<ModeloPersonas>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Cedula`,`Nombre`,`Apellidos`,`Estado`FROM `personas` ORDER BY Apellidos;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloPersonas modeloPersona = new ModeloPersonas();
                modeloPersona.setId(res.getInt("id"));
                modeloPersona.setCedula(res.getString("Cedula"));
                modeloPersona.setNombre(res.getString("Nombre"));
                modeloPersona.setApellidos(res.getString("Apellidos"));
                modeloPersona.setEstado(res.getString("Estado"));
                modeloPersonas.add(modeloPersona);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return modeloPersonas;
    }

    public ModeloPersonas Select(Integer Id) {
        ModeloPersonas modeloPersonas = new ModeloPersonas();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Cedula`,`Nombre`,`Apellidos`,`Estado`FROM `personas` WHERE Id = ?");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloPersonas.setId(res.getInt("id"));
                modeloPersonas.setCedula(res.getString("Cedula"));
                modeloPersonas.setNombre(res.getString("Nombre"));
                modeloPersonas.setApellidos(res.getString("Apellidos"));
                modeloPersonas.setEstado(res.getString("Estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return modeloPersonas;
    }

    public ModeloPersonas SelectCedula(String CodigoBuscar) {
        ModeloPersonas modeloPersonas = null;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Cedula`,`Nombre`,`Apellidos`,`Estado`FROM `personas` WHERE Cedula = ?");
            SQL.setString(1, CodigoBuscar);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloPersonas = new ModeloPersonas();
                modeloPersonas.setId(res.getInt("id"));
                modeloPersonas.setCedula(res.getString("Cedula"));
                modeloPersonas.setNombre(res.getString("Nombre"));
                modeloPersonas.setApellidos(res.getString("Apellidos"));
                modeloPersonas.setEstado(res.getString("Estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return modeloPersonas;
    }

    public String Upload(String RutaDispo) {
        String resul = "False";
        Tools tools = new Tools();
        try
        {
            File file = new File(RutaDispo);
            int CanLine = tools.ContarArchi(file);
            List<ModeloPersonas> modeloPersonas = new ArrayList<ModeloPersonas>();
            modeloPersonas = GenerarArreglo(file, CanLine);
            int con = 0;
            ModeloPersonas mod = new ModeloPersonas();
            String estado = "True";
            for (ModeloPersonas modeloPersona : modeloPersonas)
            {
                if (con != 0)
                {
                    mod = SelectCedula(modeloPersona.getCedula());
                    if (mod.getId() == 0)
                    {
                        if ("True".equals(estado))
                        {
                            boolean r = Insert(modeloPersona);
                            if (r == true)
                            {
                                resul = "True";
                            } else
                            {
                                estado = "False";
                                resul = "False";
                            }
                        }
                    }
                } else
                {
                    con++;
                }
            }
        } catch (IOException e)
        {
        }

        return resul;
    }

    private List<ModeloPersonas> GenerarArreglo(File file, int CanLine) {
        List<ModeloPersonas> listModeloPersonas = new ArrayList<ModeloPersonas>();
        BufferedReader brPerReader = null;
        String linea;
        try
        {
            brPerReader = new BufferedReader(new FileReader(file));
            while (brPerReader.ready())
            {
                linea = brPerReader.readLine();
                StringTokenizer stringTokenizer = new StringTokenizer(linea, ";");
                int numtokens = stringTokenizer.countTokens();
                if (numtokens == 3)
                {
                    ModeloPersonas modeloPersona = new ModeloPersonas();
                    modeloPersona.setCedula(stringTokenizer.nextToken());
                    modeloPersona.setNombre(stringTokenizer.nextToken());
                    modeloPersona.setApellidos(stringTokenizer.nextToken());
                    modeloPersona.setEstado("Activo");
                    listModeloPersonas.add(modeloPersona);
                }
            }
        } catch (IOException e)
        {
            System.out.println(e);
        }
        return listModeloPersonas;
    }

}

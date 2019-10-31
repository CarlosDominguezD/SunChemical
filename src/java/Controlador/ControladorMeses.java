/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloMeses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorMeses {

    public ModeloMeses SelectMesAno(String Mes, String Ano) {
        ModeloMeses modeloMeses = null;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Codigo`,`Descripcion`,`Ano` FROM `meses` "
                    + "WHERE `Descripcion` = ? AND `Ano` = ? ;");
            SQL.setString(1, Mes);
            SQL.setInt(2, Integer.parseInt(Ano));
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloMeses = new ModeloMeses();
                modeloMeses.setId(res.getInt("id"));
                modeloMeses.setDescripcion(res.getString("Descripcion"));
                modeloMeses.setAno(res.getInt("Ano"));
                modeloMeses.setCodigo(res.getString("Codigo"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return modeloMeses;
    }
    
    public ModeloMeses SelectMesAno(int IdMes, String Ano) {
        ModeloMeses modeloMeses = new ModeloMeses();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Codigo`,`Descripcion`,`Ano` FROM `meses` "
                    + "WHERE `Id` = ? AND `Ano` = ? ;");
            SQL.setInt(1, IdMes);
            SQL.setInt(2, Integer.parseInt(Ano));
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloMeses.setId(res.getInt("id"));
                modeloMeses.setDescripcion(res.getString("Descripcion"));
                modeloMeses.setAno(res.getInt("Ano"));
                modeloMeses.setCodigo(res.getString("Codigo"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return modeloMeses;
    }

    public Integer CodigoMes(String NombreMes) {
        int Codigo = 0;
        switch (NombreMes)
        {
            case "Enero":
                Codigo = 1;
                break;
            case "Febreo":
                Codigo = 2;
                break;
            case "Marzo":
                Codigo = 3;
                break;
            case "Abril":
                Codigo = 4;
                break;
            case "Mayo":
                Codigo = 5;
                break;
            case "Junio":
                Codigo = 6;
                break;
            case "Julio":
                Codigo = 7;
                break;
            case "Agosto":
                Codigo = 8;
                break;
            case "Septiembre":
                Codigo = 9;
                break;
            case "Octubre":
                Codigo = 10;
                break;
            case "Noviembre":
                Codigo = 11;
                break;
            case "Diciembre":
                Codigo = 12;
                break;
        }
        return Codigo;
    }

    public String NombreMes(int CodMes) {
        String nombreMes = "";
        switch (CodMes)
        {
            case 1:
                nombreMes = "Enero";
                break;
            case 2:
                nombreMes = "Febreo";
                break;
            case 3:
                nombreMes = "Marzo";
                break;
            case 4:
                nombreMes = "Abril";
                break;
            case 5:
                nombreMes = "Mayo";
                break;
            case 6:
                nombreMes = "Junio";
                break;
            case 7:
                nombreMes = "Julio";
                break;
            case 8:
                nombreMes = "Agosto";
                break;
            case 9:
                nombreMes = "Septiembre";
                break;
            case 10:
                nombreMes = "Octubre";
                break;
            case 11:
                nombreMes = "Noviembre";
                break;
            case 12:
                nombreMes = "Diciembre";
                break;
        }
        return nombreMes;
    }

    public ModeloMeses Select(int Id) {
        ModeloMeses modeloMeses = new ModeloMeses();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Codigo`,`Descripcion`,`Ano` FROM `meses` WHERE Id = ?");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloMeses.setId(res.getInt("id"));
                modeloMeses.setCodigo(res.getString("Codigo"));
                modeloMeses.setDescripcion(res.getString("Descripcion"));
                modeloMeses.setAno(res.getInt("Ano"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return modeloMeses;
    }

    public ModeloMeses Select(String Sql) {
        ModeloMeses modeloMeses = null;
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
                modeloMeses = new ModeloMeses();
                modeloMeses.setId(res.getInt("id"));
                modeloMeses.setDescripcion(res.getString("Descripcion"));
                modeloMeses.setAno(res.getInt("Ano"));
                modeloMeses.setCodigo(String.valueOf(res.getInt("Codigo")));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return modeloMeses;
    }

    public boolean Update(ModeloMeses modeloMeses) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `meses`  SET `Codigo` = ?,`Descripcion` = ?,`Ano` = ? WHERE `Id` = ?;");
            SQL.setString(1, modeloMeses.getCodigo());
            SQL.setString(2, modeloMeses.getDescripcion());
            SQL.setInt(3, modeloMeses.getAno());
            SQL.setString(4, modeloMeses.getCodigo());
            SQL.setInt(5, modeloMeses.getId());
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

    public LinkedList<ModeloMeses> Read() {
        LinkedList<ModeloMeses> listModeloMeses = new LinkedList<ModeloMeses>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Codigo`,`Descripcion`,`Ano` FROM `meses`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloMeses modeloMeses = new ModeloMeses();
                modeloMeses.setId(res.getInt("id"));
                modeloMeses.setCodigo(res.getString("Codigo"));
                modeloMeses.setDescripcion(res.getString("Descripcion"));
                modeloMeses.setAno(res.getInt("Ano"));
                listModeloMeses.add(modeloMeses);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return listModeloMeses;
    }
}

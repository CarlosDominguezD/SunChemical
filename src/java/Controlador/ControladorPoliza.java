/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPoliza;
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
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorPoliza {

    public boolean Insert(ModeloPoliza modeloPoliza) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO `poliza`(`Nombre`,`Codigo`) VALUE (?,?);");
                SQL.setString(1, modeloPoliza.getNombre());
                SQL.setString(2, modeloPoliza.getCodigo());
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

    public boolean Update(ModeloPoliza modeloPoliza) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `poliza` SET `Nombre` = ? , `Codigo` = ? WHERE `Id` = ?;");
            SQL.setString(1, modeloPoliza.getNombre());
            SQL.setString(2, modeloPoliza.getCodigo());
            SQL.setInt(3, modeloPoliza.getId());
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

    public boolean Delete(ModeloPoliza modeloPoliza) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("DELETE FROM `poliza` WHERE `Id` = ?;");
            SQL.setInt(1, modeloPoliza.getId());
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

    public LinkedList<ModeloPoliza> Read() {
        LinkedList<ModeloPoliza> modelopoliza = new LinkedList<ModeloPoliza>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Nombre`,`Codigo`FROM `poliza`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloPoliza modeloPoliza = new ModeloPoliza();
                modeloPoliza.setId(res.getInt("id"));
                modeloPoliza.setNombre(res.getString("Nombre"));
                modeloPoliza.setCodigo(res.getString("Codigo"));
                modelopoliza.add(modeloPoliza);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return modelopoliza;
    }

    public ModeloPoliza Select(Integer CodigoBuscar) {
        ModeloPoliza modeloPoliza = new ModeloPoliza();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Nombre`,`Codigo`FROM `poliza` WHERE Id = ?");
            SQL.setInt(1, CodigoBuscar);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloPoliza.setId(res.getInt("id"));
                modeloPoliza.setNombre(res.getString("Nombre"));
                modeloPoliza.setCodigo(res.getString("Codigo"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en Select ControladorPoliza " + e);
        }
        return modeloPoliza;
    }

    public String Upload(String RutaDispo) {
        String resul = "False";
        Tools tools = new Tools();
        try
        {
            File file = new File(RutaDispo);
            int CanLine = tools.ContarArchi(file);
            List<ModeloPoliza> modeloPolizas = new ArrayList<ModeloPoliza>();
            modeloPolizas = GenerarArreglo(file, CanLine);
            int con = 0;
            ModeloPoliza mod = new ModeloPoliza();
            String estado = "True";
            for (ModeloPoliza modeloPoliza : modeloPolizas)
            {
                if (con != 0)
                {
                    mod = SelectCedula(modeloPoliza.getCodigo());
                    if (mod.getId() == 0)
                    {
                        if ("True".equals(estado))
                        {
                            boolean r = Insert(modeloPoliza);
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
            System.out.println(e);
        }

        return resul;
    }

    private List<ModeloPoliza> GenerarArreglo(File file, int CanLine) {
        List<ModeloPoliza> listModeloPolizas = new ArrayList<ModeloPoliza>();
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
                if (numtokens == 2)
                {
                    ModeloPoliza modeloPoliza = new ModeloPoliza();
                    modeloPoliza.setCodigo(stringTokenizer.nextToken());
                    modeloPoliza.setNombre(stringTokenizer.nextToken());
                    listModeloPolizas.add(modeloPoliza);
                }
            }
            brPerReader.close();
        } catch (IOException e)
        {
            System.out.println(e);
        }
        return listModeloPolizas;
    }

    public ModeloPoliza SelectCedula(String CodigoBuscar) {
        ModeloPoliza modeloPoliza = new ModeloPoliza();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT `Id`,`Nombre`,`Codigo`FROM `poliza` WHERE Codigo = ?");
            SQL.setString(1, CodigoBuscar);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloPoliza.setId(res.getInt("id"));
                modeloPoliza.setNombre(res.getString("Nombre"));
                modeloPoliza.setCodigo(res.getString("Codigo"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return modeloPoliza;
    }
    
    public ModeloPoliza SelectCedulaa(String Sql) {
        ModeloPoliza modeloPoliza = new ModeloPoliza();
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
                modeloPoliza.setId(res.getInt("id"));
                modeloPoliza.setNombre(res.getString("Nombre"));
//                modeloPoliza.setCodigoCorredor(res.getString("CodigoCorredor"));
//                modeloPoliza.setCodigoNomina(res.getString("CodigoNomina"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {

        }
        return modeloPoliza;
    }
}

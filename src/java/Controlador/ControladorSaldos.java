/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloSaldos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorSaldos {

    ModeloSaldos Select(int Id) {
        ModeloSaldos modeloSaldos = new ModeloSaldos();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            //SQL = con.prepareStatement("SELECT `Id`,`IdPersonas`,`Saldo`,`Debaja`,`IdPoliza`,`FechaBaja`,`UsuarioBaja` FROM `saldos` WHERE Id = ?");
            SQL = con.prepareStatement("SELECT `Id`,`Saldo`,`Debaja`,`FechaBaja`,`UsuarioBaja`,`idPoliza`,`idMes`,`valorDeBaja`FROM `saldos` WHERE Id = ?");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloSaldos.setId(res.getInt("Id"));
                //modeloSaldos.setIdPersonas(res.getInt("IdPersonas"));
                modeloSaldos.setSaldo(res.getInt("Saldo"));
                modeloSaldos.setDebaja(res.getString("Debaja"));
                //modeloSaldos.setIdPoliza(res.getInt("IdPoliza"));
                modeloSaldos.setFechaBaja(res.getString("FechaBaja"));
                modeloSaldos.setUsuarioBaja(res.getString("UsuarioBaja"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return modeloSaldos;
    }

    public ModeloSaldos Select(String Sql) {
        ModeloSaldos modeloSaldos = null;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;

        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloSaldos = new ModeloSaldos();
                modeloSaldos.setId(res.getInt("Id"));
                modeloSaldos.setIdPoliza(res.getInt("IdPoliza"));
                modeloSaldos.setIdPersona(res.getInt("IdPersona"));
                modeloSaldos.setIdMes(res.getInt("idMes"));
                modeloSaldos.setSaldo(res.getInt("Saldo"));
                modeloSaldos.setDebaja(res.getString("Debaja"));
                modeloSaldos.setFechaBaja(res.getString("FechaBaja"));
                modeloSaldos.setUsuarioBaja(res.getString("UsuarioBaja"));
                modeloSaldos.setValorDeBaja(res.getInt("valorDeBaja"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {

        }
        return modeloSaldos;
    }

    public boolean InsertarRegistro(String SqlInsertar) {
        boolean Insertado = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement(SqlInsertar);
                if (SQL.executeUpdate() > 0) {
                    Insertado = true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return Insertado;
    }

    public String SetBaja(String[] IdResultadoGetIdSaldos) {
        String resul = "false";
        ModeloSaldos modeloSaldos = new ModeloSaldos();
        for (String Id : IdResultadoGetIdSaldos) {
            modeloSaldos = Select(Integer.parseInt(Id));
            modeloSaldos.setDebaja("S");
            int saldo = modeloSaldos.getSaldo();
            int Opuesto = 0;
            if (saldo < 0) {
                Opuesto = Math.abs(saldo);
                modeloSaldos.setValorDeBaja(Opuesto);
                resul = Update(modeloSaldos);
            } else {
                Opuesto = saldo * (-1);
                modeloSaldos.setValorDeBaja(Opuesto);
                resul = Update(modeloSaldos);
            }
        }
        return resul;
    }

    private String Update(ModeloSaldos modeloSaldos) {
        String resul = "false";
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("UPDATE saldos SET Debaja = 'S', valorDeBaja = ? WHERE Id = ?");
//            SQL.setString(1, modeloSaldos.getNombre());
            SQL.setInt(1, modeloSaldos.getValorDeBaja());
            SQL.setInt(2, modeloSaldos.getId());
            if (SQL.executeUpdate() > 0) {
                resul = "true";
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return resul;
    }

}

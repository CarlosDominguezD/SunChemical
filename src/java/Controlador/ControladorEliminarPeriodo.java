/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloMeses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorEliminarPeriodo {

    ControladorMeses controladorMeses = new ControladorMeses();

    public String DeletePeriodo(HttpServletRequest request, HttpServletResponse response) {
        String resul = "false";
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            ModeloMeses modeloMeses;
            modeloMeses = controladorMeses.SelectMesAno(request.getParameter("mes"), request.getParameter("ano"));
            SQL = con.prepareStatement("DELETE FROM `resultados` WHERE `IdMeses` = ?;");
            SQL.setInt(1, modeloMeses.getId());
            if (SQL.executeUpdate() >= 0)
            {
                resul = "true";
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Delete " + e);
        }
        return resul;
    }

}

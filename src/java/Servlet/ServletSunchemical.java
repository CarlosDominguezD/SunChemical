/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.ControladorEliminarPeriodo;
import Controlador.ControladorMeses;
import Controlador.ControladorPersonas;
import Controlador.ControladorPersonasPoliza;
import Controlador.ControladorPoliza;
import Controlador.ControladorResultados;
import Controlador.ControladorSaldos;
import Modelo.ModeloResultados;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ServletSunchemical extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletSunchemical</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletSunchemical at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Accion = request.getParameter("accion");
        String res;
        ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
        switch (Accion)
        {
            case "EliminarPeriodo":
                ControladorEliminarPeriodo controladorEliminarPeriodo = new ControladorEliminarPeriodo();
                res = controladorEliminarPeriodo.DeletePeriodo(request, response);
                if ("true".equals(res))
                {
                    res = "Periodo Eliminado";
                }
                else
                {
                    res = "Error al eliminado el Periodo";
                }
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.getWriter().write(res);
//                request.setAttribute("espuesta", res);
//                processRequest(request, response);
                break;
            case "ConsultarPoliza":
                //ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
                res = controladorPersonasPoliza.SelectIdPersonas(request, response);
                PrintWriter pw = response.getWriter();
                pw.write(res);
                System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                break;
            case "Update":
                ControladorResultados controladorResultados = new ControladorResultados();
                ModeloResultados modeloResultados = new ModeloResultados();  
                
                modeloResultados.setAno(request.getParameter("idano"));
                modeloResultados.setDeduccion(Integer.valueOf(request.getParameter("iddeducionpoliza")));
                //modeloResultados.setDiferencia(0);
                //modeloResultados.setId(0);
                ControladorMeses controladorMeses = new ControladorMeses();
                modeloResultados.setIdMeses(controladorMeses.Select("SELECT `Id`,`Codigo`,`Descripcion`,`Ano` FROM `meses` WHERE `Descripcion` = '"+request.getParameter("idmes")+"' AND `Ano` = '"+request.getParameter("idano")+"'"));                
                ControladorPersonas controladorPersonas = new ControladorPersonas();
                modeloResultados.setIdPersona(controladorPersonas.Select(Integer.valueOf(request.getParameter("idempleado"))));
                
                modeloResultados.setIdPersona_Poliza(controladorPersonasPoliza.SelectIdPersonaPoliza(modeloResultados.getIdPersona().getId(),request.getParameter("idpoliza")));
                
                ControladorPoliza controladorPoliza = new ControladorPoliza();
                modeloResultados.setIdPoliza(controladorPoliza.Select(modeloResultados.getIdPersona_Poliza().getModeloPoliza().getId()));
                
//                ControladorSaldos controladorSaldos = new ControladorSaldos();
//                modeloResultados.setIdSaldos(controladorSaldos.Select(request.getParameter("idmes")));
                modeloResultados.setNotas("Ingreso de datos Manual");
                modeloResultados.setObservacion(request.getParameter("idobservacion"));
                //modeloResultados.setPago(0);
                //modeloResultados.setRealizado(res);
                //modeloResultados.setValorMes(0);
                if (controladorResultados.Registro(modeloResultados))
                {
                    res = "true";
                } else
                {
                    res = "false";
                }
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.getWriter().write(res);
                break;
        }

        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

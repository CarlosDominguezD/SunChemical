/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.ControladorGrupoPolizas;
import Controlador.ControladorPersonas;
import Controlador.ControladorPersonasPoliza;
import Controlador.ControladorPoliza;
import Modelo.ModeloPersonasPolizas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
public class PersonaPolizaServlet extends HttpServlet {

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
            out.println("<title>Servlet PersonaPolizaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PersonaPolizaServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        String Accion = request.getParameter("Accion");
        String Id = request.getParameter("Id");
        String IdPersona = request.getParameter("Persona");
        String IdPoliza = request.getParameter("Poliza");
        String ValorPoliza = request.getParameter("ValorPoliza");
        String Estado = request.getParameter("Estado");
        String FechaInicio = request.getParameter("FechaInicio");
        String FechaFin = request.getParameter("FechaFin");
        String Observacion = request.getParameter("Observacion");
        String CodigoPoliza = request.getParameter("Poliza");
        String CodigoBuscar = request.getParameter("CodigoBuscar");
        String respuesta = "false";
        if ("Guardar".equals(Accion))
        {
            ModeloPersonasPolizas modePersonasPolizas = new ModeloPersonasPolizas();
            ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
            ControladorPersonas controladorPersona = new ControladorPersonas();
            ControladorPoliza controladorPoliza = new ControladorPoliza();
            ControladorGrupoPolizas controladorGrupoPolizas = new ControladorGrupoPolizas();
            if ("".equals(Id))
            {
                modePersonasPolizas.setId(-1);
                modePersonasPolizas.setModeloPersonas(controladorPersona.Select(Integer.parseInt(IdPersona)));
                modePersonasPolizas.setModeloPoliza(controladorPoliza.Select(Integer.parseInt(IdPoliza)));
                modePersonasPolizas.setValorPoliza(ValorPoliza);
                modePersonasPolizas.setActivo(Estado);
                modePersonasPolizas.setFechaInicio(FechaInicio);
                modePersonasPolizas.setFechaFin(FechaFin);
                modePersonasPolizas.setObservacion(Observacion);
                //modePersonasPolizas.setCodigoPoliza(Integer.parseInt(CodigoPoliza));
                modePersonasPolizas.setCodigoPoliza(controladorGrupoPolizas.select("SELECT Id, IdPoliza, Descripcion, CodigoNomina, CodigoCorredor FROM grupo_poliza where Id =" + CodigoPoliza));
                if (controladorPersonasPoliza.Insert(modePersonasPolizas))
                {
                    respuesta = "true";
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/plain");
                    response.getWriter().write(respuesta);
                }
            } else
            {
                modePersonasPolizas.setId(Integer.valueOf(Id));
                modePersonasPolizas.setModeloPersonas(controladorPersona.Select(Integer.parseInt(IdPersona)));
                modePersonasPolizas.setModeloPoliza(controladorPoliza.Select(Integer.parseInt(IdPoliza)));
                modePersonasPolizas.setValorPoliza(ValorPoliza);
                modePersonasPolizas.setActivo(Estado);
                modePersonasPolizas.setFechaInicio(FechaInicio);
                modePersonasPolizas.setFechaFin(FechaFin);
                modePersonasPolizas.setObservacion(Observacion);
                //modePersonasPolizas.setCodigoPoliza(Integer.parseInt(CodigoPoliza));
                modePersonasPolizas.setCodigoPoliza(controladorGrupoPolizas.select("SELECT Id, IdPoliza, Descripcion, CodigoNomina, CodigoCorredor FROM grupo_poliza where Id =" + CodigoPoliza));
                if (controladorPersonasPoliza.Update(modePersonasPolizas))
                {
                    respuesta = "true";
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/plain");
                    response.getWriter().write(respuesta);
                }
            }
        }

        if ("Update".equals(Accion))
        {
            ModeloPersonasPolizas modePersonasPolizas = new ModeloPersonasPolizas();
            ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
            ControladorPersonas controladorPersona = new ControladorPersonas();
            ControladorPoliza controladorPoliza = new ControladorPoliza();
            Id = request.getParameter("IdModal");
            ValorPoliza = request.getParameter("ModalValorPoliza");
            Estado = request.getParameter("ModalEstado");
            FechaInicio = request.getParameter("ModalFechaInicio");
            FechaFin = request.getParameter("ModalFechaFin");
            Observacion = request.getParameter("ModalObservacion");

            IdPersona = request.getParameter("Persona");
            IdPoliza = request.getParameter("Poliza");
            CodigoPoliza = request.getParameter("Poliza");

            modePersonasPolizas.setId(Integer.valueOf(Id));
            modePersonasPolizas.setValorPoliza(ValorPoliza);
            modePersonasPolizas.setActivo(Estado);
            modePersonasPolizas.setFechaInicio(FechaInicio);
            modePersonasPolizas.setFechaFin(FechaFin);
            modePersonasPolizas.setObservacion(Observacion);
            if (controladorPersonasPoliza.Update(modePersonasPolizas))
            {
                respuesta = "true";
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.getWriter().write(respuesta);
            }
        }

        if ("Eliminar".equals(Accion))
        {
            //Id = request.getParameter(Id);
            ModeloPersonasPolizas modeloPersonasPolizas = new ModeloPersonasPolizas();
            ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
            modeloPersonasPolizas.setId(Integer.valueOf(Id));
            if (controladorPersonasPoliza.Delete(modeloPersonasPolizas))
            {
                respuesta = "true";
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.getWriter().write(respuesta);
            }
        }

//        if ("Buscar".equals(Accion))
//        {
//            ModeloPersonasPolizas modeloPersonasPolizas = new ModeloPersonasPolizas();
//            ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
//            modeloPersonasPolizas = controladorPersonasPoliza.Select(Integer.valueOf(CodigoBuscar));
//            request.setAttribute("modeloPersonas", modeloPersonasPolizas);
//
////            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Usuarios.jsp");
////            requestDispatcher.forward(request, response);
//        }

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.ControladorGrupoPolizas;
import Controlador.ControladorPoliza;
import Modelo.ModeloGrupoPolizas;
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
public class GrupoPolizaServlet extends HttpServlet {

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
            out.println("<title>Servlet GrupoPolizaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GrupoPolizaServlet at " + request.getContextPath() + "</h1>");
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
        String Id = request.getParameter("Id");
        String Accion = request.getParameter("Accion");
        String IdPoliza = request.getParameter("Poliza");
        String Descripcion = request.getParameter("Descripcion");
        String CodigoNomina = request.getParameter("CodigoNomina");
        String CodigoCorredor = request.getParameter("CodigoCorredor");
        String respuesta = "false";
        ModeloGrupoPolizas modeloGrupoPolizas = new ModeloGrupoPolizas();
        ControladorGrupoPolizas controladorGrupoPolizas = new ControladorGrupoPolizas();
        ControladorPoliza controladorPoliza = new ControladorPoliza();

        if ("Guardar".equals(Accion))
        {
            if ("".equals(Id))
            {
                //String IdPoliza = request.getParameter("Poliza");
                modeloGrupoPolizas.setId(-1);
                modeloGrupoPolizas.setIdPoliza(Integer.parseInt(IdPoliza));
                modeloGrupoPolizas.setDescripcion(Descripcion);
                modeloGrupoPolizas.setCodigoNomina(CodigoNomina);
                modeloGrupoPolizas.setCodigoCorredor(CodigoCorredor);
                modeloGrupoPolizas.setModeloPoliza(controladorPoliza.Select(Integer.parseInt(IdPoliza)));
                if (controladorGrupoPolizas.Insert(modeloGrupoPolizas))
                {
                    respuesta = "true";
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/plain");
                    response.getWriter().write(respuesta);
                }
            } else
            {
                //String IdPoliza = request.getParameter("Poliza");
                modeloGrupoPolizas.setId(Integer.parseInt(Id));
                modeloGrupoPolizas.setIdPoliza(Integer.parseInt(IdPoliza));
                modeloGrupoPolizas.setDescripcion(Descripcion);
                modeloGrupoPolizas.setCodigoNomina(CodigoNomina);
                modeloGrupoPolizas.setCodigoCorredor(CodigoCorredor);
                modeloGrupoPolizas.setModeloPoliza(controladorPoliza.Select(Integer.parseInt(IdPoliza)));
                if (controladorGrupoPolizas.Update(modeloGrupoPolizas))
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
            String IdModal = request.getParameter("IdModal");
            String IdModalIdPoliza = request.getParameter("ModalIdPoliza");
            String ModalDescripcion = request.getParameter("ModalDescripcion");
            String ModalCodigoNomina = request.getParameter("ModalCodigoNomina");
            String ModalCodigoCorredor = request.getParameter("ModalCodigoCorredor");
            String ModalNombrePoliza = request.getParameter("ModalNombrePoliza");

            modeloGrupoPolizas.setId(Integer.parseInt(IdModal));
            modeloGrupoPolizas.setIdPoliza(Integer.parseInt(IdModalIdPoliza));
            modeloGrupoPolizas.setDescripcion(ModalDescripcion);
            modeloGrupoPolizas.setCodigoNomina(ModalCodigoNomina);
            modeloGrupoPolizas.setCodigoCorredor(ModalCodigoCorredor);
            modeloGrupoPolizas.setModeloPoliza(controladorPoliza.Select(Integer.parseInt(IdModalIdPoliza)));
            if (controladorGrupoPolizas.Update(modeloGrupoPolizas))
            {
                respuesta = "true";
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.getWriter().write(respuesta);

            }
        }

        if ("Eliminar".equals(Accion))
        {
            modeloGrupoPolizas.setId(Integer.parseInt(Id));
            modeloGrupoPolizas.setIdPoliza(Integer.parseInt(IdPoliza));
            modeloGrupoPolizas.setDescripcion(Descripcion);
            modeloGrupoPolizas.setCodigoNomina(CodigoNomina);
            modeloGrupoPolizas.setCodigoCorredor(CodigoCorredor);
            modeloGrupoPolizas.setModeloPoliza(controladorPoliza.Select(Integer.parseInt(IdPoliza)));
            if (controladorGrupoPolizas.Delete(modeloGrupoPolizas))
            {
                respuesta = "true";
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.getWriter().write(respuesta);
            }

            //processRequest(request, response);
        }
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

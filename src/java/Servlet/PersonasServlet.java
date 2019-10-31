/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.ControladorPersonas;
import Modelo.ModeloPersonas;
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
public class PersonasServlet extends HttpServlet {

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
            out.println("<title>Servlet PersonasServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PersonasServlet at " + request.getContextPath() + "</h1>");
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
        String Codigo = request.getParameter("Codigo");
        String Nombre = request.getParameter("Nombre");
        String Apellido = request.getParameter("Apellido");
        String Estado = request.getParameter("Estado");
        String CodigoBuscar = request.getParameter("CodigoBuscar");
        String respuesta = "false";
        System.out.println(Id);
        System.out.println(Accion);
        System.out.println(Codigo);
        System.out.println(Nombre);
        System.out.println(Apellido);
        System.out.println(Estado);
        System.out.println(CodigoBuscar);
        if ("Guardar".equals(Accion))
        {
            ModeloPersonas modePersonas = new ModeloPersonas();
            ControladorPersonas controladorPersonas = new ControladorPersonas();
            if ("".equals(Id))
            {
                modePersonas.setId(-1);
                modePersonas.setCedula(Codigo);
                modePersonas.setNombre(Nombre);
                modePersonas.setApellidos(Apellido);
                modePersonas.setEstado(Estado);
                if (controladorPersonas.Insert(modePersonas))
                {
                    respuesta = "true";
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/plain");
                    response.getWriter().write(respuesta);
                }
            } else
            {
                modePersonas.setId(Integer.valueOf(Id));
                modePersonas.setCedula(Codigo);
                modePersonas.setNombre(Nombre);
                modePersonas.setApellidos(Apellido);
                modePersonas.setEstado(Estado);
                if (controladorPersonas.Update(modePersonas))
                {
                    respuesta = "true";
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/plain");
                    response.getWriter().write(respuesta);
                }
            }
        }

        if ("Eliminar".equals(Accion))
        {
            ModeloPersonas modeloPersonas = new ModeloPersonas();
            ControladorPersonas controladorPersonas = new ControladorPersonas();
            modeloPersonas.setId(Integer.valueOf(Id));
            modeloPersonas.setCedula(Codigo);
            modeloPersonas.setNombre(Nombre);
            modeloPersonas.setApellidos(Apellido);
            modeloPersonas.setEstado(Estado);
            if (controladorPersonas.Delete(modeloPersonas))
            {
                respuesta = "true";
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.getWriter().write(respuesta);
            }
        }

        if ("Buscar".equals(Accion))
        {
            ModeloPersonas modeloPersonas = new ModeloPersonas();
            ControladorPersonas controladorPersonas = new ControladorPersonas();
            modeloPersonas = controladorPersonas.Select(Integer.valueOf(CodigoBuscar));
            request.setAttribute("modeloPersonas", modeloPersonas);

//            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Usuarios.jsp");
//            requestDispatcher.forward(request, response);
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

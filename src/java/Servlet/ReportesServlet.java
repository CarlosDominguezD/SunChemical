/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import Controlador.ControladorReportes;
import Modelo.ModeloReportes;
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
public class ReportesServlet extends HttpServlet {

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
            out.println("<title>Servlet ReportesServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportesServlet at " + request.getContextPath() + "</h1>");
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
        String Nombre = request.getParameter("Nombre");
        String PlantillaSql = request.getParameter("PlantillaUrl");
        String Sql = request.getParameter("Sql");
        String respuesta = "false";
        String Accion = request.getParameter("Accion");
        System.out.println(Id);
        System.out.println(Nombre);
        System.out.println();
        System.out.println(Nombre);
        System.out.println(PlantillaSql);
        System.out.println(Sql);        
        if ("Guardar".equals(Accion))
        {
            Modelo.ModeloReportes modeloReportes = new ModeloReportes();
            ControladorReportes controladorReportes = new ControladorReportes();
            if ("".equals(Id))
            {
                modeloReportes.setId(-1);
                modeloReportes.setNombre(Nombre);
                modeloReportes.setPlantillaURL(PlantillaSql);
                modeloReportes.setSql(Sql);                
                if (controladorReportes.Insert(modeloReportes))
                {
                    respuesta = "true";
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/plain");
                    response.getWriter().write(respuesta);
                }
            } else
            {
                modeloReportes.setId(Integer.valueOf(Id));
                modeloReportes.setNombre(Nombre);
                modeloReportes.setPlantillaURL(PlantillaSql);
                modeloReportes.setSql(Sql);   ;
                if (controladorReportes.Update(modeloReportes))
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
            ModeloReportes modeloReportes = new ModeloReportes();
            ControladorReportes controladorReportes = new ControladorReportes();
            modeloReportes.setId(Integer.valueOf(Id));
            modeloReportes.setNombre(Nombre);
            modeloReportes.setPlantillaURL(PlantillaSql);
            modeloReportes.setSql(Sql);
            
            if (controladorReportes.Delete(modeloReportes))
            {
                respuesta = "true";
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.getWriter().write(respuesta);
            }
        }

        if ("Buscar".equals(Accion))
        {
            ModeloReportes modeloReportes = new ModeloReportes();
            ControladorReportes controladorReportes = new ControladorReportes();
            modeloReportes = controladorReportes.Select(Integer.valueOf(Id));
            request.setAttribute("modeloReportes", modeloReportes            );

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

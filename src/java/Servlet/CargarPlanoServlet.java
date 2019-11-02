/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.ControladorMeses;
import Controlador.ControladorResultados;
import Controlador.ControladorPersonas;
import Controlador.ControladorPersonasPoliza;
import Controlador.ControladorPoliza;
import Modelo.ModeloMeses;
import Modelo.ModeloResultados;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Carlos A Dominguez D
 */
@MultipartConfig
public class CargarPlanoServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "archivos";
    String respuesta = "false";

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if ("True".equals(respuesta)) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<h1> Archivo Cargado Exitosamente </h1>");
                out.println("<head>");
                out.println("<title>Plano Cargado</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h3> Plano Cargado</h3>");
                out.println("<li><a href=../../../SunChemical/CargarPlanos.jsp>Volver Cargar Planos</a></li>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<h1> Errores en la carga del plano </h1>");
                out.println("<head>");
                out.println("<title>Mensaje Error Plano</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h3>" + respuesta + "</h3>");
                out.println("<li><a href=../../../SunChemical/CargarPlanos.jsp>Volver Cargar Planos</a></li>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs1
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
//   @Override
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
        ControladorMeses controladorMeses = new ControladorMeses();
        ModeloMeses modeloMeses = new ModeloMeses();
        String Accion = request.getParameter("Accion");

        if ("Update".equals(Accion)) {
            ModeloResultados modeloResultados = new ModeloResultados();
            String idModal = request.getParameter("IdModal");
            String Deduccion = request.getParameter("ModalDeduccion");
            String Pago = request.getParameter("ModalPago");
            String Diferencia = request.getParameter("ModalDiferencia");
            String Observacion = request.getParameter("ModalObservacion");
            String DeBaja = request.getParameter("ModalDeBaja");
            //String idModalPersonPoliza = request.getParameter("ModalPersonaPoliza");
            String idMeses = request.getParameter("ModalIdMesFil");
            String idAno = request.getParameter("ModalAnoFil");
            String Notas = request.getParameter("ModalNotas");
            String IdPersona = request.getParameter("ModalIdPersona");
            String IdPoliza = request.getParameter("ModalIdPoliza");

            modeloResultados.setId(Integer.valueOf(idModal));
            modeloResultados.setDeduccion(Integer.valueOf(Deduccion));
            modeloResultados.setPago(Integer.valueOf(Pago));
            modeloResultados.setDiferencia(Integer.valueOf(Diferencia));
            modeloResultados.setObservacion(Observacion);
            ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
            //modeloResultados.setIdPersona_Poliza(controladorPersonasPoliza.Select(Integer.parseInt(idModalPersonPoliza)));
            ControladorPoliza controladorPoliza = new ControladorPoliza();
            modeloResultados.setIdPoliza(controladorPoliza.Select(Integer.parseInt(IdPoliza)));
            ControladorPersonas controladorPersonas = new ControladorPersonas();
            modeloResultados.setIdPersona(controladorPersonas.Select(Integer.parseInt(IdPersona)));
            modeloMeses = controladorMeses.SelectMesAno(idMeses, idAno);
            modeloResultados.setIdMeses(controladorMeses.Select(modeloMeses.getId()));
            modeloResultados.setNotas(Notas);

            ControladorResultados controladorResultados = new ControladorResultados();
            if (controladorResultados.Registro(modeloResultados)) {
                respuesta = "true";
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.getWriter().write(respuesta);
            }
        } else {
            long tamanorequies = request.getPart("archivo").getSize();
            System.out.println(tamanorequies);

            Part arch = request.getPart("archivo");
            //String nombre = arch.getName();
            String formato = request.getParameter("Formato");
            //InputStream is = arch.getInputStream();
            String RutaDispo = "C:\\Zred\\SunChemical\\" + formato + " " + ObtenerFecha() + ".txt";
            File f = new File(RutaDispo);
            if (f.exists())
            {
                f.delete();
            }
            try (InputStream input = arch.getInputStream())
            {
                Files.copy(input, f.toPath()); 
                input.close();
                //f.exists();                
            }
            catch(IOException e)
            {
                System.out.println(e);                
            }
            
            
            
//            FileOutputStream ous = new FileOutputStream(f);
//            int dato = is.read();
//            while (dato != -1) {
//                ous.write(dato);
//                dato = is.read();
//            }
//            ous.close();
//            is.close();
            String total = "0";
            String totalTrue = "0";
            String totalFalse = "0";

            if ("Personas".equals(formato)) {
                ControladorPersonas controladorPersonas = new ControladorPersonas();
                respuesta = controladorPersonas.Upload(RutaDispo);

            }
            if ("Poliza".equals(formato)) {
                ControladorPoliza controladorPoliza = new ControladorPoliza();
                respuesta = controladorPoliza.Upload(RutaDispo);
            }
            if ("Cobros".equals(formato)) {
                ControladorResultados controladorResultados = new ControladorResultados();
                respuesta = controladorResultados.Upload(RutaDispo);
            }
            if ("Deduccion".equals(formato)) {
                ControladorResultados controladorResultados = new ControladorResultados();
                respuesta = controladorResultados.UploadNomi(RutaDispo);
            }
            request.setAttribute("espuesta", respuesta);
            processRequest(request, response);




//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            response.getWriter().write(respuesta);
//            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/CargarPlanos.jsp");
//            requestDispatcher.forward(request, response);
        }

//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/plain");
//        response.getWriter().write(respuesta);
//        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/CargarPlanos.jsp");
//        requestDispatcher.forward(request, response);
    }

    public static String ObtenerFecha() {
        Calendar fecHor = Calendar.getInstance();
        String dia = Integer.toString(fecHor.get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(fecHor.get(Calendar.MONDAY) + 1);
        String año = Integer.toString(fecHor.get(Calendar.YEAR));
        String fh = (dia + "-" + mes + "-" + año);
        return fh;
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

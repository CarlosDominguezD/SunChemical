/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.ConexionBDMySql;
import Controlador.ControladorExcel;
import Controlador.ControladorMeses;
import Controlador.ControladorPersonas;
import Controlador.ControladorPoliza;
import Controlador.ControladorResultados;
import Controlador.ControladorSaldos;
import Modelo.ModeloMeses;
import Modelo.ModeloPersonas;
import Modelo.ModeloPoliza;
import Modelo.ModeloResultados;
import static Servlet.EjecutarReporteServlet.DATA_SHEET;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ResultadosServlet extends HttpServlet {

    public String TEMPLATE_PATH = "./reports/templates/";
    public String TEMPORAL_PATH = "./reports/temporal/";
    //public String GENERATED_PATH = "./reports/deploy/";
    public String GENERATED_PATH = "";
    public String EXCEL_SUFFIX = ".xls";
    Integer IDMESES = 1;

    public static final String DATA_SHEET = "datos";
    private static final String DEFAULT_TEMPLATE_FILE = "plantilla_generica.xls";
    public static Integer DEFAULT_TABLE_REPORTID;

    private boolean hideSheet = true;

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
            out.println("<title>Servlet ResultadosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResultadosServlet at " + request.getContextPath() + "</h1>");
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
        String Accion = request.getParameter("Filtrar");
        String Formato = request.getParameter("Formato");
        String IdPersona = request.getParameter("Persona");
        String IdPoliza = request.getParameter("Poliza");
        String IdMes = request.getParameter("Mes");
        String Conector = request.getParameter("Conector");
        String AnoConsultar = request.getParameter("AnoConsultar");
        String Operador1 = request.getParameter("Operador1");
        String ValorConsultar1 = request.getParameter("ValorConsultar1");
        String Operador2 = request.getParameter("Operador2");
        String ValorConsultar2 = request.getParameter("ValorConsultar2");

        String Diferencia = request.getParameter("Diferencia");
        LinkedList<ModeloResultados> listModeloResultadoses = new LinkedList<ModeloResultados>();
        ControladorResultados controladorResultados = new ControladorResultados();
        LinkedList<ModeloPoliza> listaPolizas = new LinkedList<ModeloPoliza>();
        ModeloMeses modeloMeses = new ModeloMeses();
        ControladorMeses controladorMeses = new ControladorMeses();
        ModeloPersonas modeloPersonas = new ModeloPersonas();
        ControladorPersonas controladorPersonas = new ControladorPersonas();
        ModeloPoliza modeloPoliza = new ModeloPoliza();
        ControladorPoliza controladorPoliza = new ControladorPoliza();
        /*
         Enviamos los datos al modelo para que nos munistre el modelo
         */
        if ("Filtrar".equals(Accion))
        {
            modeloMeses = controladorMeses.SelectMesAno(IdMes, AnoConsultar);
            IDMESES = modeloMeses.getId();
            String Filtro = "";
            if (IdPersona == null)
            {
                Filtro = Filtro + "Empleados: Todos > ";
            } else
            {
                modeloPersonas = controladorPersonas.Select(Integer.parseInt(IdPersona));
                Filtro = Filtro +  " Empleado: "+modeloPersonas.getNombre()+" "+modeloPersonas.getApellidos()+" > ";
            }
            if (IdPoliza == null)
            {
                Filtro = Filtro + " Polizas: Todas > ";
            }
            else
            {
                modeloPoliza = controladorPoliza.Select(Integer.parseInt(IdPoliza));
                Filtro = Filtro +  " Poliza: "+modeloPoliza.getNombre()+" > ";                
            }
            if (!"".equals(AnoConsultar))
            {
                Filtro = Filtro + " Año: " +AnoConsultar+" > ";
            }   
            
            if (!"".equals(IdMes))
            {
                Filtro = Filtro + " Mes: "+modeloMeses.getDescripcion()+" > ";
            }
            if (!"".equals(IdMes))
            {
                Filtro = Filtro + " Operador: "+Operador1+" ";
            }
            if (ValorConsultar1 == null)
            {
                Filtro = Filtro + " Valor: 0 > ";
            }
            else
            {
                Filtro = Filtro + " Valor: "+ValorConsultar1;
            }
           
            listModeloResultadoses = controladorResultados.SelectFiltro(IdPersona, IdPoliza, String.valueOf(modeloMeses.getId()), AnoConsultar, Operador1, ValorConsultar1, Conector, Operador2, ValorConsultar2);
            request.setAttribute("listModeloResultadoses", listModeloResultadoses);
            request.setAttribute("IdMes", IdMes);
            request.setAttribute("IdAno", AnoConsultar);
            request.setAttribute("Fil", Filtro);
            RequestDispatcher requestDispacher = getServletContext().getRequestDispatcher("/Resultados.jsp");
            requestDispacher.forward(request, response);

        }
        if ("SumaResultados".equals(Accion))
        {
            //modeloMeses = controladorMeses.SelectMesAno(Integer.parseInt(IdMes), AnoConsultar);
            modeloMeses = controladorMeses.SelectMesAno(IdMes, AnoConsultar);
            IDMESES = modeloMeses.getId();
            listaPolizas = controladorResultados.SumaPolizasPorMes(modeloMeses);
            request.setAttribute("ListaPoliza", listaPolizas);
            RequestDispatcher requestDispacher = getServletContext().getRequestDispatcher("/Calculos.jsp");
            requestDispacher.forward(request, response);
        }
        if ("Baja".equals(request.getParameter("Accion")))
        {
            String[] IdResultadoGetIdSaldos = request.getParameterValues("ResultadoGetIdSaldos");
            ControladorSaldos controladorSaldos = new ControladorSaldos();
            String debaja = controladorSaldos.SetBaja(IdResultadoGetIdSaldos);
            request.setAttribute("ListaPoliza", listaPolizas);
            RequestDispatcher requestDispacher = getServletContext().getRequestDispatcher("/Resultados.jsp");
            requestDispacher.forward(request, response);
        }

        if ("BtnExport".equals(request.getParameter("BtnExport")))
        {
            //modeloMeses = controladorMeses.SelectMesAno(IdMes, AnoConsultar);
            String[] NombrePolizas = request.getParameterValues("NombrePolizas");
            String[] ValoresPolizas = request.getParameterValues("ValoresPolizas");
            String TotalCalculado = request.getParameter("TotalCalculado");
            String TotalSap = request.getParameter("TotalSap");
            String DiferenciaCalculos = request.getParameter("Diferencia");
            String Observacion = request.getParameter("Observacion");
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < NombrePolizas.length; i++)
            {
                String NomPoliza = NombrePolizas[i];
                String ValPoliza = ValoresPolizas[i];
                map.put(NomPoliza, ValPoliza);
            }
//            String SQLReporte = "SELECT pol.Nombre AS 'POLIZA ' ,perpol.Observacion AS 'OBSERVACION',per.Cedula AS 'NIT ',"
//                    + "CONCAT(per.Apellidos, ' ', per.Nombre) AS 'EMPLEADO ',"
//                    + "mes.Descripcion AS 'MES', "
//                    + "mes.Ano AS 'ANO', "
//                    + "res.Deduccion AS 'DEDUCCION ', "
//                    + "res.Pago AS 'PAGO ', "
//                    + "sal.saldo AS 'DIFERENCIA ', "
//                    + "sal.valorDeBaja AS 'DE BAJA', "
//                    + "res.Notas AS 'NOTAS ', "
//                    + "res.Observacion AS 'MOVIMIENTO ', "
//                    + "perpol.ValorPoliza AS 'COBRO MENSUAL', "
//                    + "'"+map.get("SALUD")+"' AS 'SALUD', "
//                    + "'"+map.get("VEHICULO")+"' AS 'VEHICULO', "
//                    + "'"+map.get("EMI")+"' AS 'EMI', "
//                    + "'"+map.get("VIDA")+"' AS 'VIDA', "
//                    + "'"+map.get("HOGAR")+"' AS 'HOGAR', "
//                    + "'"+map.get("LOS OLIVOS")+"' AS 'FUNERARIA', "
//                    + "'"+TotalCalculado+"' AS 'TOTAL', "
//                    + "'"+TotalSap+"' AS 'SAP', "
//                    + "'"+DiferenciaCalculos+"' AS 'DIFERENCIA', "
//                    + "'"+Observacion+"' AS 'COMENTARIO' "
//                    + " FROM resultados res "
//                    + "join personas_poliza perpol on (perpol.id = res.IdPersona_Poliza)"
//                    + "join personas per on (per.id = perpol.IdPersonas) "
//                    + "join saldos sal on (res.IdSaldos = sal.id) "
//                    + "join poliza pol on (pol.id = perpol.IdPoliza)"
//                    + "join meses mes on (mes.id = res.IdMeses) WHERE mes.id <= " + IDMESES;

            String SQLReporte = "SELECT "
                    + "pol.Nombre AS 'POLIZA ', "
                    + "GROUP_CONCAT(perpol.Observacion SEPARATOR ' + ') AS 'OBSERVACION', "
                    + "per.Cedula AS 'NIT ', "
                    + "CONCAT(per.Apellidos, ' ', per.Nombre) AS 'EMPLEADO ', "
                    //                    + "CONCAT(mes.Descripcion, '-', mes.Ano) AS 'MES', "
                    + "mes.Descripcion AS 'MES', "
                    + "mes.Ano AS 'ANO', "
                    + "res.Deduccion AS 'DEDUCCION ', "
                    + "res.Pago AS 'PAGO ', "
                    + "sal.saldo AS 'DIFERENCIA ', "
                    + "sal.valorDeBaja AS 'DE BAJA', "
                    + "res.Notas AS 'NOTAS ', "
                    + "res.Observacion AS 'MOVIMIENTO ', "
                    + "SUM(perpol.ValorPoliza) AS 'COBRO MENSUAL', "
                    + "'" + map.get("SALUD") + "' AS 'SALUD', "
                    + "'" + map.get("VEHICULO") + "' AS 'VEHICULO', "
                    + "'" + map.get("EMI") + "' AS 'EMI', "
                    + "'" + map.get("VIDA") + "' AS 'VIDA', "
                    + "'" + map.get("HOGAR") + "' AS 'HOGAR', "
                    + "'" + map.get("LOS OLIVOS") + "' AS 'FUNERARIA', "
                    + "'" + TotalCalculado + "' AS 'TOTAL', "
                    + "'" + TotalSap + "' AS 'SAP', "
                    + "'" + DiferenciaCalculos + "' AS 'DIFERENCIA', "
                    + "'" + Observacion + "' AS 'COMENTARIO' "
                    + "FROM resultados res "
                    + "LEFT OUTER join personas_poliza perpol on (perpol.IdPoliza = res.IdPoliza and perpol.IdPersonas = res.IdPersona) "
                    + "join personas per on (per.id = res.IdPersona) "
                    + "join saldos sal on (res.IdSaldos = sal.id) "
                    + "join poliza pol on (pol.id = res.IdPoliza) "
                    + "join meses mes on (mes.id = res.IdMeses) "
                    + "WHERE mes.id <= " + IDMESES
                    + " group by res.id";

            try
            {

                //String UrlArchivo = "C:\\Users\\Carlos A Dominguez D\\GlasFish\\glassfish\\domains\\GlassFish\\config\\SunChemical\\Informe.xls";//request.getParameter("PlantillaUrl");
                String UrlArchivo = "C:\\Zred\\SunChemical\\MacroSistemaSeguros.xls";//request.getParameter("PlantillaUrl");                
                String newQuery = SQLReporte;
                ControladorExcel controladorExcel = new ControladorExcel();
                String archivo = controladorExcel.GenerarExcel(UrlArchivo, newQuery);
                downloadFile(response, archivo);
            } catch (SQLException ex)
            {
                Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex)
            {
                Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void downloadFile(HttpServletResponse response, String filePath)
            throws ServletException, IOException {

        File fileToDownload = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(fileToDownload);

        ServletOutputStream out = response.getOutputStream();
        String mimeType = new MimetypesFileTypeMap().getContentType(filePath);

        response.setContentType(mimeType);
        response.setContentLength(fileInputStream.available());
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + fileToDownload.getName() + "\"");

        int c;
        while ((c = fileInputStream.read()) != -1)
        {
            out.write(c);
        }
        out.flush();
        out.close();
        fileInputStream.close();
    }

//    public LinkedList<LinkedHashMap<String, Object>> resultSet2ContextList(ResultSet rs) throws Exception {
//        LinkedList<LinkedHashMap<String, Object>> records = new LinkedList<LinkedHashMap<String, Object>>();
//        while (rs.next())
//        {
//            LinkedHashMap<String, Object> record = new LinkedHashMap<String, Object>();
//            ResultSetMetaData rsmd = rs.getMetaData();
//            for (int col = 1; col <= rsmd.getColumnCount(); col++)
//            {
//                record.put(rsmd.getColumnLabel(col), rs.getObject(col));
//            }
//            records.add(record);
//        }
//        return records;
//    }
//
//    private String generateReport(String plantilla, String reportName, List<LinkedHashMap<String, Object>> records) throws Exception {
//        String generatedFileName = "";
//        String generatedFullFileName = "";
//
//        long started = new Date().getTime();
//        File datosXLSFile;
//
//        try
//        {
//            Workbook wb;
//
//            byte[] dataFile = getFileData(plantilla);
//            ByteArrayInputStream file = new ByteArrayInputStream(dataFile);
//            File tempFile = File.createTempFile("reportes", null);
//            FileOutputStream fileStreamB = new FileOutputStream(tempFile);
//
//            int line = file.read();
//
//            while (line != -1)
//            {
//                fileStreamB.write(line);
//                line = file.read();
//            }
//            fileStreamB.close();
//            file.close();
//
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String dateWithFormat = format.format(started);
//
//            String path = getRealPath(GENERATED_PATH) + "/";
//            if (path.startsWith("null"))
//            {
//                path = "";
//            }
//
//            generatedFullFileName = path + reportName + "_" + dateWithFormat + EXCEL_SUFFIX;
//            generatedFileName = GENERATED_PATH + reportName + "_" + dateWithFormat + EXCEL_SUFFIX;
//
//            datosXLSFile = new File(generatedFullFileName);
//
//            if (datosXLSFile.exists())
//            {
//                datosXLSFile = new File(generatedFullFileName);
//            }
//
//            try
//            {
//                if (EXCEL_SUFFIX.endsWith("x"))
//                {
//                    wb = new XSSFWorkbook(new FileInputStream(tempFile));
//                } else
//                {
//                    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(tempFile));
//                    wb = new HSSFWorkbook(fs);
//                }
//            } catch (IOException e)
//            {
//                if (EXCEL_SUFFIX.endsWith("x"))
//                {
//                    wb = new XSSFWorkbook();
//                } else
//                {
//                    wb = new HSSFWorkbook();
//                }
//            }
//            //Se crea la hoja de datos "hoja1"
//            createSheet(DATA_SHEET, records, wb);
//            FileOutputStream fileOut = new FileOutputStream(datosXLSFile);
//            wb.write(fileOut);
//            fileOut.close();
//        } catch (Exception e)
//        {
//            System.err.println("Error al Tratar de Generar el Reporte. Causa: " + e.getMessage());
//            throw e;
//        }
//        System.gc();
//        return generatedFileName;
//    }
//
//    @SuppressWarnings("deprecation")
//    private void createSheet(String sheetName, List<LinkedHashMap<String, Object>> records, Workbook wb) throws Exception {
//        try
//        {
//            if (records.size() == 0)
//            {
//                return;
//            }
//
//            //Se crea la hoja con el nombre de hoja almacenado en la BD
//            Sheet sheet = wb.getSheet(sheetName);
//            if (sheet == null)
//            {
//                sheet = wb.createSheet(sheetName);
//            } else
//            {
//                wb.removeSheetAt(wb.getSheetIndex(sheetName));
//                sheet = wb.createSheet(sheetName);
//            }
//
//            Cell celltemp;
//            Set<String> columnNames = (Set<String>) records.get(0).keySet();
//            int colNum = 0;
//            Row row = sheet.createRow(colNum);
//            for (String columnName : columnNames)
//            {
//                celltemp = row.createCell((short) (colNum++));
//                celltemp.setCellValue(columnName.toUpperCase());
//            }
//            int rowCount = 1;
//            for (HashMap<String, Object> record : records)
//            {
//                row = sheet.createRow(rowCount);
//                int cellNum = 0;
//                for (String columnName : columnNames)
//                {
//                    celltemp = row.createCell((short) (cellNum++));
//                    Object var = record.get(columnName);
//                    if (var instanceof BigDecimal)
//                    {
//                        celltemp.setCellValue(((BigDecimal) var).doubleValue());
//                    } else if (var instanceof Calendar)
//                    {
//                        celltemp.setCellValue((Calendar) var);
//                    } else if (var instanceof Date)
//                    {
//                        celltemp.setCellValue((Date) var);
//                    } else if (var instanceof Long)
//                    {
//                        celltemp.setCellValue((Long) var);
//                    } else if (var instanceof Integer)
//                    {
//                        celltemp.setCellValue((Integer) var);
//                    } else if (var instanceof Double)
//                    {
//                        celltemp.setCellValue((Double) var);
//                    } else
//                    {
//                        celltemp.setCellValue((String) var);
//                    }
//                }
//                rowCount++;
//            }
//
//            if (hideSheet)
//            {
//                wb.setSheetHidden(wb.getSheetIndex(sheetName), true);
//            }
//        } catch (Exception e)
//        {
//            System.err.println("Error al Tratar de Generar el Reporte. Causa: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static byte[] getFileData(String fileName) throws Exception {
//        File file = new File(fileName);
//        byte[] data = new byte[(int) file.length()];
//        FileInputStream fis = new FileInputStream(file);
//        fis.read(data);
//        fis.close();
//        return data;
//    }
//
//    @SuppressWarnings("deprecation")
//    public static String getRealPath(String virtualPath) throws Exception {
//        HttpServletRequest req = null;
//        if (req == null)
//        {
//            return "";
//        }
//        return req.getRealPath(virtualPath);
//    }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloGrupoPolizas;
import Modelo.ModeloMeses;
import Modelo.ModeloPersonasPolizas;
import Modelo.ModeloPoliza;
import Modelo.ModeloResultados;
import Modelo.ModeloSaldos;
import Tools.Tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorResultados {

//VALIDA ARCHIVO PLANO DE FACTURACION
    public String Upload(String RutaDispo) {
        String resul = "False";
        Tools tools = new Tools();
        String Errores = "";
        try {
            File file = new File(RutaDispo);
            int CanLine = tools.ContarArchi(file);
            BufferedReader brPerReader = null;
            brPerReader = new BufferedReader(new FileReader(file));
            String linea;
            while (brPerReader.ready()) {
                linea = brPerReader.readLine();
                StringTokenizer stringTokenizer = new StringTokenizer(linea, ";");
                int numtokens = stringTokenizer.countTokens();
                if (numtokens == 5) {

                    String Identificacion_Plano = stringTokenizer.nextToken();
                    if (!Identificacion_Plano.contains("Cedula")) {
                        String NumeroPoliza_Plano = stringTokenizer.nextToken();
                        String Cobro_Plano = stringTokenizer.nextToken();
                        String Mes_Plano = stringTokenizer.nextToken();
                        String Ano_Plano = stringTokenizer.nextToken();

                        //OBTENGO EL ID DE PERSONA_POLIZA CON EL CODIGO DE LA POLIZA DEL PLANO                    
                        ModeloPersonasPolizas modeloPersonasPolizas = new ModeloPersonasPolizas();
                        ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
                        modeloPersonasPolizas = controladorPersonasPoliza.SelectIdPersonaIdPoliza(Identificacion_Plano, NumeroPoliza_Plano);

                        //SI modeloPersonasPolizas ESTA VACIO ES debido a que la persona no tiene asignada dicha poliza
                        if (modeloPersonasPolizas == null) {
                            Errores = Errores + "<br>" + " La Persona Con Identificacion: " + Identificacion_Plano + " No Tiene Asignada la Poliza No: " + NumeroPoliza_Plano + " Cobro: " + Cobro_Plano;
                        } else {
                            //SI modeloPersonasPolizas tiene vacio el modelo de persona es porque la persona no existe en el sistema
                            if (modeloPersonasPolizas.getModeloPersonas() == null) {
                                Errores = Errores + "<br>" + " La Persona Con identificacion: " + Identificacion_Plano + " No Existe en el Sistema";
                            } else {
                                //SI modeloPersonasPolizas tiene vacio el modelo poliza es porque la poliza no existe en el sistema
                                if (modeloPersonasPolizas.getModeloPoliza() == null) {
                                    Errores = Errores + "<br>" + " El codigo del plano : " + NumeroPoliza_Plano + " No Existe en el Sistema";
                                    //RETORNE ALGO INFORMANDO AL USUARIO QUE HAY CODIGOS IN EXISTENTES
                                }
                            }
                        }
                        //OBTENGO EL ID DEL MES SEGUN LO QUE VIENE DEL PLANO                    
                        ModeloMeses modeloMeses = new ModeloMeses();
                        ControladorMeses controladorMeses = new ControladorMeses();
                        modeloMeses = controladorMeses.SelectMesAno(Mes_Plano, Ano_Plano);

                        //SI modeloMeses ESTA VACIO ES DEBIDO A QUE EL MES CON EL AÑO NO ESTAN CREADOS EN LA TABLA MESES
                        if (modeloMeses == null) {
                            //RETORNE ALGO INFORMANDO AL USUARIO QUE HAY MESES IN EXISTENTES
                            Errores = Errores + "<br>" + " Valide el Mes: " + Mes_Plano + " y el año: " + Ano_Plano;
                        }
                    }
                }
            }
            brPerReader.close();
            if (Errores.contentEquals("")) {
                resul = CargaPlanoCorredorValidado(RutaDispo);
            } else {
                return Errores;
            }

        } catch (IOException e) {
        }

        return resul;
    }

    //CARGA ARCHIVO PLANO DE FACTURACION
    public String CargaPlanoCorredorValidado(String RutaDispo) {
        String resul = "False";
        Tools tools = new Tools();
        try {
            File file = new File(RutaDispo);
            int CanLine = tools.ContarArchi(file);
            BufferedReader brPerReader = null;
            brPerReader = new BufferedReader(new FileReader(file));
            String linea;
            while (brPerReader.ready()) {
                linea = brPerReader.readLine();
                StringTokenizer stringTokenizer = new StringTokenizer(linea, ";");
                int numtokens = stringTokenizer.countTokens();
                if (numtokens == 5) {

                    String Identificacion_Plano = stringTokenizer.nextToken();
                    if (!Identificacion_Plano.contains("Cedula")) {
                        String NumeroPoliza_Plano = stringTokenizer.nextToken();
                        String Cobro_Plano = stringTokenizer.nextToken();
                        String Mes_Plano = stringTokenizer.nextToken();
                        String Ano_Plano = stringTokenizer.nextToken();

                        //OBTENGO EL ID DE PERSONA_POLIZA CON EL CODIGO DE LA POLIZA DEL PLANO                    
                        ModeloPersonasPolizas modeloPersonasPolizas = new ModeloPersonasPolizas();
                        ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
                        modeloPersonasPolizas = controladorPersonasPoliza.SelectIdPersonaIdPoliza(Identificacion_Plano, NumeroPoliza_Plano);

//                        //SI modeloPersonasPolizas ESTA VACIO ES DEBIDO A QUE EL CODIGO DE LA POLIZA DEL PLANO NO EXISTE EN PERSONA_POLIZA
//                        if (modeloPersonasPolizas == null) {
//                            return resul = "Existe un problema en la linea: " + linea + " con la identificacion: " + Identificacion_Plano + " o con el codigo de la poliza No: "
//                                    + NumeroPoliza_Plano + " Valide la informacion";
//                            //RETORNE ALGO INFORMANDO AL USUARIO QUE HAY CODIGOS IN EXISTENTES
//                        }
                        //OBTENGO EL ID DEL MES SEGUN LO QUE VIENE DEL PLANO                    
                        ModeloMeses modeloMeses = new ModeloMeses();
                        ControladorMeses controladorMeses = new ControladorMeses();
                        modeloMeses = controladorMeses.SelectMesAno(Mes_Plano, Ano_Plano);

//                        //SI modeloMeses ESTA VACIO ES DEBIDO A QUE EL MES CON EL AÑO NO ESTAN CREADOS EN LA TABLA MESES
//                        if (modeloMeses == null) {
//                            //RETORNE ALGO INFORMANDO AL USUARIO QUE HAY MESES IN EXISTENTES
//                            return resul = "Existe un problema en la linea:" + linea + " debe Validar el mes y año a cargar";
//                        }
                        //VALIDA QUE EN LA TABLA DE RESULTADOS ESTE CARGADO ESE IDPERSONA_POLIZA Y EL MES
                        ModeloResultados modeloResultados = new ModeloResultados();
                        modeloResultados = Resultados(modeloPersonasPolizas.getModeloPoliza().getId(), modeloMeses.getId(), modeloPersonasPolizas.getModeloPersonas().getId());

                        // SI TIENE DATOS, O NO ESTA VACIO ES PORQUE YA SE CARGO ESE MES PARA ESA POLIZA
                        if (modeloResultados != null) {

                            // RETORNA ALGO INFORMANDO QUE YA HAY UN MES CARGADO CON LA INFORMACION DE ESA POLIZA
                            Integer Pago = modeloResultados.getPago();
                            modeloResultados.setPago(Integer.valueOf(Cobro_Plano) + Pago);

                            String SqlUpdate = "UPDATE "
                                    + "`resultados` SET "
                                    + "`Pago` = " + modeloResultados.getPago()
                                    + " WHERE `Id` = " + modeloResultados.getId();

                            if (Update(SqlUpdate)) {
                                Registro(modeloResultados);
                                resul = "True";
                            } else {
                                resul = "False";
                            }

                        } else {
                            //INSERTA LA INFORMACION DEL PLANO
                            modeloResultados = new ModeloResultados();
                            //modeloResultados.setIdPersona_Poliza(modeloPersonasPolizas);
                            modeloResultados.setIdMeses(modeloMeses);
                            modeloResultados.setPago(Integer.valueOf(Cobro_Plano));
                            modeloResultados.setIdPoliza(modeloPersonasPolizas.getModeloPoliza());
                            modeloResultados.setIdPersona(modeloPersonasPolizas.getModeloPersonas());
                            modeloResultados.setAno(Ano_Plano);
                            if (Insert(modeloResultados)) {
                                modeloResultados = Resultados(modeloPersonasPolizas.getModeloPoliza().getId(), modeloMeses.getId(), modeloPersonasPolizas.getModeloPersonas().getId());
                                //modeloResultados.setDeduccion(0);
                                Registro(modeloResultados);
                                resul = "True";
                            } else {
                                resul = "False";
                            }
                        }
                    }
                }
            }
            brPerReader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            resul = "Error";
        }

        return resul;
    }

    public String UploadNomi(String RutaDispo) {
        String Retorno = "";
        Retorno = CargarXLS(RutaDispo, "JULIO", "2019", false);
        return Retorno;
    }

    public String UploadNomi1(String RutaDispo) {

        String resul = "False";
        String Errores = "";

        Tools tools = new Tools();
        try {
            File file = new File(RutaDispo);
            int CanLine = tools.ContarArchi(file);
            BufferedReader brPerReader = null;
            brPerReader = new BufferedReader(new FileReader(file));

            String linea;
            while (brPerReader.ready()) {
                linea = brPerReader.readLine();
                StringTokenizer stringTokenizer = new StringTokenizer(linea, ";");
                int numtokens = stringTokenizer.countTokens();
                if (numtokens == 5) {

                    String Identificacion_Plano = stringTokenizer.nextToken();
                    if (!Identificacion_Plano.contains("Cedula")) {
                        String NumeroPoliza_Plano = stringTokenizer.nextToken();
                        String Cobro_Plano = stringTokenizer.nextToken();
                        String Mes_Plano = stringTokenizer.nextToken();
                        String Ano_Plano = stringTokenizer.nextToken();

                        //OBTENGO EL ID DE PERSONA_POLIZA CON EL CODIGO DE LA POLIZA DEL PLANO                    
                        ModeloPersonasPolizas modeloPersonasPolizas = new ModeloPersonasPolizas();
                        ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
                        modeloPersonasPolizas = controladorPersonasPoliza.SelectIdPersonaIdPolizaNomina(Identificacion_Plano, NumeroPoliza_Plano);

                        //SI modeloPersonasPolizas ESTA VACIO ES debido a que la persona no tiene asignada dicha poliza
                        if (modeloPersonasPolizas == null) {
                            Errores = Errores + "<br>" + " La Persona Con Identificacion: " + Identificacion_Plano + " No Tiene Asignada la Poliza No: " + NumeroPoliza_Plano;
                        } else {
                            //SI modeloPersonasPolizas tiene vacio el modelo de persona es porque la persona no existe en el sistema
                            if (modeloPersonasPolizas.getModeloPersonas() == null) {
                                Errores = Errores + "<br>" + " La Persona Con identificacion: " + Identificacion_Plano + " No Existe en el Sistema";
                            } else {
                                //SI modeloPersonasPolizas tiene vacio el modelo poliza es porque la poliza no existe en el sistema
                                if (modeloPersonasPolizas.getModeloPoliza() == null) {
                                    Errores = Errores + "<br>" + " El codigo del plano : " + NumeroPoliza_Plano + " No Existe en el Sistema";
                                    //RETORNE ALGO INFORMANDO AL USUARIO QUE HAY CODIGOS IN EXISTENTES
                                }
                            }
                        }
                        //OBTENGO EL ID DEL MES SEGUN LO QUE VIENE DEL PLANO                    
                        ModeloMeses modeloMeses = new ModeloMeses();
                        ControladorMeses controladorMeses = new ControladorMeses();
                        modeloMeses = controladorMeses.SelectMesAno(Mes_Plano, Ano_Plano);

                        //SI modeloMeses ESTA VACIO ES DEBIDO A QUE EL MES CON EL AÑO NO ESTAN CREADOS EN LA TABLA MESES
                        if (modeloMeses == null) {
                            //RETORNE ALGO INFORMANDO AL USUARIO QUE HAY MESES IN EXISTENTES
                            Errores = Errores + "<br>" + " Valide el Mes: " + Mes_Plano + " y el año: " + Ano_Plano;
                        }
                    }
                }
            }
brPerReader.close();
            if (Errores.contentEquals("")) {
                resul = CargaNominaValidado(RutaDispo);
            } else {
                return Errores;
            }

        } catch (IOException e) {
            System.out.println("Error: " + e);
            resul = "Error";
        }

        return resul;
    }

    public String CargaNominaValidado(String RutaDispo) {
        String resul = "False";
        Tools tools = new Tools();
        try {
            File file = new File(RutaDispo);
            int CanLine = tools.ContarArchi(file);
            BufferedReader brPerReader = null;
            brPerReader = new BufferedReader(new FileReader(file));

            String linea;
            while (brPerReader.ready()) {
                linea = brPerReader.readLine();
                StringTokenizer stringTokenizer = new StringTokenizer(linea, ";");
                int numtokens = stringTokenizer.countTokens();
                if (numtokens == 5) {

                    String Identificacion_Plano = stringTokenizer.nextToken();
                    if (!Identificacion_Plano.contains("Cedula")) {
                        String NumeroPoliza_Plano = stringTokenizer.nextToken();
                        String Cobro_Plano = stringTokenizer.nextToken();
                        String Mes_Plano = stringTokenizer.nextToken();
                        String Ano_Plano = stringTokenizer.nextToken();

                        //OBTENGO EL ID DE PERSONA_POLIZA CON EL CODIGO DE LA POLIZA DEL PLANO                    
                        ModeloPersonasPolizas modeloPersonasPolizas = new ModeloPersonasPolizas();
                        ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
                        modeloPersonasPolizas = controladorPersonasPoliza.SelectIdPersonaIdPolizaNomina(Identificacion_Plano, NumeroPoliza_Plano);

//                        //SI modeloPersonasPolizas ESTA VACIO ES DEBIDO A QUE EL CODIGO DE LA POLIZA DEL PLANO NO EXISTE EN PERSONA_POLIZA
//                        if (modeloPersonasPolizas == null) {
//                            //RETORNE ALGO INFORMANDO AL USUARIO QUE HAY CODIGOS IN EXISTENTES
//                        }
                        //OBTENGO EL ID DEL MES SEGUN LO QUE VIENE DEL PLANO                    
                        ModeloMeses modeloMeses = new ModeloMeses();
                        ControladorMeses controladorMeses = new ControladorMeses();
                        modeloMeses = controladorMeses.SelectMesAno(Mes_Plano, Ano_Plano);
//
//                        //SI modeloMeses ESTA VACIO ES DEBIDO A QUE EL MES CON EL AÑO NO ESTAN CREADOS EN LA TABLA MESES
//                        if (modeloMeses == null) {
//                            //RETORNE ALGO INFORMANDO AL USUARIO QUE HAY MESES IN EXISTENTES
//                        }

                        //VALIDA QUE EN LA TABLA DE RESULTADOS ESTE CARGADO ESE IDPERSONA_POLIZA Y EL MES
                        ModeloResultados modeloResultados = new ModeloResultados();
                        modeloResultados = Resultados(modeloPersonasPolizas.getModeloPoliza().getId(), modeloMeses.getId(), modeloPersonasPolizas.getModeloPersonas().getId());

                        // SI TIENE DATOS, O NO ESTA VACIO ES PORQUE YA SE CARGO ESE MES PARA ESA POLIZA
                        if (modeloResultados != null) {
                            // RETORNA ALGO INFORMANDO QUE YA HAY UN MES CARGADO CON LA INFORMACION DE ESA POLIZA
//                            Integer Pago = modeloResultados.getDeduccion();
//                            if(Pago==null)
//                                Pago=0;
                            modeloResultados.setDeduccion(Integer.valueOf(Cobro_Plano));
                            Registro(modeloResultados);
                        } else {
                            //INSERTA LA INFORMACION DEL PLANO
                            modeloResultados = new ModeloResultados();
                            //modeloResultados.setIdPersona_Poliza(modeloPersonasPolizas);
                            modeloResultados.setIdMeses(modeloMeses);
                            modeloResultados.setDeduccion(Integer.valueOf(Cobro_Plano));
                            modeloResultados.setIdPoliza(modeloPersonasPolizas.getModeloPoliza());
                            modeloResultados.setIdPersona(modeloPersonasPolizas.getModeloPersonas());
                            modeloResultados.setAno(Ano_Plano);

                            String SqlUpdate = "INSERT INTO "
                                    + "`resultados` ("
                                    //+ "(`IdPersona_Poliza`,"
                                    + "`IdMeses`,"
                                    + "`Pago`,"
                                    + "`IdPersona`,"
                                    + "`IdPoliza`,"
                                    //+ "`Diferencia`,"
                                    //+ "`IdSaldos`,"
                                    + "`Notas`,"
                                    //+ "`Observacion`,"
                                    + "`Deduccion`,"
                                    //+ "`Realizado`,"
                                    //+ "`ValorMes`,"
                                    + "Ano)"
                                    + " VALUE ("
                                    + modeloMeses.getId() + ","
                                    + "0" + ","
                                    + modeloPersonasPolizas.getModeloPersonas().getId() + ","
                                    + modeloPersonasPolizas.getModeloPoliza().getId() + ","
                                    + "'Sin Cobro'" + ","
                                    + Integer.valueOf(Cobro_Plano) + ","
                                    + "'" + Ano_Plano + "')";

                            if (Update(SqlUpdate)) {
                                resul = "True";
                                modeloResultados = Resultados(modeloPersonasPolizas.getModeloPoliza().getId(), modeloMeses.getId(), modeloPersonasPolizas.getModeloPersonas().getId());
                                Registro(modeloResultados);
                            }
//                            else {
//                                //resul = "False";
//                            }
                        }
                    }
                }

            }
            brPerReader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            resul = "Error";
        }
        return resul;
    }

    private List<ModeloResultados> GenerarArreglo(File file, int CanLine) {
        List<ModeloResultados> listModeloResultados = new ArrayList<ModeloResultados>();
        BufferedReader brPerReader = null;
        String linea;
        ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
        ControladorMeses controladorMeses = new ControladorMeses();
        try {
            brPerReader = new BufferedReader(new FileReader(file));
            while (brPerReader.ready()) {
                linea = brPerReader.readLine();
                StringTokenizer stringTokenizer = new StringTokenizer(linea, ";");
                int numtokens = stringTokenizer.countTokens();
                if (numtokens == 5) {
                    ModeloResultados modeloResultados = new ModeloResultados();
                    modeloResultados.setIdPersona_Poliza(controladorPersonasPoliza.SelectIdPersonaIdPoliza(stringTokenizer.nextToken(), stringTokenizer.nextToken()));
                    modeloResultados.setPago(Integer.parseInt(stringTokenizer.nextToken()));
                    modeloResultados.setIdMeses(controladorMeses.SelectMesAno(stringTokenizer.nextToken(), stringTokenizer.nextToken()));
                    listModeloResultados.add(modeloResultados);
                }
            }
            brPerReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return listModeloResultados;
    }

    public ModeloResultados Resultados(Integer idPoliza, Integer IdMeses, Integer IdPersona) {
        ModeloResultados modeloResultados = null;
        ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
        ControladorMeses controladorMeses = new ControladorMeses();
        ControladorSaldos controladorSaldos = new ControladorSaldos();
        ControladorPersonas controladorPersonas = new ControladorPersonas();
        ControladorPoliza controladorPoliza = new ControladorPoliza();

        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;

        try {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`IdPoliza`,"
                    + "`IdMeses`,"
                    + "`IdPersona`,"
                    + "`Pago`,"
                    + "`Diferencia`,"
                    + "`IdSaldos`,"
                    + "`Notas`,"
                    + "`Observacion`,"
                    + "`Deduccion`,"
                    + "`ValorMes` "
                    + "FROM resultados WHERE IdPoliza = ? AND IdMeses = ? AND IdPersona = ?");
            SQL.setInt(1, idPoliza);
            SQL.setInt(2, IdMeses);
            SQL.setInt(3, IdPersona);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloResultados = new ModeloResultados();
                modeloResultados.setId(res.getInt("Id"));
                //modeloResultados.setIdPersona_Poliza(controladorPersonasPoliza.Select(res.getInt("IdPersona_Poliza")));
                modeloResultados.setIdPoliza(controladorPoliza.Select(res.getInt("IdPoliza")));
                modeloResultados.setIdMeses(controladorMeses.Select("SELECT Id, Codigo, Descripcion, Ano FROM meses WHERE Id = " + res.getInt("IdMeses")));
                modeloResultados.setIdPersona(controladorPersonas.Select(res.getInt("IdPersona")));
                modeloResultados.setPago(res.getInt("Pago"));
                modeloResultados.setDeduccion(res.getInt("Deduccion"));
                modeloResultados.setDiferencia(res.getInt("Diferencia"));
                modeloResultados.setIdSaldos(controladorSaldos.Select("SELECT Id, Saldo, Debaja, FechaBaja, UsuarioBaja, idPoliza, idPersona, idMes FROM saldos where Id " + res.getInt("IdSaldos")));
                modeloResultados.setNotas(res.getString("Notas"));
                modeloResultados.setObservacion(res.getString("Observacion"));
                modeloResultados.setRealizado(res.getString("Realizado"));
                modeloResultados.setValorMes(res.getInt("ValorMes"));

            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {

        }
        return modeloResultados;
    }

    public boolean Insert(ModeloResultados modeloResultados) {
        boolean Insertado = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement("INSERT INTO "
                        + "`resultados` ("
                        //+ "(`IdPersona_Poliza`,"
                        + "`IdMeses`,"
                        + "`Pago`,"
                        + "`IdPersona`,"
                        + "`IdPoliza`,"
                        //                        + "`Diferencia`,"
                        //                        + "`IdSaldos`,"
                        //                        + "`Notas`,"
                        //                        + "`Observacion`,"
                        //                        + "`Deduccion`,"
                        + "`Realizado`,"
                        //+ "`ValorMes`,"
                        + "Ano)"
                        + " VALUE (?,?,?,?,?,?);");
                //SQL.setInt(1, modeloResultados.getIdPersona_Poliza().getId());
                SQL.setInt(1, modeloResultados.getIdMeses().getId());
                SQL.setInt(2, modeloResultados.getPago());
                SQL.setInt(3, modeloResultados.getIdPersona().getId());
                SQL.setInt(4, modeloResultados.getIdPoliza().getId());
                SQL.setString(5, "S");
                //SQL.setInt(6, Integer.valueOf(modeloResultados.getIdPersona_Poliza().getValorPoliza()));
                SQL.setString(6, modeloResultados.getAno());
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

    public boolean Registro(ModeloResultados modeloResultados) {
        boolean Registro = false;
        ModeloSaldos modeloSaldosUltimoMes = new ModeloSaldos();
        ControladorSaldos controladorSaldos = new ControladorSaldos();
        int varmes = 0;

        varmes = modeloResultados.getIdMeses().getId() - 1;

        //CONSULTO EL SALDO DEL ULTIMO MES OJO VALIDAR EL ULTIMO MES CON DATOS, NO EL MES ANTERIOR
        String SqlSelectSaldosUltimoMes = "SELECT Id, Saldo, Debaja, FechaBaja, UsuarioBaja, idPoliza, idPersona, idMes, valorDeBaja FROM saldos "
                + "where idMes = " + varmes + " and idPoliza = " + modeloResultados.getIdPoliza().getId() + " and idPersona = " + modeloResultados.getIdPersona().getId();

//        String SqlSelectSaldos = "SELECT Id, Saldo, Debaja, FechaBaja, UsuarioBaja, idPersonaPoliza, idMes FROM saldos "
//                + "where id = (select MAX(Id) from saldos where idpersonapoliza = " + modeloResultados.getIdPersona_Poliza().getId() + ")";
        modeloSaldosUltimoMes = controladorSaldos.Select(SqlSelectSaldosUltimoMes);
        Integer Diferencia = 0;
        if (modeloSaldosUltimoMes == null) {
            Diferencia = modeloResultados.getPago() + modeloResultados.getDeduccion();
        } else {
            if (modeloSaldosUltimoMes.getDebaja() != null) {
                if (modeloSaldosUltimoMes.getDebaja().contentEquals("S")) {
                    Diferencia = modeloResultados.getPago() + modeloResultados.getDeduccion() + modeloSaldosUltimoMes.getSaldo() + modeloSaldosUltimoMes.getValorDeBaja();;
                } else {
                    Diferencia = modeloResultados.getPago() + modeloResultados.getDeduccion() + modeloSaldosUltimoMes.getSaldo();
                }
            } else {
                Diferencia = modeloResultados.getPago() + modeloResultados.getDeduccion() + modeloSaldosUltimoMes.getSaldo();
            }
        }
        modeloResultados.setDiferencia(Diferencia);

        //CONSULTO SI PARA ESE MES EXISTE UN SALDO
        ModeloSaldos modeloSaldosMesActual = modeloSaldos(modeloResultados);

        //SI EXISTE UN SALDO PARA ESE MES, LO ACTUALIZO SEGUN LAS MODIFICACIONES HECHAS EN LA INTERFAZ
        if (modeloSaldosMesActual != null) {
            //ACTUALIZO EL SALDO DEL MES ACTUAL
            String SqlUpdateSaldos = "UPDATE saldos "
                    + "SET Saldo = " + Diferencia + " where Id = " + modeloSaldosMesActual.getId();
            controladorSaldos.InsertarRegistro(SqlUpdateSaldos);

            modeloResultados.setIdSaldos(modeloSaldosMesActual);

        } else {
            //INSERTO EL NUEVO SALDO DEL MES ACUTUAL
            String SqlInsertSaldos = "INSERT INTO saldos "
                    + "(Saldo,"
                    + "idPoliza,"
                    + "idPersona,"
                    + "idMes) "
                    + "VALUE ("
                    + Diferencia + ","
                    + modeloResultados.getIdPoliza().getId() + ","
                    + modeloResultados.getIdPersona().getId() + ","
                    + modeloResultados.getIdMeses().getId() + ")";
            controladorSaldos.InsertarRegistro(SqlInsertSaldos);

            //OBTENGO EL ID DEL SALDO QUE ACABO DE INSERTAR
            modeloSaldosMesActual = modeloSaldos(modeloResultados);
            modeloResultados.setIdSaldos(modeloSaldosMesActual);

        }

        String Notas = "";
        String Observacion = "";
        String Realizado = "";

        if (modeloResultados.getNotas() != null) {
            Notas = modeloResultados.getNotas().toString();
        }
        if (modeloResultados.getObservacion() != null) {
            Observacion = modeloResultados.getObservacion().toString();
        }
        if (modeloResultados.getRealizado() != null) {
            Realizado = modeloResultados.getObservacion().toString();
        }

        String SqlUpdate = "UPDATE "
                + "`resultados` SET "
                + "`Diferencia` = " + modeloResultados.getDiferencia()
                + ", `IdSaldos` = " + modeloResultados.getIdSaldos().getId()
                + ", `Notas` = '" + Notas + "'"
                //+ ", `Observacion` = '" + Observacion + "'"
                + ", `Pago` = " + modeloResultados.getPago()
                + ", `Deduccion` = " + modeloResultados.getDeduccion()
                + ", `Realizado` = '" + Realizado + "'"
                + " WHERE  `Id` = " + modeloResultados.getId();

        Registro = Update(SqlUpdate);

        return Registro;
    }

    public ModeloSaldos modeloSaldos(ModeloResultados modeloResultados) {
        ControladorSaldos controladorSaldos = new ControladorSaldos();
        ModeloSaldos modeloSaldosMesActual = new ModeloSaldos();
        String SqlSelectSaldosMesActual = "SELECT Id, Saldo, Debaja, FechaBaja, UsuarioBaja, "
                + "idPoliza, idPersona, idMes FROM saldos where "
                + "idPoliza = " + modeloResultados.getIdPoliza().getId()
                + " and idPersona = " + modeloResultados.getIdPersona().getId()
                + " and idMes = " + modeloResultados.getIdMeses().getId();

        modeloSaldosMesActual = controladorSaldos.Select(SqlSelectSaldosMesActual);

        return modeloSaldosMesActual;
    }

    public boolean Update(String Sql) {
        boolean Actualizado = false;

        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {

            SQL = con.prepareStatement(Sql);
            if (SQL.executeUpdate() > 0) {
                Actualizado = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return Actualizado;
    }

    public LinkedList<ModeloResultados> Read() {

        LinkedList<ModeloResultados> listmModeloResultados = new LinkedList<ModeloResultados>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
        ControladorMeses controladorMeses = new ControladorMeses();
        ControladorSaldos controladorSaldos = new ControladorSaldos();
        try {
            SQL = con.prepareStatement("SELECT `Id`,`IdPersona_Poliza`,`IdMeses`,"
                    + "`Pago`,`Diferencia`,`IdSaldos`,`Notas`,`Observacion`,"
                    + "`Deduccion`,`Realizado`,`ValorMes` FROM  `resultados`;");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloResultados modeloResultados = new ModeloResultados();
                modeloResultados.setId(res.getInt("Id"));
                //modeloResultados.setIdPersona_Poliza(controladorPersonasPoliza.Select(res.getInt("IdPersona_Poliza")));
                modeloResultados.setIdMeses(controladorMeses.Select("SELECT Id, Codigo, Descripcion, Ano FROM meses WHERE Id = " + res.getInt("IdMeses")));
                modeloResultados.setPago(res.getInt("Pago"));
                modeloResultados.setDiferencia(res.getInt("Diferencia"));
                modeloResultados.setIdSaldos(controladorSaldos.Select("SELECT Id, Saldo, Debaja, FechaBaja, UsuarioBaja, idPoliza, idPersona, idMes FROM saldos where Id " + res.getInt("IdSaldos")));
                modeloResultados.setNotas(res.getString("Notas"));
                modeloResultados.setObservacion(res.getString("Observacion"));
                listmModeloResultados.add(modeloResultados);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {

        }
        return listmModeloResultados;
    }

    public LinkedList<ModeloPoliza> SumaPolizasPorMes(ModeloMeses modeloMeses) {
//        LinkedList<ModeloPoliza> ListadoModeloPolizas = new LinkedList<ModeloPoliza>();
//        String SQLx = "select IdPoliza, sum(Diferencia) as Diferencia from resultados "
//                + "where IdMeses = " + modeloMeses.getId() + " group by idpoliza";
//        LinkedList<ModeloResultados> listmModeloResultados = new LinkedList<ModeloResultados>();
//        ConexionBDMySql conexion = new ConexionBDMySql();
//        Connection con;
//        con = conexion.abrirConexion();
//        PreparedStatement SQL;
//        ControladorPoliza controladorPoliza = new ControladorPoliza();
//        try {
//            SQL = con.prepareStatement(SQLx);
//            ResultSet res = SQL.executeQuery();
//            while (res.next()) {
//                ModeloResultados modeloResultados = new ModeloResultados();
//                modeloResultados.setIdPoliza(controladorPoliza.Select(res.getInt("IdPoliza")));
//                modeloResultados.setDiferencia(res.getInt("Diferencia"));
//                listmModeloResultados.add(modeloResultados);
//            }
//            for (ModeloResultados modeloResultados : listmModeloResultados) {
//
//                ListadoModeloPolizas = controladorPoliza.Read();
//                for (ModeloPoliza modeloPoliza : ListadoModeloPolizas) {
//                    if (modeloResultados.getIdPoliza().getId() == modeloPoliza.getId()) {
//                        modeloPoliza.setSuma(modeloResultados.getDiferencia());
//                        ListadoModeloPolizas.add(modeloPoliza);
//                    }
//                }
//            }
//            res.close();
//            SQL.close();
//            con.close();
//        } catch (SQLException e) {
//
//        }
//
//        return ListadoModeloPolizas;

        LinkedList<ModeloPoliza> ListadoModeloPolizas = new LinkedList<ModeloPoliza>();
        ControladorPoliza controladorPoliza = new ControladorPoliza();
        LinkedList<ModeloPoliza> ListModeloPolizas = controladorPoliza.Read();
        for (ModeloPoliza modeloPoliza : ListModeloPolizas) {
            String Sql = "SELECT `Id`, `IdPoliza`, `IdPersona`, `IdMeses`, "
                    + "`Pago`, `Diferencia`, `IdSaldos`, `Notas`, `Observacion`, "
                    + "`Deduccion`, `Realizado`, `ValorMes` FROM `resultados` WHERE `IdMeses` = " + modeloMeses.getId()
                    + " and IdPoliza = " + modeloPoliza.getId();
            LinkedList<ModeloResultados> ListModeloResultadoses = ConsultarResultados(Sql);
            Integer Suma = 0;
            for (ModeloResultados modeloResultados : ListModeloResultadoses) {

                //Suma = Suma + modeloResultados.getPago();
                Suma = Suma + modeloResultados.getDiferencia();

            }
            modeloPoliza.setSuma(Suma);
            ListadoModeloPolizas.add(modeloPoliza);
        }
        return ListadoModeloPolizas;
    }

    public LinkedList<ModeloResultados> ConsultarResultados(String Sql) {

        LinkedList<ModeloResultados> listmModeloResultados = new LinkedList<ModeloResultados>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
        ControladorPoliza controladorPoliza = new ControladorPoliza();
        ControladorPersonas controladorPersonas = new ControladorPersonas();
        ControladorMeses controladorMeses = new ControladorMeses();
        ControladorSaldos controladorSaldos = new ControladorSaldos();
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloResultados modeloResultados = new ModeloResultados();
                modeloResultados.setId(res.getInt("Id"));
                modeloResultados.setIdPoliza(controladorPoliza.Select(res.getInt("IdPoliza")));
                modeloResultados.setIdPersona(controladorPersonas.Select(res.getInt("IdPersona")));
                modeloResultados.setIdMeses(controladorMeses.Select("SELECT Id, Codigo, Descripcion, Ano FROM meses WHERE Id = " + res.getInt("IdMeses")));
                modeloResultados.setPago(res.getInt("Pago"));
                modeloResultados.setDiferencia(res.getInt("Diferencia"));
                modeloResultados.setIdSaldos(controladorSaldos.Select("SELECT Id, Saldo, Debaja, FechaBaja, UsuarioBaja, idPoliza, idPersona, idMes FROM saldos where Id " + res.getInt("IdSaldos")));
                modeloResultados.setNotas(res.getString("Notas"));
                modeloResultados.setObservacion(res.getString("Observacion"));
                listmModeloResultados.add(modeloResultados);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {

        }
        return listmModeloResultados;
    }

    public LinkedList<ModeloResultados> SelectFiltro(String IdPersona, String IdPoliza, String IdMes, String AnoConsultar,
            String Operador1, String ValorConsultar1, String Conector, String Operador2, String ValorConsultar2) {
        LinkedList<ModeloResultados> listmModeloResultados = new LinkedList<ModeloResultados>();
        if ("".equals(AnoConsultar)) {
            AnoConsultar = "2019";
        }
        if ("".equals(ValorConsultar1)) {
            ValorConsultar1 = "0";
        }
        if ("".equals(ValorConsultar2)) {
            ValorConsultar2 = "0";
        }
        if ("".equals(Conector)) {
            Conector = "AND";
        }

        /*
         configuramos el SQL
         */
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
            ControladorMeses controladorMeses = new ControladorMeses();
            ControladorSaldos controladorSaldos = new ControladorSaldos();
            ControladorPersonas controladorPersona = new ControladorPersonas();
            ControladorPoliza controladorPoliza = new ControladorPoliza();
            String SQL1;

            SQL1 = "SELECT `Id`,`IdPersona_Poliza`,`IdMeses`,`Pago`,`Diferencia`,"
                    + "`IdSaldos`,`Notas`,`Observacion`,`Deduccion`,`Realizado`,`ValorMes`,"
                    + "`IdPersona`,`IdPoliza`,`Ano` FROM `resultados` WHERE";
            if (IdPersona != null) {
                SQL1 = SQL1 + " IdPersona = " + IdPersona;
                if (IdPoliza != null) {
                    SQL1 = SQL1 + " AND IdPoliza = " + IdPoliza;
                    if (IdMes != null) {
                        SQL1 = SQL1 + " AND IdMeses = " + IdMes;
                        if (AnoConsultar != null) {
                            SQL1 = SQL1 + " AND Ano = " + AnoConsultar;
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        } else {
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        }
                    } else {
                        if (AnoConsultar != null) {
                            SQL1 = SQL1 + " AND Ano = " + AnoConsultar;
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        } else {
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        }
                    }
                } else {
                    if (IdMes != null) {
                        SQL1 = SQL1 + " AND IdMeses = " + IdMes;
                        if (AnoConsultar != null) {
                            SQL1 = SQL1 + " AND Ano = " + AnoConsultar;
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        } else {
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        }
                    } else {
                        if (AnoConsultar != null) {
                            SQL1 = SQL1 + " Ano = " + AnoConsultar;
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        } else {
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        }
                    }
                }
            } else {
                if (IdPoliza != null) {
                    SQL1 = SQL1 + " IdPoliza = " + IdPoliza;
                    if (IdMes != null) {
                        SQL1 = SQL1 + " AND IdMeses = " + IdMes;
                        if (AnoConsultar != null) {
                            SQL1 = SQL1 + " AND Ano = " + AnoConsultar;
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        } else {
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        }
                    } else {
                        if (AnoConsultar != null) {
                            SQL1 = SQL1 + " Ano = " + AnoConsultar;
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        } else {
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        }
                    }
                } else {
                    if (IdMes != null) {
                        SQL1 = SQL1 + " IdMeses = " + IdMes;
                        if (AnoConsultar != null) {
                            SQL1 = SQL1 + " AND Ano = " + AnoConsultar;
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        } else {
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        }
                    } else {
                        if (AnoConsultar != null) {
                            SQL1 = SQL1 + " Ano = " + AnoConsultar;
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        } else {
                            if (ValorConsultar1 != null) {
                                //SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "' " + Conector + " Diferencia " + Operador2 + " '" + ValorConsultar2 + "'";
                                SQL1 = SQL1 + " AND Diferencia " + Operador1 + " '" + ValorConsultar1 + "'";
                            }
                        }
                    }
                }
            }

            SQL = con.prepareStatement(SQL1);
//            SQL.setInt(1, Integer.parseInt(IdMes));
////            SQL.setInt(2, Integer.parseInt(Diferencia));
////            SQL.setInt(3, Integer.parseInt(Diferencia));
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                /*
                 `Id`,`IdPersona_Poliza`,`IdMeses`,`Pago`,`Diferencia`,"
                 + "`IdSaldos`,`Notas`,`Observacion`,`Deduccion`,`Realizado`,`ValorMes`,"
                 + "`IdPersona`,`IdPoliza`,`Ano` FROM `resultados`
                 */
                ModeloResultados modeloResultados = new ModeloResultados();
                modeloResultados.setId(res.getInt("Id"));
                //modeloResultados.setIdPersona_Poliza(controladorPersonasPoliza.Select(res.getInt("IdPersona_Poliza")));
                modeloResultados.setIdMeses(controladorMeses.Select(res.getInt("IdMeses")));
                modeloResultados.setPago(res.getInt("Pago"));
                modeloResultados.setDiferencia(res.getInt("Diferencia"));
                modeloResultados.setIdSaldos(controladorSaldos.Select(res.getInt("IdSaldos")));
                modeloResultados.setNotas(res.getString("Notas"));
                modeloResultados.setObservacion(res.getString("Observacion"));
                modeloResultados.setDeduccion(res.getInt("Deduccion"));
                modeloResultados.setRealizado(res.getString("Realizado"));
                modeloResultados.setValorMes(res.getInt("ValorMes"));
                modeloResultados.setIdPersona(controladorPersona.Select(res.getInt("IdPersona")));
                modeloResultados.setIdPoliza(controladorPoliza.Select(res.getInt("IdPoliza")));
                modeloResultados.setAno(res.getString("Ano"));
                listmModeloResultados.add(modeloResultados);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listmModeloResultados;
    }

    public String CargarXLS(String Ruta, String Mes_Plano, String Ano_Plano, boolean Validado) {
        String Realizado = "False";
        String Errores = "";
        try {
            FileInputStream inputStream = new FileInputStream(new File("C:\\Zred\\SunChemical\\DESCUENTOS.xlsx"));
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator iterator = firstSheet.iterator();

            DataFormatter formatter = new DataFormatter();
            int Contador = 0;
            String Identificacion_Plano = "";
            String NumeroPoliza_Plano = "";
            String Cobro_Plano = "";
            String Signo = "-";
            //Mes_Plano = "JULIO";
            //Ano_Plano = "2019";

            while (iterator.hasNext()) {
                Row nextRow = (Row) iterator.next();
                Iterator cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();
                    String contenidoCelda = formatter.formatCellValue(cell);
                    //System.out.println("celda: " + contenidoCelda);
                    if (Contador == 0) {
                        Identificacion_Plano = contenidoCelda;
                        System.out.println("Identifica: " + Identificacion_Plano);
                    }
                    if (Contador == 3) {
                        NumeroPoliza_Plano = contenidoCelda;
                        System.out.println("Num Poliza: " + NumeroPoliza_Plano);
                    }
                    if (Contador == 4) {
                        if (contenidoCelda.substring(0, 1).contains("E")) {
                            Signo = "-";
                            System.out.println("Negativo: ");
                        } else {
                            Signo = "";
                            System.out.println("Positivo: ");
                        }
                    }
                    if (Contador == 5) {
                        contenidoCelda = contenidoCelda.replace("$ ", "");
                        contenidoCelda = contenidoCelda.replace(" ", "");
                        NumberFormat nf = NumberFormat.getInstance();
                        try {
                            int number = nf.parse(contenidoCelda).intValue();
                            Cobro_Plano = Signo + "" + number;
                            System.out.println("Valor: " + Cobro_Plano);
                        } catch (Exception e) {
                        }

                    }
                    Contador++;

                }

                System.out.println("*********** \n : "
                        + Identificacion_Plano + " " + NumeroPoliza_Plano + " " + Cobro_Plano
                        + "\n *************************");
                Contador = 0;

                //OBTENGO EL ID DE PERSONA_POLIZA CON EL CODIGO DE LA POLIZA DEL PLANO                    
                ModeloPersonasPolizas modeloPersonasPolizas = new ModeloPersonasPolizas();
                ControladorPersonasPoliza controladorPersonasPoliza = new ControladorPersonasPoliza();
                String SQLCodigoPlano = "";

                if (Signo.contentEquals("-")) {
                    SQLCodigoPlano = "Select Id, IdPoliza, Descripcion, CodigoNomina, CodigoCorredor, CodigoReintegro FROM grupo_poliza WHERE CodigoNomina = '" + NumeroPoliza_Plano + "'";
                } else {
                    SQLCodigoPlano = "Select Id, IdPoliza, Descripcion, CodigoNomina, CodigoCorredor, CodigoReintegro FROM grupo_poliza WHERE CodigoReintegro = '" + NumeroPoliza_Plano + "'";
                }

                //modeloPersonasPolizas = controladorPersonasPoliza.SelectIdPersonaIdPolizaNomina(Identificacion_Plano, NumeroPoliza_Plano);
                modeloPersonasPolizas = controladorPersonasPoliza.SelectPersonaPoliza(Identificacion_Plano, SQLCodigoPlano);
                if (!Identificacion_Plano.contains("Identifica") || !NumeroPoliza_Plano.contains("Concep")) {
                    //SI modeloPersonasPolizas ESTA VACIO ES debido a que la persona no tiene asignada dicha poliza
                    if (modeloPersonasPolizas == null) {
                        Errores = Errores + "<br>" + " La Persona Con Identificacion: " + Identificacion_Plano + " No Tiene Asignada la Poliza No: " + NumeroPoliza_Plano;
                    } else {
                        //SI modeloPersonasPolizas tiene vacio el modelo de persona es porque la persona no existe en el sistema
                        if (modeloPersonasPolizas.getModeloPersonas() == null) {
                            Errores = Errores + "<br>" + " La Persona Con identificacion: " + Identificacion_Plano + " No Existe en el Sistema";
                        } else {
                            //SI modeloPersonasPolizas tiene vacio el modelo poliza es porque la poliza no existe en el sistema
                            if (modeloPersonasPolizas.getModeloPoliza() == null) {
                                Errores = Errores + "<br>" + " El codigo del plano : " + NumeroPoliza_Plano + " No Existe en el Sistema";
                                //RETORNE ALGO INFORMANDO AL USUARIO QUE HAY CODIGOS IN EXISTENTES
                            }
                        }
                    }
                    //OBTENGO EL ID DEL MES SEGUN LO QUE VIENE DEL PLANO                    
                    ModeloMeses modeloMeses = new ModeloMeses();
                    ControladorMeses controladorMeses = new ControladorMeses();
                    modeloMeses = controladorMeses.SelectMesAno(Mes_Plano, Ano_Plano);

                    //SI modeloMeses ESTA VACIO ES DEBIDO A QUE EL MES CON EL AÑO NO ESTAN CREADOS EN LA TABLA MESES
                    if (modeloMeses == null) {
                        //RETORNE ALGO INFORMANDO AL USUARIO QUE HAY MESES IN EXISTENTES
                        Errores = Errores + "<br>" + " Valide el Mes: " + Mes_Plano + " y el año: " + Ano_Plano;
                    }

                    if (Validado) {
                        //VALIDA QUE EN LA TABLA DE RESULTADOS ESTE CARGADO ESE IDPERSONA_POLIZA Y EL MES
                        ModeloResultados modeloResultados = new ModeloResultados();
                        modeloResultados = Resultados(modeloPersonasPolizas.getModeloPoliza().getId(), modeloMeses.getId(), modeloPersonasPolizas.getModeloPersonas().getId());

                        // SI TIENE DATOS, O NO ESTA VACIO ES PORQUE YA SE CARGO ESE MES PARA ESA POLIZA
                        if (modeloResultados != null) {
                            Integer Pago = modeloResultados.getDeduccion();
                            if (Pago == null) {
                                Pago = 0;
                            }
                            modeloResultados.setDeduccion(Integer.valueOf(Cobro_Plano) + Pago);
                            Registro(modeloResultados);
                        } else {
                            //INSERTA LA INFORMACION DEL PLANO
                            modeloResultados = new ModeloResultados();
                            //modeloResultados.setIdPersona_Poliza(modeloPersonasPolizas);
                            modeloResultados.setIdMeses(modeloMeses);
                            modeloResultados.setDeduccion(Integer.valueOf(Cobro_Plano));
                            modeloResultados.setIdPoliza(modeloPersonasPolizas.getModeloPoliza());
                            modeloResultados.setIdPersona(modeloPersonasPolizas.getModeloPersonas());
                            modeloResultados.setAno(Ano_Plano);

                            String SqlUpdate = "INSERT INTO "
                                    + "`resultados` ("
                                    //+ "(`IdPersona_Poliza`,"
                                    + "`IdMeses`,"
                                    + "`Pago`,"
                                    + "`IdPersona`,"
                                    + "`IdPoliza`,"
                                    //+ "`Diferencia`,"
                                    //+ "`IdSaldos`,"
                                    + "`Notas`,"
                                    //+ "`Observacion`,"
                                    + "`Deduccion`,"
                                    //+ "`Realizado`,"
                                    //+ "`ValorMes`,"
                                    + "Ano)"
                                    + " VALUE ("
                                    + modeloMeses.getId() + ","
                                    + "0" + ","
                                    + modeloPersonasPolizas.getModeloPersonas().getId() + ","
                                    + modeloPersonasPolizas.getModeloPoliza().getId() + ","
                                    + "'Sin Cobro'" + ","
                                    + Integer.valueOf(Cobro_Plano) + ","
                                    + "'" + Ano_Plano + "')";

                            if (Update(SqlUpdate)) {
                                Realizado = "True";
                                modeloResultados = Resultados(modeloPersonasPolizas.getModeloPoliza().getId(), modeloMeses.getId(), modeloPersonasPolizas.getModeloPersonas().getId());
                                Registro(modeloResultados);
                            }
//                            else {
//                                //resul = "False";
//                            }
                        }
                    }

                }
            }
            if (Errores.contentEquals("") && !Validado) {
                CargarXLS(Ruta, Mes_Plano, Ano_Plano, true);
                System.out.println("Plano Correcto: ");
            } else {
                if (Errores.contentEquals("")) {
                    return Realizado;
                } else {
                    return Errores;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Realizado;
    }

}

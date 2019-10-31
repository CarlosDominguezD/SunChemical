/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos A Dominguez D
 */
public class Tools {

    public void Log(String Descrip) {
        File str = new File("SunChemical");
        if (str.exists())
        {
            try
            {
                String FT = "ObtenerFecha()";
                File Archi = new File("SunChemical\\CADD.txt");
                FileWriter Escri = new FileWriter(Archi, true);
                Escri.write(Descrip + "\n");
                Escri.close();
            } catch (IOException e)
            {
            }
        } else
        {
            str.mkdirs();
            try
            {
                String FT = "ObtenerFecha()";
                File Archi = new File("SunChemical\\CADD.txt");
                FileWriter Escri = new FileWriter(Archi, true);
                Escri.write(Descrip + "\n");
                Escri.close();
            } catch (IOException e)
            {
                Log("ObtenerFechaHora()" + " ERROR >> Error " + e);
            }
        }
    }

    public String formaFecha(String fecha) {
        String resulFormat = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        resulFormat = (format.format(fecha)) + " 00:00:00";
        return resulFormat;
    }

    public int ContarArchi(File file) throws IOException {
        int cot = 0;
        BufferedReader FileCon = null;
        try
        {

            FileCon = new BufferedReader(new FileReader(file));
            String linea = null;

            while (linea == null ? FileCon.readLine() != null : !linea.equals(FileCon.readLine()))
            {
                cot++;
            }
            FileCon.close();
        } catch (FileNotFoundException ex)
        {
            FileCon.close();
        }
        return cot;
    }
}

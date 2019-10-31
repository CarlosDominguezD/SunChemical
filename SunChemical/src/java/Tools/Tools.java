/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
}

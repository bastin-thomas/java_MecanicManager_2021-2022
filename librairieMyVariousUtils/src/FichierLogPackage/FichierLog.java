package FichierLogPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class FichierLog {
    
    private String Name = "Log_";
    private final String Extension = ".log";
    private final String FilePath = "Logs" + File.separator;
    private BufferedWriter outputStream;
    private BufferedReader inputStream;
    
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public FichierLog(){
        
        String maintenant = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.FRANCE).format(new Date());
        maintenant = maintenant.replaceAll(" ", "_");
        maintenant = maintenant.replaceAll("/", "-");
        
        Name = Name + maintenant;
        
        try {
            inputStream = new BufferedReader(new FileReader(FilePath + Name + Extension));
        } catch (FileNotFoundException ex) {
            try {
                File d = new File("Logs");
                d.mkdir();
                outputStream = new BufferedWriter(new FileWriter(FilePath + Name + Extension));
                outputStream.close();
                outputStream = null;
                
                inputStream = new BufferedReader(new FileReader(FilePath + Name + Extension));
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(FichierLog.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IOException ex1) {
                Logger.getLogger(FichierLog.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    public void PrintLN(String App,String Message){
        try {
            try {
                outputStream = new BufferedWriter(new FileWriter(FilePath + Name + Extension, true)); //Le true rend append
            } catch (IOException ex) {
                File d = new File("Logs");
                d.mkdir();
                outputStream = new BufferedWriter(new FileWriter(FilePath + Name + Extension, true));
            }
            
            String maintenant = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.FRANCE).format(new Date());
            
            outputStream.write(maintenant + ">["+ App +"] " + Message);
            outputStream.newLine();
            outputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(FichierLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getName(){
        return(FilePath + Name + Extension);
    }
    
    public int Length() throws IOException{
        int i = 0;
        String str;
        while((str = inputStream.readLine()) != null && str.length()!=0){
            i++;
        }
        return i;
    }
}

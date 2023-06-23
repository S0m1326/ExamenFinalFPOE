package co.edu.univalle.examenfinalfpoe.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Archivos {

    public Archivos() {
    }
    
    public static ArrayList<Paciente> leerArchivo (String Ruta) {
        ObjectInputStream objInputStream = null;
        ArrayList<Paciente> pacientes = new ArrayList<>();
        
        try{
            objInputStream = new ObjectInputStream(new FileInputStream(Ruta));
            pacientes = (ArrayList<Paciente>) objInputStream.readObject();
            System.out.println("Archivo binario leído");

        } catch(FileNotFoundException exception) {
            System.out.println("El archivo binario aun no ha sido creado.");

        } catch(IOException exception){
            System.out.println("Error al leer el archivo binario.");

        } catch(ClassNotFoundException exception) {
            System.out.println("Conflicto entre clases.");

        } finally {
            if(objInputStream != null)
                try {
                    objInputStream.close();
                } catch (IOException exception) {
                    System.out.println("Error cerrando el archivo objInputStream");
                }
        }
        return pacientes;
    }
    
    public static boolean guardarArchivo (ArrayList<Paciente> pacientes, String Ruta) {
        boolean completado = false;
        ObjectOutputStream objOutputStream = null;
        
        try{
            objOutputStream = new ObjectOutputStream(new FileOutputStream(Ruta));
            objOutputStream.writeObject(pacientes);
            System.out.println("¡Archivo binario guardado!");
            completado = true;

        } catch(FileNotFoundException exception){ 
            System.out.println("verificar la ruta donde se guardará el archivo binario.");

        } catch(IOException exception){
            System.out.println("Error guardando el archivo binario.");

        } finally {
            if(objOutputStream != null){
                try {
                    objOutputStream.close();
                } catch (IOException exception) {
                     System.out.println("Error cerrando el archivo objOutputStream");
                }
            }
        }
        return completado;
    }
    
}

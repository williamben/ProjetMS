import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
//PSSH
    public static boolean firstTime = true;
    
    public static void writer(String fileName, String line) {
        // définition d'un fichier
        File fichier =  new File(fileName) ;

        try {
            if (firstTime && fichier.exists()){
                fichier.delete();
                firstTime = false;
//                System.out.println("boo");
            } 
            // ouverture d'un flux de sortie sur un fichier
            // a pour effet de créer le fichier
            System.out.println(line);
            FileWriter writer =  new FileWriter(fichier, true) ;
            // écriture dans le fichier
            writer.write(line+System.lineSeparator()) ;
            // la méthode close de FileWriter appelle elle-même flush()
            writer.close() ;

        }  catch (IOException e) {

            // affichage du message d'erreur et de la pile d'appel
            System.out.println("Erreur " + e.getMessage()) ;
            e.printStackTrace() ;
        }
    }

}
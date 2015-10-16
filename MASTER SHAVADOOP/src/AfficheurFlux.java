import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


class AfficheurFlux implements Runnable {

    private final InputStream inputStream;
    static String line1;
    public static boolean isconnected;

	AfficheurFlux(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private BufferedReader getBufferedReader(InputStream is) {
        return new BufferedReader(new InputStreamReader(is));
    }

    @Override
    public void run() {
        BufferedReader br = getBufferedReader(inputStream);
        String ligne = "";
        try {
            
        	while ((ligne = br.readLine()) != null) {
        		if(ligne.contains("Machine Disponible:")){
                	Shavadoop.ListMachine.AddMachineDispo(ligne.substring(ligne.length()-7, ligne.length())+".enst.fr");
            	}
        		if(ligne.contains("word:")){
        			String []words=ligne.split("\\s");
                	if (Shavadoop.ListeWord.get(words[3])==null){
                		Shavadoop.ListeWord.put(words[3],words[1]);
                	}else{
                   		Shavadoop.ListeWord.put(words[3],Shavadoop.ListeWord.get(words[3])+" "+words[1]);
                	}
        			
            	}
            	System.out.println(ligne);
            	
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

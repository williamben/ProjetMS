import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


class AfficheurFlux implements Runnable {

    private final InputStream inputStream;
    static String line1;
    public static boolean isconnected;
    ArrayList<String> ListWord;//=new ArrayList<String>();

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
                	Shavadoop.HashMapMachine.RemoveProcessForAMachine(ligne.substring(ligne.length()-7, ligne.length())+".enst.fr");

            	}
        		if(ligne.contains("word:")){
        			String []words=ligne.split("\\s");
        			
                	if (Shavadoop.HashMapWord.get(words[3])==null){
                		ListWord=new ArrayList<String>();
                	}else{
                		ListWord=Shavadoop.HashMapWord.get(words[3]);
                	}
                   		ListWord.add(words[1]);
                   		Shavadoop.HashMapWord.put(words[3],ListWord);
                	
        			
            	}
        		
            	System.out.println(ligne);
            	
            }
        	
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

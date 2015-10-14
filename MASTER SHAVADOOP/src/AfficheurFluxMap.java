import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


class AfficheurFluxMap implements Runnable {

    private final InputStream inputStream;
    static String line1;
    public static boolean isconnected;

    AfficheurFluxMap(InputStream inputStream) {
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
        		System.out.println(ligne);
        		if(ligne.contains("c133")){
        			System.out.println(ligne);
            	}
            	
            	
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

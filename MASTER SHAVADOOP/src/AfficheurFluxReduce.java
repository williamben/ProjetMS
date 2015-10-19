import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


class AfficheurFluxReduce implements Runnable {

    private final InputStream inputStream;
    static String line1;
    public static boolean isconnected;
    ArrayList<String> ListWord;//=new ArrayList<String>();

    AfficheurFluxReduce(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private BufferedReader getBufferedReader(InputStream is) {
        return new BufferedReader(new InputStreamReader(is));
    }
	public  void createFile(String fileName,String toWrite) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader(fileName));
		File f = new File (fileName);

		// if file doesnt exists, then create it
		if (!f.exists()) {
			f.createNewFile();
		}

		FileWriter fw = new FileWriter (f,true);
		fw.write(toWrite);
		fw.write("\n");
		fw.close();


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
        		if(ligne.contains("appaears:")){
        			String []words=ligne.split("\\s");
        			createFile("/cal/homes/wbenhaim/Desktop/OutPut.txt",words[1]+" "+words[3]);
        			
//                	if (Shavadoop.HashMapWord.get(words[3])==null){
//                		ListWord=new ArrayList<String>();
//                	}else{
//                		ListWord=Shavadoop.HashMapWord.get(words[3]);
//                	}
//                		if(!(ListWord.contains(words[1]))){
//                   		ListWord.add(words[1]);
//                   		Shavadoop.HashMapWord.put(words[3],ListWord);
//                		}
        			
            	}
        		
            	System.out.println(ligne);
            	
            }
        	
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    

    


}

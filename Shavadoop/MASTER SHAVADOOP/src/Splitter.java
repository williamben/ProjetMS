import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Splitter {
	private String FileName;
	private ArrayList<String> ListSplit;
	public Splitter(String FileName){
		this.FileName=FileName;
		ListSplit=new ArrayList<String>();
	}

	
	public void toSplitte() throws IOException{
		   BufferedReader br = new BufferedReader(new FileReader(this.FileName));
		 
		   int nbligne;
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();
		        nbligne=1;
		        while (line != null) {
		        	//System.out.println(line);
		        	createFile("/cal/homes/wbenhaim/Desktop/tmpShavadoop/S"+nbligne+".txt",line);
		            sb.append(line);
		            sb.append("\n");
		            line = br.readLine();
		          
		           // createUM("/cal/homes/wbenhaim/Desktop/tmpShavadoop/S"+nbligne+".txt");
		            
		            this.ListSplit.add("/cal/homes/wbenhaim/Desktop/tmpShavadoop/S"+nbligne+".txt");
		            nbligne++;
		        }
	
		    } finally {
		        br.close();
		        //return ListSplit;
		    }
	}
	public  void createUM(String FileName){
		
		
	}
	public  void createFile(String fileName,String toWrite) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader(fileName));
		File f = new File (fileName);

		// if file doesnt exists, then create it
		if (!f.exists()) {
			f.createNewFile();
		}

		FileWriter fw = new FileWriter (f);
		fw.write(toWrite);
		fw.close();


	}


	public ArrayList<String> getListSplit() {
		return ListSplit;
	}


	public void setListSplit(ArrayList<String> listSplit) {
		ListSplit = listSplit;
	}


	
	
}

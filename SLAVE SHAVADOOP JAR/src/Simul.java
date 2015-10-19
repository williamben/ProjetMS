import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class Simul {
	public static void main(String[] args) throws InterruptedException, IOException{




		//	afficheterminal("avant le calcul");
		//	Thread.sleep(5000);

		if (args[0].contains("MAP")){
			System.out.println("MAP");
			System.out.println(args.length);
			new Simul();
			Simul test=new Simul();
			Writer testWriter=new Writer();
			BufferedReader br = new BufferedReader(new FileReader(args[1]));
			HashMap<String,Integer>UM=new HashMap<String,Integer>();
			//String nameFileDest=args[1].substring(args[1].length()-5, args[1].length()-4);
			String nameFileDest1=args[1].substring(0, args[1].length()-4);

			String nameFileDest=nameFileDest1.substring(42, nameFileDest1.length());
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				String []str=line.split("\\s");

				for(String s:str){
					//				System.out.println(s);
					if(UM.containsKey(s)){
						UM.put(s, UM.get(s)+1);
					}else{
						UM.put(s,1);
					}
					System.out.println("File: UM"+nameFileDest+ " word: "+s);
				}

			} finally {
				br.close();
			}

			for(String s:UM.keySet()){
				test.createFile("/cal/homes/wbenhaim/Desktop/tmpShavadoop/UM"+nameFileDest+".txt",s+" "+ UM.get(s)+"\n");
				//testWriter.writer("/cal/homes/wbenhaim/Desktop/tmpShavadoop/UM"+nameFileDest+".txt",s+" "+ UM.get(s)+"\n");

			}
			afficheterminal("Machine Disponible: ");
		}
		if (args[0].contains("ShuffleAndReduce")){
			System.out.println("ShuffleAndReduce");
			System.out.println(args.length);
		}



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
		fw.close();


	}

	static void afficheterminal(String Texte) throws IOException {
		Process p;
		Runtime mRuntime = Runtime.getRuntime();
		String [] commands = {"sh", "-c","hostname" };
		//Process p = Runtime.getRuntime().exec(commands);
		ProcessBuilder pb = new ProcessBuilder(commands);
		p = pb.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		try {
			while ((line = reader.readLine()) != null)
			{
				System.out.println(Texte+" " +line);


			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

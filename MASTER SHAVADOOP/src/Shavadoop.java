import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Shavadoop {

	//la fonction readFile est sign�e String, elle prend un fichier'filename' du type String.  Elle g�n�re l'exception
	//le syntaxe pour lire un fichier depuis l'ext�rieur BufferedReader puis lui passer un autre classe 'FileReader ()'
	public static RessourceManager HashMapMachine=new RessourceManager();
	public static Splitter Split=new Splitter("/cal/homes/wbenhaim/Desktop/Input.txt");
	ArrayList<String> ListFichier=new ArrayList<String>();
	public static HashMap<String,ArrayList<String>>HashMapWord=new HashMap<String,ArrayList<String>>();

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

	public  void availableDesk(String fileName) throws IOException {
		String Result="";
		BufferedReader br = new BufferedReader(new FileReader(fileName));


		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {

				try {

					//System.out.println("ssh wbenhaim@" +line );
					String[] commande = { "sh", "-c", " ssh wbenhaim@" +line + " echo OK" };
					//Process p = Runtime.getRuntime().exec(commande);
					ProcessBuilder pb = new ProcessBuilder(commande);
					Process p = pb.start();
					AfficheurFluxwithTestConnected fluxSortie = new AfficheurFluxwithTestConnected(p.getInputStream());
					AfficheurFluxwithTestConnected fluxErreur = new AfficheurFluxwithTestConnected(p.getErrorStream());

					new Thread(fluxSortie).start();
					new Thread(fluxErreur).start();
					p.waitFor();
					if(fluxSortie.isconnected){
						Result=Result+line+"\n";
						HashMapMachine.AddMachineDispo(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}

		} finally {
			createFile("/cal/homes/wbenhaim/git/ProjetMS/MASTER SHAVADOOP/src/AvailableDesk.txt",Result);
			br.close();
		}

	}

	public  void readAndProceedMap(ArrayList<String> ListSplit) throws IOException {

		ArrayList<Process> ListProcess=new ArrayList<Process>();
		String machine=null;
		for(String Split:ListSplit){


			machine=HashMapMachine.GetFirstMachine();

			System.out.println("On lance SLAVE sur "+machine );
			HashMapMachine.AddProcessForAMachine(machine);

			Process p =Map(machine,Split);
			if(p!=null){
				ListProcess.add(p);	
			}
		}
		for(Process p:ListProcess){

			try {

				p.waitFor();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	public  Process Map(String machine,String UM) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader(fileName));
		//try {
		//StringBuilder sb = new StringBuilder();
		//String line = br.readLine();
		Process p=null;
		if(machine==null){
			return p;
		}
		try {


			String[] commande = { "sh", "-c", " ssh " +machine+ " 'java -jar ~/Desktop/Slave.jar' MAP " +UM };
			//System.out.println(" ssh " +line+ " 'java -jar ~/Desktop/Slave.jar' " +UM );

			ProcessBuilder pb = new ProcessBuilder(commande);
			//Process p = Runtime.getRuntime().exec(commande);
			p = pb.start();
			AfficheurFlux fluxSortie = new AfficheurFlux(p.getInputStream());
			AfficheurFlux fluxErreur = new AfficheurFlux(p.getErrorStream());

			new Thread(fluxSortie).start();
			new Thread(fluxErreur).start();

			//p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} //catch (InterruptedException e) {
		//e.printStackTrace();
		//}

		return p;


	}

	public void ProcessShuffleAndReduce(){
		ArrayList<Process> ListProcess=new ArrayList<Process>();
		String machine=null;
		String MotsAndMachines=null;
		for(String words: HashMapWord.keySet()){
			MotsAndMachines=words;
			System.out.println((words));
			ArrayList<String> HashMapMachineForAWord=HashMapWord.get(words);
			for (String Machine:HashMapMachineForAWord){
				MotsAndMachines=MotsAndMachines+" "+Machine;
			}
			System.out.println(MotsAndMachines);


			machine=HashMapMachine.GetFirstMachine();

			System.out.println("On lance SLAVE sur "+machine );
			HashMapMachine.AddProcessForAMachine(machine);

			Process p =ShuffleAndReduce(machine,MotsAndMachines);
			if(p!=null){
				ListProcess.add(p);	
			}
		}
		for(Process p:ListProcess){

			try {

				p.waitFor();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public Process ShuffleAndReduce(String machine,String MotsAndMachines){

		Process p=null;
		if(machine==null){
			return p;
		}
		try {


			String[] commande = { "sh", "-c", " ssh " +machine+ " 'java -jar ~/Desktop/Slave.jar' ShuffleAndReduce " +MotsAndMachines };
			//System.out.println(" ssh " +line+ " 'java -jar ~/Desktop/Slave.jar' " +UM );
			
			ProcessBuilder pb = new ProcessBuilder(commande);
			//Process p = Runtime.getRuntime().exec(commande);
			p = pb.start();
			AfficheurFlux fluxSortie = new AfficheurFlux(p.getInputStream());
			AfficheurFlux fluxErreur = new AfficheurFlux(p.getErrorStream());

			new Thread(fluxSortie).start();
			new Thread(fluxErreur).start();

			//p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} //catch (InterruptedException e) {
		//e.printStackTrace();
		//}

		return p;


	}





	public static void main(String[] tito) throws IOException {
		//ON instancie Shavadoop

		Split.toSplitte();
		ArrayList<String> ListSplit=Split.getListSplit();
		//System.out.println(ListSplit.toString());
		new Shavadoop();
		Shavadoop SD=new Shavadoop();

		System.out.println("Début\n");
		String fileName=("/cal/homes/wbenhaim/git/ProjetMS/MASTER SHAVADOOP/src/c124hosts");
		SD.availableDesk(fileName);
		System.out.println("Liste des postes disponible.\n");
		SD.readAndProceedMap(ListSplit);//("/cal/homes/wbenhaim/workspace/MASTER SHAVADOOP/src/AvailableDesk.txt");
		//Map("cal/homes/wbenhaim/workspace/MASTER SHAVADOOP/src/AvailableDesk.txt");
		System.out.println("Le calcul est terminé");

		for(String s: HashMapWord.keySet()){
			System.out.println((s));
			ArrayList<String> HashMapMachineForAWord=HashMapWord.get(s);
			for (String s1:HashMapMachineForAWord){
				System.out.println(s1);
			}
		}
		SD.ProcessShuffleAndReduce();



		///



	}

}
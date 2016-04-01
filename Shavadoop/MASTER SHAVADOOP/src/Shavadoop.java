import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Shavadoop {

	//la fonction readFile est sign�e String, elle prend un fichier'filename' du type String.  Elle g�n�re l'exception
	//le syntaxe pour lire un fichier depuis l'ext�rieur BufferedReader puis lui passer un autre classe 'FileReader ()'
	public static RessourceManager ListMachine=new RessourceManager();
	public static Splitter Split=new Splitter("/cal/homes/wbenhaim/Desktop/Input.txt");
	
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
						ListMachine.AddMachineDispo(line);
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
			createFile("/cal/homes/wbenhaim/workspace/MASTER SHAVADOOP/src/AvailableDesk.txt",Result);
			br.close();
		}

	}
	// On lit le fichier avec la liste des machines disponibles et on lancer le SLAVE
	public  void readAndProceedSlave(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			ArrayList<Process> ListProcess=new ArrayList<Process>();
			while (line != null) {

				System.out.println("On lance SLAVE sur "+ line);
				ListMachine.RemoveMachineDispo(line);
				Process p =testSlave(line);
				if(p!=null){
					ListProcess.add(p);	
				}
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
				
			}
			for(Process p:ListProcess){
				
				try {

					p.waitFor();
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
				
			//return sb.toString();
		} finally {
			br.close();
		}
		
	}
	public  void readAndProceedSlave2(ArrayList<String> ListSplit) throws IOException {
			
			ArrayList<Process> ListProcess=new ArrayList<Process>();
			String machine=null;
			for(String Split:ListSplit){
				machine=ListMachine.GetFirstMachine();
				System.out.println("On lance SLAVE sur "+machine );
				ListMachine.RemoveMachineDispo(machine);
				Process p =testSlave(machine,Split);
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
	
				
			//return sb.toString();

	}
	
	
	
	public  Process testSlave(String line,String UM) throws IOException {
				//BufferedReader br = new BufferedReader(new FileReader(fileName));
				//try {
					//StringBuilder sb = new StringBuilder();
					//String line = br.readLine();
					Process p=null;
					if(line==null){
						return p;
					}
					try {
	
						
			            String[] commande = { "sh", "-c", " ssh " +line+ " 'java -jar ~/Desktop/Slave.jar' " +UM };
			            System.out.println(" ssh " +line+ " 'java -jar ~/Desktop/Slave.jar' " +UM );
			            ListMachine.AfficheDispo();
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
					
//				} finally {
//					//br.close();
//				}

	}







	public static void main(String[] args) throws IOException {
		//ON instancie Shavadoop

			Split.toSplitte();
			ArrayList<String> ListSplit=Split.getListSplit();
			//System.out.println(ListSplit.toString());
			new Shavadoop();
			Shavadoop SD=new Shavadoop();
			
			System.out.println("Début\n");
			String fileName=("/cal/homes/wbenhaim/workspace/MASTER SHAVADOOP/src/c124hosts");
			SD.availableDesk(fileName);
			System.out.println("Liste des postes disponible.\n");
			SD.readAndProceedSlave2(ListSplit);//("/cal/homes/wbenhaim/workspace/MASTER SHAVADOOP/src/AvailableDesk.txt");
			//TestSlave("cal/homes/wbenhaim/workspace/MASTER SHAVADOOP/src/AvailableDesk.txt");
			System.out.println("Le calcul est terminé");
			
			///

			

	}

}
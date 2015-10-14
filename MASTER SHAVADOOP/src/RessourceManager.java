import java.util.ArrayList;


public class RessourceManager {
	ArrayList<String> ListMachineDispo=new ArrayList<String>();
	ArrayList<String> ListMachineUtilise=new ArrayList<String>();
	
	//ArrayList<String> ListMachineUtilise=new ArrayList<String>();
	public RessourceManager(){//ArrayList<String> ListMachineDispo,ArrayList<String> ListMachineUtilise){
//		this.ListMachineDispo=ListMachineDispo;
//		this.ListMachineUtilise=ListMachineUtilise;
	}
	public void AddMachineDispo(String Machine){
		this.ListMachineDispo.add(Machine);
		if(this.ListMachineUtilise.contains(Machine)){
			this.ListMachineUtilise.remove(Machine);
		}
	}
	public void RemoveMachineDispo(String Machine){
	
		if(this.ListMachineDispo.contains(Machine)){
			this.ListMachineDispo.remove(Machine);
			this.ListMachineUtilise.add(Machine);
		}
		
		
	}
	public String GetFirstMachine(){
		
		return this.ListMachineDispo.get(0);
	}
	
	public void AfficheDispo(){
		System.out.println("Dispo");
		for(String s:this.ListMachineDispo){
			System.out.println(s);
		}
		System.out.println("Utilisé");
		for(String s:this.ListMachineUtilise){
			System.out.println(s);
		}
	}
	
	
	
}

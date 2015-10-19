import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.text.html.HTMLDocument.Iterator;


public class RessourceManager {
	HashMap<String,Integer> HashMapMachineDispo=new HashMap<String,Integer>();
	ArrayList<String> ListMachineUtilise=new ArrayList<String>();
	
	//ArrayList<String> ListMachineUtilise=new ArrayList<String>();
	public RessourceManager(){//ArrayList<String> ListMachineDispo,ArrayList<String> ListMachineUtilise){
//		this.ListMachineDispo=ListMachineDispo;
//		this.ListMachineUtilise=ListMachineUtilise;
	}
	public void AddMachineDispo(String Machine){
		if(!this.HashMapMachineDispo.containsKey(Machine)){
			this.HashMapMachineDispo.put(Machine,0);
		}

	}
	public void AddProcessForAMachine(String Machine){
		System.out.println(Machine);
		this.HashMapMachineDispo.put(Machine,this.HashMapMachineDispo.get(Machine)+1);
		//this.HashMapMachineDispo=(HashMap<String, Integer>) sortMap(this.HashMapMachineDispo);
	}
	public void RemoveProcessForAMachine(String Machine){
		this.HashMapMachineDispo.put(Machine,this.HashMapMachineDispo.get(Machine)-1);
		
	}
	
//	private static Map sortMap(Map aMap) {
//	    Map myMap = new HashMap();
//	    TreeSet set = new TreeSet(new Comparator(){
//	        public int compare(Object obj, Object obj1) {
//		Integer val1 = (Integer) ((Map.Entry) obj).getValue();
//		Integer val2 = (Integer) ((Map.Entry) obj1).getValue();
//	                return val1.compareTo(val2);
//	        }	
//	    });
//	 
//	   set.addAll(aMap.entrySet());
//	 
//	    for(java.util.Iterator it = set.iterator(); ((java.util.Iterator) it).hasNext() ;) {
//	        Map.Entry myMapEntry = (Map.Entry) it.next();
//	        myMap.put(myMapEntry.getKey(), myMapEntry.getValue());
//	    }
//	 
//	    return myMap; 
//	}
	public String GetFirstMachine(){
	String MachineLaMoinsUtilisee=null;
	String tmpMachine=null;
	int NbProcess=-1;
	for(String Machine:HashMapMachineDispo.keySet()){
		tmpMachine=Machine;
		int TmpNbProcess=HashMapMachineDispo.get(Machine);
		if(NbProcess==-1){
			MachineLaMoinsUtilisee=Machine;
			NbProcess=HashMapMachineDispo.get(Machine);
		}
		if(TmpNbProcess<NbProcess){
			MachineLaMoinsUtilisee=Machine;
			NbProcess=TmpNbProcess;
		}
	}
	
		return MachineLaMoinsUtilisee;

		
	
		
	
	
}

//	public void RemoveMachineDispo(String Machine){
//	
//		if(this.ListMachineDispo.contains(Machine)){
//			this.ListMachineDispo.remove(Machine);
//			this.ListMachineUtilise.add(Machine);
//		}
//		
//		
//	}

	
	public void AfficheDispo(){
		System.out.println("Dispo");
		for(String s:this.HashMapMachineDispo.keySet()){
			System.out.println(s);
			System.out.println(HashMapMachineDispo.get(s));
		}

	}
	
	
	
}

import java.io.*;
import java.util.*;

public class Sort {

	String FiletoSort;
	//ArrayList<String> ListMachineUtilise=new ArrayList<String>();
	public Sort(String FiletoSort){//ArrayList<String> ListMachineDispo,ArrayList<String> ListMachineUtilise){
		this.FiletoSort=FiletoSort;
	}
    public void sort() throws IOException{
    BufferedReader reader = new BufferedReader(new FileReader(this.FiletoSort));
    Map<String, String> map=new TreeMap<String, String>();
    String line="";
    while((line=reader.readLine())!=null){
    	map.put(getField(line),line);
    }
    reader.close();
    FileWriter writer = new FileWriter(this.FiletoSort);
    for(String val : map.values()){
    	writer.write(val);	
    	writer.write('\n');
    }
    writer.close();
    }
    private static String getField(String line) {
    	return line.split(" ")[0];//extract value you want to sort on
    }
}
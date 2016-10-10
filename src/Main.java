import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {

	public static void main(String[] args) {
		
		read();
//		Collection col = new Collection();
//		col.readFile();
//		GeneticAlg alpha = col.getFittest();
//		int i = 0;
//
//		while (alpha.returnFitness() != 0) {
//			System.out.println(alpha.getVersion() + ' ' + i + " Fitness  " + alpha.fitness);
//			col.naturalSelection();
//			alpha = col.getFittest();
//			i++;
//		}
//
//		System.out.println("Final Version " + i + ": " + alpha.getVersion() + "\n");
	}
	
	private static void read(){
		HashMap<String, ArrayList<Integer>> population = new HashMap<>();
		
		ArrayList<Integer> valuesList = new ArrayList<Integer>();
		Path path = Paths.get("nanoxmltestfaultmatrix.txt");
		try (InputStream in = Files.newInputStream(path);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	if(!(line.contains(":"))) {
		    		line = line.replaceAll("\\s+",""); 
		    		valuesList.add(Integer.parseInt(line));
		    	}  
		    }
		} catch (IOException x) {
		    System.err.println(x);
		}
		
		int i = 0; 
		int counter = 0;
		while(i < valuesList.size()) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = i; j < (i+9); j++) {
				temp.add(valuesList.get(j));
			}
			String name = "UNIT-TEST_" + Integer.toString(counter);
			population.put(name, temp);
			
			i = i + 9;
			counter++;
		}
		System.out.println(population);
		
	}

}

	



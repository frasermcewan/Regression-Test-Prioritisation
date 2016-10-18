import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

public class Main {

	public static int numTests = 0;
	
	public static void main(String[] args) {
		HashMap<String, ArrayList<Integer>> inputFile = read();
		Collection col = new Collection(inputFile, (numTests/2));
		Chromosome alpha = col.getFittest();
		int i = 0;
		while (i < 100 ) {
			System.out.println("\t\t" + i + "\t" + alpha.getVersion() + "\tFitness\t" + alpha.fitness);
			col.naturalSelection();
			alpha = col.getFittest();
			i++;
		}

		System.out.println("Final Version: \t" + i + "\t" + alpha.getVersion() + "\tFitness\t" + alpha.fitness.toString() + "\n");
		
		System.out.println("HILL CLIMBER");
		
		/* HILL CLIMBER */
		HillClimber climber = new HillClimber(inputFile, (numTests/2));
		System.out.println("Terminated");
		
		/* RANDOM */
		System.out.println("Random");
		ArrayList<Chromosome> storeRandom = new ArrayList<>();
		for (int j = 0; j < 50; j++) {
			Chromosome rnd = col.generateRandom();
			storeRandom.add(rnd);
		}
		Collections.sort(storeRandom);
		for(int j = 0; j < storeRandom.size(); j++){
			System.out.println("Random Version:" + j + "\t" + storeRandom.get(j).getCases() + "\tFitness\t" + storeRandom.get(j).fitness.toString());
		}
		
	}

	private static HashMap<String, ArrayList<Integer>> read() {
		boolean numberOfTests = false;
		HashMap<String, ArrayList<Integer>> population = new HashMap<>();
		ArrayList<Integer> valuesList = new ArrayList<Integer>();
		//Path path = Paths.get("nanoxmltestfaultmatrix.txt");
		Path path = Paths.get("largedataset.txt");
		try (InputStream sizeCalculator = Files.newInputStream(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(sizeCalculator))) {
			String sizeCalc = null;
			while ((sizeCalc = reader.readLine()) != null) {
				if (!(sizeCalc.contains("unitest1")) && numberOfTests == false) {
					numTests++;

				} else if (sizeCalc.contains("unitest1") && numberOfTests == false) {
					numberOfTests = true;
					break;
				}
			}
		} catch (IOException x) {
			System.err.println(x);
		}

		try (InputStream in = Files.newInputStream(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			String newLine = null;
			while ((line = reader.readLine()) != null) {
				if (!(line.contains("unitest"))) {
					if (line.contains("v")) {
						if(line.length() == 3) {
						newLine = Character.toString(line.charAt(1));
	 					valuesList.add(Integer.parseInt(newLine)); ////??
						} else if (line.length() == 4) {
							String newLine2 = new StringBuilder().append(line.charAt(1)).append(line.charAt(2)).toString();
							valuesList.add(Integer.parseInt(newLine2));
						}
 					}
					
					if (!(line.contains("v"))) {
					line = line.replaceAll("\\s+", "");
					valuesList.add(Integer.parseInt(line));
					}
				}
			}
		} catch (IOException x) {
			System.err.println(x);
		}

		int i = 0;
		int counter = 0;
		
		while (i < valuesList.size()) {
			ArrayList<Integer> temp = new ArrayList<>();
			TreeMap<Integer, Integer> map = new TreeMap<>();
			for (int j = i; j < (i + (numTests-1)) &&  j <valuesList.size(); j++) {
				temp.add(valuesList.get(j));
			}
			
		
			
			for(int k = 0; k < temp.size()-1;k=k+2){
				map.put(temp.get(k), temp.get(k+1));
			}
			
			temp.clear();
			
			for(int k = 1; k < map.size()+1; k++){
				temp.add(map.get(k));
			}
			String name = "UNIT-TEST_" + Integer.toString(counter);
			population.put(name, temp);

			i = i + (numTests-1);
			counter++;
		}
	
		return population;

	}

}
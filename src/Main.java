import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Main {

	public static int numTests = 0;
	
	public static void main(String[] args) {
		Collection col = new Collection(read(), numTests);
		Chromosome alpha = col.getFittest();
		int i = 0;

		while (alpha.getFitness() != 1 && i < 20) {
			ArrayList<Double> fitnessList = col.returnFitnessList();
			System.out.println(alpha.getVersion() + ' ' + i + " Fitness  " + alpha.fitness);
//			for(int q = 0; q < fitnessList.size(); q++) {
//				System.out.println("Fitness at\t" + q + "\t is" + fitnessList.get(q));
//			}
			col.naturalSelection();
			alpha = col.getFittest();
			i++;
		}

		System.out.println("Final Version " + i + ": " + alpha.getVersion() + "\t" + alpha.fitness.toString() + "\n");
		
		
	}

	private static HashMap<String, ArrayList<Integer>> read() {
		boolean numberOfTests = false;
		HashMap<String, ArrayList<Integer>> population = new HashMap<>();
		ArrayList<Integer> valuesList = new ArrayList<Integer>();
		Path path = Paths.get("nanoxmltestfaultmatrix.txt");
		//Path path = Paths.get("largedataset.txt");
		try (InputStream sizeCalculator = Files.newInputStream(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(sizeCalculator))) {
			String sizeCalc = null;
			while ((sizeCalc = reader.readLine()) != null) {
				if (!(sizeCalc.contains("unitest1")) && numberOfTests == false) {
					numTests++;

				} else if (sizeCalc.contains("unitest1") && numberOfTests == false) {
					numberOfTests = true;
					System.out.println(numTests);
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
			
			
			
			//System.out.println(map.keySet());
			temp.clear();
			
			for(int k = 1; k < map.size()+1; k++){
				temp.add(map.get(k));
			}
			
//			System.out.println(temp);
			
			/**
			 * Depending of the size of the keyset, for loop to that length and then construct a new arraylist in the correct order
			 * for i = 0; i < hashSet.size(); i ++
			 * valueList.add(hashSet.get(i))
			 */
			String name = "UNIT-TEST_" + Integer.toString(counter);
			population.put(name, temp);

			i = i + (numTests-1);
			counter++;
		}
		// System.out.println(population);
		return population;

	}

}
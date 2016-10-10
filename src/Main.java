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

	public static int numTests = 0;

	public static void main(String[] args) {
		Collection col = new Collection(read(), numTests);
		Chromosome alpha = col.getFittest();
		int i = 0;
		int j = 0;

		while (alpha.getFitness() != 1) {
			System.out.println(alpha.getVersion() + ' ' + i + " Fitness  " + alpha.fitness);
			col.naturalSelection();
			alpha = col.getFittest();
			i++;
		}

		System.out.println("Final Version " + i + ": " + alpha.getVersion() + "\n");
		
		
	}

	private static HashMap<String, ArrayList<Integer>> read() {
		boolean numberOfTests = false;
		HashMap<String, ArrayList<Integer>> population = new HashMap<>();
		ArrayList<Integer> valuesList = new ArrayList<Integer>();
		Path path = Paths.get("nanoxmltestfaultmatrix.txt");
		try (InputStream sizeCalculator = Files.newInputStream(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(sizeCalculator))) {
			String sizeCalc = null;
			while ((sizeCalc = reader.readLine()) != null) {
				if (!(sizeCalc.contains("unitest1")) && numberOfTests == false) {
					numTests++;

				} else if (sizeCalc.contains("unitest1") && numberOfTests == false) {
					numTests = numTests / 2;
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
			while ((line = reader.readLine()) != null) {
				if (!(line.contains(":"))) {
					line = line.replaceAll("\\s+", "");
					valuesList.add(Integer.parseInt(line));
				}
			}
		} catch (IOException x) {
			System.err.println(x);
		}

		int i = 0;
		int counter = 0;
		while (i < valuesList.size()) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = i; j < (i + numTests); j++) {
				temp.add(valuesList.get(j));
			}
			String name = "UNIT-TEST_" + Integer.toString(counter);
			population.put(name, temp);

			i = i + 9;
			counter++;
		}
		// System.out.println(population);
		return population;

	}

}

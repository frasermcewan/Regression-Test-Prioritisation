import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class HillClimber {

	int collectionSize = 20;
	ArrayList<Chromosome> chromePop = new ArrayList<>();
	HashMap<String, ArrayList<Integer>> mapPop = new HashMap<>();
	ArrayList<Chromosome> neighbours = new ArrayList<>();
	int numberOfFaults = 0;
	Random random = new Random();

	public HillClimber(HashMap<String, ArrayList<Integer>> input, Integer faults) {
		mapPop = input;
		numberOfFaults = faults;

	}

	public void generatePopulation() {
		for (int i = 0; i < collectionSize; i++) {
			chromePop.add(generateChromosome());
		}
	}

	public Chromosome generateChromosome() {
		ArrayList<String> temp = new ArrayList<>();
		ArrayList<Integer> randomValues = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int randomTest = random.nextInt(mapPop.size());
			if (randomValues.contains(randomTest) == true) {
				i = i - 1;
			}
			if (!randomValues.contains(randomTest)) {
				temp.add((String) mapPop.keySet().toArray()[randomTest]);
			}

			randomValues.add(randomTest);
		}

		return new Chromosome(temp, 0.0, numberOfFaults, mapPop);

	}
	
	public void HillClimb() {
		
	}
	
	public void neighbours() {
		
	}
	

}

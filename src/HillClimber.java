import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class HillClimber {

	int collectionSize = 20;
	ArrayList<Chromosome> chromePop = new ArrayList<>();
	HashMap<String, ArrayList<Integer>> mapPop = new HashMap<>();
	ArrayList<Chromosome> neighbours = new ArrayList<>();
	List<List<String>> neighbourStrings = new ArrayList<>();
	int numberOfFaults = 0;
	Random random = new Random();

	public HillClimber(HashMap<String, ArrayList<Integer>> input, Integer faults) {
		mapPop = input;
		numberOfFaults = faults;

	}

	public void generatePopulation() {
		chromePop.add(generateChromosome());
		
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
		Chromosome parent;
		parent = chromePop.get(random.nextInt(chromePop.size()));
		neighbourStrings = generatePerm(parent.getCases());
		
		
	}
	
	public void neighbours() {
		
	}
	
	public List<List<String>> generatePerm(ArrayList<String> original) {
	     if (original.size() == 0) { 
	       List<List<String>> result = new ArrayList<List<String>>();
	       result.add(new ArrayList<String>());
	       return result;
	     }
	     String firstElement = original.remove(0);
	     List<List<String>> returnValue = new ArrayList<List<String>>();
	     List<List<String>> permutations = generatePerm(original);
	     for (List<String> smallerPermutated : permutations) {
	       for (int index=0; index <= smallerPermutated.size(); index++) {
	         List<String> temp = new ArrayList<String>(smallerPermutated);
	         temp.add(index, firstElement);
	         returnValue.add(temp);
	       }
	     }
	     return returnValue;
	   }
	
	
	

}

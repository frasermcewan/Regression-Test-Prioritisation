import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class HillClimber {

	int collectionSize = 20;
	ArrayList<Chromosome> chromePop = new ArrayList<>();
	HashMap<String, ArrayList<Integer>> mapPop = new HashMap<>();
	ArrayList<Chromosome> neighbours = new ArrayList<>();
	List<List<String>> neighbourStrings = new ArrayList<>();
	int numberOfFaults = 0;
	Random random = new Random();
	ArrayList<Chromosome> path = new ArrayList<>();
	HashMap<Integer, ArrayList<String>> unique = new HashMap<>();
	int counterDeath = 5;

	public HillClimber(HashMap<String, ArrayList<Integer>> input, Integer faults) {
		mapPop = input;
		numberOfFaults = faults;
		generatePopulation();
	}

	private void generatePopulation() {
		counterDeath--;
		if(counterDeath >= 0){
			path.clear();
			
			HillClimb(generateChromosome(), true);	
		} 
	}

	private Chromosome generateChromosome() {
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
	
	public void HillClimb(Chromosome chromosome, boolean firstTime) {
		Chromosome parent = chromosome;
		ArrayList<String> parentCases = new ArrayList<>();
		for (int i = 0; i < parent.getCases().size(); i++) {
			parentCases.add(null);
		}
		Collections.copy(parentCases, parent.testValues);
		neighbourStrings = generatePermutations(parentCases);
		
		if(firstTime){
			path.add(parent);
			System.out.println("Parent is\t" + parent.fitness);
			for (int i = 0; i < neighbourStrings.size(); i++) {
				if(neighbourStrings.get(i).size() == 5){
					unique.put(i,(ArrayList<String>) neighbourStrings.get(i));
				}
			}
			for (int i = 0; i < unique.size(); i++) {
				Chromosome newChromosome = new Chromosome(unique.get(i), 0.0, numberOfFaults, mapPop);
				chromePop.add(newChromosome);
			}
			chromePop.add(random.nextInt(chromePop.size()), parent);
		}
	
		for (int i = 0; i < chromePop.size(); i++) {
			double fitness = 0;
			double leftNeighbour = 0;
			double rightNeighbour = 0;
			//System.out.println(parent.getCases() + " " + chromePop.get(i).getCases() + "\n");
			if(parent.getCases() == chromePop.get(i).getCases()){
				fitness = chromePop.get(i).fitness;
				if(i==0){
					rightNeighbour = chromePop.get(i+1).fitness;
					leftNeighbour = -1;
				} else if(i == chromePop.size()-1){
					leftNeighbour = chromePop.get(i-1).fitness;
					rightNeighbour = -1;
				} else {
					rightNeighbour = chromePop.get(i+1).fitness;
					leftNeighbour = chromePop.get(i-1).fitness;
				}
				System.out.println("LEFT="+leftNeighbour+ "\tFITNESS="+fitness + "\tRIGHT="+rightNeighbour);
				if(leftNeighbour > fitness && leftNeighbour >= rightNeighbour){
					path.add(chromePop.get(i-1));
//					printPath();
					HillClimb(chromePop.get(i-1), false);
				} else if(rightNeighbour > fitness && rightNeighbour >= leftNeighbour){
					path.add(chromePop.get(i+1));
//					printPath();
					HillClimb(chromePop.get(i+1), false);
				} else {
					printPath();
					generatePopulation();
				}
			}
		}
	}
	
	private void printPath() {
		for (int i = 0; i < path.size(); i++) {
			System.out.println(path.get(i).fitness);
		}
		System.out.println("Stuck!, starting new path!");
		System.out.println("\n");
		
	}
	
	public List<List<String>> generatePermutations(ArrayList<String> original) {
	     if (original.size() == 0) { 
	       List<List<String>> listOfPermutations = new ArrayList<List<String>>();
	       listOfPermutations.add(new ArrayList<String>());
	       return listOfPermutations;
	     }
	     String firstElement = original.remove(0);
	     List<List<String>> values = new ArrayList<List<String>>();
	     List<List<String>> fullPermutations = generatePermutations(original);
	     for (List<String> smallerPermutations : fullPermutations) {
	       for (int i=0; i <= smallerPermutations.size(); i++) {
	         List<String> tempList = new ArrayList<String>(smallerPermutations);
	         tempList.add(i, firstElement);
	         values.add(tempList);
	       }
	     }
	     return values;
	   }
	
	
	

}

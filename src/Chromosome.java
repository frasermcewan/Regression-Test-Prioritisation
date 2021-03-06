import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
	ArrayList<String> testValues;
	Double fitness;
	Integer numberOfFaults;
	HashMap<String, ArrayList<Integer>> mapPop = new HashMap<>();
	Random random = new Random();

	public Chromosome(ArrayList<String> cases, Double fitnessVal, int numFaults,
		HashMap<String, ArrayList<Integer>> mapOfPopulation) {
		testValues = cases;
		fitness = fitnessVal;
		numberOfFaults = numFaults;
		mapPop = mapOfPopulation;
		setFitness();
	}

	public String getVersion() {
		return String.valueOf(getCases());
	}
	
	public void setFitness() {
		double additionFunction = 0.0;		
		for(int i = 0; i < testValues.size(); i++){
			faultsLoop:
				for(int j = 0; j < numberOfFaults; j++){
				ArrayList<Integer> temp = mapPop.get(testValues.get(i));
				if(temp.get(j) == 1 ){
					additionFunction = additionFunction + j + 1;
					break faultsLoop;
				} else {

				}
			}
		}
		
		double n = testValues.size();
		double m = numberOfFaults;
		double mn = m * n;
		fitness = 1 - (additionFunction/mn) + (1/(2*n));
	}
	
	public Chromosome mutation() {
		ArrayList<String> matrix = getCases();
		int randomPoint1 = random.nextInt(matrix.size());
		int randomPoint2 = random.nextInt(matrix.size());
		String pointOneValue = matrix.get(randomPoint1);
		String pointTwoValue = matrix.get(randomPoint2);
		matrix.set(randomPoint1, pointTwoValue);
		matrix.set(randomPoint2, pointOneValue);
		return new Chromosome(matrix, 0.0, numberOfFaults, mapPop);
	}

	public ArrayList<Chromosome> crossover(Chromosome partner2) {
		ArrayList<String> parent1 = getCases();
		ArrayList<String> parent2 = partner2.getCases();
		int pivotPoint = random.nextInt(parent1.size()-1) + 1; //TO AVOID 0
		int counter = 0;
		ArrayList<String> child1 = new ArrayList<>();
		ArrayList<String> child2 = new ArrayList<>();
		ArrayList<String> tempList = new ArrayList<>();
		ArrayList<Chromosome> returnList = new ArrayList<>();

		
		for (int i = 0; i < parent1.size(); i++) {
			child1.add(i, null);
			child2.add(i, null);

		}
				
		for (int i = 0; i < pivotPoint; i++) { 
			child1.set(i, parent1.get(i));
			child2.set(i, parent2.get(i));

		}

		for (int i = 0; i < parent2.size(); i++) {
			if (!child1.contains(parent2.get(i))) {
				tempList.add(parent2.get(i)); 
			} else {
			}
		}

		for (int i = pivotPoint; i < child1.size()  && counter < child1.size(); i++) {
			child1.set(i, tempList.get(counter));
			counter++;
		}

		tempList.clear();
		counter = 0;

		for (int x = 0; x < parent1.size(); x++) {
			if (!child2.contains(parent1.get(x))) {
				tempList.add(parent1.get(x));
			}
		}

		for (int j = pivotPoint; j < child2.size() && counter < child2.size(); j++) {
			child2.set(j, tempList.get(counter));
			counter++;
		}

		tempList.clear();
		counter = 0;
	
		returnList.add(new Chromosome((child1), 0.0, numberOfFaults, mapPop));
		returnList.add(new Chromosome((child2), 0.0, numberOfFaults, mapPop));
		
		return returnList;
	}

	public Double getFitness() {
		return fitness;
	}

	
	public ArrayList<Chromosome> neighbourList() {
		ArrayList<Chromosome> neighbours = new ArrayList<Chromosome>();
		
		return neighbours;
	}
	
	public ArrayList<String> getCases() {
		return testValues;
	}

	public ArrayList<String> populateArrayLists(ArrayList<String> in) {
		ArrayList<String> populateList = new ArrayList<String>();
		for(int i =0; i < in.size(); i++) {
			populateList.set(i, null);
		}
		return populateList;
		
	}
	
	@Override
	public int compareTo(Chromosome o) {
		if (fitness < o.fitness) {
			return -1;
		} else if (fitness > o.fitness) {
			return 1;
		} else {
			return 0;
		}
	}

}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
	private ArrayList<String> testValues;
	private Double fitness;
	private Integer numberOfFaults;
	private HashMap<String, ArrayList<Integer>> mapPop = new HashMap<>();
	Random random = new Random();
	
	public Chromosome(ArrayList<String> cases, Double fitnessVal, int numFaults, HashMap<String, ArrayList<Integer>> mapOfPopulation) {
		testValues = cases;
		fitness = fitnessVal;
		numberOfFaults = numFaults;
		mapPop = mapOfPopulation;
		setFitness();
	}
	
	public void setFitness() {
		double additionFunction = 0.0;
		
		for(int i = 0; i < numberOfFaults; i++){//number of faults
			for(int j = 0; j < testValues.size(); j++){ //number of test cases
				ArrayList<Integer> temp = mapPop.get(testValues.get(j));
				if(temp.get(i) == 1){
					additionFunction = additionFunction + (i+1);
					break;
				}
			}
		}
		
		fitness = (1-(additionFunction/(numberOfFaults*5))+(1/(2*numberOfFaults)));
		
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
	
	public Chromosome[] crossover(Chromosome partner2) {
		ArrayList<String> parent1 = getCases();
		ArrayList<String> parent2 = partner2.getCases();
		int pivotPoint = random.nextInt(parent1.size());
		int counter = 0;
		ArrayList<String> child1 = new ArrayList<>();
		ArrayList<String> child2 = new ArrayList<>();
		ArrayList<String> tempList = new ArrayList<>();
		
		for (int k=0; k<=pivotPoint; k++) {
			child1.set(k, parent1.get(k));
			child2.set(k, parent2.get(k));
		}
		
		
		for (int j =0; j < pivotPoint; j++) {
			for (int x=0; x< parent2.size(); x++) {
				
				if (!(parent2.get(j).equals(child1.get(x)))) {
					   tempList.add(parent2.get(j));   
			   }
			   
			}
			
			
		}
			
		for (int j = pivotPoint; j < child1.size(); j++) {
			child1.set(j, tempList.get(counter));
			counter++;
		}
		
		tempList.clear();
		counter = 0;
		
		
		
			for (int x=0; x< parent2.size(); x++) {
				if (!child1.contains(parent2.get(x))) {
					tempList.add(parent2.get(x));
				}
				
			}
			
			
		for (int j = pivotPoint; j < child1.size(); j++) {
			child1.set(j, tempList.get(counter));
			counter++;
		}
		
		tempList.clear();
		counter = 0;
		
		

		
	
			for (int x=0; x< parent1.size(); x++) {
				if (!child2.contains(parent1.get(x))) {
					tempList.add(parent1.get(x));
				   
			   }
				
			}
				
		for (int j = pivotPoint; j < child1.size(); j++) {
			child2.set(j, tempList.get(counter));
			counter++;
		}

		tempList.clear();
		counter = 0;
		
		return new Chromosome[] { new Chromosome((child1), 0.0, numberOfFaults, mapPop), new Chromosome((child2), 0.0, numberOfFaults, mapPop) };
	}
	
	
	
	public Double getFitness() {
		return fitness;
	}

	
	public ArrayList<String> getCases() {
		return testValues;
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

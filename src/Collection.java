import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Collection {	
	int tournamentSize = 5;
	int collectionSize = 1000;
	double mutationRatio = 0.02;
	boolean increaseMutation = false;
	boolean increaseMutation2 = false;
	double selectionRatio = 0.85;
	double elitismRatio = 0.2;
	String targetString = "Hello, world!";
	Random random = new Random();
	ArrayList<Chromosome> chromePop = new ArrayList<>();
	HashMap<String, ArrayList<Integer>> mapPop = new HashMap<>();
	int numberOfFaults = 0;

	public Collection(HashMap<String, ArrayList<Integer>> input, Integer faults) {
		mapPop = input;
		numberOfFaults = faults;
		generatePopulation();
	}


	public void generatePopulation() {
		for (int i = 0; i < 20; i++) {
			chromePop.add(generateChromosome());
		}
		
	}
	
	public Chromosome generateChromosome() {
		ArrayList<String> temp = new ArrayList<>();
		ArrayList<Integer> randomValues = new ArrayList<>();
		for(int i = 0; i < 5; i ++) {
			int randomTest = random.nextInt(mapPop.size());
			if (randomValues.contains(randomTest) == true) {
				i = i -1;
			}
			if(!randomValues.contains(randomTest)) {
				temp.add((String) mapPop.keySet().toArray()[randomTest]);	
			} 
						
			randomValues.add(randomTest);
		}
		double fitness = setFitness(temp); 
		System.out.println("New Chromosome - " + fitness);
		for (int i=0; i< temp.size(); i ++ ) {
			System.out.println(temp.get(i));
		}
		System.out.println("\n");
		return new Chromosome(temp, 0);
		//return new Chromosome(temp, setFitness(temp)); 
	}

	private Double setFitness(ArrayList<String> input){
		double fitness = 0;
		double additionFunction = 0;
		
		for(int i = 0; i < numberOfFaults; i++){//number of faults
			for(int j = 0; j < input.size(); j++){ //number of test cases
				ArrayList<Integer> temp = mapPop.get(input.get(j));
				if(temp.get(i) == 1){
					additionFunction = additionFunction + (i+1);
					break;
				}
			}
		}
		//System.out.println(additionFunction);
		/*
		0/0/0/0/0/0/1/0/0
		0/0/0/0/0/0/1/0/0
		0/0/0/0/0/0/1/0/0
		0/0/0/0/0/0/1/0/0
		0/0/0/0/0/0/1/0/0
		WILL = 7*/
		fitness = (1-(additionFunction/(numberOfFaults*5))+(1/(2*numberOfFaults)));
		return fitness;
	}
	
//	public void naturalSelection() {
//		GeneticAlg[] temporaryList = new GeneticAlg[arrayCollection.length];
//		int elitismPoint = (int) Math.round(arrayCollection.length * elitismRatio);
//		for (int i = 0; i <= elitismPoint; i++) {
//			temporaryList[i] = arrayCollection[i];
//
//		}
//		
//		while (elitismPoint < temporaryList.length) {
//
//			if (getFittest().fitness <= 5 && increaseMutation == false) {
//				mutationRatio = mutationRatio * 10;
//				increaseMutation = true;
//			}
//			
//			if (getFittest().fitness <=1 && increaseMutation2 == false) {
//				mutationRatio = mutationRatio * 25;
//				increaseMutation2 = true;
//			}
//	
//			if (random.nextDouble() <= selectionRatio) {		
//				GeneticAlg[] parentVersions = new GeneticAlg[2];
//				parentVersions[0] = tournament();
//				parentVersions[1] = tournament();
//				GeneticAlg[] childrenVersions = parentVersions[0].crossover(parentVersions[1]);
//
//				if (random.nextDouble() <= mutationRatio) {
//				
//					temporaryList[elitismPoint++] = childrenVersions[0].mutation();
//				} else {
//					temporaryList[elitismPoint++] = childrenVersions[0];
//				}
//
//				if (elitismPoint < temporaryList.length) {
//					if (random.nextDouble() <= mutationRatio) {
//						temporaryList[elitismPoint] = childrenVersions[1].mutation();
//					} else {
//						temporaryList[elitismPoint] = childrenVersions[1];
//					}
//				}
//				
//			} 
//			
//			else {
//				if (random.nextDouble() <= mutationRatio) {
//					temporaryList[elitismPoint] = arrayCollection[elitismPoint].mutation();
//				} else {
//					temporaryList[elitismPoint] = arrayCollection[elitismPoint];
//				}
//			}
//
//			++elitismPoint;
//		}
//		Arrays.sort(temporaryList);
//		arrayCollection = temporaryList;
//
//	}
//
//	public GeneticAlg getFittest() {
//		return arrayCollection[0];
//	}
//
//	public int Fitness() {
//		return arrayCollection[0].fitness;
//
//	}
//
//
//	
//	
//	public GeneticAlg tournament() {
//		GeneticAlg parent;
//		parent = arrayCollection[random.nextInt(arrayCollection.length)];
//		for (int j = 0; j < tournamentSize; j++) {
//			int point = random.nextInt(arrayCollection.length);
//			if (arrayCollection[point].compareTo(parent) < 0) {
//				parent = arrayCollection[point];
//			}
//		}
//
//		return parent;
//	}
//	
//	

}

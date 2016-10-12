import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Collection {
	int tournamentSize = 5;
	int collectionSize = 20;
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

	
	public ArrayList<Chromosome> populateArrayLists(ArrayList<String> in) {
		ArrayList<Chromosome> populateList = new ArrayList<Chromosome>();
		for(int i =0; i < in.size(); i++) {
			populateList.set(i, null);
		}
		return populateList;
		
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

	public void naturalSelection() {
		ArrayList<Chromosome> temporaryList = new ArrayList<Chromosome>();
		
		int elitismPoint = (int) Math.round(chromePop.size() * elitismRatio);
		for (int j =0; j < chromePop.size(); j++) {
			temporaryList.add(j, null);
		}
		
		for (int i = 0; i <= elitismPoint; i++) {
			temporaryList.set(i, chromePop.get(i));
		}
		
		while (elitismPoint < temporaryList.size()) {

			if (random.nextDouble() <= selectionRatio) {
				ArrayList<Chromosome> parentVersions = new ArrayList<>();
				for (int i = 0; i <2; i ++){
					parentVersions.add(i, null);
				}
				parentVersions.set(0,tournament());
				parentVersions.set(1, tournament());
				
				ArrayList<Chromosome>childrenVersions = new ArrayList<>();
				for (int i = 0; i <2; i ++){
					childrenVersions.add(i, null);
				}
				
				childrenVersions = parentVersions.get(0).crossover(parentVersions.get(1));

				if (random.nextDouble() <= mutationRatio) {
					temporaryList.set(elitismPoint,childrenVersions.get(0).mutation());//TOOK OUT elitismPoint++
				} else {
					temporaryList.set(elitismPoint, childrenVersions.get(0));//TOOK OUT elitismPoint++
				}

				if (elitismPoint < temporaryList.size()) {
					if (random.nextDouble() <= mutationRatio) {
						temporaryList.set(elitismPoint, childrenVersions.get(1).mutation());
					} else {
						temporaryList.set(elitismPoint, childrenVersions.get(1));
					}
				}

			} else {
				if (random.nextDouble() <= mutationRatio) {
					temporaryList.set(elitismPoint, chromePop.get(elitismPoint).mutation());
				} else {
					temporaryList.set(elitismPoint, chromePop.get(elitismPoint));
				}
			}

			++elitismPoint;
		}
		Collections.sort(temporaryList);
		Collections.reverse(temporaryList);
		chromePop = temporaryList;
	}

	public Chromosome getFittest() {
		return chromePop.get(0);
	}
	
	public ArrayList<Chromosome> getCollection() {
		return chromePop;
	}
	
	public Double Fitness() {
	 return chromePop.get(0).fitness;
	}
	
	public Double getFintess(int pos){
		return chromePop.get(pos).fitness;
	}

	public Chromosome tournament() {
		Chromosome parent;
		parent = chromePop.get(random.nextInt(chromePop.size()));
		for (int j = 0; j < tournamentSize; j++) {
			int point = random.nextInt(chromePop.size());
			if (chromePop.get(point).compareTo(parent) > 0) {
				parent = chromePop.get(point);
			}
		}
		return parent;
	}

}

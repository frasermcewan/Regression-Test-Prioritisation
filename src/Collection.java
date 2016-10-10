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

	public void generatePopulation() {
		for (int i = 0; i < collectionSize; i++) {
			chromePop.add(generateChromosome());
		}

//		for (int j = 0; j < chromePop.size(); j++) {
//			System.out.println(chromePop.get(j).getFitness());
//		}
		
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
		ArrayList<Chromosome> temporaryList = new ArrayList<>();
		;
		int elitismPoint = (int) Math.round(chromePop.size() * elitismRatio);
		for (int j =0; j <= elitismPoint; j++) {
			temporaryList.add(j, null);
		}
		for (int i = 0; i <= elitismPoint; i++) {
			temporaryList.set(i, chromePop.get(i));
		}

		while (elitismPoint < temporaryList.size()) {

			if (getFittest().fitness <= 5 && increaseMutation == false) {
				mutationRatio = mutationRatio * 10;
				increaseMutation = true;
			}

			if (getFittest().fitness <= 1 && increaseMutation2 == false) {
				mutationRatio = mutationRatio * 25;
				increaseMutation2 = true;
			}

			if (random.nextDouble() <= selectionRatio) {
				ArrayList<Chromosome> parentVersions = new ArrayList<>();
				parentVersions.set(0,tournament());
				parentVersions.set(1, tournament());
				ArrayList<Chromosome>childrenVersions = parentVersions.get(0).crossover(parentVersions.get(1));

				if (random.nextDouble() <= mutationRatio) {

					temporaryList.set(elitismPoint++,childrenVersions.get(0).mutation());
				} else {
					temporaryList.set(elitismPoint++, childrenVersions.get(0));
				}

				if (elitismPoint < temporaryList.size()) {
					if (random.nextDouble() <= mutationRatio) {
						temporaryList.set(elitismPoint, childrenVersions.get(1).mutation());
					} else {
						temporaryList.set(elitismPoint, childrenVersions.get(1));
					}
				}

			}

			else {
				if (random.nextDouble() <= mutationRatio) {
					temporaryList.set(elitismPoint, chromePop.get(elitismPoint).mutation());
				} else {
					temporaryList.set(elitismPoint, chromePop.get(elitismPoint));
				}
			}

			++elitismPoint;
		}
		Collections.sort(temporaryList);
		chromePop = temporaryList;

	}

	public Chromosome getFittest() {
		return chromePop.get(0);
	}

	public Double Fitness() {
	 return chromePop.get(0).fitness;
	}

	public Chromosome tournament() {
		Chromosome parent;
		parent = chromePop.get(random.nextInt(chromePop.size()));
		for (int j = 0; j < tournamentSize; j++) {
			int point = random.nextInt(chromePop.size());
			if (chromePop.get(point).compareTo(parent) < 0) {
				parent = chromePop.get(point);
			}
		}

		return parent;
	}

}

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlg implements Comparable<GeneticAlg> {
	Random random = new Random();
	ArrayList<Integer> chromoIn = new ArrayList<>();
	ArrayList<Chromosome> List = new ArrayList<>();
	int fitness = 0;
	int testLength = 0;
	String testName;
	char[] targetStringinArray = "Hello, world!".toCharArray();

	public GeneticAlg(ArrayList<Integer> matrix) {
		this.testLength = matrix.size();
		this.chromoIn = matrix;
		setFitness(chromoIn);
	}

	public String getVersion() {
		return String.valueOf(chromoIn);
	}
	
	public void generateObjects() {
		ArrayList<Integer> matrix = new ArrayList<>();
		ArrayList <Integer> valueList = readFile();
		int i = 0; 
		while(i < valueList.size()) {
			for (int j = i; j<8; j++) {
				matrix.add(valueList.get(j));
				
			}
		System.out.println(matrix);	
		GeneticAlg alg = new GeneticAlg(matrix);
		arrayListCollection.add(alg);
		matrix.clear();
		i = i +8;
		}
		
	}

	public void setFitness(ArrayList<Integer>chromo) {
		for (int i = 0; i < chromo.size(); i++) {
			fitness += Math.abs(((int) chromo.get(i) - ((int) targetStringinArray[i])));
		}

	}

	public int returnFitness() {
		return fitness;
	}

	public char[] toCharArray(String In) {
		char[] convertorArray;
		convertorArray = In.toCharArray();
		return convertorArray;
	}

	public Chromosome mutation() {
		ArrayList<Integer> matrix = chromoIn;
		int randomPoint1 = random.nextInt(matrix.size());
		int randomPoint2 = random.nextInt(matrix.size());
		int pointOneValue = matrix.get(randomPoint1);
		int pointTwoValue = matrix.get(randomPoint2);
		matrix.set(randomPoint1, pointTwoValue);
		matrix.set(randomPoint2, pointOneValue);
		return new Chromosome(matrix);

	}

	public Chromosome[] crossover(GeneticAlg partner) {
		ArrayList<Integer> parent1 = chromoIn;
		ArrayList<Integer> parent2 = partner.chromoIn;
		int pivotPoint = random.nextInt(parent1.size());
		
		ArrayList<Integer> child1 = new ArrayList<>();
		ArrayList<Integer> child2 = new ArrayList<>();
		
		for (int k=0; k<=pivotPoint; k++) {
			child1.set(k, parent1.get(k));
			child2.set(k, parent2.get(k));
		}
		
		
		for (int j = pivotPoint; j < child1.size(); j++) {
			for (int x=0; x< parent2.size(); x++) {
				
			}
			
		}

		return new Chromosome[] { new Chromosome((child1)), new Chromsome((child2)) };
	}
		

	@Override
	public int compareTo(GeneticAlg o) {
		if (fitness < o.fitness) {
			return -1;
		} else if (fitness > o.fitness) {
			return 1;
		} else {
		return 0;
		}

	}

}

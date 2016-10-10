import java.util.ArrayList;
import java.util.Random;

public class GeneticAlg {
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


	public void setFitness(ArrayList<Integer>chromo) {
		for (int i = 0; i < chromo.size(); i++) {
			fitness += Math.abs(((int) chromo.get(i) - ((int) targetStringinArray[i])));
		}

	}

	public int returnFitness() {
		return fitness;
	}
	
//	public Chromosome mutation(Chromosome in) {
//		ArrayList<String> matrix = in.getCases();
//		int randomPoint1 = random.nextInt(matrix.size());
//		int randomPoint2 = random.nextInt(matrix.size());
//		String pointOneValue = matrix.get(randomPoint1);
//		String pointTwoValue = matrix.get(randomPoint2);
//		matrix.set(randomPoint1, pointTwoValue);
//		matrix.set(randomPoint2, pointOneValue);
//		return new Chromosome(matrix, 0);
//
//	}

//	public Chromosome[] crossover(Chromosome partner1, Chromosome partner2) {
//		ArrayList<String> parent1 = partner1.getCases();
//		ArrayList<String> parent2 = partner2.getCases();
//		int pivotPoint = random.nextInt(parent1.size());
//		int counter = 0;
//		ArrayList<String> child1 = new ArrayList<>();
//		ArrayList<String> child2 = new ArrayList<>();
//		ArrayList<String> tempList = new ArrayList<>();
//		
//		for (int k=0; k<=pivotPoint; k++) {
//			child1.set(k, parent1.get(k));
//			child2.set(k, parent2.get(k));
//		}
//		
//		
//		for (int j =0; j < pivotPoint; j++) {
//			for (int x=0; x< parent2.size(); x++) {
//				
//				if (!(parent2.get(j).equals(child1.get(x)))) {
//					   tempList.add(parent2.get(j));   
//			   }
//			   
//			}
//			
//			
//		}
//			
//		for (int j = pivotPoint; j < child1.size(); j++) {
//			child1.set(j, tempList.get(counter));
//			counter++;
//		}
//		
//		tempList.clear();
//		counter = 0;
//		
//		
//		
//			for (int x=0; x< parent2.size(); x++) {
//				if (!child1.contains(parent2.get(x))) {
//					tempList.add(parent2.get(x));
//				}
//				
//			}
//			
//			
//		for (int j = pivotPoint; j < child1.size(); j++) {
//			child1.set(j, tempList.get(counter));
//			counter++;
//		}
//		
//		tempList.clear();
//		counter = 0;
//		
//		
//
//		
//	
//			for (int x=0; x< parent1.size(); x++) {
//				if (!child2.contains(parent1.get(x))) {
//					tempList.add(parent1.get(x));
//				   
//			   }
//				
//			}
//				
//		for (int j = pivotPoint; j < child1.size(); j++) {
//			child2.set(j, tempList.get(counter));
//			counter++;
//		}
//
//		tempList.clear();
//		counter = 0;
//		
//		return new Chromosome[] { new Chromosome((child1), 0), new Chromosome((child2), 0) };
//	}
		

}

import java.util.ArrayList;

public class Chromosome implements Comparable<Chromosome> {
	private ArrayList<String> testValues;
	private Integer fitness;
	
	public Chromosome( ArrayList<String> cases, Integer fitnessVal) {
		testValues = cases;
		fitness = fitnessVal;
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

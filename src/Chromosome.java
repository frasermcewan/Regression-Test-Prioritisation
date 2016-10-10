import java.util.ArrayList;

public class Chromosome {
	private ArrayList<String> testValues;
	private Integer fitness;
	
	public Chromosome( ArrayList<String> cases, Integer fitnessVal) {
		testValues = cases;
		fitness = fitnessVal;
	}
	
	
	public ArrayList<String> getCases() {
		return testValues;
	}
	
	
	
}

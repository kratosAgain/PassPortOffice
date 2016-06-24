package passportoffice;

public class MainClass {
	//main class to run 5 test cases
	public static void main(String[] args){
		ComparisonSuite compare = new ComparisonSuite();
		
		//lets take ananlysis for 5 run times
		
		for(int i=1;i<=5;i++){
			System.out.println("\nTest "+i+"\n");
			compare.compare();
		}
	}
}

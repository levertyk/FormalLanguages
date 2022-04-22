import java.util.Scanner;

public class mwgcCompute {
	static Scanner sc = new Scanner(System.in);
	
	final int[][] lookUp = {
			{10, 10, 1, 10},
			{2, 10, 0, 10},
			{1, 4, 10, 3},
			{10, 10, 5, 2},
			{10, 2, 6, 10},
			{10, 7, 3, 10},
			{10, 10, 4, 7},
			{8, 5, 10, 6},
			{7, 10, 9, 10},
			{10, 10, 8, 10},
			{10, 10, 10, 10}
	};
	static enum item { m, w, g, c};
	
	int currentState;
	int acceptedState;
	String input;
	
	public mwgcCompute() {
		currentState = 0;
		acceptedState = 9;
		input = "";
	}
	
	public void setInput(String userInput) {
		input = userInput;
	}
	
	public boolean getResult() {
		boolean output = false;
		
		for(int i = 0; i < input.length(); i++) {
			String inputSub = input.substring(i, i + 1);
			currentState = lookUp[currentState][ item.valueOf(inputSub).ordinal() ];
		}
		
		output = currentState == acceptedState;
		
		return output;
	}
	
	public static void main(String[] args) {
		mwgcCompute compute = new mwgcCompute();
		System.out.println("Please input your proposed solution:");
		
		compute.setInput(sc.nextLine());
		
		if(compute.getResult()) {
			System.out.println("This is a solution.");
		} else {
			System.out.println("This is not a solution.");
		}
	}
}

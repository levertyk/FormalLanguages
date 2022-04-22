
import java.util.Scanner;
import java.lang.String;

public class NFA {
	
	static Scanner sc = new Scanner(System.in);
	
	static String stateDiagram[][] = {
		{ "000010010", "000110111" },
		{ "000000010", "000010111" },
		{ "000011011", "000010011" },
		{ "000010111", "000001000" },
		{ "000010000", "000100000" },
		{ "001010011", "010000000" },
		{ "100010011", "000110111" },
		{ "000100000", "001010011" },
		{ "010000000", "100000000" }
	};
	
	static String startingStates = "000010011";
	static String acceptingStates = "001000100";
	static String currentState = startingStates;
	static boolean accepted = false;
	
	static String or(String a, String b) {
		String output = "";
		for(int i = 0; i < a.length(); i++) {
			output += (Character.getNumericValue(a.charAt(i)) + Character.getNumericValue(b.charAt(i)) > 0) ? "1" : "0";
		}
		return output;
	}
	
	static String and(String a, String b) {
		String output = "";
		for(int i = 0; i < a.length(); i++) {
			output += (a.charAt(i) == b.charAt(i)) ? a.charAt(i) : "0";
		}
		return output;
	}
	
	static boolean compute(String input) {
		for(int i = input.length() - 1; i >= 0; i--) {
			String tempState = currentState;
			currentState = "000000000";
			
			if(input.charAt(i) == '0') {
				for(int j = 0; j < tempState.length(); j++) {
					currentState = or(currentState, stateDiagram[Character.getNumericValue(tempState.charAt(j))][0]);
				}
			} else {
				for(int j = 0; j < tempState.length(); j++) {
					currentState = or(currentState, stateDiagram[Character.getNumericValue(tempState.charAt(j))][1]);
				}
			}
		}
		
		for(int i = 0; i < currentState.length(); i++) {
			accepted |= (acceptingStates.charAt(i) == '1') && (currentState.charAt(i) == '1');
		}
		
		return accepted;
	}
	
	public static void main(String[] args) {
		System.out.println("Enter the binary number to process:");
		
//		String input = sc.nextLine();
//		String input2 = sc.nextLine();
//		
//		System.out.println(or(input, input2));
		
		boolean result = compute(sc.nextLine());
		
		if(result) {
			System.out.println("ACCEPTED\nThe binary number can be partitioned into substrings such that each substring has value congruent to 1 mod 3 or 2 mod 5.");
		} else {
			System.out.println("REJECTED\nThe binary number can NOT be partitioned into substrings such that each substring has value congruent to 1 mod 3 or 2 mod 5.");
		}
	}
}

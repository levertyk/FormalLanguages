import java.util.Scanner;

public class Backtracking_NFA {
	static Scanner sc = new Scanner(System.in);
	
	private static int[][][] delta = {
			{{0, 1}, {0}},
			{{2}, {}},
			{{2}, {2}}
			
	};
	
	private static boolean accepts (int s, String in, int pos) {
		if(pos == in.length()) {
			return (s == 2);
		}
		
		char c = in.charAt(pos++);
		int[] nextStates;
		
		try {
			nextStates = delta[s][c - '0'];
		} catch (ArrayIndexOutOfBoundsException ex) {
			return false;
		}
		
		for(int i = 0; i < nextStates.length; i++) {
			if (accepts(nextStates[i], in, pos)) return true;
		}
		
		return false;
	}
	
	public static boolean accepts(String in) {
		return accepts(0, in, 0);
	}

	public static void main(String[] args) {
		System.out.println("Enter a string to test");
		
		String input = sc.nextLine();
		
		input = input.replaceAll("a", "0");
		input = input.replaceAll("b", "1");
		
		if (accepts(input))
			System.out.println("The string is accepted");
		else
			System.out.println("The string is not accepted");

	}
}

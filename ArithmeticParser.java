import java.util.Stack;
import java.util.Scanner;

public class ArithmeticParser {
	
    static String[][] ll_table = {
            //+       -       *       /       (       )       double  $
             {""     ,""     ,""     ,""     ,"rT"   ,""     ,"rT"   ,""},  // t->
             {"+rT"  ,"-rT"  ,""     ,""     ,""     ,"e"    ,""     ,"e"}, // T->
             {""     ,""     ,""     ,""     ,"vR"   ,""     ,"vR"   ,""},  // r->
             {"e"    ,"e"    ,"*vR"  ,"/vR"  ,""     ,"e"    ,""     ,"e"}, // R->
             {""     ,""     ,""     ,""     ,"(t)"  ,""     ,"d"    ,""}   // v->
        };

	enum Tokens {
		PLUS_TOKEN(0), MINUS_TOKEN(1), MULT_TOKEN(2), DIV_TOKEN(3), LP_TOKEN(4), RP_TOKEN(5), DOUBLE_TOKEN(6), EOF_TOKEN(7), t_NT(0), T_NT(1), r_NT(2),
		R_NT(3), v_NT(4);
		
		private int index;
		
		Tokens(int index) {
		      this.index = index;
		   }
		
	   public int getIndex() {
	      return this.index;
	   }
	};
	
	Tokens tokens[] = Tokens.values();

	static String inputExp = "";
	static String math_exp = "";
	static Scanner sc = new Scanner(System.in);
	static Stack<Character> stack_machine = new Stack<>();

	public static void main(String[] args) {
		System.out.println("Enter an arithmetic expression: ");
		inputExp = sc.next();

		math_exp = inputExp.replaceAll("(\\d+\\.?\\d*|\\.\\d+)", "d");
		
		boolean valid = true;
		stack_machine.push('$');
		stack_machine.push('T');
		stack_machine.push('r');
		
		for(int i = 0; i < math_exp.length(); i++) {
			char popTop = stack_machine.pop();
			char indexChar = math_exp.charAt(i);
			int column;
			int row;
			String pushString = "";
			
			switch (indexChar){
				case '+': column = 0; break;
				case '-': column = 1; break;
				case '*': column = 2; break;
				case '/': column = 3; break;
				case '(': column = 4; break;
				case ')': column = 5; break;
				case 'd': column = 6; break;
				case '$': column = 7; break;
				default: System.out.println("ERROR: Bad Character Entered\n\n" + errorOutput(math_exp, i)); column = -1; valid = false; 
			}
			
			while(indexChar != popTop && valid) {
				switch (popTop) {
					case 't': row = 0; break;
					case 'T': row = 1; break;
					case 'r': row = 2; break;
					case 'R': row = 3; break;
					case 'v': row = 4; break;
					default : System.out.println("ERROR: Invalid arithmetic expression\n\n" + errorOutput(math_exp, i)); row = -1; valid = false;
				}
				
				pushString = ll_table[row][column];
				
				if(pushString.length() == 0) {
					System.out.println("ERROR: Invalid arithmetic expression\n\n" + errorOutput(math_exp, i));
					valid = false;
					break;
				}

				if (stack_machine.empty()) {
					System.out.println("ERROR: Invalid arithmetic expression\n\n" + errorOutput(math_exp, i));
					valid = false;
					break;
				}

				if (pushString.charAt(0) != 'e') {
					for (int j = pushString.length() - 1; j >= 0; j--) {
						stack_machine.push(pushString.charAt(j));
					}
				}

				popTop = stack_machine.pop();
			}
		}

		if(valid){
			System.out.println("The Expression is valid");
		}
	}

	private static String errorOutput(String input, int i){
		String output = "";

		output += "Error location:\n" + input.substring(0, i) + ">" + input.substring(i,input.length());

		return output;
	}

}

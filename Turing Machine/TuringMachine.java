import java.util.Scanner;

public class TuringMachine {

    Move turingTable[][] = {
        //  B   a   b   t   u   v   w   X   Y
        { new Move('B', 'R', 11), new Move('t', 'R', 1), new Move('u', 'R', 1), new Move('t', 'L', 4), new Move('u', 'L', 4) },
        { new Move('B', 'L', 2), new Move('a', 'R', 1), new Move('b', 'R', 1), null, null, new Move('v', 'L', 2), new Move('w', 'L', 2) },
        { null, new Move('v', 'L', 3), new Move('w', 'L', 3) },
        { null, new Move('a', 'L', 3), new Move('b', 'L', 3) },
        { new Move('B', 'R', 5), null, null, new Move('t', 'L', 4), new Move('u', 'L', 4) },
        { null, null, null, new Move('X', 'R', 6), new Move('X', 'R', 8), null, null, null, new Move('Y', 'R', 10) },
        { null, null, null, new Move('t', 'R', 6), new Move('u', 'R', 6), new Move('Y', 'L', 7) },
        { null, null, null, new Move('t', 'L', 7), new Move('u', 'L', 7), null, null, new Move('X', 'R', 5) },
        { null, null, null, null, new Move('1', '1', 9) },
        { null, null, null, new Move('t', 'R', 9), new Move('u', 'R', 9), null, new Move('Y', 'L', 7), null, new Move('Y', 'R', 9) },
        { new Move('B', 'R', 11), null, null, null, null, null, null, null, new Move('Y', 'R', 10) }
    };

    Tape tape = new Tape();
    int state;
    boolean halt = false;

    TuringMachine(String input) {
        tape.loadData(input);
        process();
    }

    private void process() {
        
        while(!halt && state != 11) {
            Move tempMove = null;

            try{
                tempMove = turingTable[state][tape.getData()];
            } catch(Exception e) {
                halt = true;
                break;
            }

            if(tempMove == null) {
                halt = true;
                break;
            } else {
                if(tempMove.getDirection() == 'R') {
                    tape.setData(tempMove.getReplaceCharacter());
                    tape.moveRight();
                    state = tempMove.getNextState();
                } else if (tempMove.getDirection() == 'L') {
                    tape.setData(tempMove.getReplaceCharacter());
                    tape.moveLeft();
                    state = tempMove.getNextState();
                } else {
                    state = tempMove.getNextState();
                }
            }
        }
    }

    public String toString() {
        if(halt) return "Turing Machine halted, the string is not in the language.";
        else if (state == 11) return"The string is in the language";
        else return "Turing Machine failed, the string is not in the language.";
    }
    
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);

        System.out.println("Enter the string to test {XX} with the alphabet {a, b}*");
        String input = sc.next();

        sc.close();

        TuringMachine machine = new TuringMachine(input);

        System.out.println(machine.toString());
    }
}

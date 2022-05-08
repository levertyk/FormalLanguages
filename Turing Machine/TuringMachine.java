import java.util.Scanner;

public class TuringMachine {
    int state;
    boolean halt = false;

    char tempInput[];
    int position = 1;

    TuringMachine(String input) {
        tempInput = ("$" + input + "$").toCharArray();
        process();
    }

    private void process() {

        while(!halt && state != 9) {

            char temp = tempInput[position];

            switch(state) {
                case 0: {
                    if(temp == 'a') {
                        tempInput[position] = 'X';
                        position++;
                        state = 1;
                    } else if(temp == 'b') {
                        tempInput[position] = 'Y';
                        position++;
                        state = 1;
                    } else if(temp == 'X' || temp == 'Y') {
                        position--;
                        state = 4;
                    } else halt = true;
                    break;
                }
                case 1: {
                    if(temp == 'a' || temp == 'b') {
                        position++;
                    } else if(temp == 'Y' || temp == 'X' || temp == '$') {
                        position--;
                        state = 2;
                    } else halt = true;
                    break;
                }
                case 2: {
                    if(temp == 'a') {
                        tempInput[position] = 'X';
                        position--;
                        state = 3;
                    } else if(temp == 'b') {
                        tempInput[position] = 'Y';
                        position--;
                        state = 3;
                    } else halt = true;
                    break;
                }
                case 3: {
                    if(temp == 'a' || temp == 'b') {
                        position--;
                    } else if(temp == 'X' || temp == 'Y') {
                        position++;
                        state = 0;
                    } else halt = true;
                    break;
                }
                case 4: {
                    if(temp == '$') {
                        position++;
                        state = 5;
                    } else if(temp == 'X') {
                        tempInput[position] = 'a';
                        position--;
                    } else if(temp == 'Y') {
                        tempInput[position] = 'b';
                        position--;
                    } else halt = true;
                    break;
                }
                case 5: {
                    if(temp == 'b') {
                        tempInput[position] = 'Y';
                        position++;
                        state = 7;
                    } else if(temp == 'a') {
                        tempInput[position] = 'X';
                        position++;
                        state = 6;
                    } else if(temp == 'B'){
                        position--;
                        state = 9;
                    } else halt = true;
                    break;
                }
                case 6: {
                    if(temp == 'a' || temp == 'b' || temp == 'B') {
                        position++;
                    } else if(temp == 'X') {
                        tempInput[position] = 'B';
                        position--;
                        state = 8;
                    } else halt = true;
                    break;
                }
                case 7: {
                    if(temp == 'a' || temp == 'b' || temp == 'B') {
                        position++;
                    } else if(temp == 'Y') {
                        tempInput[position] = 'B';
                        position--;
                        state = 8;
                    } else halt = true;
                    break;
                }
                case 8: {
                    if(temp == 'a' || temp == 'b' || temp == 'B') {
                        position--;
                    } else if(temp == 'X' || temp == 'Y') {
                        position++;
                        state = 5;
                    } else halt = true;
                    break;
                }
            }
        }
    }

    public String toString() {
        if(halt) return "Turing Machine halted, the string is not in the language.";
        else if (state == 9) return"The string is in the language";
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

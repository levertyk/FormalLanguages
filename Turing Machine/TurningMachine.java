import java.util.Scanner;

public class TurningMachine {

    Tape tape = new Tape();

    TuringMachine(String input) {

    }
    
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);

        System.out.println("Enter the string to test {XX}");
        String input = sc.next();

        TuringMachine machine = new TuringMachine();

        System.out.println(machine.toString());
    }
}

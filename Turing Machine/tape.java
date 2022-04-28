import java.util.ArrayList;

public class Tape {
    
    ArrayList<Node> tape;
    int pointer;

    Tape() {
        tape = new ArrayList<Node>();
        pointer = 0;
    }

    Tape(String input) {
        tape = new ArrayList<Node>();
        pointer = 0;
        loadData(input);
    }

    public void loadData(String data) {

        tape.add(new Node(null, null, 'B'));

        for(int i = 0; i < data.length(); i++) {
            tape.add(new Node(null, null, 'B'));
        }

        tape.add(new Node(tape.get(tape.size() - 1), null, 'B'));

        for(int i = 1; i < data.length() + 1; i++) {
            tape.get(i).setPrev(tape.get(i-1));
            tape.get(i).setNext(tape.get(i+1));
            tape.get(i).setChar(data.charAt(i-1));
        }

        tape.get(0).setNext(tape.get(1));
    }

    public char getData() {
        return tape.get(pointer).getChar();
    }

    public void setData(char c) {
        tape.get(pointer).setChar(c);
    }

    public void moveLeft() {
        pointer -= 1;
    }

    public void moveRight() {
        pointer += 1;
    }
}
import java.util.ArrayList;

public class Tape {
    
    ArrayList<Node> tape = new ArrayList<Node>();
    int pointer = 0;

    Tape() {
        
    }

    Tape(String input) {
        loadData(input);
    }

    private void loadData(String data) {

        tape.add(new Node(null, null, 'B'));

        for(int i = 0; i < data.length(); i++) {
            tape.add(new Node(null, null, 'B'));
        }

        tape.add(new Node(tape.get(tape.size() - 1), null, 'B'));

        for(int i = 1; i < data.length() + 1; i++) {
            tape.get(i).setHead(tape.get(i-1));
            tape.get(i).setTail(tape.get(i+1));
            tape.get(i).setChar(data.charAt(i));
        }

        tape.get(0).setTail(tape.get(1));
    }

    public char getData() {
        return tape.get(0).getChar();
    }

    public void moveLeft() {
        pointer -= 1;
    }

    public void moveRight() {
        pointer += 1;
    }
}
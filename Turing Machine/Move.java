public class Move {
    char replace, direction;
    int nextState;

    Move(char replaceCharacter, char directionCharacter, int nextState) {
        this.replace = replaceCharacter;
        this.direction = directionCharacter;
        this.nextState = nextState;
    }

    public char getReplaceCharacter() {
        return replace;
    }

    public char getDirection() {
        return direction;
    }

    public int getNextState() {
        return nextState;
    }
}

public class Node {
    Node prev;
    Node next;
    char data;

    Node(Node prev, Node next, char character) {
        this.prev = prev;
        this.next = next;
        this.data = character;
    }

    Node(char d) {
        data = d;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setChar(char c) {
        data = c;
    }

    public char getChar() {
        return data;
    }
}

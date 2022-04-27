public class Node {
    Node head;
    Node tail;
    char data;

    Node(Node head, Node tail, char character) {
        head = head.head;
        tail = tail.tail;
        data = character;
    }

    Node(char d) {
        data = d;
    }

    public void setHead(Node head) {
        head = head.head;
    }

    public void setTail(Node tail) {
        tail = tail.tail;
    }

    public void setChar(char c) {
        data = c;
    }

    public char getChar() {
        return data;
    }
}

public class PriorityQueue {
    private Node[] array;
    private int size;

    public PriorityQueue() {
        this.array = new Node[10];
        size = 0;
    }

    public void write(Node node) {
        if (size % 10 == 0) {

        }
        size++;
        array[size] = node;
    }
}

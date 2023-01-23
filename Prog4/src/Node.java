public class Node implements Comparable<Node> {
    private String character;
    private int frequency;
    private Node left;
    private Node right;

    public Node(String character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(frequency, node.frequency);
    }
}

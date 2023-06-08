import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ASD4 {
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                Scanner input = new Scanner(new File(args[0]));
                PriorityQueue queue = new PriorityQueue();
                while(input.hasNext())
                    queue.insert(new HuffmanNode(input.next().charAt(0), Integer.parseInt(input.next())));
//                queue.print();
                while (queue.getSize()>1)
                    queue.insert(new HuffmanNode(queue.pull(), queue.pull()));
                queue.generateHuffmanCode(queue.pull());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class PriorityQueue {
    private HuffmanNode head;
    private int size;

    public PriorityQueue() {
        size = 0;
    }

    public void insert(HuffmanNode node) {
        size++;
        if (head == null)
            head = node;
        else {
            if (node.compareTo(head) < 0) {
                node.setNext(head);
                head = node;
            } else {
                HuffmanNode current = head.getNext(), previous = head;
                while (current != null && node.compareTo(current) > 0) {
                    previous = current;
                    current = current.getNext();
                }
                node.setNext(current);
                previous.setNext(node);
            }
        }
    }



    public void generateHuffmanCode(HuffmanNode node) {
        if (node.hasChildren())
            generateHuffmanCodeRec(node, "");
        else
            System.out.println(node.getCharacter() + " 0");
    }

    public void generateHuffmanCodeRec(HuffmanNode node, String code) {
        if (node.getLeft() != null)
            generateHuffmanCodeRec(node.getLeft(), code.concat("0"));
        if (node.getRight() != null)
            generateHuffmanCodeRec(node.getRight(), code.concat("1"));
        if (!node.hasChildren())
            System.out.println(node.getCharacter() + " " + code);
    }


    public HuffmanNode pull() {
        HuffmanNode result = head;
        if (head != null) {
            size--;
            head = head.getNext();
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public void print() {
        HuffmanNode current = head;
        while (current != null) {
            System.out.print(current + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}

class HuffmanNode implements Comparable<HuffmanNode> {
    private Character character;
    private int frequency;
    private HuffmanNode left;
    private HuffmanNode right;

    private HuffmanNode next;

    public HuffmanNode(Character character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.left = left;
        this.right = right;
        frequency = left.frequency + right.frequency;
    }

    public boolean hasChildren() {
        return right != null && left != null;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return Integer.compare(frequency, node.frequency);
    }

    @Override
    public String toString() {
        return character + "" + frequency;
    }

    public Character getCharacter() {
        return character;
    }

    public HuffmanNode getNext() {
        return next;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setNext(HuffmanNode next) {
        this.next = next;
    }
}
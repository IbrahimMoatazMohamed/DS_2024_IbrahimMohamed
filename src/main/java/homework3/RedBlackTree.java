package homework3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedBlackTree {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public ArrayList<Entry> get(String searchableName) {
        Node x = root;
        int redCounter = 0;
        int blackCounter = 0;
        while (x != null) {
            int cmp = searchableName.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                System.out.println("Entries found: " + x.values.size());
                System.out.println("Red edges on the path: " + redCounter);
                System.out.println("Black edges on the path: " + blackCounter + '\n');
                return x.values;
            }

            if (isRed(x.right)) {
                redCounter++;
            } else {
                blackCounter++;
            }
        }

        return null;
    }


    public void put(String searchableName, Entry entry) {
        root = put(root, searchableName, entry);
        root.color = BLACK;
    }

    public List<Integer> countRedAndBlackEdges() {
        List<Integer> counts = Arrays.asList(0, 0);
        countEdges(root, counts);
        return counts;
    }

    private void countEdges(Node node, List<Integer> counts) {
        if (node == null) return;

        if (node.left != null) {
            if (isRed(node.left)) {
                counts.set(1, counts.get(1) + 1);
            } else {
                counts.set(0, counts.get(0) + 1);
            }
            countEdges(node.left, counts);
        }

        if (node.right != null) {
            if (isRed(node.right)) {
                counts.set(1, counts.get(1) + 1);
            } else {
                counts.set(0, counts.get(0) + 1);
            }
            countEdges(node.right, counts);
        }
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.right.color = BLACK;
        h.left.color = BLACK;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private Node put(Node h, String key, Entry value) {
        if (h == null) {
            return new Node(key, value, RED);
        }

        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);
        } else {
            h.values.add(value);
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        return h;
    }


    //part 4
    public ArrayList<Entry> getEntriesInOrder() {
        ArrayList<Entry> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    private void inOrderTraversal(Node node, ArrayList<Entry> result) {
        if (node == null) return;

        inOrderTraversal(node.left, result);
        result.addAll(node.values);
        inOrderTraversal(node.right, result);
    }
}
package homework3;

import java.util.ArrayList;


public class Node {

    public String key;
    public ArrayList<Entry> values = new ArrayList<>();
    public Node left, right;
    public boolean color;

    public Node(String key, Entry value, boolean color) {
        this.key = key;
        this.values.add(value);
        this.color = color;
    }
}
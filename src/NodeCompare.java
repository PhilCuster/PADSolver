import java.util.Comparator;

public class NodeCompare implements Comparator<Node>{
    public int compare(Node n1, Node n2) {
        if (n1.getH() < n2.getH()) {
            return 1;
        } else {
            return -1;
        }
    }
}

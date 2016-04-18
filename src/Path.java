import java.util.ArrayList;
import java.util.TreeSet;

public class Path {
    public static void findBest(Node start) {
        // Nodes already evaluated.
        ArrayList<Node> closed = new ArrayList<>();

        // Set of nodes to be evaluated.
        TreeSet<Node> open = new TreeSet<>(new NodeCompare());
        // Add the start Node.
        open.add(start);

        // The node that is the best path to our current node.
        Node best_source = null;

        // Loop while there are nodes to evaluate.
        while (open.size() > 0) {

            // Set the current node to the node with the lowest f-cost.
            Node current = open.pollFirst();

            // Add the current Node to the closed list.
            closed.add(current);

            // If we found a goal then stop.


        }

    }
}

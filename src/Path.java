import java.util.ArrayList;
import java.util.TreeSet;

public class Path {
    public static void findBest(Node start, int max) {
        // Nodes already evaluated.
        ArrayList<Node> closed = new ArrayList<>();

        // Set of nodes to be evaluated.
        TreeSet<Node> open = new TreeSet<>(new NodeCompare());
        // Add the start Node.
        open.add(start);

        // The node that is our Node with the highest score.
        Node best = start;

        // Loop while there are nodes to evaluate.
        while (open.size() > 0) {

            // Set the current node to the node with the lowest f-cost.
            Node current = open.pollFirst();

            // Add the current Node to the closed list.
            closed.add(current);

            // If we found a node that is better than the our current best then record that.
            if (current.getH() > best.getH()) {
                best = current;
            }

            // If we have not reached our max amount of moves.
            if (current.getG() < max) {

            }


        }

    }
}

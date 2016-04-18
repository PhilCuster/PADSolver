import java.util.*;

public class Path {
    public static void findBest(Node start, int max) {
        // Nodes already evaluated.
        HashMap<Board, Node> closed = new HashMap<>();

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
            closed.put(current.getState(), current);

            // If we found a node that is better than the our current best then record that.
            if (current.getH() > best.getH()) {
                best = current;
            }

            // If we have not reached our max amount of moves.
            if (current.getG() < max) {
                // Generate children.
                current.generateChildren();

                // For each child.
                for (Node child : current.getChildren()) {
                    // Check to see if we have seen it before.
                    if (closed.get(child.getState()) != null) {
                        // We have seen it before, if the path to it is not quicker then skip.
                        if (closed.get(child.getState()).getG() >= current.getG()) {
                            continue;
                        }
                    }
                    open.add(child);
                }
            }

        }

        // We are done.  Reconstruct the path.
        ArrayList<Node> best_path = new ArrayList<>();

        best_path.add(best);
        while(best.getParent() != null) {
            best_path.add(best.getParent());
            best = best.getParent();
        }

        Collections.reverse(best_path);

        //  Print the path.
        for (Node n : best_path) {
            char[][] board = n.getState().getBoard();
            System.out.println(n.getG());
            for (int i = 0; i < 5; i++) {
                System.out.print(board[i][0] + " ");
                System.out.print(board[i][1] + " ");
                System.out.print(board[i][2] + " ");
                System.out.print(board[i][3] + " ");
                System.out.print(board[i][4] + " ");
                System.out.print(board[i][5] + " ");
                System.out.println();
            }
            System.out.println(n.getH());
            System.out.println();
        }
        System.out.println(best_path.size());
    }
}

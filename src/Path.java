import java.util.*;

public class Path {
    public static void findBest(Node start, int max) {
        int node_count = 1;

        // Nodes already evaluated.
        HashMap<Board, Node> closed = new HashMap<>();

        // Set of nodes to be evaluated.
        TreeSet<Node> open = new TreeSet<>(new NodeCompare());
        // Add the start Node.
        open.add(start);

        // Set showmoves of start Node.
        start.getState().setShowMoves(start.getState().getBoard(), 0, 0, 0, true);

        // The node that is our Node with the highest score.
        Node best = start;

        // Loop while there are nodes to evaluate.
        while (open.size() > 0) {

            // Set the current node to the node with the lowest f-cost.
            Node current = open.pollFirst();

            // Add the current Node to the closed list.
            closed.put(current.getState(), current);

            // If we found a node that is better than the our current best then record that.
            if (current.getH() > best.getH() || (current.getG() < best.getG() && current.getH() == best.getH())) {
                best = current;
            }

            // If we have not reached our max amount of moves.
            if (current.getG() < max+1) {
                // Generate children.
                current.generateChildren();

                // For each child.
                for (Node child : current.getChildren()) {
                    node_count++;
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
            char[][] board = n.getState().getShowMoves();
            System.out.println("Move: " + (n.getG()-1));
            for (int i = 0; i < 5; i++) {
                System.out.print(board[i][0] + " ");
                System.out.print(board[i][1] + " ");
                System.out.print(board[i][2] + " ");
                System.out.print(board[i][3] + " ");
                System.out.print(board[i][4] + " ");
                System.out.print(board[i][5] + " ");
                System.out.println();
            }
            System.out.println("Score: " + n.getH());
            System.out.println();
        }
        System.out.println("Number of nodes generated: " + node_count);
        System.out.println("Number of moves: " + (best_path.size()-1));
    }

}

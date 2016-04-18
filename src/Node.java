import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Node {
    private Node parent;
    private ArrayList<Node> children;
    private int g;
    private double h;
    private double f;
    private int depth;
    private char[] types;

    private Board state;

    public Node(Node _parent, int _depth, Board s, char[] t) {
        state = s;
        types = t;
        parent = _parent;
        depth = _depth;
        children = null;
        if (parent == null) {
            g = 1;
        } else {
            g = parent.getG() + 1;
        }
        h = generateScore(types);
        f = g - h;
    }

    private char[][] swapPieces(char[][] old_board, int row1, int col1, int row2, int col2) {
        char[][] new_board = old_board.clone();
        char temp = new_board[row2][col2];
        new_board[row2][col2] = new_board[row1][col1];
        new_board[row1][col1] = temp;
        return new_board;
    }

    public void generateChildren() {
        char[][] work = state.getBoard().clone();
        int[] active = state.getActive();

        ArrayList<Node> c = new ArrayList<>();

        boolean firstMove = false;

        if (active[0] == -1) {
            firstMove = true;
        }

        // We need to check the moves for every single piece.
        if (firstMove) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    // Move down, if not in last row.
                    if (i < 4) {
                        c.add(new Node(this, g + 1, new Board(swapPieces(work, i, j, i+1, j), new int[]{i+1,j}), types));
                    }
                    // Move up, if not in first row.
                    if (i > 0) {
                        c.add(new Node(this, g + 1, new Board(swapPieces(work, i, j, i-1, j), new int[]{i-1,j}), types));
                    }
                    // Move left, if not in first column.
                    if (j > 0) {
                        c.add(new Node(this, g + 1, new Board(swapPieces(work, i, j, i, j-1), new int[]{i, j-1}), types));
                    }
                    // Move right, if not in last column.
                    if (j < 5) {
                        c.add(new Node(this, g + 1, new Board(swapPieces(work, i, j, i, j+1), new int[]{i, j+1}), types));
                    }
                }
            }
        }

        // If not the first move then we need to check the moves only for the active piece.
        else {
            int i = state.getActive()[0];
            int j = state.getActive()[1];
            // Move down, if not in last row.
            if (i < 4) {
                c.add(new Node(this, g + 1, new Board(swapPieces(work, i, j, i+1, j), new int[]{i+1,j}), types));
            }
            // Move up, if not in first row.
            if (i > 0) {
                c.add(new Node(this, g + 1, new Board(swapPieces(work, i, j, i-1, j), new int[]{i-1,j}), types));
            }
            // Move left, if not in first column.
            if (j > 0) {
                c.add(new Node(this, g + 1, new Board(swapPieces(work, i, j, i, j-1), new int[]{i, j-1}), types));
            }
            // Move right, if not in last column.
            if (j < 5) {
                c.add(new Node(this, g + 1, new Board(swapPieces(work, i, j, i, j+1), new int[]{i, j+1}), types));
            }
        }

//        System.out.println(Arrays.deepToString(swapPieces(work, new int[]{0,0},new int[]{0,1})));

        children = c;


    }

    private ArrayList<Match> findMatches(char[][] board) {
        int combo = 0;
        int damage = 0;

        // Create an empty board where can copy over matches we find
        char[][] matches = {{'X','X','X','X','X','X'},
                {'X','X','X','X','X','X'},
                {'X','X','X','X','X','X'},
                {'X','X','X','X','X','X'},
                {'X','X','X','X','X','X'}};

        // Check for 3 or more matching orbs
        // Horizontal matches
        for (int i = 0; i < 5; i++) {
            // First in series
            char o1 = 'X';
            // Second in series
            char o2 = 'X';
            for (int j = 0; j < 6; j++) {
                char current = board[i][j];

                if (o1 == o2 && o2 == current && current != 'X') {
                    matches[i][j] = current;
                    matches[i][j-1] = current;
                    matches[i][j-2] = current;
                }
                o1 = o2;
                o2 = current;
            }
        }

        // Vertical matches.
        for (int j = 0; j < 6; j++) {
            // First in series
            char o1 = 'X';
            // Second in series
            char o2 = 'X';
            for (int i = 0; i < 5; i++) {
                char current = board[i][j];

                if (o1 == o2 && o2 == current && current != 'X') {
                    matches[i][j] = current;
                    matches[i-1][j] = current;
                    matches[i-2][j] = current;
                }
                o1 = o2;
                o2 = current;
            }
        }
        char[][] work = matches.clone();
//        System.out.println(Arrays.deepToString(matches));
        // Check our matches.

        ArrayList<Match> match_list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                char current = work[i][j];

                if (current == 'X') {
                    continue;
                }

                Stack<int[]> stack = new Stack<>();
                stack.push(new int[]{i,j});
                int count = 0;
                char[][] current_match = {{'X','X','X','X','X','X'},
                        {'X','X','X','X','X','X'},
                        {'X','X','X','X','X','X'},
                        {'X','X','X','X','X','X'},
                        {'X','X','X','X','X','X'}};

                while(stack.size() > 0) {
                    int[] x = stack.pop();

                    if (work[x[0]][x[1]] != current) {
                        continue;
                    }
                    count++;

                    work[x[0]][x[1]] = 'X';
                    current_match[x[0]][x[1]] = 'O';
                    if (x[0] > 0) {
                        stack.push(new int[]{x[0]-1,x[1]});
                    }
                    if (x[0] < 4) {
                        stack.push(new int[]{x[0]+1,x[1]});
                    }
                    if (x[1] > 0) {
                        stack.push(new int[]{x[0],x[1]-1});
                    }
                    if (x[1] < 5) {
                        stack.push(new int[]{x[0],x[1]+1});
                    }
                }

                boolean isRow = false;
                for (int k = 0; k < 4; k++) {
                    if(current_match[k][0] == 'O' && current_match[k][1] == 'O' && current_match[k][2] == 'O' &&
                            current_match[k][3] == 'O' && current_match[k][4] == 'O' && current_match[k][5] == 'O') {
                        isRow = true;
                    }
                }

//                System.out.println(current + " - " + count);

                match_list.add(new Match(current, count, isRow, matches));

            }
        }

        return match_list;
    }

    private double generateScore(char[] types) {
        char[][] work_board = state.getBoard().clone();

        ArrayList<Match> all_matches = new ArrayList<>();

        all_matches.addAll(findMatches(work_board));

        /*
        while(true) {
            ArrayList<Match> matches = findMatches(work_board);
            if (matches.size() == 0) {
                break;
            }
            System.out.println(Arrays.deepToString(matches.get(0).matches));
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    if (matches.get(0).matches[i][j] != 'X') {
                        work_board[i][j] = 'X';
                    }
                }
            }
            for (int j = 0; j < 6; j++) {
                int target = 4;
                for (int i = 4; i >= 0; i--) {
                    if (work_board[i][j] != 'X') {
                        work_board[target][j] = work_board[i][j];
                        target--;
                    }
                }
                for (; target >= 0; target--) {
                    work_board[target][j] = 'X';
                }
            }
            all_matches.addAll(matches);
        }
        */
        double score = 0;
        int combo = 0;
        for (Match m : all_matches) {
            int damage = 1;
            combo++;
            char type = m.element;
            for (int i = 0; i < types.length; i++) {
                if (type == types[i]) {
                    score += damage * (1.0 + (m.count-3) * .25);
                }
            }
        }
//        System.out.println("Base damage: " + score);
        double multi = 1 + (combo-1) * .25;
//        System.out.println("Total combo: " + multi);

        return score * multi;

    }

    public double getH() {
        return h;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void addChild(Node newChild) {
        children.add(newChild);
    }


    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getG() {
        return g;
    }

    public double getF() {
        return f;
    }

    public Board getState() {
        return state;
    }
}

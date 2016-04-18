import java.util.Arrays;

public class Board {
    private char[][] board;
    private int[] active;

    public char[][] getShowMoves() {
        return showMoves;
    }

    public void setShowMoves(char[][] moves, int x, int y, int dir, boolean root) {
        // 0 = Up, 1 = Right, 2 = Down, 3 = Left
        showMoves = moves;
        if (!root) {
            if (dir == 0) {
                showMoves[x][y] = '|';
                showMoves[x-1][y] = '^';
            } else if (dir == 1) {
                showMoves[x][y] = '-';
                showMoves[x][y+1] = '>';
            } else if (dir == 2) {
                showMoves[x][y] = '|';
                showMoves[x+1][y] = 'v';
            } else if (dir == 3) {
                showMoves[x][y] = '-';
                showMoves[x][y-1] = '<';
            }
        }
    }

    private char[][] showMoves;

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public int[] getActive() {
        return active;
    }

    public void setActive(int[] active) {
        this.active = active;
    }

    public Board(char[][] b, int[] a) {
        board = b;
        active = a;
    }

    public int hashCode() {
        return Arrays.deepHashCode(board) + Arrays.hashCode(active);
    }

    public boolean equals(Object o) {
        Board b = (Board)o;
        if (Arrays.deepEquals(board, b.getBoard()) && Arrays.equals(active, b.getActive())) {
            return true;
        }
        else {
            return false;
        }
    }
}

import java.util.Arrays;

public class Board {
    private char[][] board;
    private int[] active;

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

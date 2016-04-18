
public class Match {
    public char element;
    public int count;
    public boolean isRow;
    public char[][] matches;

    public Match(char e, int c, boolean i, char[][] m) {
        element = e;
        count = c;
        isRow = i;
        matches = m;
    }
}

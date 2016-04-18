import java.util.ArrayList;

public class Solver {

    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'r','y','r','r','y','r'},
                {'p','b','b','b','y','p'},
                {'r','r','b','g','b','g'},
                {'p','p','y','r','y','p'},
                {'r','r','b','y','b','g'}
        };

        Node example = new Node(null, 0, 0, board, new char[]{'b'});
        System.out.println(example.getH());
    }
}

import java.util.ArrayList;

public class Solver {

    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'r','y','r','r','y','r'},
                {'b','b','b','b','y','r'},
                {'r','r','b','g','b','r'},
                {'p','p','y','r','y','p'},
                {'r','r','b','y','y','y'}
        };

        Node example = new Node(null, 0, 0, board, new char[]{'b'});
        System.out.println(example.getH());
    }
}

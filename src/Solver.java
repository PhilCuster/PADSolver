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

        Node example = new Node(null, 0, new Board(board, new int[]{-1,-1}), new char[]{'b'});
        /*
        example.generateChildren();
        System.out.println(example.getH());
        System.out.println(example.getF());
        System.out.println(example.getChildren().size());
        Node child = example.getChildren().get(0);
        child.generateChildren();
        System.out.println(child.getChildren().size());
        */
        Path.findBest(example, 5);
    }
}



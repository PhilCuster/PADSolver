import java.util.*;
import java.io.*;


public class Solver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter max number of moves: ");
        int maxMoves = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter filename for board: ");

        String file = sc.nextLine();

        char[][] input = new char[5][6];
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(new File(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] line_list = line.split(",");

                for(int j = 0; j < line_list.length; j++) {
                    input[i][j] = line_list[j].charAt(0);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        char[][] board = new char[][] {
                {'r','y','r','r','y','r'},
                {'y','b','p','b','y','g'},
                {'r','g','b','g','b','r'},
                {'b','p','y','r','y','p'},
                {'r','r','b','y','r','r'}
        };

        System.out.println();

        Node example = new Node(null, 0, new Board(input, new int[]{-1,-1}), new char[]{'r'});
        Path.findBest(example, maxMoves);
    }
}



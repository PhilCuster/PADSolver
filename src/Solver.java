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
                for(int j = 0; j < 6; j++) {
                    input[i][j] = line.charAt(j);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map m = new HashMap<>();

        System.out.print("Enter filename for team comp: ");
        file = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] line_list = line.split(",");
                m.put('r',Integer.parseInt(line_list[0]));
                m.put('b',Integer.parseInt(line_list[1]));
                m.put('g',Integer.parseInt(line_list[2]));
                m.put('y',Integer.parseInt(line_list[3]));
                m.put('p',Integer.parseInt(line_list[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        Path.findBest(new Node(null, 0, new Board(input, new int[]{-1,-1}), m), maxMoves);
    }
}



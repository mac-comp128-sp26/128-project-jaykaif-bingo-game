package projectBingo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ProjectBingo {
    private static Bag bag;
    public static void main(String[] args) {
        
        GoalLoader goalLoader = new GoalLoader();

        Scanner scanner = new Scanner(System.in);
        List<Tile> goals = new ArrayList<>();

        while(goals.isEmpty()) {
            System.out.println("provide file path to goals.csv");
            String response = scanner.nextLine();
            goals = goalLoader.loadFromCSV(response);
            Collections.shuffle(goals);
        }

        while(true) {
            System.out.println("[Priority] or [Normal] bag?");
            String response = scanner.nextLine();
            if (response.equals("Priority")) {
                bag = new PriorityBag(goals);
                break;
            } else if (response.equals("Normal")) {
                bag = new RegularBag(goals);
                break;
            }
        }
        
        Board board = new Board(bag);

        board.print();

        List<HotKey> hotKeys = new ArrayList<>();
        for(int i = 1; i < 6; i++) {
            for(int j = 1; j < 6; j++) {
                for(int k = 0; k < 3; k++) {
                    //ijk
                    hotKeys.add(new HotKey(((Integer)i).toString() + ((Integer)j).toString() + ((Integer)k).toString(), 
                        () -> {
                            Board.getTile(i,j); //TODO: fix me
                        }
                    ));
                }
            }
        }





        scanner.close();
    }
}

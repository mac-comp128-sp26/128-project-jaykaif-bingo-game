package projectBingo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ProjectBingo {

    private GoalLoader goalLoader;
    private Bag bag;
    private Board board;
    private Gui gui;

    public static void main(String[] args) {
        ProjectBingo projectBingo = new ProjectBingo();
        projectBingo.run();
    }

    public ProjectBingo () {
    }

    public void run() {

        gui = new Gui();


        loadBags();
        
        board = new Board(bag);
        board.print();


        

    }

    private void loadBags() {
        goalLoader = new GoalLoader();

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

        scanner.close();
    }

    
    

}

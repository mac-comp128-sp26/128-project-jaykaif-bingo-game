package projectBingo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.MouseButtonEvent;
import edu.macalester.graphics.events.MouseButtonEventHandler;

public class ProjectBingo {

    private GoalLoader goalLoader;
    private Bag bag;
    private Board board;
    private Gui gui;
    private CanvasWindow canvas;

    public static void main(String[] args) {
        ProjectBingo projectBingo = new ProjectBingo();
        projectBingo.run();
    }

    public ProjectBingo () {
    }

    public void run() {

        canvas = new CanvasWindow("Bingo", 750, 750);
        gui = new Gui(canvas);


        loadBags();
        
        board = new Board(bag);
        board.print();

        
        while (true) { 
            //TODO: make the onclick events work better
            canvas.onClick(new MouseButtonEventHandler() {
                @Override
                public void handleEvent(MouseButtonEvent event) {
                    int x = (int) Math.floor((event.getPosition().getX()-125) / 100);
                    int y = (int) Math.floor((event.getPosition().getY()-125) / 100);

                    gui.drawTile(x, y, bag.remove());
                }
            });
        }
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

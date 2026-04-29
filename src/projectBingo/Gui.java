package projectBingo;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.TextAlignment;

public class Gui {
    public CanvasWindow canvas;

    public Gui (CanvasWindow canvas) {
        this.canvas = canvas;


        String[] title = {"B", "I", "N", "G", "O"};

        for (int i = 0; i < 500; i += 100) {
            GraphicsText character = new GraphicsText(title[i/100],i+175,100);
            character.setFontSize(100);
            character.setAlignment(TextAlignment.CENTER);
            this.canvas.add(character);


            for (int j = 0; j < 500; j += 100) {
                Rectangle square = new Rectangle(i+125,j+125,100,100);
                this.canvas.add(square);
            }
        }

        this.canvas.draw();
    }

    /**
     * draws a tile at the x,y position on board
     * @param x horizontal tile position (0,1,2,3,4). NOT THE actual X position
     * @param y vertical tile position like x
     * @param tile the tile to draw
     */
    public void drawTile(int x, int y, Tile tile){
        //set fill
        Rectangle square = new Rectangle(125 + 100*x,125 + 100*y,100,100);
        if (tile.getCompletionState()==1) {
            square.setFillColor(Color.CYAN);
        } else if (tile.getCompletionState()==2) {
            square.setFillColor(Color.RED);
        } else {
            square.setFillColor(Color.LIGHT_GRAY);
        }
        canvas.add(square);

        //set title
        //TODO: ensure that the scaling of the text is always correct
        GraphicsText text = new GraphicsText(tile.getGoal(),175 + 100*x,150 + 100*y);
        text.setAlignment(TextAlignment.CENTER);
        text.setFontSize(20);

        canvas.add(text);  
        canvas.draw();
    }

    /**
     * Displays a win message overlay on the canvas
     * @param player the winning player (1 or 2)
     */
    public void showWinner(int player){
        Rectangle overlay = new Rectangle(125,300, 500, 120);
        overlay.setFillColor(player == 1 ? Color.CYAN : Color.RED);
        canvas.add(overlay);

        GraphicsText message = new GraphicsText("Player" + player + " wins!", 375,375);
        message.setFontSize(48);
        message.setAlignment(TextAlignment.CENTER);
        canvas.add(message);
    }

    
}

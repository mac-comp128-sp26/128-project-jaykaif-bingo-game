package projectBingo;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.TextAlignment;

public class Gui {
    private CanvasWindow window;

    public final int WIDTH = 750;
    public final int HEIGHT = 750;

    public Gui () {
        window = new CanvasWindow("Bingo", WIDTH, HEIGHT);


        String[] title = {"B", "I", "N", "G", "O"};

        for (int i = 0; i < 500; i += 100) {
            GraphicsText character = new GraphicsText(title[i/100],i+175,100);
            character.setFontSize(100);
            character.setAlignment(TextAlignment.CENTER);
            window.add(character);


            for (int j = 0; j < 500; j += 100) {
                Rectangle square = new Rectangle(i+125,j+125,100,100);
                window.add(square);
            }
        }

        window.draw();
    }
}

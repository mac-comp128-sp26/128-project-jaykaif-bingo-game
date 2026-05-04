package projectBingo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GoalLoader {


    /**
     * 
     * @param filePath
     * @return List of Tile objects paprsed from the
     */
    public List<Tile> loadFromCSV (String fileName){
        File filePath = getFile(fileName);
        List<Tile> tiles = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line = reader.readLine(); // this skips header row of csv, consumes top and 

            while((line = reader.readLine()) != null){
                line = line.trim();
                if(line.isEmpty()) continue;

                String[] parts = line.split(",", 3); // max 3 splits - parts[0] = goal, parts[1] = priority, parts[2] = category.

                if(parts.length < 2) continue;

                String goal = parts[0].trim().replace("\"", "");
                int priority = Integer.parseInt(parts[1].trim());

                tiles.add(new Tile(goal, priority));
            }

        } catch(IOException e){
            System.err.println("Error reading CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Bad priority value in CSV:" + e.getMessage());
        }
        

        return tiles;

    }
    
    /**
     * Loads a file from the res folder.
     **/ 
    public static File getFile(String resourceName){
        try {
            URL url = ProjectBingo.class.getResource("/" + resourceName);
            if (url != null) {
                return new File(url.toURI());
            }
            else {
                System.out.println("Cannot find file with name "+resourceName);
                return null;
            }
        } catch (URISyntaxException syntaxException){
            syntaxException.printStackTrace();
            return null;
        }
    }
}

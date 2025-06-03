package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoreManager {
    private static final String FILE_NAME = "highscores.ser";
    private final List<HighScore> highScores = new ArrayList<>();

    public HighScoreManager() {
        load();
    }

    private void load() {
        File file = new File(FILE_NAME);
        if(!file.exists()){return;}

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            List<?> loaded = (List<?>) in.readObject();
            for(Object obj : loaded){
                if(obj instanceof HighScore hs){
                    highScores.add(hs);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    public void addScore(HighScore highScore){
        highScores.add(highScore);
        highScores.sort((a,b) -> b.getScore() - a.getScore());
        save();
    }

    private void save(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<HighScore> getHighScores() {
        return highScores;
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreManager {
    private String fileName = "scores.txt";

    private File getScoreFile() {
        return new File(fileName);
    }

    public void saveScore(String username, int score) {
        try {
            File file = getScoreFile();
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);
            writer.write(username + "," + score + "\n");
            writer.close();
        } catch (IOException e) {
        }
    }

    public ArrayList<String> getScores() {
        ArrayList<String> scoreList = new ArrayList<String>();
        ArrayList<String[]> rawScores = new ArrayList<String[]>();

        try {
            File file = getScoreFile();
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 2) {
                    rawScores.add(data);
                }
            }

            reader.close();
        } catch (IOException e) {
        }

        Collections.sort(rawScores, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                int scoreA = Integer.parseInt(a[1]);
                int scoreB = Integer.parseInt(b[1]);
                return scoreB - scoreA;
            }
        });

        for (int i = 0; i < rawScores.size(); i++) {
            String[] data = rawScores.get(i);
            scoreList.add(data[0] + " - " + data[1]);
        }

        return scoreList;
    }
}

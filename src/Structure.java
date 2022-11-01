import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Structure {
    String file;
    Player playerA;
    Player playerB;
    HashMap<Pair<String, String>, Pair<Integer, Integer>> movesValues = new HashMap<>();
    HashMap<Pair<String, String>, Pair<Integer, Integer>> reversedMovesValues = new HashMap<>();

    public Structure(String file) {
        this.file = file;
        initFromFile();
    }

    private void initFromFile() {
        String data;
        Pattern pattern1 = Pattern.compile("\\d+/\\d+");
        Matcher matcher;
        int line = 0;
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                line++;
                data = myReader.nextLine();
                matcher = pattern1.matcher(data);
                String[] words = data.split(" ");

                if (line == 1) {
                    playerA = new Player(words[0]);

                    playerA.moves.addAll(Arrays.asList(words).subList(1, words.length));
                } else if (line == 2) {
                    playerB = new Player(words[0]);
                    playerB.moves.addAll(Arrays.asList(words).subList(1, words.length));
                } else {
                    int aIndex = line - 3;
                    String aMoveName = playerA.moves.get(aIndex);
                    int bIndex = 0;
                    while (matcher.find()) {
                        String numbers = matcher.group();

                        String[] numberArray = numbers.split("/");
                        int aMoveValue = Integer.parseInt(numberArray[0]);
                        int bMoveValue = Integer.parseInt(numberArray[1]);
                        String bMoveName = playerB.moves.get(bIndex);

                        movesValues.put(new Pair<>(aMoveName, bMoveName), new Pair<>(aMoveValue, bMoveValue));
                        reversedMovesValues.put(new Pair<>(bMoveName, aMoveName), new Pair<>(bMoveValue, aMoveValue));
                        bIndex++;
                    }
                }
            }

            playerA.setMovesMap(movesValues);
            playerB.setMovesMap(reversedMovesValues);

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}

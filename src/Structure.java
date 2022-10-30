import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Structure {
    String file;
    String playerAName;
    String playerBName;
    HashMap<Pair<String, String>, Pair<Integer, Integer>> movesValues = new HashMap<>();
    List<String> playerAMoves = new ArrayList<>() ;
    List<String> playerBMoves = new ArrayList<>() ;

    public Structure(String file) {
        this.file = file;
        initFromFile();
    }

    private void initFromFile() {
        String data;
        Pattern pattern2 = Pattern.compile("[0-9]+");
        Pattern pattern1 = Pattern.compile("[0-9]+/[0-9]+");
        Matcher matcher;
        Matcher matcher2;        int line = 0;
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                line++;
                data = myReader.nextLine();
                matcher = pattern1.matcher(data);
                String[] words = data.split(" ");

                if(line == 1) {
                    playerAName = words[0];

                    for (int i = 1; i < words.length; i++) {
                        playerAMoves.add(words[i]);
                    }
                }
                else
                if(line == 2) {
                    playerBName = words[0];
                    for (int i = 1; i < words.length; i++) {
                        playerBMoves.add(words[i]);
                    }
                } else {
                    int AIndex = line - 3;
                    String AMoveName = playerAMoves.get(AIndex);
                    int Bindex = 0;
                    while (matcher.find()) {
                        String numbers = matcher.group();

                        String[] numberArray = numbers.split("/");
                        int AMoveValue = Integer.parseInt(numberArray[0]);
                        int BMoveValue = Integer.parseInt(numberArray[1]);
                        String BMoveName = playerBMoves.get(Bindex);

                        movesValues.put(new Pair<>(AMoveName, BMoveName), new Pair<>(AMoveValue, BMoveValue));
                        Bindex++;
                    }
                }



            }

            System.out.println(movesValues);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}

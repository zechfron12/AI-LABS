import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Structure {
    protected int[] board;
    protected HashMap<Integer, ArrayList<Integer>> variablesMap;
    protected HashMap<Integer, ArrayList<Integer>> obstacles;
    protected int n;
    String file;


    public Structure(String file) {
        this.file = file;
        initFromFile();
    }

    private void init(int n) {
        this.n = n;
        board = new int[n + 1];
        variablesMap = new HashMap<>();
        obstacles = new HashMap<>();

        ArrayList<Integer> allVariables = new ArrayList<>(IntStream.range(1, n + 1).boxed().toList());
        for (int i = 1; i <= n; i++) {
            variablesMap.put(i, (ArrayList<Integer>) allVariables.clone());
            obstacles.put(i, new ArrayList<>());
        }
    }

    private void initFromFile() {
        String data;
        Pattern pattern = Pattern.compile("[0-9]+");
        List<Integer> numbersFound = new ArrayList<>();
        Matcher matcher;

        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                matcher = pattern.matcher(data);
                while (matcher.find()) {
                    int match = Integer.parseInt(matcher.group());
                    numbersFound.add(match);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        init(numbersFound.get(0));
        for (int i = 1; i < numbersFound.size(); i += 2) {
            obstacles.get(numbersFound.get(i)).add(numbersFound.get(i + 1));
            variablesMap.get(numbersFound.get(i)).remove(numbersFound.get(i + 1));
        }
    }

    public void showResult() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                if (board[i] == j)
                    System.out.print("👑 ");
                else if (obstacles.get(i).contains(j)) System.out.print("❌ ");
                else System.out.print("🟪 ");
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            System.out.print("➖ ");
        }

        System.out.println();
    }
}

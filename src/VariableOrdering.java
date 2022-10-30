import java.util.Random;

public class VariableOrdering extends Structure {

    Random r = new Random();
    public VariableOrdering(String file) {
        super(file);
    }

    private int getLineWithLeastVariablesAvailabe() {
        final int[] line = {-1};
        final int[] minLength = {100_000_000};

        variablesMap.forEach((currentLine, variables) -> {
            if (variables.size() > 0 && variables.size() < minLength[0]) {
                minLength[0] = variables.size();
                line[0] = currentLine;
            }
        });
        return line[0];
    }

    public void solve() {
        System.out.println("Solving with variable ordering...");
        int line = getLineWithLeastVariablesAvailabe();
        vo(line, 0);
    }

    private void vo(int line, int queensPlaced) {

        if (queensPlaced == n) {
            showResult();
        } else {
            if(variablesMap.get(line) == null) {
                System.out.println("NO SOLUTION FOUND");
                return;
            }
            board[line] = variablesMap.get(line).get(r.nextInt(variablesMap.get(line).size()));
            variablesMap.remove(line);
            removeVariables(line, board[line]);
            int newLine = getLineWithLeastVariablesAvailabe();
            vo(newLine, queensPlaced + 1);
        }

    }

    private void removeVariables(int line, int queen) {
        variablesMap.forEach((currentLine, variables) -> {
            variables.removeIf(variable -> variable == queen || Math.abs(queen - variable) == Math.abs(currentLine - line));
        });
    }


}

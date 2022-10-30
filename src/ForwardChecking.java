import java.util.Arrays;

import static java.lang.System.exit;

public class ForwardChecking extends Structure {

    private boolean completed = false;

    public ForwardChecking(String file) {
        super(file);
    }

    public boolean isValid(int line) {
        if (obstacles.get(line).contains(board[line]))
            return false;
        for (int prevLine = 1; prevLine < line; prevLine++)
            if (board[prevLine] == board[line] || (Math.abs(board[line] - board[prevLine]) == (line - prevLine)))
                return false;
        return true;
    }

    public void solve() {

        System.out.println("Solving with FC ...");
        FC(1);
        if(!completed)
            System.out.println("NO SOLUTION FOUND");
    }

    public void FC(int line) {
        if (completed)
            return;
        if (line == n + 1) {
            showResult();
            completed = true;
        } else {
            for (int i = 1; i <= n; i++) {
                board[line] = i;
                if (isValid(line))
                    FC(line + 1);
            }
        }
    }


}

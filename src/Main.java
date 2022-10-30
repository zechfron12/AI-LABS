
public class Main {

    public static String createFilePath(int numberOfBlockers, int exampleNummber) {
        return "block-10/block-10-" + numberOfBlockers + "/block-10-" + numberOfBlockers +"-" + exampleNummber + ".param";
    }
    public static void main(String[] args) {

        String inputFile = createFilePath(2, 1);
        ForwardChecking fc = new ForwardChecking(inputFile);
        fc.solve();

        VariableOrdering vo = new VariableOrdering(inputFile);
        vo.solve();
    }
}
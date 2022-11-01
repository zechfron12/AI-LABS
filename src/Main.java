import java.util.HashMap;
import java.util.Map;

public class Main {
    public static final String BREAK_LINES = "➖➖➖➖➖➖➖➖➖➖➖➖➖";

    public static Map<String, String> getNashEquilibrium(Player playerA, Player playerB) {
        Map<String, String> nashEquilibrium = new HashMap<>();
        var strategyA = playerA.findDominantStrategy(playerB);
        var strategyB = playerB.findDominantStrategy(playerA);

        strategyA.forEach((key, values) ->
                values.forEach(value -> {
                    if (strategyB.get(value).contains(key))
                        nashEquilibrium.put(value, key);
                })
        );
        return nashEquilibrium;
    }

    public static void main(String[] args) {

        Structure s = new Structure("src/input.txt");
        Player playerA = s.playerA;
        Player playerB = s.playerB;
        System.out.println(BREAK_LINES);
        System.out.println(playerA.name + "'s" + " dominant strategy: ");
        System.out.println(playerA.findDominantStrategy(playerB));
        System.out.println(BREAK_LINES);
        System.out.println(playerB.name + "'s" + " dominant strategy: ");
        System.out.println(playerB.findDominantStrategy(playerA));
        System.out.println(BREAK_LINES);
        System.out.println("Nash Equilibrium:");
        System.out.println(getNashEquilibrium(playerA, playerB));
        System.out.println(BREAK_LINES);
    }
}
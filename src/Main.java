import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static Map<String, String> findNashEquilibra(Player playerA, Player playerB){
        Map<String, String> nashEquilibra = new HashMap<>();
        var strategyA = playerA.findDominantStrategy(playerB);
        var strategyB = playerB.findDominantStrategy(playerA);
        
        strategyA.forEach((key, values) -> {
            values.forEach(value -> {
                if(strategyB.get(value).contains(key))
                    nashEquilibra.put(value, key);
            });
        });
        return nashEquilibra;
    }
    public static void main(String[] args) {

        Structure s = new Structure("src/input.txt");
        Player playerA = s.playerA;
        Player playerB = s.playerB;

        System.out.println(playerA.findDominantStrategy(playerB));
        System.out.println(playerB.findDominantStrategy(playerA));
        System.out.println(findNashEquilibra(playerA, playerB));

    }
}
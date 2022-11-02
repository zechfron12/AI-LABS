import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Player {
    String name;
    List<String> moves;
    Map<Pair<String, String>, Pair<Integer, Integer>> movesMap;

    public Player(String name) {
        this.name = name;
        this.moves = new ArrayList<>();
    }

    public void setMovesMap(Map<Pair<String, String>, Pair<Integer, Integer>> movesMap) {
        this.movesMap = movesMap;
    }

    public Map<String, Set<String>> findDominantStrategy(Player playerB) {

        HashMap<String, Set<String>> dominantStrategy = new HashMap<>();

        playerB.moves.forEach(moveB -> {
            dominantStrategy.put(moveB, new HashSet<>());
            AtomicReference<Integer> maxGain = new AtomicReference<>(Integer.MIN_VALUE);
            AtomicReference<Integer> maxValue = new AtomicReference<>(Integer.MIN_VALUE);
            AtomicReference<String> chosenMove = new AtomicReference<>("");

            this.moves.forEach(moveA -> {

                var moveValue = movesMap.get(new Pair<>(moveA, moveB));
                int gain = moveValue.first - moveValue.second;
                var currentValue = moveValue.first;
                if(currentValue > maxValue.get()) {
                    maxValue.set(currentValue);
                    maxGain.set(gain);
                    chosenMove.set(moveA);
                    dominantStrategy.put(moveB, new HashSet<>());
                    dominantStrategy.get(moveB).add(moveA);
                }

                if(Objects.equals(currentValue, maxValue.get())){
                    dominantStrategy.get(moveB).add(moveA);
                }
            });

            dominantStrategy.get(moveB).add(chosenMove.get());

        });
        return dominantStrategy;
    }
}

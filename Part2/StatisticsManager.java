import java.util.HashMap;
import java.util.Map;

public class StatisticsManager {
    private Map<String, Integer> wins;
    private int totalRaces;

    public StatisticsManager() {
        wins = new HashMap<>();
        totalRaces = 0;
    }

    public void recordWin(String horseName) {
        wins.put(horseName, wins.getOrDefault(horseName, 0) + 1);
        totalRaces++;
    }

    public String getStats() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total Races: ").append(totalRaces).append("\n");
        for (String horse : wins.keySet()) {
            sb.append(horse).append(": ").append(wins.get(horse)).append(" wins\n");
        }
        return sb.toString();
    }
}

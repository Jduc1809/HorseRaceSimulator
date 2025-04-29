import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class TrackPanel extends JPanel {
    private List<HorseGUI> horses;
    private int raceLength = 800; // pixels
    private Random rand = new Random();
    private WeatherCondition weather = WeatherCondition.SUNNY; // Default weather

    public TrackPanel(List<HorseGUI> horses) {
        this.horses = horses;
        setPreferredSize(new Dimension(raceLength + 100, horses.size() * 100));
    }

    public void setWeather(WeatherCondition weather) {
        this.weather = weather;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw lanes
        for (int i = 0; i < horses.size(); i++) {
            g.setColor(Color.GRAY);
            g.fillRect(0, i * 100 + 40, raceLength, 5); // lane line
        }

        // Draw horses
        for (int i = 0; i < horses.size(); i++) {
            HorseGUI horse = horses.get(i);

            g.setColor(horse.getColor());
            int x = horse.getDistanceTravelled();
            int y = i * 100 + 10;

            g.drawString(horse.getSymbol() + " " + horse.getName(), x, y);
        }
    }

    public void updateRace() {
        for (HorseGUI horse : horses) {
            if (!horse.hasFallen()) {
                double moveChance = horse.getConfidence();
                double fallChance = 0.1 * (1 - horse.getConfidence());

                // Apply weather effects
                if (weather == WeatherCondition.MUDDY) {
                    moveChance *= 0.7; // 30% slower
                } else if (weather == WeatherCondition.ICY) {
                    fallChance *= 2.0; // double fall chance
                }

                if (rand.nextDouble() < moveChance) {
                    horse.moveForward();
                }
                if (rand.nextDouble() < fallChance) {
                    horse.fall();
                }
            }
        }
        repaint();
    }

    public HorseGUI checkWinner() {
        for (HorseGUI horse : horses) {
            if (horse.getDistanceTravelled() >= raceLength) {
                return horse;
            }
        }
        return null;
    }
}

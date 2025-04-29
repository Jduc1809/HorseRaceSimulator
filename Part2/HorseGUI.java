import java.awt.Color;

public class HorseGUI {
    private String name;
    private String breed;
    private String symbol;
    private Color color;
    private int distanceTravelled;
    private boolean hasFallen;
    private double confidence;

    public HorseGUI(String name, String breed, String symbol, Color color, double confidence) {
        this.name = name;
        this.breed = breed;
        this.symbol = symbol;
        this.color = color;
        this.confidence = confidence;
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }

    public void moveForward() {
        if (!hasFallen) {
            distanceTravelled += 5; // move 5 pixels per update
        }
    }

    public void fall() {
        hasFallen = true;
    }

    public void reset() {
        distanceTravelled = 0;
        hasFallen = false;
    }

    public String getName() { return name; }
    public String getBreed() { return breed; }
    public String getSymbol() { return symbol; }
    public Color getColor() { return color; }
    public int getDistanceTravelled() { return distanceTravelled; }
    public boolean hasFallen() { return hasFallen; }
    public double getConfidence() { return confidence; }
}

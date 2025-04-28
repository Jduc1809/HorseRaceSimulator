/**
 * Represents a horse participating in the race.
 * Implements encapsulation with private fields and public accessors/mutators.
 */
public class Horse
{
    // Fields
    private String horseName;
    private String horseSymbol;
    private int distanceTravelled;
    private boolean hasFallen;
    private double horseConfidence;

    // Constructor
    public Horse(String horseSymbol, String horseName, double horseConfidence)
    {
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        setConfidence(horseConfidence); // Use setter for validation
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }

    // Methods
    public void fall()
    {
        this.hasFallen = true;
    }

    public double getConfidence()
    {
        return this.horseConfidence;
    }

    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }

    public String getName()
    {
        return this.horseName;
    }

    public String getSymbol()
    {
        return this.horseSymbol;
    }

    public void goBackToStart()
    {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }

    public boolean hasFallen()
    {
        return this.hasFallen;
    }

    public void moveForward()
    {
        this.distanceTravelled += 1;
    }

    public void setConfidence(double newConfidence)
    {
        if (newConfidence < 0.0) {
            this.horseConfidence = 0.0;
        } else if (newConfidence > 1.0) {
            this.horseConfidence = 1.0;
        } else {
            this.horseConfidence = newConfidence;
        }
    }

    public void setSymbol(String newSymbol)
    {
        this.horseSymbol = newSymbol;
    }
}

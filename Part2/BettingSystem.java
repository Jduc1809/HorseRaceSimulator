public class BettingSystem {
    private int balance;
    private String selectedHorse;
    private int betAmount;

    public BettingSystem(int startingBalance) {
        balance = startingBalance;
        selectedHorse = "";
        betAmount = 0;
    }

    public void placeBet(String horseName, int amount) {
        if (amount <= balance && amount > 0) {
            selectedHorse = horseName;
            betAmount = amount;
        } else {
            selectedHorse = "";
            betAmount = 0;
        }
    }

    public String resolveBet(String winnerHorseName) {
        if (selectedHorse.equals("")) {
            return "No valid bet placed.";
        }

        if (selectedHorse.equals(winnerHorseName)) {
            balance += betAmount; // win, gain amount
            return "You won the bet! New balance: " + balance + " credits.";
        } else {
            balance -= betAmount; // lose, lose amount
            return "You lost the bet. New balance: " + balance + " credits.";
        }
    }

    public int getBalance() {
        return balance;
    }
}

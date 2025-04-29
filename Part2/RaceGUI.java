import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RaceGUI extends JFrame {
    private TrackPanel trackPanel;
    private Timer timer;
    private StatisticsManager stats;
    private BettingSystem betting;
    private JComboBox<String> horseSelectBox;
    private JTextField betAmountField;

    public RaceGUI() {
        setTitle("Horse Race Simulator - GUI Version");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setup horses
        List<HorseGUI> horses = new ArrayList<>();
        horses.add(new HorseGUI("Ferrari", "Thoroughbred", "🏎", java.awt.Color.RED, 0.9));
        horses.add(new HorseGUI("Porsche", "Arabian", "🚗", java.awt.Color.BLUE, 0.85));
        horses.add(new HorseGUI("Audi", "Quarter Horse", "🚙", java.awt.Color.GREEN, 0.8));

        trackPanel = new TrackPanel(horses);
        add(trackPanel, BorderLayout.CENTER);

        stats = new StatisticsManager();
        betting = new BettingSystem(100); // Start with 100 credits

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Weather selection
        JComboBox<String> weatherBox = new JComboBox<>(new String[]{"Sunny", "Muddy", "Icy"});
        weatherBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) weatherBox.getSelectedItem();
                switch (selected) {
                    case "Sunny":
                        trackPanel.setWeather(WeatherCondition.SUNNY);
                        break;
                    case "Muddy":
                        trackPanel.setWeather(WeatherCondition.MUDDY);
                        break;
                    case "Icy":
                        trackPanel.setWeather(WeatherCondition.ICY);
                        break;
                }
            }
        });

        // Horse selection for betting
        horseSelectBox = new JComboBox<>(new String[]{"Ferrari", "Porsche", "Audi"});

        // Bet amount field
        betAmountField = new JTextField(5);

        // Start Race button
        JButton startButton = new JButton("Start Race!");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedHorse = (String) horseSelectBox.getSelectedItem();
                int betAmount = 0;
                try {
                    betAmount = Integer.parseInt(betAmountField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RaceGUI.this,
                        "Invalid bet amount. Please enter a number.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (betAmount <= 0 || betAmount > betting.getBalance()) {
                    JOptionPane.showMessageDialog(RaceGUI.this,
                        "Invalid bet! Bet must be positive and within your balance.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                betting.placeBet(selectedHorse, betAmount);
                startRace();
            }
        });

        controlPanel.add(new JLabel("Weather:"));
        controlPanel.add(weatherBox);
        controlPanel.add(new JLabel("Bet on:"));
        controlPanel.add(horseSelectBox);
        controlPanel.add(new JLabel("Amount:"));
        controlPanel.add(betAmountField);
        controlPanel.add(startButton);

        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center window
        setVisible(true);
    }

    private void startRace() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                trackPanel.updateRace();
                HorseGUI winner = trackPanel.checkWinner();
                if (winner != null) {
                    timer.stop();
                    stats.recordWin(winner.getName()); // Record winner
                    String bettingResult = betting.resolveBet(winner.getName()); // Resolve bet result

                    JOptionPane.showMessageDialog(RaceGUI.this,
                        "🏆 The winner is " + winner.getName() + " (" + winner.getBreed() + ")!\n\n" +
                        stats.getStats() + "\n" + bettingResult,
                        "Race Finished", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        timer.start();
    }
}

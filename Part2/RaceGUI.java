import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class RaceGUI extends JFrame {
    private TrackPanel trackPanel;
    private javax.swing.Timer timer;

    public RaceGUI() {
        setTitle("Horse Race Simulator - GUI Version");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setup horses
        List<HorseGUI> horses = new ArrayList<>();
        horses.add(new HorseGUI("Ferrari", "Thoroughbred", "🏎", Color.RED, 0.9));
        horses.add(new HorseGUI("Porsche", "Arabian", "🚗", Color.BLUE, 0.85));
        horses.add(new HorseGUI("Audi", "Quarter Horse", "🚙", Color.GREEN, 0.8));

        trackPanel = new TrackPanel(horses);
        add(trackPanel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Race!");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startRace();
            }
        });

        add(startButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // center window
        setVisible(true);
    }

    private void startRace() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new javax.swing.Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                trackPanel.updateRace();
                HorseGUI winner = trackPanel.checkWinner();
                if (winner != null) {
                    timer.stop();
                    JOptionPane.showMessageDialog(RaceGUI.this,
                        "🏆 The winner is " + winner.getName() + " (" + winner.getBreed() + ")!",
                        "Race Finished", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        timer.start();
    }
}

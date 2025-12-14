import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InteractiveLayer extends JPanel {

    private GameFrame frame;
    private City city;

    private JComboBox<String> buildingSelector;
    private JTextArea statusArea;
    private JTextArea statsArea;

    // Building options matching City.java keys
    private final String[] BUILDINGS = {
            "Solar Power Plant",
            "Coal Power Plant",
            "Park",
            "School",
            "Factory",
            "Public Transport",
            "Shop",
            "Public Service",
            "Residence"
    };



    public InteractiveLayer(GameFrame frame, City city) {
        this.frame = frame;
        this.city = city;

        setLayout(null);
        setOpaque(false);

        // --- Controls Panel ---
        JPanel controls = new JPanel();
        controls.setBounds(10, 10, 300, 300);
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
        controls.setBackground(new Color(255, 255, 255, 200)); // Semi-transparent white
        controls.setBorder(BorderFactory.createEtchedBorder());
        add(controls);

        // Label
        JLabel title = new JLabel("Construction Menu");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        controls.add(title);
        // controls.add(Box.createVerticalStrut(10));

        // Selector
        buildingSelector = new JComboBox<>(BUILDINGS);
        buildingSelector.setMaximumSize(new Dimension(280, 30));
        controls.add(buildingSelector);
        controls.add(Box.createVerticalStrut(10));

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        JButton addBtn = new JButton("Add Building");
        JButton remBtn = new JButton("Remove Building");
        btnPanel.add(addBtn);
        btnPanel.add(remBtn);
        controls.add(btnPanel);

        // Status Message Area
        statusArea = new JTextArea(3, 20);
        statusArea.setEditable(false);
        statusArea.setLineWrap(true);
        statusArea.setWrapStyleWord(true);
        statusArea.setOpaque(false);
        statusArea.setText("Welcome! Select a building to construct.");
        statusArea.setBorder(BorderFactory.createTitledBorder("Status"));
        controls.add(statusArea);

        // City Stats Area
        statsArea = new JTextArea(6, 20);
        statsArea.setEditable(false);
        statsArea.setOpaque(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        statsArea.setBorder(BorderFactory.createTitledBorder("City Metrics"));
        controls.add(statsArea);

        // Finish Button
        JButton finishBtn = new JButton("Finish City");
        finishBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        finishBtn.addActionListener(e -> finishGame());
        controls.add(finishBtn);

        // --- Event Listeners ---
        addBtn.addActionListener(e -> handleAdd());
        remBtn.addActionListener(e -> handleRemove());

        // Initial Stats Update
        updateStats();
    }

    private void handleAdd() {
        String type = (String) buildingSelector.getSelectedItem();
        if (type == null)
            return;

        // Check limit
        if (!city.canAdd(type, 1)) {
            statusArea.setText("Cannot add " + type + ". Limit of 3 reached.");
            return;
        }

        // Create instance
        Buildings b = createBuilding(type);
        if (b != null) {
            city.addBuilding(b, 1);
            statusArea.setText("Constructed " + type + ".");
            frame.updateView();
            updateStats();
        }
    }

    private void handleRemove() {
        String type = (String) buildingSelector.getSelectedItem();
        if (type == null)
            return;

        int current = city.getCount(type);
        if (current <= 0) {
            statusArea.setText("No " + type + " to remove.");
            return;
        }

        Buildings b = createBuilding(type); // Need prototype for stats effect
        if (b != null) {
            city.removeBuilding(b, 1);
            statusArea.setText("Removed " + type + ".");
            frame.updateView();
            updateStats();
        }
    }

    private void updateStats() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Pollution:  %d\n", Math.max(0, city.getTotalPollution())));
        sb.append(String.format("Energy:     %d\n", city.getTotalEnergy()));
        sb.append(String.format("Happiness:  %d\n", city.getTotalHappiness()));
        sb.append(String.format("Well-Being: %d\n", city.getTotalWellBeing()));
        statsArea.setText(sb.toString());
    }

    private void finishGame() {


        String msg = getFinishMessage();
        JOptionPane.showMessageDialog(frame, msg, "City Report", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private String getFinishMessage() {
        int rawP = city.getTotalPollution();
        int P = Math.max(0, rawP);
        int E = city.getTotalEnergy();
        int H = city.getTotalHappiness();
        int W = city.getTotalWellBeing();

        final int TARGET = 30;

        double pollutionHealthPct = clampPercent((TARGET - P) / (double) TARGET * 100);
        double energyPct = clampPercent(E / (double) TARGET * 100);
        double happinessPct = clampPercent(H / (double) TARGET * 100);
        double wellbeingPct = clampPercent(W / (double) TARGET * 100);

        double overall = (pollutionHealthPct + energyPct + happinessPct + wellbeingPct) / 4;

        // ===== CITY ENDING =====
        String endingText;

        if (pollutionHealthPct >= 90 && energyPct >= 80 && happinessPct >= 85 && wellbeingPct >= 85) {
            endingText =
                    "UTOPIA\n" +
                            "Your city has become a perfect harmony of clean energy, happy citizens, " +
                            "and thriving well-being. A shining example for the world!";

        } else if (pollutionHealthPct >= 80 && energyPct >= 70 && happinessPct >= 70 && wellbeingPct >= 70) {
            endingText =
                    "ECO PARADISE\n" +
                            "Your city is a sustainable haven with strong infrastructure and satisfied citizens.";

        } else if (pollutionHealthPct < 40 && happinessPct >= 80 && wellbeingPct >= 80 && energyPct >= 60) {
            endingText =
                    "JOYFUL CHAOS\n" +
                            "Despite environmental struggles, your citizens remain incredibly happy and socially fulfilled.";

        } else if (pollutionHealthPct < 50 && energyPct < 40) {
            endingText =
                    "INDUSTRIAL COLLAPSE\n" +
                            "The city has been overwhelmed by pollution and energy shortages, " +
                            "leading to declining living conditions.";

        } else if (happinessPct < 40 && wellbeingPct < 40 && pollutionHealthPct >= 60) {
            endingText =
                    "EMPTY SHELL\n" +
                            "Your city is clean and efficient — but emotionally lifeless. " +
                            "Something important is missing.";

        } else if (energyPct >= 85 && (happinessPct < 50 || wellbeingPct < 50)) {
            endingText =
                    "TECHNOCRATIC STATE\n" +
                            "Your city runs like a machine: powerful, efficient, but lacking warmth and community spirit.";

        } else {
            endingText =
                    "BALANCED REALITY\n" +
                            "Your city has grown into a realistic society with strengths, weaknesses, and room to evolve.";
        }

        return String.format(
                "Final Score: %.1f%%\n\n%s\n\n" +
                        "Pollution Health: %.1f%%\n" +
                        "Energy Score: %.1f%%\n" +
                        "Happiness Score: %.1f%%\n" +
                        "Well-being Score: %.1f%%", overall, endingText, pollutionHealthPct, energyPct, happinessPct, wellbeingPct
        );
    }




    private double clampPercent(double v) {
        if (v < 0)
            return 0;
        if (v > 100)
            return 100;
        return v;
    }

    private Buildings createBuilding(String type) {
        switch (type) {
            case "Solar Power Plant":
                return new GreenPowerPlant();
            case "Coal Power Plant":
                return new CoalPowerPlant();
            case "Park":
                return new Park();
            case "School":
                return new School();
            case "Factory":
                return new Factory();
            case "Public Transport":
                return new PublicTransport();
            case "Shop":
                return new Shop();
            case "Public Service":
                return new PublicService();
            case "Residence":
                return new Residences();
            default:
                return null;
        }
    }
}

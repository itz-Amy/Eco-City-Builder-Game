import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GameFrame extends JFrame {

    public static final int MIN_WIDTH = 1000;
    public static final int MIN_HEIGHT = 800;

    private City city;
    private JLayeredPane layeredPane;

    // Layers
    private ImageLayer backgroundLayer;
    private ImageLayer factoryLayer;
    private ImageLayer coalLayer;
    private ImageLayer greenLayer;
    private ImageLayer parkLayer;
    private ImageLayer servicesLayer;
    private ImageLayer residenceLayer;
    private ImageLayer shopLayer;
    private ImageLayer schoolLayer;
    private ImageLayer transportLayer;

    private InteractiveLayer interactiveLayer;

    public GameFrame(City city) {
        this.city = city;
        setTitle("Eco City Builder");
        setSize(MIN_WIDTH, MIN_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //  Layered Pane
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, getWidth(), getHeight());
        add(layeredPane);



        backgroundLayer = new ImageLayer("src/images/Plain bg.png");
        backgroundLayer.setBounds(0, 0, getWidth(), getHeight());

        factoryLayer = new ImageLayer(null);
        coalLayer = new ImageLayer(null);
        greenLayer = new ImageLayer(null);
        parkLayer = new ImageLayer(null);
        servicesLayer = new ImageLayer(null);
        residenceLayer = new ImageLayer(null);
        shopLayer = new ImageLayer(null);
        schoolLayer = new ImageLayer(null);
        transportLayer = new ImageLayer(null);

        // Set bounds for all layers
        ImageLayer[] layers = { factoryLayer, coalLayer, greenLayer, parkLayer, servicesLayer, residenceLayer,
                shopLayer, schoolLayer, transportLayer };
        for (ImageLayer il : layers) {
            il.setBounds(0, 0, getWidth(), getHeight());
        }

        // Add to Layered Pane (Order matters: 0 is bottom)
        layeredPane.add(backgroundLayer, Integer.valueOf(0));
        layeredPane.add(factoryLayer, Integer.valueOf(1));
        layeredPane.add(coalLayer, Integer.valueOf(2));
        layeredPane.add(greenLayer, Integer.valueOf(3));
        layeredPane.add(parkLayer, Integer.valueOf(4));
        layeredPane.add(servicesLayer, Integer.valueOf(5));
        layeredPane.add(residenceLayer, Integer.valueOf(6));
        layeredPane.add(shopLayer, Integer.valueOf(7));
        layeredPane.add(schoolLayer, Integer.valueOf(8));
        layeredPane.add(transportLayer, Integer.valueOf(9));

        // Interactive Layer (Top most)
        interactiveLayer = new InteractiveLayer(this, city);
        interactiveLayer.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(interactiveLayer, Integer.valueOf(20));

        setVisible(true);
    }

    public void updateView() {
        // Update each layer based on City counts
        updateLayer(factoryLayer, "Factory", "Factories", 3);
        updateLayer(coalLayer, "Coal Power Plant", "Coal Power Plant", 2);
        updateLayer(parkLayer, "Park", "Park", 2);



        updateLayer(greenLayer, "Solar Power Plant", "Green energy", 3);
        updateLayer(servicesLayer, "Public Service", "Public service", 4); // File list shows up to 4
        updateLayer(residenceLayer, "Residence", "Residence", 7);

        updateLayer(shopLayer, "Shop", "Shops", 3);
        updateLayer(schoolLayer, "School", "School", 2); // File list shows School 1, School 2.
        updateLayer(transportLayer, "Public Transport", "Transport", 5);

        repaint();
    }

    private void updateLayer(ImageLayer layer, String buildingName, String filePrefix, int maxFiles) {
        int count = city.getCount(buildingName);
        if (count > 0) {
            // Cap visual at max available images if count exceeds logical files
            int visualIndex = Math.min(count, maxFiles);
            layer.setImagePath("src/images/" + filePrefix + " " + visualIndex + ".png");
        } else {
            layer.setImagePath(null);
        }
    }
}

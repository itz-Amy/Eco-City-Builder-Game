import javax.swing.*;
import java.awt.*;

public class FrameTest extends JFrame {

    public static final int MIN_WIDTH = 480;
    public static final int MIN_HEIGHT = 360;

    private JLayeredPane layeredPane;
    private ImageLayer factoryLayer;
    private ImageLayer coalLayer;
    private ImageLayer greenLayer;
    private ImageLayer parkLayer;
    private ImageLayer servicesLayer;
    private ImageLayer residenceLayer;
    private ImageLayer shopLayer;
    private ImageLayer schoolLayer;
    private ImageLayer transportLayer;
    private ImageLayer backgroundLayer;
    private InteractiveLayer interactiveLayer;
    private JPanel tooSmallPanel;

//        private JLayeredPane layeredPane;
//        private ImageLayer backgroundLayer;

        public FrameTest() {
            setTitle("Image Pane Test");
            setSize(1640, 740);
            setDefaultCloseOperation(EXIT_ON_CLOSE);


            // Create the layered pane
            layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, 0, getWidth(), getHeight()); // full frame size
            layeredPane.setLayout(null); // absolute positioning for layers
            add(layeredPane);

            //Allows scaling
            layeredPane.setLayout(new OverlayLayout(layeredPane));

            // Create layers
            backgroundLayer = new ImageLayer("src/images/Plain bg.png");
            backgroundLayer.setSize(getWidth(), getHeight());

            factoryLayer = new ImageLayer("images/Transparent.png");
            coalLayer = new ImageLayer("images/Transparent.png");
            greenLayer = new ImageLayer("images/Transparent.png");
            parkLayer = new ImageLayer("images/Transparent.png");
            servicesLayer = new ImageLayer("images/Transparent.png");
            residenceLayer = new ImageLayer("images/Transparent.png");
            shopLayer = new ImageLayer("images/Transparent.png");
            schoolLayer = new ImageLayer("images/Transparent.png");
            transportLayer = new ImageLayer("images/Transparent.png");

            int a = 1;
            int count = 1;
            switch (a) {
                case 1: // Factories
                    switch (count) {
                        case 1:
                            factoryLayer = new ImageLayer("images/Factory 1.png");
                            break;
                        case 2:
                            factoryLayer = new ImageLayer("images/Factory 2.png");
                            break;
                        case 3:
                            factoryLayer = new ImageLayer("images/Factory 3.png");
                            break;
                        default:
                            System.out.println("Invalid factory count");
                            break;
                    }
                    break;

                case 2: // Coal Power Plant
                    switch (count) {
                        case 1:
                            coalLayer = new ImageLayer("images/Coal Power Plant 1.png");
                            break;
                        case 2:
                            coalLayer = new ImageLayer("images/Coal Power Plant 2.png");
                            break;
                        default:
                            System.out.println("Invalid coal plant count");
                            break;
                    }
                    break;

                case 3: // Green Power Plant
                    switch (count) {
                        case 1:
                            greenLayer = new ImageLayer("images/Green energy 1.png");
                            break;
                        case 2:
                            greenLayer = new ImageLayer("images/Green energy 2.png");
                            break;
                        case 3:
                            greenLayer = new ImageLayer("images/Green energy 3.png");
                            break;
                        default:
                            System.out.println("Invalid green plant count");
                            break;
                    }
                    break;

                case 4: // Park
                    switch (count) {
                        case 1:
                            parkLayer = new ImageLayer("images/Park 1.png");
                            break;
                        case 2:
                            parkLayer = new ImageLayer("images/Park 2.png");
                            break;
                        case 3:
                            parkLayer = new ImageLayer("images/Park 3.png");
                            break;
                        default:
                            System.out.println("Invalid park count");
                            break;
                    }
                    break;

                case 5: // Public Services
                    switch (count) {
                        case 1:
                            servicesLayer = new ImageLayer("images/Services 1.png");
                            break;
                        case 2:
                            servicesLayer = new ImageLayer("images/Services 2.png");
                            break;
                        case 3:
                            servicesLayer = new ImageLayer("images/Services 3.png");
                            break;
                        case 4:
                            servicesLayer = new ImageLayer("images/Services 4.png");
                            break;
                        default:
                            System.out.println("Invalid services count");
                            break;
                    }
                    break;

                case 6: // Residences
                    switch (count) {
                        case 1:
                            residenceLayer = new ImageLayer("images/Residence 1.png");
                            break;
                        case 2:
                            residenceLayer = new ImageLayer("images/Residence 2.png");
                            break;
                        case 3:
                            residenceLayer = new ImageLayer("images/Residence 3.png");
                            break;
                        case 4:
                            residenceLayer = new ImageLayer("images/Residence 4.png");
                            break;
                        case 5:
                            residenceLayer = new ImageLayer("images/Residence 5.png");
                            break;
                        case 6:
                            residenceLayer = new ImageLayer("images/Residence 6.png");
                            break;
                        case 7:
                            residenceLayer = new ImageLayer("images/Residence 7.png");
                            break;
                        default:
                            System.out.println("Invalid residence count");
                            break;
                    }
                    break;

                case 7: // Shops
                    switch (count) {
                        case 1:
                            shopLayer = new ImageLayer("images/Shop 1.png");
                            break;
                        case 2:
                            shopLayer = new ImageLayer("images/Shop 2.png");
                            break;
                        case 3:
                            shopLayer = new ImageLayer("images/Shop 3.png");
                            break;
                        default:
                            System.out.println("Invalid shop count");
                            break;
                    }


                case 8: // School
                    switch (count) {
                        case 1:
                            schoolLayer = new ImageLayer("images/School 1.png");
                            break;
                        case 2:
                            schoolLayer = new ImageLayer("images/School 2.png");
                            break;
                        default:
                            schoolLayer = new ImageLayer("images/Transparent.png");
                            break;
                    }
                    break;

                case 9: // Public Transport
                    switch (count) {
                        case 1:
                            transportLayer = new ImageLayer("images/Transport 1.png");
                            break;
                        case 2:
                            transportLayer = new ImageLayer("images/Transport 2.png");
                            break;
                        case 3:
                            transportLayer = new ImageLayer("images/Transport 3.png");
                            break;
                        case 4:
                            transportLayer = new ImageLayer("images/Transport 4.png");
                            break;
                        case 5:
                            transportLayer = new ImageLayer("images/Transport 5.png");
                            break;
                        default:
                            transportLayer = new ImageLayer("images/Transparent.png");
                            break;
                    }
                    break;
            }


            layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, 0, 1640, 740);
            layeredPane.setLayout(null);  // IMPORTANT for layered layout

// Background at the lowest level (0)
            backgroundLayer.setBounds(0, 0, 1340, 740);
            layeredPane.add(backgroundLayer, 0);

// Now stack every other layer on top, one level higher each time
            factoryLayer.setBounds(0, 0, 1640, 740);
            layeredPane.add(factoryLayer, 1);

            coalLayer.setBounds(0, 0, 1640, 740);
            layeredPane.add(coalLayer, 2);

            greenLayer.setBounds(0, 0, 1640, 740);
            layeredPane.add(greenLayer, 3);

            parkLayer.setBounds(0, 0, 1640, 740);
            layeredPane.add(parkLayer, 4);

            servicesLayer.setBounds(0, 0, 1640, 740);
            layeredPane.add(servicesLayer, 5);

            residenceLayer.setBounds(0, 0, 1640, 740);
            layeredPane.add(residenceLayer, 6);

            shopLayer.setBounds(0, 0, 1640, 740);
            layeredPane.add(shopLayer, 7);

            schoolLayer.setBounds(0, 0, 1640, 740);
            layeredPane.add(schoolLayer, 8);

            transportLayer.setBounds(0, 0, 1640, 740);
            layeredPane.add(transportLayer, 9);


// Add layeredPane to frame
            add(layeredPane);

            setSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
            setLayout(new BorderLayout());
            setVisible(true);

        }
}






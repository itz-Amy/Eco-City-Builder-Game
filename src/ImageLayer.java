import javax.swing.*;
import java.awt.*;
import java.util.Objects;

//Panes for images used in the GUI
public class ImageLayer extends JPanel {
    private Image image;

    // Load image using a direct file path
    public ImageLayer(String path) {
        setImagePath(path);
        setOpaque(false);
    }

    public void setImagePath(String path) {
        if (path == null) {
            this.image = null;
            repaint();
            return;
        }

        try {
            java.io.File f = new java.io.File(path);
            if (!f.exists()) {
                System.out.println("DEBUG: Image file NOT found at: " + f.getAbsolutePath());
            } else {
                System.out.println("DEBUG: Loading image from: " + f.getAbsolutePath());
            }
            image = new ImageIcon(path).getImage();

            // Check if image loaded
            if (image.getWidth(null) == -1) {
                System.out.println("DEBUG: Image not fully loaded yet or invalid: " + path);
            }

            repaint();
        } catch (Exception e) {
            System.err.println("Could not load image: " + path);
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Draw image scaled to fit component bounds
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
    }
}


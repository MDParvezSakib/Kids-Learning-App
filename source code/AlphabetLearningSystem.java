import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;

public class AlphabetLearningSystem extends JFrame {

    private Map<String, String> alphabetMap;
    private JLabel imageLabel;
    private JLabel textLabel;

    public AlphabetLearningSystem() {
        setTitle("Alphabet Learning System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        alphabetMap = new HashMap<>();
        loadAlphabetData();

        // Create a panel for the banner
        JPanel bannerPanel = new JPanel();
        bannerPanel.setBackground(new Color(255, 228, 181)); // Light orange
        JLabel bannerLabel = new JLabel("Welcome to the Alphabet Learning System!");
        bannerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        bannerLabel.setForeground(new Color(139, 69, 19)); // Brown
        bannerPanel.add(bannerLabel);
        add(bannerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 7, 10, 10)); // 4 rows, 7 columns, 10px gap
        buttonPanel.setBackground(new Color(255, 245, 238)); // White smoke

        Color[] colors = {new Color(240, 128, 128), new Color(135, 206, 250), new Color(144, 238, 144), new Color(255, 182, 193), new Color(255, 165, 0)};
        int colorIndex = 0;

        for (char c = 'A'; c <= 'Z'; c++) {
            JButton button = createAlphabetButton(String.valueOf(c), colors[colorIndex % colors.length]);
            buttonPanel.add(button);
            colorIndex++;
        }

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        textLabel = new JLabel();
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.add(imageLabel, BorderLayout.CENTER);
        displayPanel.add(textLabel, BorderLayout.SOUTH);
        displayPanel.setBackground(new Color(255, 245, 238)); // White smoke

        add(buttonPanel, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.SOUTH);
    }

    private void loadAlphabetData() {
        alphabetMap.put("A", "apple");
        alphabetMap.put("B", "ball");
        alphabetMap.put("C", "cat");
        alphabetMap.put("D", "dog");
        alphabetMap.put("E", "elephant");
        alphabetMap.put("F", "fish");
        alphabetMap.put("G", "goat");
        alphabetMap.put("H", "hat");
        alphabetMap.put("I", "ice cream");
        alphabetMap.put("J", "juice");
        alphabetMap.put("K", "kite");
        alphabetMap.put("L", "lion");
        alphabetMap.put("M", "monkey");
        alphabetMap.put("N", "nose");
        alphabetMap.put("O", "orange");
        alphabetMap.put("P", "pencil");
        alphabetMap.put("Q", "queen");
        alphabetMap.put("R", "rabbit");
        alphabetMap.put("S", "sun");
        alphabetMap.put("T", "tiger");
        alphabetMap.put("U", "umbrella");
        alphabetMap.put("V", "van");
        alphabetMap.put("W", "whale");
        alphabetMap.put("X", "xylophone");
        alphabetMap.put("Y", "yacht");
        alphabetMap.put("Z", "zebra");
    }

    private JButton createAlphabetButton(String letter, Color color) {
        JButton button = new JButton(letter);
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(60, 60));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAlphabetImage(letter);
            }
        });
        return button;
    }

    private void showAlphabetImage(String letter) {
        String thing = alphabetMap.get(letter);
        if (thing != null) {
            // Load image from the images directory
            ImageIcon icon = new ImageIcon("images/" + thing + ".png");
            animateImageDisplay(icon);
            textLabel.setText(letter + " for " + thing.substring(0, 1).toUpperCase() + thing.substring(1));
        }
    }

    private void animateImageDisplay(ImageIcon icon) {
        imageLabel.setIcon(null); // Clear previous image

        Timer timer = new Timer(50, new ActionListener() {
            private float opacity = 0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.1f;
                if (opacity > 1f) {
                    opacity = 1f;
                    ((Timer) e.getSource()).stop();
                }
                imageLabel.setIcon(new ImageIcon(getImageWithOpacity(icon.getImage(), opacity)));
            }
        });
        timer.start();
    }

    private Image getImageWithOpacity(Image srcImage, float opacity) {
        BufferedImage bufferedImage = new BufferedImage(srcImage.getWidth(null), srcImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.setComposite(AlphaComposite.SrcOver.derive(opacity));
        g2.drawImage(srcImage, 0, 0, null);
        g2.dispose();
        return bufferedImage;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AlphabetLearningSystem frame = new AlphabetLearningSystem();
                frame.setVisible(true);
            }
        });
    }
}

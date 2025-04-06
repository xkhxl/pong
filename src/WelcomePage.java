import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WelcomePage extends JFrame {
    private JTextField levelField;

    public WelcomePage() {
        setTitle("Pong");
        setSize(600, 350); // Adjusted size for better aspect ratio
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon("images/pong-69.png").getImage());

        Font orbitronFont = loadCustomFont("fonts/Orbitron-Black.ttf", 30);
        Font bebasFont = loadCustomFont("fonts/BebasNeue-Regular.ttf", 22);
        Font electrFont = loadCustomFont("fonts/Electrolize-Regular.ttf", 20);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel welcomeMessage = new JLabel("PONG GAME", SwingConstants.CENTER);
        welcomeMessage.setFont(orbitronFont);
        welcomeMessage.setForeground(Color.WHITE);
        // welcomeMessage.setBorder(BorderFactory.createEmptyBorder(-20, 0, 10, 0)); //
        // Moves Up!

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 2;
        gbc.insets = new Insets(50, 10, 5, 10);
        panel.add(welcomeMessage, gbc);

        // Level Label
        JLabel levelLabel = new JLabel("Enter Level:");
        levelLabel.setFont(electrFont);
        levelLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        panel.add(levelLabel, gbc);

        // Level Input Field
        levelField = new JTextField(12);
        levelField.setFont(orbitronFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(levelField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0)); // Increased horizontal spacing
        buttonPanel.setBackground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        // Play Now Button
        JButton playNowButton = createStyledButton("PLAY NOW", bebasFont);
        playNowButton.addActionListener(e -> startGame());
        buttonPanel.add(playNowButton);

        // Instructions Button
        JButton howToPlayButton = createStyledButton("INSTRUCTIONS", bebasFont);
        howToPlayButton.addActionListener(e -> showInstructions());
        buttonPanel.add(howToPlayButton);

        add(panel);
        setVisible(true);
    }

    // Load custom fonts
    private Font loadCustomFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) size); // Fallback font
        }
    }

    // Create styled buttons with padding, hover effects, and rounded edges
    private JButton createStyledButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setPreferredSize(new Dimension(180, 50));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 30, 30));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.BLACK);
            }
        });

        return button;
    }

    private void startGame() {
        String level = levelField.getText();
        try {
            int levelNum = Integer.parseInt(level);
            new GameFrame(levelNum);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid level number!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showInstructions() {
        JOptionPane.showMessageDialog(this, "Pong Game Instructions:\n" +
                "1. Use W/S keys to control the left paddle.\n" +
                "2. Use Up/Down keys to control the right paddle.\n" +
                "3. Prevent the ball from touching the boundary wall.\n" +
                "4. Gain points when the opponent fails to bounce back the ball.",
                "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WelcomePage::new);
    }
}

package src.main.view.pages;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

import src.main.view.components.*;

@SuppressWarnings("serial")
public class MenuView extends JFrame {
    
    private final Color PANEL_BG = new Color(4, 113, 166); 
    private final Color CONTENT_BG = new Color(240, 240, 240); 
    private final Color INFO_BG = new Color(220, 220, 220); 
    private BackButton backButton;
    
    public JTabbedPane tabbedPane;
    public List<JButton> editButtons = new ArrayList<>();

   
    public MenuView() {
        setSize(850, 650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("SGCU - Menús Disponibles");
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(PANEL_BG);
        getContentPane().add(mainPanel);

        JLabel mainTitle = new JLabel("MENÚS DEL DÍA", SwingConstants.CENTER);
        mainTitle.setFont(new Font("Sans Serif", Font.BOLD, 28));
        mainTitle.setForeground(Color.WHITE);
        mainTitle.setBorder(new EmptyBorder(15, 0, 15, 0));
        mainPanel.add(mainTitle, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Sans Serif", Font.BOLD, 16));
        tabbedPane.setBackground(new Color(3, 93, 146));
        tabbedPane.setForeground(Color.WHITE);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        backButton = new BackButton();
        backButton.setPreferredSize(new Dimension(150, 40));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(PANEL_BG);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addMenuTab(String title, String[] items, String timeRange, String price, String imageName, boolean isAdmin) {
        JPanel menuPanel = createMenuPanel(items, timeRange, price, imageName);

        if (isAdmin) {
            JButton editButton = new JButton("Editar " + title);
            editButton.setActionCommand("Editar" + title);
            menuPanel.add(editButton, BorderLayout.SOUTH);
            editButtons.add(editButton);
        }
        
        tabbedPane.addTab(title, menuPanel);
    }

    private JPanel createMenuPanel(String[] items, String timeRange, String price, String imageName) {
        JPanel containerPanel = new JPanel(new BorderLayout(10, 10));
        containerPanel.setBackground(CONTENT_BG);
        containerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel infoPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        infoPanel.setBackground(INFO_BG);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
            new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel timeLabel = new JLabel("⏰ " + timeRange);
        timeLabel.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        timeLabel.setForeground(Color.BLACK);
        
        JLabel priceLabel = new JLabel("💰 " + price);
        priceLabel.setFont(new Font("Sans Serif", Font.BOLD, 14));
        priceLabel.setForeground(new Color(0, 150, 0));

        infoPanel.add(timeLabel);
        infoPanel.add(priceLabel);
        containerPanel.add(infoPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(15, 0));
        contentPanel.setBackground(CONTENT_BG);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));

        ImageIcon icon = new ImageIcon("src/assets/" + imageName);
        Image scaledImage = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setBorder(new EmptyBorder(0, 0, 0, 15));
        contentPanel.add(imageLabel, BorderLayout.WEST);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(CONTENT_BG);

        JLabel itemsTitle = new JLabel("🍽️ Incluye:");
        itemsTitle.setFont(new Font("Sans Serif", Font.BOLD, 16));
        itemsTitle.setForeground(Color.BLACK);
        itemsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        itemsPanel.add(itemsTitle);
        itemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        for (String item : items) {
            JLabel itemLabel = new JLabel("• " + item);
            itemLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
            itemLabel.setForeground(Color.BLACK);
            itemLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            itemsPanel.add(itemLabel);
            itemsPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        }

        contentPanel.add(itemsPanel, BorderLayout.CENTER);
        containerPanel.add(contentPanel, BorderLayout.CENTER);

        return containerPanel;
    }

    public void setController(ActionListener controller) {
        for (JButton btn : editButtons) {
            btn.addActionListener(controller);
        }
        backButton.addActionListener(controller);
    }

}

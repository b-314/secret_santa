package io.github.b314;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout; 
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GiftGameUI {

    // UI frame
    private final JFrame frame;

    // Game state
    private GiftGame game;
    private boolean assignmentsCreated = false; 

    // UI components
    private JLabel gameTitleLabel;
    private DefaultListModel<String> playerListModel;
    private JList<String> playerList;
    private DefaultListModel<String> giftListModel;
    private JList<String> giftList;
    private JScrollPane giftScrollPane;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GiftGameUI::new);
    }

    public GiftGameUI() {
        frame = new JFrame("Secret Santa");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        createComponents();
        frame.setVisible(true);
        
        game = new GiftGame("Secret Santa"); 
    }

    private void createComponents() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Dimension buttonSize = new Dimension(160, 30);

        // =========================================================
        // LEFT SIDE
        // =========================================================
        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));

        // ---------- GAME TITLE ----------
        JPanel titlePanel = new JPanel(new BorderLayout());

        gameTitleLabel = new JLabel("Secret Santa");
        gameTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        JButton updateTitleButton = new JButton("Update Title");
        updateTitleButton.setPreferredSize(buttonSize);
        updateTitleButton.setMinimumSize(buttonSize);
        updateTitleButton.setMaximumSize(buttonSize);

        updateTitleButton.addActionListener(e -> updateGameTitle());

        titlePanel.add(gameTitleLabel, BorderLayout.WEST);
        titlePanel.add(updateTitleButton, BorderLayout.EAST);

        // ---------- PLAYER LIST ----------
        playerListModel = new DefaultListModel<>();
        playerList = new JList<>(playerListModel);

        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane playerScrollPane = new JScrollPane(playerList);
        playerScrollPane.setBorder(BorderFactory.createTitledBorder("Players"));

        // ---------- GIFT LIST ----------
        giftListModel = new DefaultListModel<>();
        giftList = new JList<>(giftListModel);

        giftScrollPane = new JScrollPane(giftList);
        giftScrollPane.setBorder(BorderFactory.createTitledBorder("Gift Ideas"));

        // When a player is selected, update the gift list
        playerList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateSelectedPlayerGifts();
            }
        });

        // ---------- LEFT CONTENT ----------
        JPanel playerAndGiftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridy = 0;
        gbc.weighty = 1.2;
        playerAndGiftPanel.add(playerScrollPane, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.8;
        playerAndGiftPanel.add(giftScrollPane, gbc);

        leftPanel.add(titlePanel, BorderLayout.NORTH);
        leftPanel.add(playerAndGiftPanel, BorderLayout.CENTER);

        // =========================================================
        // RIGHT SIDE
        // =========================================================
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));

        // ---------- PLAYER LIST ----------
        JPanel playerButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton createPlayerButton = new JButton("Create Player");
        JButton deletePlayerButton = new JButton("Delete Player");
        JButton updateGiftsButton = new JButton("Update Gift List");

        createPlayerButton.setPreferredSize(buttonSize);
        deletePlayerButton.setPreferredSize(buttonSize);
        updateGiftsButton.setPreferredSize(buttonSize);

        createPlayerButton.addActionListener(e -> createPlayer());
        deletePlayerButton.addActionListener(e -> deletePlayer());
        updateGiftsButton.addActionListener(e -> updatePlayerGifts());

        playerButtonPanel.add(createPlayerButton);
        playerButtonPanel.add(deletePlayerButton);
        playerButtonPanel.add(updateGiftsButton);

        // ---------- ASSIGNMENTS ----------
        JPanel assignmentButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton assignButton = new JButton("Assign Players");
        JButton viewAssignmentButton = new JButton("View Assignment");

        assignButton.setPreferredSize(buttonSize);
        viewAssignmentButton.setPreferredSize(buttonSize);

        assignButton.addActionListener(e -> assignPlayers(assignButton));
        viewAssignmentButton.addActionListener(e -> viewAssignment());

        assignmentButtonPanel.add(assignButton);
        assignmentButtonPanel.add(viewAssignmentButton);

        // ---------- FILE I/O ----------
        JPanel importExportButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton importButton = new JButton("Import Players");
        JButton exportButton = new JButton("Export Assignments");

        importButton.setPreferredSize(buttonSize);
        exportButton.setPreferredSize(buttonSize);

        importButton.addActionListener(e -> importPlayers());
        exportButton.addActionListener(e -> exportAssignments());

        importExportButtonPanel.add(importButton);
        importExportButtonPanel.add(exportButton);

        playerButtonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        assignmentButtonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));
        importExportButtonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));

        // =========================================================
        // ADD PANELS
        // =========================================================
        rightPanel.add(playerButtonPanel);
        rightPanel.add(assignmentButtonPanel);
        rightPanel.add(importExportButtonPanel);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        frame.setContentPane(mainPanel);
    }

    private void updateGameTitle() {
        String newTitle = JOptionPane.showInputDialog(frame, "Enter title:", gameTitleLabel.getText());

        if (newTitle != null && !newTitle.trim().isEmpty()) {
            newTitle = newTitle.trim();
            gameTitleLabel.setText(newTitle);
            game.setTitle(newTitle);
        }
    }

    private void importPlayers() {
        JFileChooser chooser = new JFileChooser(); 
        int result = chooser.showOpenDialog(frame); 

        if(result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                game = GiftGameReader.gameReader(file); 
                updatePlayerList(); 
                gameTitleLabel.setText(game.getTitle()); 
                JOptionPane.showMessageDialog(frame, "Players imported successfully!"); 
            } catch(Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), 
                "Import Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportAssignments() {
        if (game == null) {
            JOptionPane.showMessageDialog(frame, "There is no game to export.", 
            "Export Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!assignmentsCreated) {
            JOptionPane.showMessageDialog(frame, "Please assign players before exporting assignments.", 
            "No Assignments", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Export Secret Santa Assignments");
        chooser.setSelectedFile(new File(game.getTitle() + "_assignments.json"));

        int result = chooser.showSaveDialog(frame);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = chooser.getSelectedFile();
        if (!file.getName().toLowerCase().endsWith(".json")) {
            file = new File(file.getAbsolutePath() + ".json");
        }

        if (file.exists()) {
            int overwrite = JOptionPane.showConfirmDialog(frame, "The file already exists. Overwrite it?",
            "Confirm Overwrite", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (overwrite != JOptionPane.YES_OPTION) {
                return;
            }
        }

        try {
            GiftGameWriter.gameWriter(game, file);
            JOptionPane.showMessageDialog(frame, "Assignments exported successfully!",
            "Export Complete", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Could not export assignments:\n" + ex.getMessage(), 
            "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePlayerList() {
        Player selectedPlayer = getSelectedPlayer();

        playerListModel.clear(); 
        for(Player player : game.getPlayers()) {
            playerListModel.addElement(player.getName()); 
        }

        if (selectedPlayer != null) {
            int index = game.getPlayers().indexOf(selectedPlayer);
            if (index >= 0) {
                playerList.setSelectedIndex(index);
            }
        }
        updateSelectedPlayerGifts();
    }

    private void updateSelectedPlayerGifts() {
        giftListModel.clear();
        Player selectedPlayer = getSelectedPlayer();
        if (selectedPlayer == null) {
            giftScrollPane.setBorder(BorderFactory.createTitledBorder("Gift Ideas"));
            return;
        }

        giftScrollPane.setBorder(BorderFactory.createTitledBorder(selectedPlayer.getName() + "'s Gift Ideas"));
        for (String gift : selectedPlayer.getGifts()) {
            giftListModel.addElement(gift);
        }
    }
    
    private void createPlayer() {
        String name = JOptionPane.showInputDialog(frame, "Enter player name:");
        if (name == null || name.trim().isEmpty()) {
            return;
        }
        name = name.trim();

        Player player;
        try {
            player = game.addPlayer(name);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), 
            "Could Not Create Player", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String gifts = JOptionPane.showInputDialog(frame, "Enter gift ideas separated by commas:");
        if (gifts == null) {
            game.removePlayer(player);
            return;
        }

        // Add each gift
        String[] giftArray = gifts.split(",");
        for (String gift : giftArray) {
            gift = gift.trim();
            if (!gift.isEmpty()) {
                player.addGift(gift);
            }
        }

        updatePlayerList();
        int newPlayerIndex = game.getPlayers().indexOf(player);
        if (newPlayerIndex >= 0) {
            playerList.setSelectedIndex(newPlayerIndex);
            playerList.ensureIndexIsVisible(newPlayerIndex);
        }
        
        JOptionPane.showMessageDialog(frame, "Player created successfully!");
    }

    private void updatePlayerGifts() {
        Player selectedPlayer = getSelectedPlayer();
        if(selectedPlayer == null) {
            JOptionPane.showMessageDialog(frame, "Please select a player first."); 
            return; 
        }

        String gifts = JOptionPane.showInputDialog(frame, "Enter gift ideas separated by commas:", selectedPlayer.getGiftsString());
        if (gifts == null) {
            return;
        }

        selectedPlayer.clearGifts();
        String[] giftArray = gifts.split(",");

        for (String gift : giftArray) {
            gift = gift.trim();
            if (!gift.isEmpty()) {
                selectedPlayer.addGift(gift);
            }
        }

        updateSelectedPlayerGifts();
        JOptionPane.showMessageDialog(frame, "Gift ideas updated successfully!");
    }

    public void deletePlayer() {
        Player selectedPlayer = getSelectedPlayer(); 
        if(selectedPlayer == null) {
            JOptionPane.showMessageDialog(frame, "Please select a player first."); 
            return; 
        }

        int result = JOptionPane.showConfirmDialog(frame, "Delete " + selectedPlayer.getName() + "?",
        "Confirm Delete", JOptionPane.YES_NO_OPTION); 

        if(result == JOptionPane.YES_OPTION) {
            game.removePlayer(selectedPlayer); 
            updatePlayerList(); 
        }
    }

    private Player getSelectedPlayer() {
        int index = playerList.getSelectedIndex(); 
        if(index == -1) {
            return null; 
        }
        else {
            return game.getPlayers().get(index); 
        }
    }

    private void assignPlayers(JButton assignButton) {
        if(game == null) {
            return;
        }

        if(assignmentsCreated) {
            int result = JOptionPane.showConfirmDialog(frame, "Re-assign everyone?", 
                "Confirm Re-assignment", JOptionPane.YES_NO_OPTION); 
            if(result != JOptionPane.YES_OPTION) {
                return; 
            }
        }

        try {
            game.assignPlayers();
            assignmentsCreated = true;
            assignButton.setText("Re-assign Players");
            JOptionPane.showMessageDialog(frame, "Secret Santa assignments completed!", 
            "Assignments Complete", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Could Not Assign Players", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewAssignment() {
        Player giver = getSelectedPlayer();

        if(giver == null) {
            JOptionPane.showMessageDialog(frame, "Please select a player.");
            return;
        }
        if(!assignmentsCreated) {
            JOptionPane.showMessageDialog(frame, "Please assign players before viewing assignments.");
            return;
        }

        Player giftee = giver.getAssigned(); 
        showAssignmentDialog(giver, giftee);
    }

    private void showAssignmentDialog(Player giver, Player giftee) {
        JDialog dialog = new JDialog(frame, "Secret Santa Assignment", true);

        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());

        // ---------- ASSIGNMENT PANEL ----------
        JPanel assignmentPanel = new JPanel();
        assignmentPanel.setLayout(new BoxLayout(assignmentPanel, BoxLayout.Y_AXIS));
        JLabel giverLabel = new JLabel(giver.getName() + ", you'll be the secret santa for...");
        giverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        giverLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        
        JLabel gifteeLabel = new JLabel(giftee.getName());
        gifteeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gifteeLabel.setFont(new Font("SansSerif", Font.BOLD, 32));

        JLabel giftIdeasLabel = new JLabel("Gift Ideas");
        giftIdeasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        giftIdeasLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        assignmentPanel.add(Box.createVerticalStrut(30));
        assignmentPanel.add(giverLabel);
        assignmentPanel.add(Box.createVerticalStrut(15));
        assignmentPanel.add(gifteeLabel);
        assignmentPanel.add(Box.createVerticalStrut(30));
        assignmentPanel.add(giftIdeasLabel);
        assignmentPanel.add(Box.createVerticalStrut(10));

        // ---------- GIFT IDEAS ----------
        DefaultListModel<String> giftModel = new DefaultListModel<>();

        for (String gift : giftee.getGifts()) {
            giftModel.addElement("• " + gift);
        }

        giftList = new JList<>(giftModel);

        giftList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        giftList.setVisibleRowCount(4);

        giftScrollPane = new JScrollPane(giftList);
        giftScrollPane.setPreferredSize(new Dimension(300, 100));
        giftScrollPane.setMaximumSize(new Dimension(300, 100));
        giftScrollPane.setMinimumSize(new Dimension(300, 100));
        giftScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        assignmentPanel.add(giftScrollPane);

        // ---------- BOTTOM PANEL ----------
        JLabel timerLabel = new JLabel("This assignment will hide in 10 seconds.", SwingConstants.CENTER);
        timerLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));

        // ---------- ADD COMPONENTS ----------
        dialog.add(assignmentPanel, BorderLayout.CENTER);
        dialog.add(timerLabel, BorderLayout.SOUTH);

        // ---------- 10 SECOND TIMER ----------
        Timer timer = new Timer(10_000, e -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();

        // ---------- SHOW DIALOG ----------
        dialog.setVisible(true);
    }
}

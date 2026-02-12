package io.github.b314;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GiftGameUI extends JFrame implements ActionListener {
    private static final String APP_TITILE = "Gift Game";

    /**
     * Starts the GUI for the GiftGame application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new GiftGameUI(); 
    }

    public GiftGameUI() {
        super(); 

        setSize(1200, 700);
        setLocation(50, 50); 
        setTitle(APP_TITILE); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 

        // frame = new JFrame("Gift Game"); 
        // frame.setSize(800, 600); 
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); 

        // frame.setVisible(true); 

        // JButton importButton = new JButton("Import Players"); 
        // importButton.addActionListener(e -> {
        //     JFileChooser chooser = new JFileChooser(); 
        //     int choice = chooser.showOpenDialog(frame); 
        //     if(choice == JFileChooser.APPROVE_OPTION) {
        //         File file = chooser.getSelectedFile(); 
        //         try {
        //             GiftGame game = GiftGameReader.gameReader(file);  
        //         }
        //         catch(IOException ioE) {
        //             JOptionPane.showMessageDialog(frame, ioE.getMessage(), "Invalid File", JOptionPane.ERROR_MESSAGE); 
        //         }
        //     }
        // });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

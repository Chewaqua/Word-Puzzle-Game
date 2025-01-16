import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Project: Word Puzzle Game by Saahil Patel
 * Lab Section:E
 */

public class FileMenuHandler implements ActionListener {
    JFrame jframe; // Reference to the main JFrame

    // Constructor that assigns the provided JFrame to the local variable
    public FileMenuHandler(JFrame jf) {
        jframe = jf;
    }

    // This method is triggered whenever an action occurs (like a menu item being clicked)
    public void actionPerformed(ActionEvent event) {
        String menuName = event.getActionCommand(); // Get the name of the menu item that was clicked

        if (menuName.equals("Open")) {
            JFileChooser fileChooser = new JFileChooser(); // Create a file chooser dialog
            int returnValue = fileChooser.showOpenDialog(null); // Show the file chooser and capture the user's response

            if (returnValue == JFileChooser.APPROVE_OPTION) { // If the user selects a file
                try {
                    // Attempt to read puzzle data from the selected file and restart the game loop
                    ((PuzzleGUI) jframe).readPuzzleData(fileChooser.getSelectedFile().getAbsolutePath());
                    ((PuzzleGUI) jframe).gameLoop(); // Restart or start the game loop after file is loaded
                } catch (FileNotFoundException e) {
                    // Show an error message if the file is not found
                    JOptionPane.showMessageDialog(jframe, "File not found: " + fileChooser.getSelectedFile().getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (menuName.equals("Quit")) { // If the "Quit" menu item is selected
            System.exit(0); // Exit the application
        }
    }
}

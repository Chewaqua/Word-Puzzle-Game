import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Project: Word Puzzle Game by Saahil Patel
 * Lab Section:E
 */

public class PuzzleGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    // GUI components for displaying game information
    private JLabel letterLabel; // Displays the letters available for forming words
    private JLabel wordLabel;   // Displays "Words Found"
    private JLabel scoreLabel;  // Displays the current score
    private JTextArea wordArea; // Displays words that the user has correctly guessed
    private JPanel letterPanel; // Panel to hold the letterLabel
    private JPanel wordPanel;   // Panel to hold the wordLabel and scoreLabel

    // Data management
    private UnsortedWordList solutions;    // Stores all possible solutions read from the file
    private SortedWordList userSolutions;  // Stores words guessed correctly by the user
    private String letters;                // Stores the current set of letters to be used for forming words
    private String userGuess;              // Stores the current guess from the user
    private int score;                     // Stores the current score of the game
    private String userReplay;             // Stores the user's choice to replay the game
    private boolean illegalWordsDetected = false; // A check to see if illegal words are detected

    /**
     * Constructor initializes the GUI and starts the game.
     */
    public PuzzleGUI() {
        initializeGUI(); // Set up the graphical user interface
        createMenu(); // Create the menu
        try {
            readPuzzleData("P1input.txt"); // Attempt to load the default puzzle data
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Default file P1input.txt not found, please select a file.", "Error", JOptionPane.ERROR_MESSAGE);
            // Optionally prompt for a file selection here
        }
        gameLoop(); // Start the main game loop
    }

    /**
     * Initializes the main window and its components.
     */
    private void initializeGUI() {
        setTitle("Word Game"); // Set the title of the window
        setLayout(new GridLayout(1, 2)); // Set the layout to a grid with 1 row and 2 columns, dividing the window into two equal parts
        setResizable(false); // Prevent the window from being resizable
        setSize(420, 420); // Set the size of the window to 420x420 pixels
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make the window visible

        letterPanel = new JPanel(new BorderLayout()); // Create a panel for displaying letters with a BorderLayout
        wordPanel = new JPanel(new BorderLayout()); // Create a panel for displaying words and scores with a BorderLayout
        wordArea = new JTextArea(); // Create a text area for displaying found words
        wordArea.setEditable(false); // Make the text area non-editable
        JScrollPane scrollPane = new JScrollPane(wordArea); // Put the text area in a scroll pane to enable scrolling
        wordPanel.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the word panel

        letterLabel = new JLabel(); // Create a label for displaying the puzzle letters
        wordLabel = new JLabel("Words Found"); // Create a label to label the text area where found words will be shown
        scoreLabel = new JLabel("Score is 0"); // Create a label for displaying the initial score

        letterPanel.add(letterLabel, BorderLayout.NORTH); // Add the letter label to the north of the letter panel
        wordPanel.add(wordLabel, BorderLayout.NORTH); // Add the word label to the north of the word panel
        wordPanel.add(scoreLabel, BorderLayout.SOUTH); // Add the score label to the south of the word panel
        add(letterPanel); // Add the letter panel to the JFrame
        add(wordPanel); // Add the word panel to the JFrame
    }

    protected void readPuzzleData(String filename) throws FileNotFoundException {
        InputStream inputStream;

        // Try to open the file from the resources folder first
        inputStream = getClass().getResourceAsStream("/" + filename);
        if (inputStream == null) {
            // If the file is not found in resources, try to open it from the file system
            try {
                inputStream = new FileInputStream(filename);
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("File not found: " + filename);
            }
        }

        Scanner puzzleData = new Scanner(inputStream);
        try {
            letters = puzzleData.nextLine(); // Read the first line for puzzle letters
            solutions = new UnsortedWordList(); // Initialize the list for storing solutions
            while (puzzleData.hasNextLine()) {
                String line = puzzleData.nextLine();
                try {
                    solutions.add(line); // Attempt to add each solution to the unsorted list
                } catch (IllegalWordException e) {
                    System.err.println(e.getMessage()); // Log the error for illegal words
                    illegalWordsDetected = true;
                }
            }
            letterLabel.setText("Puzzle Letters: " + letters); // Update the letter label with puzzle letters
        } finally {
            puzzleData.close(); // Ensure the scanner is closed after use
        }

        // If illegal words were detected, show a message and do not continue to the game loop
        if (illegalWordsDetected) {
            JOptionPane.showMessageDialog(null, "Illegal Words Detected");
            return; // Simply return to stop further execution
        }
    }

    /**
     * Updates the display area where found words are listed.
     */
    private void updateWordArea() {
        StringBuilder words = new StringBuilder(); // Create a StringBuilder to accumulate the words found by the user
        WordNode current = userSolutions.first.next; // Start iterating from the first actual node (skipping the dummy head node)

        while (current != null) { // Loop through all nodes in the linked list
            words.append(current.data).append("\n"); // Append each word and a newline to the StringBuilder
            current = current.next; // Move to the next node in the list
        }

        wordArea.setText(words.toString()); // Set the text of the wordArea to display all the words accumulated in the StringBuilder
    }


    /**
     * Creates the menu bar with options for opening files and quitting the game.
     */
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar(); // Create a menu bar to hold the menu items
        JMenu fileMenu = new JMenu("File"); // Create a "File" menu

        JMenuItem openItem = new JMenuItem("Open"); // Create an "Open" menu item
        openItem.addActionListener(new FileMenuHandler(this)); // Add an action listener that handles "Open" action
        fileMenu.add(openItem); // Add the "Open" item to the "File" menu

        fileMenu.addSeparator(); // Add a visual separator in the menu for better organization

        JMenuItem quitItem = new JMenuItem("Quit"); // Create a "Quit" menu item
        quitItem.addActionListener(e -> System.exit(0)); // Add an action listener that exits the application when "Quit" is selected
        fileMenu.add(quitItem); // Add the "Quit" item to the "File" menu

        menuBar.add(fileMenu); // Add the "File" menu to the menu bar
        setJMenuBar(menuBar); // Set this menu bar as the menu bar for the JFrame
    }


    protected void gameLoop() {
        do {
        	if (illegalWordsDetected == true) {
        		break;
        	}
            score = 0; // Initialize the score to zero at the start of each game
            scoreLabel.setText("Score is 0"); // Display the reset score in the score label
            userSolutions = new SortedWordList(); // Initialize a new list to store user's correct guesses
            wordArea.setText(""); // Clear the text area that displays guessed words

            char firstLetter = letters.charAt(0); // Retrieve the first letter from the set of puzzle letters

            while (true) {
                userGuess = JOptionPane.showInputDialog("Type in a word or type exit to quit"); // Prompt the user to enter a word or exit
                if (userGuess == null || userGuess.equalsIgnoreCase("exit")) {
                    return; // Exit the method if the user chooses to exit
                }
                userGuess = userGuess.toLowerCase(); // Convert the user's guess to lowercase

                while (userGuess.length() < 5) { // Ensure the guess is at least 5 letters long
                    JOptionPane.showMessageDialog(null, "Your guess has to be greater than 5 letters");
                    userGuess = JOptionPane.showInputDialog("Type in a word or type exit to quit");
                    if (userGuess == null || userGuess.equalsIgnoreCase("exit")) {
                        return; // Exit if user chooses to exit during this prompt
                    }
                    userGuess = userGuess.toLowerCase();
                }

                boolean isValidLetter = true; // Flag to track if all characters in the guess are valid
                for (int i = 0; i < userGuess.length(); i++) {
                    char c = userGuess.charAt(i);
                    if (letters.indexOf(c) == -1) { // Check if the guessed letter is not in the puzzle letters
                        JOptionPane.showMessageDialog(null, "Your guess includes a letter that is not in the list");
                        isValidLetter = false;
                        break;
                    }
                }

                boolean containsAllLetters = true; // Flag to check if the guess contains all puzzle letters
                for (int i = 0; i < letters.length(); i++) {
                    char c = letters.charAt(i);
                    if (userGuess.indexOf(c) == -1) { // Check each puzzle letter against the user's guess
                        containsAllLetters = false;
                        break;
                    }
                }

                if (isValidLetter && userGuess.indexOf(firstLetter) != -1) { // If the guess is valid and contains the first letter
                    if (userSolutions.contains(userGuess)) {
                        JOptionPane.showMessageDialog(null, "You already entered this word"); // Inform user if the word was already guessed
                    } else {
                        boolean isSolutionFound = false; // Flag to indicate if the guess matches a solution
                        for (int i = 0; i < solutions.size(); i++) {
                            if (userGuess.equals(solutions.get(i))) {
                                userSolutions.add(userGuess); // Add valid guess to the sorted list
                                updateWordArea(); // Update the display area with the new list of guessed words
                                isSolutionFound = true; // Mark that a solution was found
                                if (containsAllLetters) {
                                    score += 3; // Increase score by 3 if the guess contains all letters
                                } else {
                                    score++; // Otherwise, increase score by 1
                                }
                                scoreLabel.setText("Score: " + score); // Update the score display
                                break;
                            }
                        }
                        if (!isSolutionFound) {
                            JOptionPane.showMessageDialog(null, "Your guess is valid but not on the list"); // Inform user if the guess is valid but not a solution
                        }
                    }
                } else if (isValidLetter) {
                    JOptionPane.showMessageDialog(null, "Your guess must include the first letter of the puzzle\nFirst Letter: " + firstLetter); // Prompt to include the first letter if missing
                }

                if (userSolutions.size() == solutions.size()) { // Check if all solutions have been guessed
                    JOptionPane.showMessageDialog(null, "Congrats! You have found all the words in the game."); // Congratulate the user for finding all words
                    break; // Exit the loop after winning
                }
            }
            
            userReplay = JOptionPane.showInputDialog(null, "Would you like to play again? (Yes/No)"); // Ask user if they want to play again
        } while (userReplay != null && userReplay.equalsIgnoreCase("Yes")); // Continue if the user says "Yes"
    }   
}

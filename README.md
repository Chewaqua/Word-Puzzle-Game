# Word Puzzle Game

## Overview

The Word Puzzle Game is an interactive Java-based game where players guess valid words from a given set of letters. The game dynamically reads puzzle data from an external file and provides a user-friendly graphical interface built using Java Swing. The goal is to find all the valid words listed in the puzzle file while adhering to specific rules.

---

## Features

- **Dynamic Puzzle Loading**: Reads puzzle letters and valid word lists from external text files for customizable gameplay.
- **Interactive Gameplay**:
  - Users guess words using a set of letters provided at the start.
  - Guessed words are validated in real time and displayed on the interface.
- **Scoring System**:
  - Points awarded for valid guesses, with additional points for using all puzzle letters.
- **Input Validation**:
  - Enforces minimum word length (5 letters) and mandatory use of specific letters.
  - Prevents duplicate guesses and flags invalid inputs.
- **Custom Exceptions**: Uses `IllegalWordException` to handle invalid words.
- **Graphical User Interface**: Built with Swing, featuring a split layout for puzzle letters, guessed words, and scoring.
- **Replay Functionality**: Players can replay the game or load a new puzzle.

---

## How It Works

1. **Setup**: The game reads a file (e.g., `P1input.txt`) containing:
   - The first line: A string of letters available for forming words.
   - Subsequent lines: List of valid words for the puzzle.
2. **Gameplay**:
   - Players guess words through a dialog box.
   - Valid guesses are added to the displayed list and update the score.
   - Players win by guessing all valid words in the list.
3. **Endgame**:
   - The game announces when all words are found.
   - Players are prompted to replay or exit.

---

## Rules

- **Word Requirements**:
  - Must be at least 5 letters long.
  - Must use letters from the provided set.
  - Must include the first letter of the puzzle.
- **Invalid Inputs**:
  - Words containing characters not in the set.
  - Words shorter than 5 letters.
  - Duplicate guesses.

---

## File Structure

- `Project3.java`: Main driver program.
- `PuzzleGUI.java`: Handles the graphical interface and game logic.
- `FileMenuHandler.java`: Manages file loading and menu interactions.
- `IllegalWordException.java`: Defines a custom exception for invalid words.
- `SortedWordList.java`: Manages valid guesses in sorted order.
- `UnsortedWordList.java`: Stores solutions read from the puzzle file.
- `WordList.java`: Abstract class for managing word lists.
- `WordNode.java`: Represents individual words as nodes in a linked list.
- `P1input.txt`: Sample input file containing puzzle letters and word solutions.

---

## Requirements

- **Java**: Version 8 or higher
- **IDE**: IntelliJ IDEA, Eclipse, or any Java-supported IDE

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/word-puzzle-game.git
   ```
2. Open the project in your preferred Java IDE.
3. Ensure the input file (`P1input.txt`) is in the correct directory.
4. Run `Project3.java` to start the game.

---

## Future Enhancements

- Add support for multiplayer mode.
- Improve the graphical interface with animations.
- Include a timer for competitive gameplay.
- Add more complex scoring mechanisms for bonus points.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

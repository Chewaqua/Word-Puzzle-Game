/**
 * Project: Word Puzzle Game by Saahil Patel
 * Lab Section:E
 */
public class UnsortedWordList extends WordList {
    // Constructor for UnsortedWordList
    public UnsortedWordList() {
        super(); // Call the constructor of the superclass (WordList)
    }

    // Method to add a word to the end of the list
    public void add(String word) throws IllegalWordException {
    	if (!word.matches("[a-z]+")) {  // Checks if the word contains only lowercase letters
            throw new IllegalWordException("Illegal word detected: " + word);
        }
        append(word); // Call the append method from the superclass to add the word to the end of the list
    }
}


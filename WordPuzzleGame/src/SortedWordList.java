/**
 * Project: Word Puzzle Game by Saahil Patel
 * Lab Section:E
 */
public class SortedWordList extends WordList {
    // Constructor for SortedWordList
    public SortedWordList() {
        super(); // Call the constructor of the superclass (WordList)
    }

    // Method to add a word to the list in sorted order
    public void add(String word) throws IllegalWordException {
    	
    	if (!word.matches("[a-z]+")) {  // Checks if the word contains only lowercase letters
            throw new IllegalWordException("Illegal word detected: " + word);
        }
        WordNode newNode = new WordNode(word); // Create a new node with the word
        WordNode current = first; // Start from the dummy head node

        // If the list is empty or the new word should be inserted at the beginning
        if (current.next == null || current.next.data.compareTo(word) >= 0) {
            newNode.next = first.next; // Point the new node to the first real node
            first.next = newNode; // Insert the new node after the dummy head node
        } else {
            // Find the insertion point (the node after which the new node should be inserted)
            while (current.next != null && current.next.data.compareTo(word) < 0) {
                current = current.next;
            }

            // Insert the new node after the found node
            newNode.next = current.next;
            current.next = newNode;
        }
        length++; // Increment the length of the list
    }
}



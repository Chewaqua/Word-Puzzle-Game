/**
 * Project: Word Puzzle Game by Saahil Patel
 * Lab Section:E
 */
public abstract class WordList {
    protected WordNode first; // Dummy head node
    protected WordNode last;  // Last node in the list
    protected int length;     // Number of nodes in the list (excluding dummy head node)

    // Constructor for WordList
    public WordList() {
        first = new WordNode(null); // Initialize with a dummy head node
        last = first;               // Initially, last is the same as first
        length = 0;                 // Length is 0 since there are no real nodes yet
    }

    // Method to append a word to the end of the list
    public void append(String word) {
        WordNode w = new WordNode(word); // Create a new node with the word
        last.next = w;                   // Add the new node to the end of the list
        last = w;                        // Update the last node to be the new node
        length++;                        // Increment the length of the list
    }

    // Method to check if the list contains a specific word
    public boolean contains(String word) {
        WordNode current = first.next; // Start from the first real node
        while (current != null) {
            if (current.data.equals(word)) {
                return true; // Word found
            }
            current = current.next; // Move to the next node
        }
        return false; // Word not found
    }

    // Method to get the word at a specific index
    public String get(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }
        WordNode current = first.next; // Skip the dummy head node
        for (int i = 0; i < index; i++) {
            current = current.next; // Move to the node at the specified index
        }
        return current.data; // Return the word at the specified index
    }

    // Method to get the size of the list
    public int size() {
        return length; // Return the length of the list
    }
}


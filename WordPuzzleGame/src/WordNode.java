/**
 * Project: Word Puzzle Game by Saahil Patel
 * Lab Section:E
 */
public class WordNode {
    protected String data; // Data stored in this node (a word)
    protected WordNode next; // Reference to the next node in the list

    // Constructor for WordNode
    public WordNode(String word) {
        data = word; // Initialize the data with the given word
        next = null; // By default, the next node is null (this is the end of the list)
    }
}


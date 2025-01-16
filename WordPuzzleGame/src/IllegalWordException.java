public class IllegalWordException extends IllegalArgumentException {
	
    private static final long serialVersionUID = 1L;

    // Constructor for IllegalWordException that takes a message string as an argument.
    public IllegalWordException(String message) {
        super(message); // Calls the constructor of the superclass (IllegalArgumentException) with the provided message.
    }
}

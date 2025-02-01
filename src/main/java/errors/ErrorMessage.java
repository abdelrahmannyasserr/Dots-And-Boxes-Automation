package errors;

import static java.lang.System.err;

public class ErrorMessage {
    private boolean print = true; // Default value is false

    public String genericErrorMessage(String message) {
        return genericErrorMessage(message, print); // Default to 'false' if no print parameter is provided
    }

    public String genericErrorMessage(String message, boolean print) {
        if (print) {
            err.println(message);  // Print the message only if the flag is true
        }
        return message;  // Return the message as usual
    }
}

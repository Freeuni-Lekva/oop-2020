package bad;

public class EmailSender {
    public EmailSender(String mailServerAddress, String username, String password) {
        // Authenticates with mail server and establishes connection
    }

    public void send(String address, String message) {
        System.out.println("Sending email to " + address + ": " + message);
    }
}

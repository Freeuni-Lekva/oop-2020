package ver2;

public class SMSSender implements MessageSender {
    public SMSSender(String gsmProviderAddress, String authToken) {
        // Authenticates with GSM provider and establishes connection
    }

    public void send(String address, String message) {
        System.out.println("Sending SMS to " + address + ": " + message);
    }
}

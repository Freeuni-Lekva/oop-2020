package ver2;

public class AlertDispatcher {
    private EmailSender email;
    private SMSSender sms;

    public AlertDispatcher(EmailSender email, SMSSender sms) {
        this.email = email;
        this.sms = sms;
    }

    public void send(String address, Alert alert) {
        switch (alert.getPriority()) {
            case LOW:
                email.send(address, alert.getMessage());
                break;
            case MEDIUM:
                sms.send(address, alert.getMessage());
                break;
            case HIGH:
                email.send(address, alert.getMessage());
                sms.send(address, alert.getMessage());
                break;
        }
    }
}

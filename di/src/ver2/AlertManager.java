package ver2;

import java.util.Scanner;

public class AlertManager {
    private AlertDispatcher dispatcher;

    public AlertManager(AlertDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void sendAlert(String address, Alert alert) {
        dispatcher.send(address, alert);
    }

    public static void main(String[] args) {
        EmailSender email = new EmailSender(
                "gmail-address",
                "alerts",
                "bar");
        SMSSender sms = new SMSSender(
                "magti-address",
                "kjqhgwerasdf123");
        AlertDispatcher dispatcher = new AlertDispatcher(email, sms);
        AlertManager alerts = new AlertManager(dispatcher);
        Scanner inp = new Scanner(System.in);
        while (true) {
            String priority = inp.next();
            if (priority.equals("exit")) {
                break;
            }
            String address = inp.next();
            String message = inp.next();
            alerts.sendAlert(address, new Alert(message, Alert.Priority.valueOf(priority)));
        }
        System.out.println("Done");
        inp.close();
    }
}

package ver1;

import java.util.Scanner;

public class AlertManager {
    private EmailSender email;
    private SMSSender sms;

    public AlertManager(EmailSender email, SMSSender sms) {
        this.email = email;
        this.sms = sms;
    }


    public void sendAlert(String address, String message, int priority) {
        if (priority == 1) {
            email.send(address, message);
        } else if (priority == 2) {
            sms.send(address, message);
        } else {
            email.send(address, message);
            sms.send(address, message);
        }
    }

    public static void main(String[] args) {
        EmailSender email = new EmailSender(
                "gmail-address",
                "alerts",
                "bar");
        SMSSender sms = new SMSSender(
                "magti-address",
                "kjqhgwerasdf123");
        AlertManager alerts = new AlertManager(email, sms);
        Scanner inp = new Scanner(System.in);
        while (true) {
            int priority = inp.nextInt();
            if (priority <= 0) {
                break;
            }
            String address = inp.next();
            String message = inp.next();
            alerts.sendAlert(address, message, priority);
        }
        System.out.println("Done");
        inp.close();
    }
}

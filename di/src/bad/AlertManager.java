package bad;

import java.util.Scanner;

public class AlertManager {
    private EmailSender email;

    public AlertManager() {
        email = new EmailSender(
                "gmail-address",
                "alerts",
                "bar");
    }


    public void sendAlert(String address, String message) {
        email.send(address, message);
    }

    public static void main(String[] args) {
        AlertManager alerts = new AlertManager();
        Scanner inp = new Scanner(System.in);
        while (true) {
            int p = inp.nextInt();
            if (p <= 0) {
                break;
            }
            String address = inp.next();
            String message = inp.next();
            alerts.sendAlert(address, message);
        }
        System.out.println("Done");
        inp.close();
    }
}

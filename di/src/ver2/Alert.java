package ver2;

public class Alert {
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    private String message;
    private Priority priority;


    public Alert(String message, Priority priority) {
        this.message = message;
        this.priority = priority;
    }

    public String getMessage() {
        return message;
    }

    public Priority getPriority() {
        return priority;
    }
}

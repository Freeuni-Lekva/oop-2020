package ge.edu.freeuni.model;

public class Note {
    private int id;
    private String username;
    private String text;

    public Note(String username, String text) {
        this(-1, username, text);
    }

    public Note(int id, String username, String text) {
        this.id = id;
        this.username = username;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

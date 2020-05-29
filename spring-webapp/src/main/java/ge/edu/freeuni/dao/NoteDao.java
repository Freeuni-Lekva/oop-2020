package ge.edu.freeuni.dao;

import ge.edu.freeuni.model.Note;

import java.util.Collection;

public interface NoteDao {
    public Note get(int id);
    public boolean create(Note note);
    public boolean delete(int id);
    public Collection<Note> getAll(String username);
}

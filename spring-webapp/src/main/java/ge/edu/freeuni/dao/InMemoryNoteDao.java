package ge.edu.freeuni.dao;

import ge.edu.freeuni.model.Note;

import java.util.*;

public class InMemoryNoteDao implements NoteDao {
    private Map<Integer, Note> notes;

    public InMemoryNoteDao() {
        notes = new HashMap<>();
    }

    @Override
    public Note get(int id) {
        return notes.get(id);
    }

    @Override
    public boolean create(Note note) {
        note.setId(notes.size());
        notes.put(note.getId(), note);
        return true;
    }

    @Override
    public boolean delete(int id) {
        if (notes.containsKey(id)) {
            notes.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Collection<Note> getAll(String username) {
        List<Note> ret = new ArrayList<>();
        for (Note note : notes.values()) {
            if (note.getUsername().equals(username)) {
                ret.add(note);
            }
        }
        return ret;
    }
}

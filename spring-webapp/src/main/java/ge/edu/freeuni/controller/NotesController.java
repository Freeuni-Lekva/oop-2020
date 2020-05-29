package ge.edu.freeuni.controller;

import ge.edu.freeuni.dao.InMemoryNoteDao;
import ge.edu.freeuni.dao.NoteDao;
import ge.edu.freeuni.model.Note;
import ge.edu.freeuni.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class NotesController {
    @Autowired
    private NoteDao notes;

//    public NotesController() {
//        this.notes = new InMemoryNoteDao();
//    }

    @GetMapping(value = {"/", "/notes"})
    public ModelAndView home(HttpServletResponse resp, HttpSession ses) throws IOException {
        User user = (User) ses.getAttribute("user");
        ModelAndView ret = new ModelAndView("notes");
        ret.addObject("notes", notes.getAll(user.getUsername()));
        return ret;
    }

    @PostMapping("/notes")
    public void createNote(HttpServletResponse resp,
                           HttpSession ses,
                           @RequestParam String text) throws IOException {
        User user = (User) ses.getAttribute("user");
        if (!notes.create(new Note(user.getUsername(), text))) {
            resp.sendError(500, "Could not create new note");
        } else {
            resp.sendRedirect("/");
        }
    }

    @PostMapping("notes/{id}")
    public void deleteNote(HttpServletResponse resp,
                           HttpSession ses,
                           @PathVariable int id) throws IOException {
        User user = (User) ses.getAttribute("user");
        Note note = notes.get(id);
        if (note != null && note.getUsername().equals(user.getUsername())) {
            notes.delete(id);
        }
        resp.sendRedirect("/");
    }
}

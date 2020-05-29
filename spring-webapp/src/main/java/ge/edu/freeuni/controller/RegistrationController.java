package ge.edu.freeuni.controller;

import ge.edu.freeuni.dao.InMemoryUserDao;
import ge.edu.freeuni.dao.SqlUserDao;
import ge.edu.freeuni.dao.UserDao;
import ge.edu.freeuni.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class RegistrationController {
    @Autowired
    private UserDao users;

//    public RegistrationController() throws SQLException, ClassNotFoundException {
//        users = SqlUserDao.getInstance();
//    }

    @GetMapping("/register")
    public String get() {
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView post(HttpServletResponse resp,
                             @RequestParam String username,
                             @RequestParam String password) throws IOException {
        ModelAndView ret = new ModelAndView("register");
        User user = users.get(username);
        if (user != null) {
            ret.addObject("error", "Username " + username + " is already taken");
            ret.addObject("username", username);
            return ret;
        }
        users.create(new User(username, password));
        resp.sendRedirect("/login");
        return null;
    }
}

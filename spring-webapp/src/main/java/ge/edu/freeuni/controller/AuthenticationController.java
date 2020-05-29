package ge.edu.freeuni.controller;

import ge.edu.freeuni.dao.InMemoryUserDao;
import ge.edu.freeuni.dao.SqlUserDao;
import ge.edu.freeuni.dao.UserDao;
import ge.edu.freeuni.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
class AuthenticationController {
    @Autowired
    private UserDao users;

//    public AuthenticationController() throws SQLException, ClassNotFoundException {
//        users = SqlUserDao.getInstance();
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest req,
                              HttpServletResponse resp,
                              HttpSession ses,
                              @RequestParam String username,
                              @RequestParam String password) throws IOException {
        User user = users.get(username);
        ModelAndView ret = new ModelAndView("login");
        if (user == null) {
            ret.addObject("error", "User not found");
            ret.addObject("username", username);
            return ret;
        }
        if (!user.getPassword().equals(password)) {
            ret.addObject("error", "Incorrect password provided");
            ret.addObject("username", username);
            return ret;
        }
        ses.setAttribute("user", user);
        resp.sendRedirect("/");
        return ret;
    }

    @RequestMapping("/logout")
    public void logout(HttpServletResponse resp, HttpSession ses) throws IOException {
        ses.invalidate();
        resp.sendRedirect("/");
    }
}
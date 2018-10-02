package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/registration")
public class Registration extends HttpServlet {
    private UsersDB usersDB = UsersDB.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String status = req.getParameter("status");
        User user = new User(name, surname, login, password, status);
        if (usersDB.getBase().containsKey(login)){
            resp.getOutputStream().write(("loginBusy").getBytes(StandardCharsets.UTF_8));
        } else {
            usersDB.add(user);
            usersDB.getBase().get(login).setStatus("online");
            resp.getOutputStream().write(("registration is successful").getBytes(StandardCharsets.UTF_8));
        }
    }
}

package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/join")
public class Join extends HttpServlet {
    private UsersDB usersDB = UsersDB.getInstance();
    static {
        UsersDB.getInstance().add(new User("Vasyl", "Kobylchak", "admin", "admin", "offline"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (checkJoin(login, password)) {
            usersDB.getBase().get(login).setStatus("online");
            resp.getOutputStream().write("join".getBytes(StandardCharsets.UTF_8));
        } else {
            resp.getOutputStream().write("registration".getBytes(StandardCharsets.UTF_8));
        }
    }

    private static boolean checkJoin(String login, String password) {
        return UsersDB.getInstance().getBase().containsKey(login) && UsersDB.getInstance().getBase().get(login).getPassword().equals(password);
    }
}

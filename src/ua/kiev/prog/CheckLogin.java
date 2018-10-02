package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/checkLogin")
public class CheckLogin extends HttpServlet {
    private UsersDB usersDB = UsersDB.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if (usersDB.getBase().containsKey(login)) {
            resp.getOutputStream().write("pravda".getBytes(StandardCharsets.UTF_8));
        } else {
            resp.getOutputStream().write("false".getBytes(StandardCharsets.UTF_8));
        }
    }
}

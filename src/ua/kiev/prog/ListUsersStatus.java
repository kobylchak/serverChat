package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/list")
public class ListUsersStatus extends HttpServlet {
    private UsersDB usersDB = UsersDB.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder("ALL USERS AND THEIR STATUS").append(System.lineSeparator());
        usersDB.getBase().forEach((s, user) -> sb.append(s + " - \t" + user.getStatus() + "\n"));
        resp.getOutputStream().write(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}

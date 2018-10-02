package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/addUserRoom")
public class AddUserRoom extends HttpServlet {
    private UsersDB usersDB = UsersDB.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String room = req.getParameter("room");
        usersDB.getChatRooms().get(room).add(login);
        usersDB.getBase().get(login).setStatus(room);
        resp.getOutputStream().write("ok".getBytes(StandardCharsets.UTF_8));
    }
}

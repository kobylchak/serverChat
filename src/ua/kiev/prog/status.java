package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/status")
public class status extends HttpServlet {
    private UsersDB usersDB = UsersDB.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("room");
        String login = req.getParameter("login");
        if (status.equals("exitChat")) {
            usersDB.getBase().get(login).setStatus("offline");
            resp.getOutputStream().write(("offline").getBytes(StandardCharsets.UTF_8));
        } else if (status.equals("room1") || status.equals("room2") || status.equals("room3") || status.equals("room4")) {
            usersDB.getBase().get(login).setStatus("online");
            usersDB.getChatRooms().get(status).remove(login);
            resp.getOutputStream().write(("exit from the " + status).getBytes(StandardCharsets.UTF_8));
        }
    }
}

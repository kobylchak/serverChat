package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/help")
public class Help extends HttpServlet {
    private static Map<String,String> help = new HashMap<>();
    static{
        help.put("help     ", "Show commands, which help you");
        help.put("exit     ", "Exit from the chat");
        help.put("list     ", "Show all users and their status");
        help.put("incognito", "If you want to send message only one user");
        help.put("room     ", "Chat in a separate room");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder("COMMANDS, WHICH HELP YOU: ").append(System.lineSeparator());
        help.forEach((s, s2) -> sb.append(s + "\t - " + s2 + "\n" ));
        resp.getOutputStream().write(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}

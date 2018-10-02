package ua.kiev.prog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/add")
public class AddServlet extends HttpServlet {
    private UsersDB usersDB = UsersDB.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = RequestReader.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        Message msg = Message.fromJSON(bufStr);
        if (msg != null && msg.getTo().equals("All")) {
            Map<String, MessageList> map = usersDB.getMessageList();
            map.forEach((k, messageList) -> messageList.add(msg));
        } else if (msg != null && msg.getTo().startsWith("room")) {
            usersDB.getChatRooms().get(msg.getTo()).stream()
                    .forEach(user -> usersDB.getMesList(user).add(msg));
        } else if (msg != null && usersDB.getBase().containsKey(msg.getTo())) {
            usersDB.getMesList(msg.getTo()).add(msg);
            usersDB.getMesList(msg.getFrom()).add(msg);
        } else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}

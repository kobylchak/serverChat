package ua.kiev.prog;

import java.util.*;

public class UsersDB {
    private static final UsersDB usersDB = new UsersDB();
    private static Map<String, User> base = Collections.synchronizedMap(new HashMap<>());
    private static Map<String, MessageList> messageList = Collections.synchronizedMap(new HashMap<>());
    private static Map<String, List<String>> chatRooms = Collections.synchronizedMap(new HashMap<>());

    private UsersDB() {
    }

    static {
        chatRooms.put("room1", new LinkedList<>());
        chatRooms.put("room2", new LinkedList<>());
        chatRooms.put("room3", new LinkedList<>());
        chatRooms.put("room4", new LinkedList<>());
    }

    public static UsersDB getInstance() {
        return usersDB;
    }

    public Map<String, User> getBase() {
        return base;
    }

    public Map<String, MessageList> getMessageList() {
        return messageList;
    }

    public MessageList getMesList(String login) {
        return messageList.get(login);
    }

    public Map<String, List<String>> getChatRooms() {
        return chatRooms;
    }

    public void add(User user) {
        base.put(user.getLogin(), user);
        messageList.put(user.getLogin(), new MessageList());

    }
}

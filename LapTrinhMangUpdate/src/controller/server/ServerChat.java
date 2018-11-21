package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import models.DAO;
import static models.DAO.getListUSer;
import models.Question;
import models.User;

/**
 *
 * @author hung
 */
public class ServerChat extends Thread {

    ArrayList<User> listUser;

    String username;
    double maxpoint;
    String listUserOnline = null;
    String message = null;
    BufferedReader is = null;
    PrintWriter os = null;
    public Socket clientSocket = null;

    public ServerChat(Socket clientSocket) throws ClassNotFoundException, SQLException {
        this.clientSocket = clientSocket;
        DAO db = new DAO();
        listUser = db.getPlayers();
        try {
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintWriter(clientSocket.getOutputStream());

        } catch (IOException e) {
            System.out.println("Loi doc ghi du lieu tu server -> client!");
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                message = is.readLine();
                System.out.println("tin nhan nhan ddc :" + message);
                switch (message) {
                    case "/login":
                        login();
                        break;
                    case "/getUserOnline":
                        userOnline();
                        break;
                    case "/getListQuestions":
                        getListQuestion();
                        break;
                    case "/testRoom":
                        invitePlay();
                        break;
                    case "/thoat":
                        Server.threads.remove(username);
                        break;

                }
            }
        } catch (IOException e) {
            message = this.getName(); //reused String line for getting thread name
            System.out.println("IO Error/ Client " + message + " terminated abruptly");
        } catch (NullPointerException e) {
            message = this.getName(); //reused String line for getting thread name
            System.out.println("Client " + message + " Closed");
        } finally {
            try {
                System.out.println("Connection Closing..");
                if (is != null) {
                    is.close();
                    System.out.println(" Socket Input Stream Closed");
                }

                if (os != null) {
                    os.close();
                    System.out.println("Socket Out Closed");
                }
                if (clientSocket != null) {
                    clientSocket.close();
                    System.out.println("Socket Closed");
                }
            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }
    }

    public boolean checkUSer(String username, String pass) {
        for (User u : listUser) {
            if (u.getUsername().equals(username) && u.getPassword().equals(pass)) {
                System.out.println("Check ok :" + username);
                return true;
            }
        }
        return false;
    }

    public void sendMessage(String ms) {
        os.println("ms");
        os.flush();
    }

    public String getMessage() throws IOException {
        return is.readLine();
    }

    public void login() throws IOException {
        message = is.readLine();
        System.out.println("Login");
        //kiem tra dang nhap
        String[] ms = message.split("/");
        if (checkUSer(ms[0], ms[1]) && !Server.threads.containsKey(ms[0])) {
            System.out.println("ok");
            Server.threads.put(ms[0], this);
            username = ms[0];
            os.println("accept");
            os.flush();
        } else {
            os.println("invalid");
            os.flush();
        }
    }

    public void userOnline() {
        os.println("/showUserOnline");
        os.flush();
        String listU = "";
        Set<String> usersOnline = Server.threads.keySet();
        for (User u : listUser) {
            if (usersOnline.contains(u.getUsername())) {
                listU += u.getAllInfor() + " @ ";
            }
        }

        //
        os.println(listU);
        os.flush();
//        System.out.println(listU);
    }

    public void invitePlay() throws IOException {
        String user2_name = is.readLine();
        ServerChat chatWithClient2 = Server.threads.get(user2_name);
        BufferedReader is2 = null;
        PrintWriter os2 = null;
        try {
            is2 = new BufferedReader(new InputStreamReader(chatWithClient2.clientSocket.getInputStream()));
            os2 = new PrintWriter(chatWithClient2.clientSocket.getOutputStream());
        } catch (Exception e) {
        }
        os2.println("/inviteTest");
        os2.flush();
        os2.println(this.username);
        os2.flush();
        //xac nhan
        System.out.println("Server xac nhan gui loi moi den :" + chatWithClient2.username);
        String reply = is2.readLine();
        os.println("/replyInvitation");
        os.flush();
        System.out.println("Tra loi client 1");
        if (reply.equals("/yes")) {
            os.println("/replyInviteYes");
            os.flush();
        } else if (reply.equals("/no")) {
            os.println("replyInviteNo");
            os.flush();
        }
        System.out.println("da gui");

    }

    private void getListQuestion() {
        System.out.println("Dang Lay cau hoi");
        String response = "";
        ArrayList<Question> list = new ArrayList<>();
        try {
            DAO db = new DAO();
            list = db.getQuestions();
        } catch (Exception e) {
        }
        for (Question q : list) {
            response += q.toString() + "@";
        }
        System.out.println(response);
        os.println("/responseListQuestions");
        os.flush();
        os.println(response);
        os.flush();
    }

}

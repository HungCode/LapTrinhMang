package controller.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hung
 */
public class Room extends Thread {
    
    ServerChat chatWithClient1, chatWithClient2;
    
    public Room(ServerChat client1, ServerChat client2) {
        this.chatWithClient1 = client1;
        this.chatWithClient2 = client2;
    }
    
    @Override
    public void run() {
        try {
            client2_response();
        } catch (IOException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean client2_response() throws IOException {
        chatWithClient2.sendMessage("/inviteTest");
//        chatWithClient2.sendMessage(chatWithClient1.username);
        chatWithClient2.sendMessage("/showUserOnline");
        //xac nhan
        System.out.println("Xac nhan da gui loi moi den :" + chatWithClient2.username);
        String check = chatWithClient2.getMessage();
        if (check.equals("/yes")) {
            return true;
        } else if (check.equals("/no")) {
            return false;
        }
        return false;
    }
}

package com.mygdx.distributedGameScore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    private final String serverHost = "localhost";
    private final int serverPort = 8080;

    public Client( ) throws IOException {
        s = new Socket(serverHost, serverPort);
        in = new DataInputStream(s.getInputStream());
        out = new DataOutputStream(s.getOutputStream());
    }

    public void closeConnection() throws IOException {
        in.close();
        out.close();
        s.close();
    }

    public boolean sendScore(int score) throws IOException {
        out.flush();
        out.write(score);
        return in.readBoolean();
    }
}

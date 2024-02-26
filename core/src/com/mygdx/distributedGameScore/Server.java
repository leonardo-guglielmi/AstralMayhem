package com.mygdx.distributedGameScore;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;

public class Server {
    // todo: può convenire farlo concorrente?
    private boolean isActive = false;
    private ServerSocket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public Server() throws IOException {
        s = new ServerSocket(8080);
    }

    public void start(){
        synchronized (this) {
            if (!isActive)
                isActive = true;
        }
        System.out.println("Server started @ "+(new Timestamp(System.currentTimeMillis())));
    }

    public void stop(){
        synchronized(this) {
            if (isActive)
                isActive = false;
        }
        System.out.println("Server stopped @ "+(new Timestamp(System.currentTimeMillis())));
    }

    public void listen(){

    }

    public static void main(String[] args){

    }
}

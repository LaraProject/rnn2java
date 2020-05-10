package com.zlalanne;

import java.net.*; 
import java.io.*; 
import java.util.concurrent.TimeUnit;

import com.zlalanne.Message.Command;

public class Server {

    final private int PORT = 9988;
    private Socket socket;
    private OutputStream os;
    private InputStream is;

    public Command makeQuestion(String q) {
        return Command.newBuilder()
                      .setType(Command.CommandType.QUESTION)
                      .setName("Question")
                      .setData(q)
                      .build();
    }

    public Command makeShutdown() {
        return Command.newBuilder()
                      .setType(Command.CommandType.SHUTDOWN)
                      .setName("Shuting down")
                      .setData("Shutdown server.")
                      .build();
    }

    public void send_without_answer(Command cmd) {
        try {
            openSock();
            cmd.writeTo(os);
            closeSock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Command send(Command cmd) {
        try {
            openSock();
            cmd.writeTo(os);
            Command ret = Command.parseFrom(is);
            closeSock();
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            //return Command.parseFrom(is);
            return Command.newBuilder().build();
        }
    }

    public String sendQuestion(String q) {
        return send(makeQuestion(q)).getData();
    }

    public void shutdownServer() {
        send_without_answer(makeShutdown());
        closeSock();
    }

    public void openSock() {
        try {
            socket = new Socket("localhost", PORT);
            os = socket.getOutputStream();
            is = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public void closeSock() {
        try {
            os.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}

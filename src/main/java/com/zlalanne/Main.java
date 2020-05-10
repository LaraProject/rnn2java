package com.zlalanne;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        System.out.println("Asking 'hello'");
		System.out.println("Answer is '" + server.sendQuestion("hello") + "'.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Asking 'good bye'");
		System.out.println("Answer is '" + server.sendQuestion("good bye") + "'.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Shuting down the server.");
		server.shutdownServer();
    }
}

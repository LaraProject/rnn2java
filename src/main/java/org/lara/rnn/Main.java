package org.lara.rnn;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        // Testing
        System.out.println("Switch to personn 1.");
        server.switchPersonn(1);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Asking 'What are you interests'");
		System.out.println("Answer is '" + server.sendQuestion("What are you interests") + "'.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Asking 'What is your number'");
		System.out.println("Answer is '" + server.sendQuestion("What is your number") + "'.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Shuting down the server.");
		server.shutdownServer();
    }
}

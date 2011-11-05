package cz.cvut.keyczar.homework;

import cz.cvut.keyczar.exceptions.KeyczarException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class VerificationServer {

	public static void main(String[] arguments) throws IOException, KeyczarException {

        if (arguments.length<2) {
            System.err.println("Usage:");
            System.err.println("\tVerificationServer <port> <kudoFile>");
            System.exit(-1);
        }

        int socketPort = Integer.parseInt(arguments[0]);
        String kudoFile = arguments[1];
        String keyDir = arguments[2];

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(socketPort);
            System.out.println("Listen on: "+String.valueOf(socketPort));
            Thread t = new Thread(new ConnectionAcceptor(serverSocket, kudoFile, keyDir));
            t.setDaemon(true);
            t.start();
            t.join();
        } catch (IOException e) {
            System.err.println("Could not listen on ports: " + String.valueOf(socketPort) );
            System.exit(-1);
        } catch (InterruptedException e) { }
    }
    static class ConnectionAcceptor implements Runnable {
        private ServerSocket socket;
        private String kudoFile;
        private String keyDir;

        public ConnectionAcceptor(ServerSocket socket, String kudoFile, String keyDir) {
            this.socket = socket;
            this.kudoFile = kudoFile;  
            this.keyDir = keyDir;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Socket clientSocket = this.socket.accept();
                    System.out.println("Connect from: "+clientSocket.toString());
                    Thread t = new Thread(new MessageVerifier(clientSocket, kudoFile, keyDir));
                    t.setDaemon(true);
                    t.start();
                } catch (IOException e) {
                    System.err.println("Accept failed: "+String.valueOf(this.socket.getLocalPort()));
                    System.exit(-1);
                } catch (KeyczarException e) {
                    System.err.println("Accept failed: "+String.valueOf(this.socket.getLocalPort()));
                    System.exit(-1);
                }
            }
        }
    }

}

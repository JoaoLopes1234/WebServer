package io.metropolis.supernans;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;

public class Server {
    File file = new File("resources/carbon.png");
    File file2 = new File("resources/file.html");
    ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    public File getFile() {
        return file2;
    }


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket1 = new ServerSocket(8081);
        Server server = new Server(serverSocket1);

        System.out.println("Server online");
        while (true) {
            try {
                Socket clientSocket = server.serverSocket.accept();

                if (clientSocket != null) {
                    Thread thread = new Thread(new Client(server, clientSocket));
                    thread.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}

//     Socket clientSocket = serverSocket.accept();
//  System.out.println("New client");




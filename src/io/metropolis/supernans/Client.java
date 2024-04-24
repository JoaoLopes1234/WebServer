package io.metropolis.supernans;

import java.io.*;
import java.net.Socket;
public class Client implements Runnable {
    Server server;
    Socket clientSocket;

    public Client(Server server, Socket socket) {
        this.server=server;
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream outputStream = clientSocket.getOutputStream();
            sendTextResponse(server.file2, outputStream);


            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendImageResponse(File image, OutputStream outputStream) throws FileNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(image)) {
            byte[] buffer = new byte[1024];
            int bytesRead = 0;

            String responseHeader = "HTTP/1.0 200 OK\r\n" +
                    "Content-Type: image/png\r\n" +
                    "Content-Length: <file_byte_size>  \r\n\r\n";

            outputStream.write(responseHeader.getBytes());

            while (bytesRead != -1) {
                bytesRead = fileInputStream.read(buffer);
                outputStream.write(buffer);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public void sendTextResponse(File text, OutputStream outputStream) throws FileNotFoundException{
        try{
            FileInputStream fileInputStream = new FileInputStream(text);
            byte[] buffer = new byte[1024];
            int bytesRead = 0;

            String respondeHeader = "HTTP/1.0 200 Document Follows\r\n" +
            "Content-Type: text/html; charset=UTF-8\r\n" +
            "Content-Length: " + text.length() + "\r\n\r\n";

            outputStream.write(respondeHeader.getBytes());

            while (bytesRead != -1){
                bytesRead = fileInputStream.read(buffer);
                outputStream.write(buffer);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

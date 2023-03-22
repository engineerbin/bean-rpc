package com.binwu.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer {
    public static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    public void start(int port) {
        try (ServerSocket server = new ServerSocket(port);) {
            Socket socket;
            logger.info("start listening:{}", port);
            while ((socket = server.accept()) != null) {
                logger.info("client connected!");
                try(ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
                    Message message = (Message) objectInputStream.readObject();
                    logger.info("server receive message:{}",message.getContent());
                    message.setContent("new content");
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                } catch (IOException | ClassNotFoundException e) {
                    logger.error("occur exception:",e);
                }
            }
        } catch (IOException e) {
            logger.error("occur IOException: {}", e);
        }
    }
    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer();
        socketServer.start(1234);
    }
}

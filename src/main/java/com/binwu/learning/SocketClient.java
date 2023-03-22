package com.binwu.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient {
    public static final Logger logger = LoggerFactory.getLogger(SocketClient.class);

    public Object send(Message message, String host, int port){
        try(Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException e){
            logger.error("occur exception:",e);
        }
        return null;
    }

    public static void main(String[] args) {
        SocketClient socketClient = new SocketClient();
        Message message = (Message) socketClient.send(new Message("content from client"),"127.0.0.1",1234);
        logger.info("client receive message:{}",message.getContent());
    }
}

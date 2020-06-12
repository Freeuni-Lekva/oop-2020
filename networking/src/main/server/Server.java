package server;

import dao.ContactDao;
import model.Contact;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    private final ServerSocket serverSocket;
    private final ContactDao contacts;
    private boolean shutdown;
    private Thread main;

    public Server(ServerSocket serverSocket, ContactDao contacts) {
        this.serverSocket = serverSocket;
        this.contacts = contacts;
        this.shutdown = false;
    }

    public void start(int timeout) {
        main = new Thread(() -> {
            try {
                serverSocket.setSoTimeout(timeout);
            } catch (SocketException e) {
                e.printStackTrace();
                return;
            }
            while (!shutdown) {
                Socket socket = null;
                try {
                    System.out.println("Waiting for connection");
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    continue;
                }
                if (socket != null) {
                    System.out.println("New client connection detected");
                    handleClient(socket);
                }
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        main.start();
    }

    private void handleClient(Socket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader inp;
                ObjectOutputStream out;
                try {
                     inp = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     out = new ObjectOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                while (true) {
                    String command = null;
                    try {
                        command = inp.readLine();
                    } catch (IOException e) {
                        break;
                    }
                    try {
                        if (command == null || command.equals("exit")) {
                            break;
                        } else if (command.startsWith("echo ")) {
                            out.writeObject(command.substring(5));
                        } else if (command.startsWith("get ")) {
                            Contact contact = contacts.get(command.substring(4));
                            out.writeObject(contact);
                        }
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Client connection closed");
            }
        }).start();
    }

    public void shutDown() {
        shutdown = true;
        try {
            main.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

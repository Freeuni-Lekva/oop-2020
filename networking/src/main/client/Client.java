package client;

import model.Contact;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public String echo(String echo) throws IOException, ClassNotFoundException {
        ObjectInputStream inp = new ObjectInputStream(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println("echo " + echo);
        out.flush();
        String res = (String) inp.readObject();
        inp.close();
        out.close();
        return res;
    }

    public void exit() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println("exit");
        out.flush();
        out.close();
    }

    public Contact get(String id) throws IOException, ClassNotFoundException {
        ObjectInputStream inp = new ObjectInputStream(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println("get " + id);
        out.flush();
        Contact res = (Contact) inp.readObject();
        inp.close();
        out.close();
        return res;
    }

    public void close() throws IOException {
        socket.close();
        socket = null;
    }
}

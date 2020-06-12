import model.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
    private int port;
    private Server server;

    @BeforeEach
    public void setUp() throws IOException {
        port = 12345;
        ServerSocket serverSocket = new ServerSocket(port);
        server = new Server(serverSocket, new TestContactDao());
        server.start(100);
    }

    @AfterEach
    public void shutDown() {
        server.shutDown();
    }

    @Test
    public void echo() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", port);
        ObjectInputStream inp = new ObjectInputStream(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println("echo foo");
        out.flush();
        assertEquals("foo", inp.readObject());
        inp.close();
        out.close();
        socket.close();
    }

    @Test
    public void exit() throws IOException {
        Socket socket = new Socket("localhost", port);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println("exit");
        out.flush();
        out.close();
        socket.close();
    }

    @Test
    public void get() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", port);
        ObjectInputStream inp = new ObjectInputStream(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println("get 0");
        out.flush();
        assertEquals(
                new Contact("Foo", "Bar", "foo@bar.com", "12345"),
                inp.readObject());
        inp.close();
        out.close();
        socket.close();
    }

    @Test
    public void getNull() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", port);
        ObjectInputStream inp = new ObjectInputStream(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println("get -1");
        out.flush();
        assertNull(inp.readObject());
        inp.close();
        out.close();
        socket.close();
    }
}
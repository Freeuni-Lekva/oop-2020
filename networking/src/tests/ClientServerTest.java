import client.Client;
import model.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClientServerTest {
    private int port;
    private Server server;
    private Client client;

    @BeforeEach
    public void setUp() throws IOException {
        port = 12345;
        ServerSocket serverSocket = new ServerSocket(port);
        server = new Server(serverSocket, new TestContactDao());
        server.start(100);
        client = new Client(new Socket("localhost", port));
    }

    @AfterEach
    public void shutDown() throws IOException {
        server.shutDown();
        client.close();
    }

    @Test
    public void echo() throws IOException, ClassNotFoundException {
        assertEquals("foo", client.echo("foo"));
    }

    @Test
    public void exit() throws IOException {
        client.exit();
    }

    @Test
    public void get() throws IOException, ClassNotFoundException {
        assertEquals(
                new Contact("Foo", "Bar", "foo@bar.com", "12345"),
                client.get("0"));
    }

    @Test
    public void getNull() throws IOException, ClassNotFoundException {
        assertNull(client.get("-1"));
    }
}
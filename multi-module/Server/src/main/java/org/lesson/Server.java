package org.lesson;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    private final HttpServer httpServer;

    private static final int PORT = 8080;

    public Server() throws IOException {
        this.httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/ping", new PingHandler());
        httpServer.createContext("/sorting", new SortingHandler());
    }

    public static void main(String[] args) throws IOException {
        final Server server = new Server();
        server.httpServer.start();

        System.out.println("Hello world!");
    }
}
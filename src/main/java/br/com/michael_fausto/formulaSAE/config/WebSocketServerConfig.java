package br.com.michael_fausto.formulaSAE.config;

import lombok.AllArgsConstructor;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.context.annotation.Bean;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;


@AllArgsConstructor
public class WebSocketServerConfig extends WebSocketServer {


    private final Map<WebSocket, String> clients = new ConcurrentHashMap<>();
    private Logger logger = Logger.getLogger(WebSocketServerConfig.class.getName());
    private boolean running;

    public WebSocketServerConfig(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        clients.put(webSocket, "");
        logger.info("Connection with WebSocketClient " + webSocket.getRemoteSocketAddress().getAddress().getHostName());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        clients.remove(webSocket);
        logger.info("Connection close by WebSocketClient " + webSocket.getRemoteSocketAddress().getAddress().getHostName());
    }

    @Override
    public void onMessage(WebSocket webSocket, String clientMessage) {
        logger.info("Message " + webSocket.getRemoteSocketAddress().getAddress().getHostName() + ":" + clientMessage);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        logger.info("Error in connection" + e.getMessage());
    }

    @Override
    public void onStart() {
        logger.info("WebSocket on in port: " + getPort());
    }

    @Override
    public void start() {
        super.start();
        running = true;
    }

    @Override
    public void stop() throws InterruptedException {
        super.stop();
        running = false;
    }

    public void broadcastMessage(String message) {
        clients.keySet().forEach(ws -> {
            if (ws.isOpen()) ws.send(message);
        });
    }

    public boolean isRunning() {
        return running;
    }
}

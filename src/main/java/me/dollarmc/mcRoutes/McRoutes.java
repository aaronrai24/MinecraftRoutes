package me.dollarmc.mcRoutes;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public final class McRoutes extends JavaPlugin {

    private static final Logger logger = LoggerFactory.getLogger(McRoutes.class);
    private static McRoutes instance;
    private HttpServer httpServer;

    @Override
    public void onEnable() {
        instance = this;
        logger.info("McRoutes has been enabled!");
        startHttpServer();
    }

    @Override
    public void onDisable() {
        logger.info("McRoutes has been disabled!");
        if (httpServer != null) {
            httpServer.stop(0);
        }
    }

    public static McRoutes getInstance() {
        return instance;
    }

    private void startHttpServer() {
        try {
            logger.info("Starting HTTP server on port 8000");
            httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
            httpServer.createContext("/execute", new CommandHandler());
            httpServer.setExecutor(null);
            httpServer.start();
        } catch (IOException e) {
            logger.error("Failed to start HTTP server", e);
            e.printStackTrace();
        }
    }

    public class CommandHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            logger.info("Received request: " + exchange.getRequestURI().toString());

            if ("POST".equals(exchange.getRequestMethod())) {
                byte[] requestBody = exchange.getRequestBody().readAllBytes();
                String command = new String(requestBody);
                logger.info("Received command: " + command);

                Bukkit.getScheduler().runTask(instance, () -> {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, command);
                });

                String response = "Command executed: " + command;
                exchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }
}
package me.dollarmc.mcRoutes.commands;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.dollarmc.mcRoutes.McRoutes;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This class is responsible for handling the command
 * sent to the HTTP server
 *
 * @author Aaron Rai
 */
public class CommandHandler implements HttpHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommandHandler.class);
    private final McRoutes instance = McRoutes.getInstance();

    /**
     * This method handles the command sent to the HTTP server
     *
     * @param exchange the HTTP exchange
     * @throws IOException if an I/O error occurs
     */
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

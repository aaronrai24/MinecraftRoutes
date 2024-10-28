package me.dollarmc.mcRoutes.server;

import com.sun.net.httpserver.HttpServer;
import me.dollarmc.mcRoutes.commands.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * This class is responsible for starting the HTTP server
 * on port 8000
 *
 * @author Aaron Rai
 */
public class HTTPServer {

    private static final Logger logger = LoggerFactory.getLogger(HTTPServer.class);
    private final CommandHandler commandHandler;
    private HttpServer httpServer;

    public HTTPServer(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }


    /**
     * This method starts the HTTP server on port 8000
     */
    public void startHttpServer() {
        try {
            logger.info("Starting HTTP server on port 8000");
            httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
            httpServer.createContext("/execute", commandHandler);
            httpServer.setExecutor(null);
            httpServer.start();
            logger.info("HTTP server started successfully");
        } catch (IOException e) {
            logger.error("Failed to start HTTP server", e);
            e.printStackTrace();
        }
    }

    /**
     * This method stops the HTTP server
     */
    public void killHttpServer() {
        try {
            logger.info("Stopping HTTP server");
            httpServer.stop(0);
            logger.info("HTTP server stopped successfully");
        } catch (Exception e) {
            logger.error("Failed to stop HTTP server", e);
            e.printStackTrace();
        }
    }

}

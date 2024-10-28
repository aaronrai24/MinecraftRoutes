package me.dollarmc.mcRoutes;


import me.dollarmc.mcRoutes.commands.CommandHandler;
import me.dollarmc.mcRoutes.server.HTTPServer;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is the main class of the plugin
 * and is responsible for enabling and disabling the plugin
 * and starting the HTTP server
 *
 * @author Aaron Rai
 */
public final class McRoutes extends JavaPlugin {

    private static final Logger logger = LoggerFactory.getLogger(McRoutes.class);
    private static McRoutes instance;
    private HTTPServer httpServer;

     /*
        * This method is called when the plugin is enabled
     */
    @Override
    public void onEnable() {
        logger.info("McRoutes has been enabled!");
        instance = this;
        CommandHandler commandHandler = new CommandHandler();
        httpServer = new HTTPServer(commandHandler);
        httpServer.startHttpServer();
    }


    /*
        * This method is called when the plugin is disabled
     */
    @Override
    public void onDisable() {
        logger.info("McRoutes has been disabled!");
        httpServer.killHttpServer();
    }

    /*
        * This method returns the instance of the plugin
        *
        * @return the instance of the plugin
     */
    public static McRoutes getInstance() {
        return instance;
    }

}
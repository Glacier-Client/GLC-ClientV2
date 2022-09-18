package net.glacierclient.util.misc;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GLCLogger {
    private static final Logger LOGGER = LogManager.getLogger();
    public static void info(String message)
    {
        LOGGER.info("[GLC] " + message);
    }

    public static void error(String message)
    {
        LOGGER.error("[GLC - ERROR] " + message);
    }

    public static void warn(String message)
    {
        LOGGER.warn("[GLC - WARNING] " + message);
    }

}

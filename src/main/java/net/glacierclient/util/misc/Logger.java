package net.glacierclient.util.misc;

public class Logger {
    public static void info(String message) {
        System.out.println("[GLC] " + message);
    }

    public static void error(String message) {
        System.out.println("[GLC - ERROR] " + message);
    }

}

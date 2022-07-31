package net.glacierclient.util.security;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Identification {
    public static String getHWID() {
        String hwid = System.getenv("COMPUTERNAME")  + " | " + System.getProperty("user.name") + " | " + System.getProperty("os.name") + " | " + System.getenv("PROCESSOR_IDENTIFIER") + " | " + System.getenv("PROCESSOR_LEVEL");
    return hwid;
    }
    public static String getIP() {
        try {
            URL ip = new URL("http://checkip.amazonaws.com");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ip.openStream()));
            return bufferedReader.readLine();
        } catch (Exception e) {
            return "127.0.0.1";
        }
    }
}

package net.glacierclient.util.http;
import net.glacierclient.util.misc.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;

public class WS {
    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;

    public static void connect()
    {
        try
        {
            Logger.info("IP of Socket Server: " + InetAddress.getByName("api.glacierclient.net").getHostAddress());
            socket = new Socket(InetAddress.getByName("api.glacierclient.net").getHostAddress(), 8808);
            Logger.info("Connected to WS");
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(System.in);
            dataOutputStream.writeUTF("connect");
            dataOutputStream.flush();
        } catch (Exception e)
        {
            Logger.error(e.toString());
        }
    }
    public static void disconnect()
    {
        try{
            dataOutputStream.close();
            dataInputStream.close();
            socket.close();
        } catch (Exception e)
        {
            Logger.error(e.toString());
        }

    }
}

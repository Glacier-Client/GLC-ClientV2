package net.glacierclient.mod.impl.util;


import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.glacierclient.util.misc.GLCLogger;

public class DiscordRP {

    private boolean running = true;
    private long created = 0;
    public DiscordUser discordUser;
    public String discordUserName;
    public String discordUserID;

    public  void start()
    {
        this.created = System.currentTimeMillis();
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
            @Override
            public void apply(DiscordUser user) {
                discordUser = user;
                discordUserName = user.username + "#" + user.discriminator;
                discordUserID = user.userId;
                GLCLogger.info("Welcome " + user.username + "#" + user.discriminator);
                update("");
            }
        }).build();
        DiscordRPC.discordInitialize("944777934915399691", handlers, true);
        new Thread("Discord RPC Callback")
        {
            @Override
            public void run() {
                while (running) {
                    DiscordRPC.discordRunCallbacks();
                }
            }
        }.start();
    }
    public void shutdown()
    {
        running = false;
        DiscordRPC.discordShutdown();
    }
    public void update(String secondLine)
    {
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);
        b.setBigImage("large", "https://www.glacierclient.net/");
        b.setDetails("Playing Minecraft 1.8.9");
        b.setStartTimestamps(created);

        DiscordRPC.discordUpdatePresence(b.build());
    }


}

package net.glacierclient;

import net.glacierclient.event.EventManager;
import net.glacierclient.event.EventTarget;
import net.glacierclient.event.impl.ClientTick;
import net.glacierclient.util.http.API;
import net.glacierclient.mod.impl.util.DiscordRP;
import net.glacierclient.mod.ui.BanScreen;
import net.glacierclient.mod.ui.SplashProgress;
import net.glacierclient.mod.ui.clientsettings.ClientSettings;
import net.glacierclient.mod.ui.clientsettings.hudposconfig.HUDPosConfig;
import net.glacierclient.mod.management.HudManager;
import net.glacierclient.mod.management.ModManager;
import net.glacierclient.util.misc.DiscordWebhook;
import net.glacierclient.util.misc.LocationUtil;
import net.glacierclient.util.misc.GLCLogger;
import net.glacierclient.util.misc.SessionChanger;
import net.glacierclient.util.security.Check;
import net.glacierclient.util.security.Identification;
import net.minecraft.client.Minecraft;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;


public class GlacierClient {
    public String NAME = "Glacier Client", VERSION = "1.0.3",VERSIONLong = VERSION + "/augustine", AUTHOR = "SpyMiner", NAMEVER = NAME + " " + VERSIONLong, WINDOWTITLE = "Glacier Client (" + VERSION + ") - 1.8.9",  playerUUID, playerName, banReason;
    public boolean isBanned;
    public static GlacierClient INSTANCE = new GlacierClient();
    public Minecraft mc = Minecraft.getMinecraft();
    public DiscordRP discordRP = new DiscordRP();
    public EventManager eventManager;
    public ModManager modManager;
    public HudManager hudManager;
    public JSONObject userObj;
    private JSONObject banObj;

    public void startup() {
        SplashProgress.setProggress(6, "Client - Discord RP");
        discordRP.start();
        SplashProgress.setProggress(7, "Client - Loading Mods");
        eventManager = new EventManager();
        modManager = new ModManager();
        hudManager = new HudManager();
        GLCLogger.info("Starting " + NAMEVER + " by " + AUTHOR);
        if(Check.isVPN())
        {
            GLCLogger.error("Your using a VPN");

            mc.shutdown();
        }

        SessionChanger.getInstance().setUserOffline("SpyMiner");

        //WS.connect();

        userObj = new JSONObject(API.get("user/playerUUID/" + mc.getSession().getUsername()));
        playerUUID = userObj.getString("UUID");
        playerName = userObj.getString("Player");


        banObj = new JSONObject(API.get("user/isbanned/" + playerUUID));

        if(banObj.getInt("isBanned") == 1)
        {
            isBanned = true;
            banReason = banObj.getString("banReason");
            try {
                DiscordWebhook webhook = new DiscordWebhook(LocationUtil.clientLaunchURL);
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle("Client Launched (" + VERSION + ") -- Banned User")
                        .setColor(Color.RED)
                        .setThumbnail("https://api.glacierclient.net/assets/minecraft/renders/face/" + playerUUID)
                        .addField("Username", playerName, true)
                        .addField("UUID", playerUUID, true)
                        .addField("IP", Identification.getIP(), false)
                        .addField("Discord User", discordRP.discordUserName, false));
                webhook.execute();
            }
            catch (Exception e)
            {
                GLCLogger.error(e.toString());
            }
        }
        else {
            try {
                DiscordWebhook webhook = new DiscordWebhook(LocationUtil.clientLaunchURL);
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle("Client Launched (" + VERSION + ")")
                        .setColor(Color.CYAN)
                        .setThumbnail("https://api.glacierclient.net/assets/minecraft/renders/face/" + playerUUID)
                        .addField("Username", playerName, true)
                        .addField("UUID", playerUUID, true)
                        .addField("IP", Identification.getIP(), false)
                        .addField("HWID", Identification.getHWID(), false)
                        .addField("Discord User", discordRP.discordUserName, false));
                webhook.execute();
            }
            catch (Exception e)
            {
                GLCLogger.error(e.toString());
            }
        }

        eventManager.register(this);
    }


    public void shutdown(){
        GLCLogger.info("Stopping " + NAMEVER + " by " + AUTHOR);
        discordRP.shutdown();
        //WS.disconnect();
        //System.out.println(API.get("client/logout/" + playerUUID));

        hudManager.saveAllMods();


        eventManager.unregister(this);
    }

    @EventTarget
    public void onTick(ClientTick event) throws IOException {
        if(mc.gameSettings.HUD_CONFIG.isPressed())
        {
            mc.displayGuiScreen(new HUDPosConfig());
        }
        if(mc.gameSettings.CLIENT_SETTINGS.isPressed())
        {
            mc.displayGuiScreen(new ClientSettings());
        }
        if(isBanned)
        {
            mc.displayGuiScreen(new BanScreen(banReason));
        }

    }
}

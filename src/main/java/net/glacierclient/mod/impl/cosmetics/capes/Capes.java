package net.glacierclient.mod.impl.cosmetics.capes;

import net.glacierclient.util.http.API;
import net.glacierclient.mod.management.ModNoGUI;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import org.json.JSONObject;

import java.util.HashMap;


public class Capes extends ModNoGUI {
    static HashMap<String, String> playerList = new HashMap<String, String>();
    static String apiNone = API.get("client/getNone");

    public Capes() {
        super("Capes", "A mod that adds custom capes");
        playerList.clear();
    }

    public static boolean capeEquiped(AbstractClientPlayer entitylivingbaseIn) {
            if (playerList.containsKey(entitylivingbaseIn.getName())) {
                if(!(playerList.get(entitylivingbaseIn.getName()).equals(apiNone)))
                {
                    return true;
                } else return false;
            } else {
                checkUser(entitylivingbaseIn.getName());
                return false;
            }
    }

    public static ResourceLocation getCape(AbstractClientPlayer entitylivingbaseIn) {
            return new ResourceLocation("glacier/capes/" + playerList.get(entitylivingbaseIn.getName()));
    }

    @Override
    public void onEnable() {
        super.onEnable();
        System.out.println("Loading Capes");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        System.out.println("Unloading Capes");
    }

    private static void checkUser(String playerName) {
        if (!playerList.containsKey(playerName)) {
            JSONObject playerObj = new JSONObject(API.get("user/playerUUID/" + playerName).replaceAll("\"", ""));
            String playerUuid = playerObj.getString("UUID");
            if(!playerUuid.equals(apiNone)) {
                playerList.put(playerName, API.get("user/assets/equipedCape/txt/" + playerUuid).replaceAll("\"", ""));
            } else {
                playerList.put(playerName, apiNone);
            }
        }
    }
}

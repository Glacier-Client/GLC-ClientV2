package net.glacierclient.mod.impl.gui;

import net.glacierclient.mod.management.HudMod;

public class PingDisplay extends HudMod {
    public PingDisplay()
    {
        super("Ping Display", 5, 125, false);
    }

    public PingDisplay(boolean forcenew)
    {
        super("Ping Display", 5, 125, forcenew);
    }
    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }
    @Override
    public int getWidth() {
//        if(mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()) != null) {
//            return fr.getStringWidth(mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime() + (" ms"));
//        } else {
            return fr.getStringWidth("1 ms");
//        }
    }
    @Override
    public void draw() {
        if(mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()) != null) {
            fr.drawString(mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime() + (" ms"), this.getX() + 1, this.getY() + 1, -1);
        } else {
            fr.drawString("1 ms", this.getX() + 1, this.getY() + 1, -1);
        }
        super.draw();
    }

    @Override
    public void renderDummy(int mouseX, int mouseY) {
        if(mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()) != null) {
            fr.drawString(mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime() + (" ms"), this.getX() + 1, this.getY() + 1, -1);
        } else {
            fr.drawString("1 ms", this.getX() + 1, this.getY() + 1, -1);
        }
        super.renderDummy(mouseX, mouseY);
    }

}

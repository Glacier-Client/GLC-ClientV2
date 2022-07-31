package net.glacierclient.mod.impl.gui;

import net.glacierclient.mod.management.HudMod;
import net.minecraft.entity.EntityLivingBase;

public class ReachDisplay extends HudMod {

    EntityLivingBase hit = null;
    double reach;

    public ReachDisplay()
    {
        super("Reach Display", 5, 115, false);
    }
    public ReachDisplay(boolean forceNew)
    {
        super("Reach Display", 5, 115, forceNew);
    }

    @Override
    public void renderDummy(int mouseX, int mouseY) {
        fr.drawStringWithShadow("3.00", getX(), getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }

    @Override
    public void draw() {
        hit = (EntityLivingBase) mc.pointedEntity;
        if (hit != null) {
            if (hit.hurtTime > 0) {
                reach = mc.thePlayer.getDistanceToEntity(hit) - 1;
            }
        }
        int n = 3;
        fr.drawStringWithShadow(Double.parseDouble(("" + reach).substring(0, n)) + "", getX(), getY(), -1);
        super.draw();
    }

    @Override
    public int getWidth() {
        return (int) fr.getStringWidth("3.00");
    }

    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT;
    }
}

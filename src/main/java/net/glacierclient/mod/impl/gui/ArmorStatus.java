package net.glacierclient.mod.impl.gui;

import net.glacierclient.mod.management.HudMod;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ArmorStatus extends HudMod {
    public ArmorStatus()
    {
        super("Armor Status", 5, 120, false);
    }
    public ArmorStatus(boolean forcenew)
    {
        super("Armor Status", 5, 120, forcenew);
    }
    @Override
    public void draw() {
        mc.getRenderItem().renderItemAndEffectIntoGUI(mc.thePlayer.getCurrentArmor(3), getX(), getY() + 2);
        mc.getRenderItem().renderItemAndEffectIntoGUI(mc.thePlayer.getCurrentArmor(2), getX() + 20, getY() + 2);
        mc.getRenderItem().renderItemAndEffectIntoGUI(mc.thePlayer.getCurrentArmor(1), getX() + 40, getY() + 2);
        mc.getRenderItem().renderItemAndEffectIntoGUI(mc.thePlayer.getCurrentArmor(0), getX() + 60, getY() + 2);
    super.draw();
    }
    @Override
    public void renderDummy(int mouseX, int mouseY) {
        mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.diamond_helmet), getX(), getY() + 2);
        mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.diamond_chestplate), getX() + 20, getY() + 2);
        mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.diamond_leggings), getX() + 40, getY() + 2);
        mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.diamond_boots), getX() + 60, getY() + 2);
        super.renderDummy(mouseX, mouseY);
    }
}

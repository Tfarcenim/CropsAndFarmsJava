package tfar.cropsandfarmsjava.client.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.world.AnimalHeaterMenu;

public class AnimalHeaterScreen extends AbstractContainerScreen<AnimalHeaterMenu> {

    public static final ResourceLocation BACKGROUND = CropsAndFarms.id("textures/gui/animal_heater.png");

    static final ResourceLocation HEAT_SPRITE = CropsAndFarms.id("heat");
    static final ResourceLocation POWER_SPRITE = CropsAndFarms.id("power");

    public AnimalHeaterScreen(AnimalHeaterMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    static final int POWER_BAR_HEIGHT = 35;

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(BACKGROUND,leftPos,topPos,0,0,imageWidth,imageHeight);

        float f = (float) menu.getDisplayEnergy()/10;

        guiGraphics.blitSprite(HEAT_SPRITE, leftPos+82, topPos+36, 13, 13);

        guiGraphics.blitSprite(POWER_SPRITE, leftPos+105, topPos+24, 7, POWER_BAR_HEIGHT);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderLabels(guiGraphics, mouseX, mouseY);
    }
}

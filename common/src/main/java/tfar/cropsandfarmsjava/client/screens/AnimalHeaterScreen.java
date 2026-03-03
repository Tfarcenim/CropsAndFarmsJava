package tfar.cropsandfarmsjava.client.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.world.AnimalHeaterMenu;
import tfar.cropsandfarmsjava.world.block.entity.AnimalHeaterBlockEntity;

public class AnimalHeaterScreen extends AbstractContainerScreen<AnimalHeaterMenu> {

    public static final ResourceLocation BACKGROUND = CropsAndFarms.id("textures/gui/animal_heater.png");

    static final ResourceLocation HEAT_SPRITE = CropsAndFarms.id("heat");
    static final ResourceLocation POWER_SPRITE = CropsAndFarms.id("power");

    EnergyBarWidget<AnimalHeaterBlockEntity,AnimalHeaterMenu> energyBarWidget;

    public AnimalHeaterScreen(AnimalHeaterMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();
        energyBarWidget = new EnergyBarWidget<>(leftPos+105, topPos+24, 7, POWER_BAR_HEIGHT,Component.empty(),this.menu);
        addRenderableWidget(energyBarWidget);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        energyBarWidget.updateTooltip();
    }

    static final int POWER_BAR_HEIGHT = 35;

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(BACKGROUND,leftPos,topPos,0,0,imageWidth,imageHeight);


        guiGraphics.blitSprite(HEAT_SPRITE, leftPos+82, topPos+36, 13, 13);

    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderLabels(guiGraphics, mouseX, mouseY);
        guiGraphics.drawString(this.font, AnimalHeaterBlockEntity.UPGRADES, 160, this.titleLabelY, 0x404040, false);
    }
}

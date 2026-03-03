package tfar.cropsandfarmsjava.client.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import tfar.cropsandfarmsjava.world.BlockEntityMenu;
import tfar.cropsandfarmsjava.world.block.entity.EnergyBlockEntity;

public class EnergyBarWidget<B extends BlockEntity & EnergyBlockEntity, E extends BlockEntityMenu<B>> extends AbstractWidget {
    private final E menu;

    public EnergyBarWidget(int x, int y, int width, int height, Component message, E blockEntityMenu) {
        super(x, y, width, height, message);
        this.menu = blockEntityMenu;
    }


    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        float f = (float) getDisplayEnergy() / menu.blockEntity().getCapacity() * AnimalHeaterScreen.POWER_BAR_HEIGHT;
        guiGraphics.blitSprite(AnimalHeaterScreen.POWER_SPRITE, getX(), getY() + (int)Math.ceil(AnimalHeaterScreen.POWER_BAR_HEIGHT - f), width, (int) f);

    }

    long getDisplayEnergy() {
        ContainerData data = menu.data();
        long part0 = data.get(0);
        long part1 = data.get(1);
        long part2 = data.get(2);
        long part3 = data.get(3);
        return part0 * (1L << 48) + part1 * (1L << 32) + part2 * (1L << 16) + part3;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }

    public void updateTooltip() {
        setTooltip(Tooltip.create(Component.literal(getDisplayEnergy() + "/" + menu.blockEntity().getCapacity())));
    }
}

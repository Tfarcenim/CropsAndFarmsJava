package tfar.cropsandfarmsjava.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import tfar.cropsandfarmsjava.client.renderer.FancyCowRenderer;
import tfar.cropsandfarmsjava.client.screens.AnimalHeaterScreen;
import tfar.cropsandfarmsjava.world.CropsAndFarmsEntityTypes;
import tfar.cropsandfarmsjava.world.CAFMenuTypes;

public class CropsAndFarmsClient {


    public static void renderers() {
        EntityRenderers.register(CropsAndFarmsEntityTypes.FANCY_COW, FancyCowRenderer::new);
    }

    public static void registerMenus() {
        MenuScreens.register(CAFMenuTypes.ANIMAL_HEATER, AnimalHeaterScreen::new);
    }
}

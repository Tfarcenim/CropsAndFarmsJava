package tfar.cropsandfarmsjava.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import tfar.cropsandfarmsjava.client.screens.AnimalHeaterScreen;
import tfar.cropsandfarmsjava.world.AnimalHeaterMenu;
import tfar.cropsandfarmsjava.world.CropsAndFarmsEntityTypes;
import tfar.cropsandfarmsjava.world.CropsAndFarmsMenuTypes;

public class CropsAndFarmsClient {
    public static void renderers() {
        EntityRenderers.register(CropsAndFarmsEntityTypes.HOLSTEIN_COW, HolsteinCowRenderer::new);
    }

    public static void registerMenus() {
        MenuScreens.register(CropsAndFarmsMenuTypes.ANIMAL_HEATER, AnimalHeaterScreen::new);
    }
}

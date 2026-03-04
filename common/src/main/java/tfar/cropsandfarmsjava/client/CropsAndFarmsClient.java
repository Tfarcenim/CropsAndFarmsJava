package tfar.cropsandfarmsjava.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.client.screens.AnimalHeaterScreen;
import tfar.cropsandfarmsjava.world.CropsAndFarmsEntityTypes;
import tfar.cropsandfarmsjava.world.CAFMenuTypes;
import tfar.cropsandfarmsjava.world.entity.FancyCowEntity;

public class CropsAndFarmsClient {
    public static final DefaultedEntityGeoModel<FancyCowEntity> MALE =new DefaultedEntityGeoModel<>(CropsAndFarms.id("male_holstein_cow"));
    public static final DefaultedEntityGeoModel<FancyCowEntity> FEMALE =new DefaultedEntityGeoModel<>(CropsAndFarms.id("female_holstein_cow"));

    public static void renderers() {
        EntityRenderers.register(CropsAndFarmsEntityTypes.HOLSTEIN_COW, (EntityRendererProvider.Context renderManager) ->
                new HolsteinCowRenderer(renderManager,MALE,FEMALE));
    }

    public static void registerMenus() {
        MenuScreens.register(CAFMenuTypes.ANIMAL_HEATER, AnimalHeaterScreen::new);
    }
}

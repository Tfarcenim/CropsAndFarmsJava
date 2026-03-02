package tfar.cropsandfarmsjava.world;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import tfar.cropsandfarmsjava.CropsAndFarms;

public class CropsAndFarmsMenuTypes {
    public static final MenuType<AnimalHeaterMenu> ANIMAL_HEATER = register("animal_heater",AnimalHeaterMenu::new);

    private static <T extends AbstractContainerMenu> MenuType<T> register(String key, MenuType.MenuSupplier<T> factory) {
        return Registry.register(BuiltInRegistries.MENU, CropsAndFarms.id(key), new MenuType<>(factory, FeatureFlags.VANILLA_SET));
    }

    public static void init() {

    }
}

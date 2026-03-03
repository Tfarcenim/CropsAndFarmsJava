package tfar.cropsandfarmsjava.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.platform.Services;

public class CAFMenuTypes {
    public static final MenuType<AnimalHeaterMenu> ANIMAL_HEATER = registerExtended("animal_heater", AnimalHeaterMenu::new, BlockPos.STREAM_CODEC);

    private static <T extends AbstractContainerMenu> MenuType<T> register(String key, MenuType.MenuSupplier<T> factory) {
        return Registry.register(BuiltInRegistries.MENU, CropsAndFarms.id(key), new MenuType<>(factory, FeatureFlags.VANILLA_SET));
    }

    private static <T extends AbstractContainerMenu,D> MenuType<T> registerExtended(String key, ExtendedMenuSupplier<T,D> factory,
                                                                                    StreamCodec<? super RegistryFriendlyByteBuf,D> streamCodec) {
        return Registry.register(BuiltInRegistries.MENU, CropsAndFarms.id(key), Services.PLATFORM.createExtended(factory,streamCodec));
    }

    public interface ExtendedMenuSupplier<T extends AbstractContainerMenu,D> {
        T createExtended(int containerId, Inventory playerInventory, D data);
    }

    public static void init() {

    }
}

package tfar.cropsandfarmsjava.platform;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import tfar.cropsandfarmsjava.platform.services.IPlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import tfar.cropsandfarmsjava.world.CAFMenuTypes;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public <T extends AbstractContainerMenu, D> MenuType<T> createExtended(CAFMenuTypes.ExtendedMenuSupplier<T, D> extendedMenu,
                                                                           StreamCodec<? super RegistryFriendlyByteBuf, D> streamCodec) {
        return IMenuTypeExtension.create((windowId, inv, buf) -> {
            D d = streamCodec.decode(buf);
            return extendedMenu.createExtended(windowId,inv,d);
        });
    }

    @Override
    public <D> void openExtendedMenu(ServerPlayer player, MenuProvider menuProvider, D data, StreamCodec<? super RegistryFriendlyByteBuf, D> streamCodec) {
        player.openMenu(menuProvider,buf -> streamCodec.encode(buf,data));
    }
}
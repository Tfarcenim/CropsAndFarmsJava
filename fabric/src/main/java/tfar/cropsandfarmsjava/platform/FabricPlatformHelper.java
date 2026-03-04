package tfar.cropsandfarmsjava.platform;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;
import tfar.cropsandfarmsjava.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import tfar.cropsandfarmsjava.world.CAFMenuTypes;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public <T extends AbstractContainerMenu, D> MenuType<T> createExtended(CAFMenuTypes.ExtendedMenuSupplier<T, D> extendedMenu, StreamCodec<? super RegistryFriendlyByteBuf, D> streamCodec) {
        return new ExtendedScreenHandlerType<>(extendedMenu::createExtended,streamCodec);
    }

    /**
     * @see net.fabricmc.fabric.mixin.screenhandler.ServerPlayerEntityMixin
     * @param player
     * @param menuProvider
     * @param data
     * @param streamCodec
     * @param <D>
     */
    @Override
    public <D> void openExtendedMenu(ServerPlayer player, MenuProvider menuProvider, D data, StreamCodec<? super RegistryFriendlyByteBuf, D> streamCodec) {
        ExtendedScreenHandlerFactory<D> extendedScreenHandlerFactory = new ExtendedScreenHandlerFactory<>() {
            @Override
            public D getScreenOpeningData(ServerPlayer player) {
                return data;
            }

            @Override
            public Component getDisplayName() {
                return menuProvider.getDisplayName();
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                return menuProvider.createMenu(i, inventory, player);
            }
        };
        player.openMenu(extendedScreenHandlerFactory);
    }

    @Override
    public <T> void registerDataSerializer(EntityDataSerializer<T> serializer) {
        EntityDataSerializers.registerSerializer(serializer);
    }
}

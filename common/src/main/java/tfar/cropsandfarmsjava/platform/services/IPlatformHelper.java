package tfar.cropsandfarmsjava.platform.services;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import tfar.cropsandfarmsjava.world.CAFMenuTypes;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    <T extends AbstractContainerMenu,D> MenuType<T> createExtended(CAFMenuTypes.ExtendedMenuSupplier<T,D> extendedMenu,
                                                                   StreamCodec<? super RegistryFriendlyByteBuf,D> streamCodec);

    <D> void openExtendedMenu(ServerPlayer player, MenuProvider menuProvider, D data, StreamCodec<? super RegistryFriendlyByteBuf, D> streamCodec);

    default void openExtendedMenu(ServerPlayer player, MenuProvider menuProvider, BlockPos pos) {
        openExtendedMenu(player,menuProvider,pos,BlockPos.STREAM_CODEC);
    }

    <T> void registerDataSerializer(EntityDataSerializer<T> serializer);

}
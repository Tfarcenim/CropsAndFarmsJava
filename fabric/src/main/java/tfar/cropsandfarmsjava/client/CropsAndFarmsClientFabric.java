package tfar.cropsandfarmsjava.client;

import net.fabricmc.api.ClientModInitializer;

public class CropsAndFarmsClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CropsAndFarmsClient.registerMenus();
        CropsAndFarmsClient.renderers();
    }
}

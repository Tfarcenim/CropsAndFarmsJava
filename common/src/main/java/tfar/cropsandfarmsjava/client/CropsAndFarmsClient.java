package tfar.cropsandfarmsjava.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import tfar.cropsandfarmsjava.world.CropsAndFarmsEntityTypes;

public class CropsAndFarmsClient {
    public static void renderers() {
        EntityRenderers.register(CropsAndFarmsEntityTypes.HOLSTEIN_COW, HolsteinCowRenderer::new);
    }
}

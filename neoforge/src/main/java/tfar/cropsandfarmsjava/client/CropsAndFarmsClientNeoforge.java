package tfar.cropsandfarmsjava.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import tfar.cropsandfarmsjava.CropsAndFarms;

@Mod(value = CropsAndFarms.MOD_ID, dist = Dist.CLIENT)
public class CropsAndFarmsClientNeoforge {


    public CropsAndFarmsClientNeoforge(IEventBus bus) {
        bus.addListener(this::renderers);
        bus.addListener(this::registerMenus);
    }

    void renderers(final EntityRenderersEvent.RegisterRenderers event) {
        CropsAndFarmsClient.renderers();
    }

    void registerMenus(RegisterMenuScreensEvent event) {
        CropsAndFarmsClient.registerMenus();
    }
}

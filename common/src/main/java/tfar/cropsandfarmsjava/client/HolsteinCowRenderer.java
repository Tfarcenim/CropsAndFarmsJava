package tfar.cropsandfarmsjava.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.world.entity.HolsteinCowEntity;

public class HolsteinCowRenderer extends GeoEntityRenderer<HolsteinCowEntity> {

   /* private static final Map<HolsteinCowEntity.Variant, ResourceLocation> TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), (map) -> {
        for(HolsteinCowEntity.Variant variant : HolsteinCowEntity.Variant.values()) {
            map.put(variant, CropsAndFarmsJava.id(String.format(Locale.ROOT, "textures/entity/holstein_cow/%s.png", variant.getName())));
        }
    });*/


    public HolsteinCowRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(CropsAndFarms.id("holstein_cow")));
    }
}
